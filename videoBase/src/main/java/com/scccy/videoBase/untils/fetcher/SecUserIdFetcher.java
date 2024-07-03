package com.scccy.videoBase.untils.fetcher;

import com.scccy.videoBase.handlerExption.CustomExceptions;
import com.scccy.videoBase.untils.Untils;
import com.scccy.videoBase.untils.downloader.OkHttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SecUserIdFetcher {

    private static final Pattern DOUYIN_URL_PATTERN = Pattern.compile("user/([^/?]*)");
    private static final Pattern REDIRECT_URL_PATTERN = Pattern.compile("sec_uid=([^&]*)");

    @Autowired
    private OkHttpClientUtil okHttpClientUtil;

    public String getSecUserId(String url) throws CustomExceptions.APITimeoutException, CustomExceptions.APIConnectionException, CustomExceptions.APIUnauthorizedException, IOException {
        if (url == null || url.isEmpty()) {
            throw new CustomExceptions.CustomException("Invalid URL provided.");
        }

        url = extractValidUrl(url);
        if (url == null) {
            throw new CustomExceptions.CustomException("Invalid URL format.");
        }

        Pattern pattern = url.contains("v.douyin.com") ? REDIRECT_URL_PATTERN : DOUYIN_URL_PATTERN;

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClientUtil.executeRequest(request, Response.class);
            if (response.code() == 200 || response.code() == 444) {
                Matcher matcher = pattern.matcher(response.request().url().toString());
                if (matcher.find()) {
                    return matcher.group(1);
                } else {
                    throw new CustomExceptions.CustomException("sec_user_id not found in the response URL.");
                }
            } else {
                throw new CustomExceptions.CustomException("Unexpected response status code: " + response.code());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<String> getAllSecUserIds(List<String> urls) throws ExecutionException, InterruptedException {
        if (urls == null || urls.isEmpty()) {
            throw new CustomExceptions.CustomException("URL list is invalid.");
        }

        urls = (List<String>) Untils.extractValidUrls(urls);
        if (urls != null && urls.isEmpty()) {
            throw new CustomExceptions.CustomException("No valid URLs found.");
        }

        List<CompletableFuture<String>> futures = null;
        if (urls != null) {
            futures = urls.stream()
                    .map(url -> CompletableFuture.supplyAsync(() -> {
                        try {
                            return getSecUserId(url);
                        } catch (Exception e) {
                            log.error("Error fetching sec_user_id for URL: " + url, e);
                            return null;
                        }
                    }))
                    .toList();
        }

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private String extractValidUrl(String input) {
        Pattern urlPattern = Pattern.compile("https?://\\S+");
        Matcher matcher = urlPattern.matcher(input);
        return matcher.find() ? matcher.group() : null;
    }




}
