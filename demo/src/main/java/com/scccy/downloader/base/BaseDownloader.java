package com.scccy.downloader.base;//package com.scccy.videoDownloader.base;
//
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
//import java.util.*;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.Semaphore;
//import java.util.stream.Collectors;
//
//public class BaseDownloader {
//    private final HttpClient client;
//    private final List<CompletableFuture<Void>> downloadTasks;
//    private final Map<String, String> headers;
//    private final Semaphore semaphore;
//    private final int concurrentDownloads;
//
//    public BaseDownloader(Map<String, String> headers, int concurrentDownloads) {
//        this.client = HttpClient.newHttpClient();
//        this.downloadTasks = new ArrayList<>();
//        this.headers = headers;
//        this.concurrentDownloads = concurrentDownloads;
//        this.semaphore = new Semaphore(concurrentDownloads);
//    }
//
//    private static void ensurePath(Path path) throws IOException {
//        if (Files.notExists(path)) {
//            Files.createDirectories(path.getParent());
//        }
//    }
//
//    private HttpRequest.Builder createRequestBuilder(String url) {
//        HttpRequest.Builder builder = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .GET();
//        headers.forEach(builder::header);
//        return builder;
//    }
//
//    private CompletableFuture<Long> getContentLength(String url) {
//        return client.sendAsync(createRequestBuilder(url).build(), BodyHandlers.discarding())
//                .thenApply(response -> {
//                    if (response.statusCode() == 200) {
//                        return response.headers().firstValueAsLong("Content-Length").orElse(0L);
//                    } else {
//                        return 0L;
//                    }
//                });
//    }
//
//    private CompletableFuture<Void> downloadFile(String url, Path fullPath, long contentLength) {
//        return CompletableFuture.runAsync(() -> {
//            try {
//                ensurePath(fullPath);
//                HttpRequest request = createRequestBuilder(url)
//                        .header("Range", "bytes=" + Files.size(fullPath) + "-")
//                        .build();
//
//                client.send(request, HttpResponse.BodyHandlers.ofFile(fullPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND));
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//    }
//
//    public void initiateDownload(String fileType, List<String> urls, Path fullPath) {
//        try {
//            ensurePath(fullPath);
//            if (Files.exists(fullPath)) {
//                System.out.println("[  跳过  ]: " + fullPath);
//                return;
//            }
//
//            CompletableFuture<Void> downloadTask = CompletableFuture.runAsync(() -> {
//                try {
//                    semaphore.acquire();
//
//                    for (String url : urls) {
//                        long contentLength = getContentLength(url).get();
//
//                        if (contentLength > 0) {
//                            downloadFile(url, fullPath, contentLength).join();
//                            System.out.println("[  完成  ]: " + fullPath);
//                            break;
//                        } else {
//                            System.out.println("[  警告  ]: 内容长度为0，尝试下一个链接");
//                        }
//                    }
//
//                } catch (InterruptedException  e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            });
//
//            downloadTasks.add(downloadTask);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void initiateStaticDownload(String fileType, String content, Path fullPath) {
//        try {
//            ensurePath(fullPath);
//            if (Files.exists(fullPath)) {
//                System.out.println("[  跳过  ]: " + fullPath);
//                return;
//            }
//
//            CompletableFuture<Void> downloadTask = CompletableFuture.runAsync(() -> {
//                try {
//                    semaphore.acquire();
//                    Files.writeString(fullPath, content, StandardOpenOption.CREATE);
//                    System.out.println("[  完成  ]: " + fullPath);
//                } catch (IOException | InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            });
//
//            downloadTasks.add(downloadTask);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private CompletableFuture<List<String>> getSegmentsFromM3U8(String url) {
//        return client.sendAsync(createRequestBuilder(url).build(), BodyHandlers.ofString())
//                .thenApply(response -> {
//                    if (response.statusCode() == 200) {
//                        return Arrays.stream(response.body().split("\n"))
//                                .filter(line -> !line.startsWith("#"))
//                                .collect(Collectors.toList());
//                    } else {
//                        return Collections.emptyList();
//                    }
//                });
//    }
//
//    public void initiateM3U8Download(String fileType, String m3u8Url, Path fullPath) {
//        try {
//            ensurePath(fullPath);
//            if (Files.exists(fullPath)) {
//                System.out.println("[  跳过  ]: " + fullPath);
//                return;
//            }
//
//            CompletableFuture<Void> downloadTask = CompletableFuture.runAsync(() -> {
//                try {
//                    semaphore.acquire();
//
//                    while (true) {
//                        List<String> segments = getSegmentsFromM3U8(m3u8Url).get();
//
//                        if (segments.isEmpty()) {
//                            System.out.println("[  丢失  ]: " + fullPath);
//                            break;
//                        }
//
//                        for (String segment : segments) {
//                            long contentLength = getContentLength(segment).get();
//                            downloadFile(segment, fullPath, contentLength).join();
//                        }
//
//                        // 等待一段时间后再次请求更新
//                        Thread.sleep(5000);
//                    }
//
//                } catch (InterruptedException | ExecutionException e) {
//                    e.printStackTrace();
//                } finally {
//                    semaphore.release();
//                }
//            });
//
//            downloadTasks.add(downloadTask);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void executeTasks() {
//        CompletableFuture.allOf(downloadTasks.toArray(new CompletableFuture[0])).join();
//        downloadTasks.clear();
//    }
//
//    public static void main(String[] args) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("User-Agent", "Your User Agent");
//        headers.put("Referer", "Your Referer");
//
//        BaseDownloader downloader = new BaseDownloader(headers, 3);
//
//        List<String> urls = List.of(
//                "http://example.com/file1",
//                "http://example.com/file2"
//        );
//        Path savePath = Paths.get("/path/to/save/file.ext");
//
//        downloader.initiateDownload("fileType", urls, savePath);
//        downloader.executeTasks();
//    }
//}
