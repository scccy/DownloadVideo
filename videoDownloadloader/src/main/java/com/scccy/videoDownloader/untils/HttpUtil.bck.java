//package com.scccy.videoDownloader.untils;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.alibaba.fastjson2.JSONReader;
//import com.scccy.videoDownloader.common.HttpHeader;
//import okhttp3.*;
//
//import java.io.*;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
//public class HttpUtil {
//
//    private static   OkHttpClient client = new OkHttpClient
//            .Builder()
//            .connectTimeout(5000, TimeUnit.MILLISECONDS)
//            .readTimeout(5000, TimeUnit.MILLISECONDS)
//            .build();
//
//
//
//
//    public static JSONObject httpGet(String url, HttpHeader header) throws IOException {
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("User-Agent", header.getUserAgent())
//                .addHeader("referer", header.getReferer())
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//            return JSON.parseObject(response.body().string(),
//                    JSONReader.Feature.FieldBased,
//                    JSONReader.Feature.SupportArrayToBean,
//                    JSONReader.Feature.SupportSmartMatch
//            );
//        }
//    }private static String extractCookie(Headers headers) {
//        List<String> cookies = headers.values("Set-Cookie");
//        StringBuilder cookieBuilder = new StringBuilder();
//        for (String cookie : cookies) {
//            String str = cookie.split(";")[0];
//            cookieBuilder.append(str).append("; ");
//        }
//        return cookieBuilder.toString().trim();
//    }
//
////    public static String httpGetByPoll(String url, HttpHeader header) throws IOException {
////        try {
//////            HttpHeader request = new HttpHeader(header, "");
////            JSONObject response = httpGet(url, header);
////            String code = response.getJSONObject("data").getString("code");
////            if ("0".equals(code)) {
////                String cookie = extractCookie(response.headers());
////                return cookie;
////            } else {
////                return null;
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////            return null;
////        }
////    }
//}
//
////    public static JSONObject getSerchPersion(String url, HttpHeader header) throws IOException {
////        return httpGet(url, header);
////    }
//
////    public static String httpGetBili(String url, HttpHeader header, String cookie) {
////        Request.Builder requestBuilder = new Request.Builder()
////                .url(url)
////                .addHeader("User-Agent", header.getUserAgent());
////
////        if (cookie != null && !cookie.equals("")) {
////            requestBuilder.addHeader("cookie", cookie);
////        }
////
////        Request request = requestBuilder.build();
////        return executeRequest(request);
////    }
//
//    public static JSONObject doPost(String url, JSONObject json) {
//        JSONObject response = null;
//        try {
//            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//            RequestBody body = RequestBody.create(JSON, json.toString());
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build();
//
//            Response httpResponse = client.newCall(request).execute();
//            if (httpResponse.isSuccessful()) {
//                String result = httpResponse.body().string();
//                // todo:使用ResultData接受返回请求
//                response = new JSONObject(Integer.parseInt(result));
//            }
//        } catch (IOException e) {
//            System.out.println("发生网络异常!");
//            e.printStackTrace();
//        }
//        return response;
//    }
//
//    public static JSONObject doPostNew(String url, JSONObject json) {
//        JSONObject response = null;
//        try {
//            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//            RequestBody body = RequestBody.create(JSON, json.toString());
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .addHeader("Content-Type", "application/json")
//                    .build();
//
//            Response httpResponse = client.newCall(request).execute();
//            if (httpResponse.isSuccessful()) {
//                String result = httpResponse.body().string();
//                response = new JSONObject(Integer.parseInt(result));
//            }
//        } catch (IOException e) {
//            System.out.println("发生网络异常!");
//            e.printStackTrace();
//        }
//        return response;
//    }
//
////    public static void downBiliFromUrl(String urlStr, String fileName, String savePath) throws IOException {
////        downLoadFromUrl(urlStr, fileName, savePath, HttpHeader.BILI_DROID);
////    }
////
////    public static void downDouFromUrl(String urlStr, String fileName, String savePath, String cookie) throws IOException {
////        downLoadFromUrl(urlStr, fileName, savePath, HttpHeader.PC_CHROME, cookie);
////    }
//
////    public static void downLoadFromUrl(String urlStr, String fileName, String savePath, HttpHeader header, String cookie) throws IOException {
////        Request.Builder requestBuilder = new Request.Builder()
////                .url(urlStr)
////                .addHeader("User-Agent", header.getUserAgent())
////                .addHeader("referer", header.getReferer());
////
////        if (cookie != null) {
////            requestBuilder.addHeader("Cookie", cookie);
////        }
////
////        Request request = requestBuilder.build();
////
////        try (Response response = client.newCall(request).execute()) {
////            if (!response.isSuccessful()) {
////                throw new IOException("Unexpected code " + response);
////            }
////
////            InputStream inputStream = response.body().byteStream();
////            byte[] data = readInputStream(inputStream);
////
////            File saveDir = new File(savePath);
////            if (!saveDir.exists()) {
////                saveDir.mkdirs();
////            }
////
////            File file = new File(saveDir, fileName);
////            try (FileOutputStream outputStream = new FileOutputStream(file)) {
////                outputStream.write(data);
////            }
////        }
////    }
//
////    public static void downLoadFromUrl(String urlStr, String fileName, String savePath, HttpHeader header) throws IOException {
////        downLoadFromUrl(urlStr, fileName, savePath, header, null);
////    }
////
////    private static byte[] readInputStream(InputStream inputStream) throws IOException {
////        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
////        byte[] buffer = new byte[4096];
////        int bytesRead;
////        while ((bytesRead = inputStream.read(buffer)) != -1) {
////            outputStream.write(buffer, 0, bytesRead);
////        }
////        return outputStream.toByteArray();
////    }
////}
