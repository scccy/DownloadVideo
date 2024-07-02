//package com.scccy.videoBase.untils.fetcher;
//
//import com.scccy.videoBase.handlerExption.CustomExceptions;
//import com.scccy.videoBase.untils.Untils;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//
//
//@Slf4j
//@Component
//public class AwemeIdFetcher {
//
//    private static final Pattern DOUYIN_VIDEO_URL_PATTERN = Pattern.compile("video/([^/?]*)");
//    private static final Pattern DOUYIN_NOTE_URL_PATTERN = Pattern.compile("note/([^/?]*)");
//
//    @Autowired
//    private OkHttpClient okHttpClient;
//
//    public String getAwemeId(String url) throws CustomExceptions.APITimeoutException, CustomExceptions.APIConnectionException, CustomExceptions.APIUnauthorizedException, IOException, CustomExceptions.APINotFoundException {
//        if (url == null || url.isEmpty()) {
//            throw new CustomExceptions.APINotFoundException("Invalid URL provided.");
//        }
//
//        url = Untils.extractValidUrl(url);
//        if (url == null) {
//            throw new CustomExceptions.APINotFoundException("Invalid URL format.");
//        }
//
//        Pattern videoPattern = DOUYIN_VIDEO_URL_PATTERN;
//        Pattern notePattern = DOUYIN_NOTE_URL_PATTERN;
//
//        try {
//            Request request = new Request.Builder().url(url).build();
//            Response response = okHttpClientUtil.executeRequest(request, Response.class);
//            if (response.code() == 200 || response.code() == 444) {
//                Matcher matcher = videoPattern.matcher(response.request().url().toString());
//                if (matcher.find()) {
//                    return matcher.group(1);
//                } else {
//                    matcher = notePattern.matcher(response.request().url().toString());
//                    if (matcher.find()) {
//                        return matcher.group(1);
//                    } else {
//                        throw new CustomExceptions.APIResponseException("aweme_id not found in the response URL.");
//                    }
//                }
//            } else {
//                throw new CustomExceptions.APIResponseException("Unexpected response status code: " + response.code());
//            }
//        } catch (IOException e) {
//            CustomExceptions.handleException(e, url);
//            return null;
//        }
//    }
//
//    public List<String> getAllAwemeIds(List<String> urls) throws  CustomExceptions.APINotFoundException {
//        if (urls == null || urls.isEmpty()) {
//            throw new CustomExceptions.APINotFoundException("URL list is invalid.");
//        }
//
//        urls = Untils.extractValidUrls(urls);
//        if (urls.isEmpty()) {
//            throw new CustomExceptions.APINotFoundException("No valid URLs found.");
//        }
//
//        List<CompletableFuture<String>> futures = urls.stream()
//                .map(url -> CompletableFuture.supplyAsync(() -> {
//                    try {
//                        return getAwemeId(url);
//                    } catch (Exception e) {
//                        log.error("Error fetching aweme_id for URL: " + url, e);
//                        return null;
//                    }
//                }))
//                .collect(Collectors.toList());
//
//        return futures.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
//    }
//
//
//
//
//
//}
