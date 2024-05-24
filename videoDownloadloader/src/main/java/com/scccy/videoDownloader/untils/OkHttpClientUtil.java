package com.scccy.videoDownloader.untils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONReader;
import okhttp3.*;

import java.io.IOException;

public class OkHttpClientUtil {

    private static final OkHttpClient client = new OkHttpClient();


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
