//package com.scccy.videoDownloader.untils;
//
//import com.scccy.videoDownloader.pojo.settings.AppSettings;
//import jakarta.annotation.Resource;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.concurrent.Semaphore;
//
////@Service
//public class BaseDownloader {
//    @Resource
//    AppSettings appSettings;
//    private static OkHttpClient client = new OkHttpClient();
//    private  Semaphore semaphore = new Semaphore(appSettings.getMax_connections()); // 控制并发数量
//
//    private static void downloadFile(String urlStr, String fileName, String savePath, String userAgent, String cookie) {
//        Request.Builder requestBuilder = new Request.Builder()
//                .url(urlStr)
//                .addHeader("User-Agent", userAgent);
//        if (cookie != null) {
//            requestBuilder.addHeader("Cookie", cookie);
//        }
//        Request request = requestBuilder.build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Failed to download file: " + response);
//            }
//
//            ResponseBody body = response.body();
//            if (body != null) {
//                File saveDir = new File(savePath);
//                if (!saveDir.exists()) {
//                    FileUtils.createDirectory(savePath);
//                }
//
//                File file = new File(saveDir, fileName);
//                try (FileOutputStream output = new FileOutputStream(file)) {
//                    output.write(body.bytes());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void downDouFromUrl(String urlStr, String fileName, String savePath, String cookie) {
//        downloadFile(urlStr, fileName, savePath, DouUtil.ua, cookie);
//    }
//
//    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) {
//        downloadFile(urlStr, fileName, savePath, "Mozilla/5.0 (Linux; Android 10; SM-G981B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36", null);
//    }
//}
