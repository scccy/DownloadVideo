package com.scccy.videoDownloader.untils;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.videoDownloader.common.HttpHeader;
import okhttp3.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class HttpUtil {

    private static OkHttpClient client = OkHttpClientFactory.createClient();

    private static class InputStreamWithProgress extends FilterInputStream {
        private long totalBytes;
        private long bytesRead;

        protected InputStreamWithProgress(InputStream in, long totalBytes) {
            super(in);
            this.totalBytes = totalBytes;
            this.bytesRead = 0;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            int bytesRead = super.read(b, off, len);
            if (bytesRead != -1) {
                this.bytesRead += bytesRead;
            }
            return bytesRead;
        }

        public int getProgress() {
            if (totalBytes > 0) {
                return (int) ((bytesRead * 100) / totalBytes);
            } else {
                return 0;
            }
        }
    }


    public static String httpGet(String url, String param, HttpHeader header) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", header.getUserAgent())
                .header("Referer", header.getReferer())
                .build();

        String responseString = "";
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("请求出错: " + response);
            }
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                responseString = new String(responseBody.bytes(), param);
            }
        }
        return responseString;
    }

    public static String httpGetBypoll(String url, String param, HttpHeader header) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", header.getUserAgent())
                .header("Referer", header.getReferer())
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return null;
            }

            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                String responseString = new String(responseBody.bytes(), param);
                JSONObject jsonObject = JSONObject.parseObject(responseString);
                String code = jsonObject.getJSONObject("data").getString("code");
                if ("0".equals(code)) {
                    StringBuilder cookieBuilder = new StringBuilder();
                    for (String headerName : response.headers().names()) {
                        if ("Set-Cookie".equalsIgnoreCase(headerName)) {
                            for (String cookie : response.headers(headerName)) {
                                cookieBuilder.append(cookie.split(";")[0]).append(";");
                            }
                        }
                    }
                    if (cookieBuilder.length() > 0) {
                        // 去掉最后一个多余的分号
                        return cookieBuilder.substring(0, cookieBuilder.length() - 1);
                    }
                }
            }
        }

        return null;
    }

    public static String getSerchPersion(String url, String param) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("请求出错: " + response);
            }
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return new String(responseBody.bytes(), param);
            }
        }
        return "";
    }

    public static String httpGetBili(String url, String param, String cookie) throws IOException {
        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36");

        if (cookie != null && !cookie.isEmpty()) {
            requestBuilder.header("Cookie", cookie);
        }

        Request request = requestBuilder.build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("请求出错: " + response);
            }
            ResponseBody responseBody = response.body();
            if (responseBody != null) {
                return new String(responseBody.bytes(), param);
            }
        }
        return "";
    }

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static JSONObject doPost(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String result = new String(response.body().bytes(), StandardCharsets.UTF_8);
                return  JSONObject.parseObject(result);
            }
        }
        return null;
    }
    public static JSONObject doPostNew(String url, JSONObject json) throws IOException {
        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String result = new String(response.body().bytes(), StandardCharsets.UTF_8);
                return  JSONObject.parseObject(result);
            }
        }
        return null;
    }

    public static void downBiliFromUrl(String urlStr, String fileName, String savePath) throws Exception {
        Request request = new Request.Builder()
                .url(urlStr)
                .header("User-Agent", "Mozilla/5.0 BiliDroid/7.25.0 (bbcallen@gmail.com)")
                .header("Referer", "https://www.bilibili.com")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to download file: " + response);
            }

            InputStream input = response.body().byteStream();
            byte[] getData = readInputStream(input);
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                FileUtils.forceMkdir(saveDir);
            }
            File file = new File(saveDir, fileName);
            try (FileOutputStream output = new FileOutputStream(file)) {
                output.write(getData);
            }

            if (input != null) {
                input.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }



    public static void downLoadFromUrl(String urlStr, String fileName, String savePath) {
        try {
            Request request = new Request.Builder()
                    .url(urlStr)
                    .header("User-Agent", "Mozilla/5.0 (Linux; Android 10; SM-G981B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36")
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Failed to download file: " + response);
                }

                InputStream input = response.body().byteStream();
                byte[] getData = readInputStream(input);
                File saveDir = new File(savePath);
                if (!saveDir.exists()) {
                    FileUtils.forceMkdir(saveDir);
                }
                File file = new File(saveDir, fileName);
                try (FileOutputStream output = new FileOutputStream(file)) {
                    output.write(getData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[10240];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}