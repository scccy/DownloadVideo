package com.scccy.downloader.untils;

import com.scccy.downloader.pojo.settings.AppSettings;
import jakarta.annotation.Resource;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.scheduling.annotation.Async;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;

//@Service
public class BaseDownloader {
    @Resource
    AppSettings appSettings;
    private OkHttpClient client = new OkHttpClient();
    private Semaphore semaphore = new Semaphore(appSettings.getMax_connections()); // 控制并发数量



    public BaseDownloader(String proxyHost, int proxyPort, Map<String, String> headers, int maxConnections) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 设置代理
        if (proxyHost != null && proxyPort > 0) {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
            builder.proxy(proxy);
        }

        // 设置请求头
        if (headers != null) {
            builder.addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                headers.forEach(requestBuilder::addHeader);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
        }


    }

    private Path ensurePath(String path) throws IOException {
        Path filePath = Path.of(path);
        Files.createDirectories(filePath.getParent());
        return filePath;
    }

    @Async
    public CompletableFuture<Void> downloadFile(String url, String fullPath) {
        return CompletableFuture.runAsync(() -> {
            try {
                semaphore.acquire();
                Request request = new Request.Builder().url(url).build();
                try (Response response = client.newCall(request).execute()) {
                    if (!response.isSuccessful()) {
                        throw new IOException("Failed to download file: " + response);
                    }
                    Path path = ensurePath(fullPath);
                    try (FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
                        outputStream.write(response.body().bytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        });
    }

    @Async
    public CompletableFuture<Void> downloadFile(List<String> urls, String fullPath) {
        return CompletableFuture.runAsync(() -> {
            for (String url : urls) {
                try {
                    downloadFile(url, fullPath).join();
                    break; // 成功下载后跳出循环
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Async
    public CompletableFuture<Void> saveFile(String content, String fullPath) {
        return CompletableFuture.runAsync(() -> {
            try {
                Path path = ensurePath(fullPath);
                Files.writeString(path, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
