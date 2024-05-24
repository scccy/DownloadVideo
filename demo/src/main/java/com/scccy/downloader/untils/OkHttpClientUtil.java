package com.scccy.downloader.untils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import okhttp3.*;

import java.io.IOException;

public class OkHttpClientUtil {

    private static final OkHttpClient client = new OkHttpClient();

//    public static <T extends DownloadFather> void downloadFiles(List<T> dataList, String destinationFolder) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
//        try {
//            for (DownloadFather data : dataList) {
//                executorService.submit(() -> {
//                    String dynamicCoverUrl = data.getDynamicCover();
//                    String originCoverUrl = data.getOriginCover();
//                    String downloadsUrl = data.getDownloads();
//                    String nickName = data.getNickname();
//
//                    String dynamicCoverFileName = getFileNameFromUrl(dynamicCoverUrl);
//                    String originCoverFileName = getFileNameFromUrl(originCoverUrl);
//                    String downloadsFileName = getFileNameFromUrl(downloadsUrl);
//
//                    String destinationFolderForData = destinationFolder + File.separator + nickName;
//                    File dataFolder = new File(destinationFolderForData);
//                    if (!dataFolder.exists()) {
//                        dataFolder.mkdirs();
//                    }
//                    try {
//                        downloadFile(dynamicCoverUrl, destinationFolderForData + File.separator + dynamicCoverFileName);
//                        downloadFile(originCoverUrl, destinationFolderForData + File.separator + originCoverFileName);
//                        downloadFile(downloadsUrl, destinationFolderForData + File.separator + downloadsFileName);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//            }
//        } finally {
//            executorService.shutdown();
//        }
//    }

//    private static String getFileNameFromUrl(String url) {
//        return url.substring(url.lastIndexOf("/") + 1);
//    }
//
//    public static void downloadFile(String url, String destination) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3")
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Failed to download file: " + response);
//            }
//            try (InputStream inputStream = response.body().byteStream();
//                 FileOutputStream outputStream = new FileOutputStream(destination)) {
//                byte[] buffer = new byte[4096];
//                int bytesRead;
//                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                    outputStream.write(buffer, 0, bytesRead);
//                }
//            }
//        }
//    }

    public static <T> T post(String url, Object requestBody, Class<T> responseClass) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(requestBody));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return JSON.parseObject(response.body().string(), responseClass, JSONReader.Feature.FieldBased,
                    JSONReader.Feature.SupportArrayToBean,
                    JSONReader.Feature.SupportSmartMatch
                     );
        }
    }

    public static <T> T get(String url, Class<T> responseClass) throws IOException {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return JSON.parseObject(response.body().string(), responseClass, JSONReader.Feature.FieldBased,
                    JSONReader.Feature.SupportArrayToBean,
                    JSONReader.Feature.SupportSmartMatch
            );
        }
    }
}
