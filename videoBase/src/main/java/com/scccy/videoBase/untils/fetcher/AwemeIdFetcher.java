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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



@Slf4j
@Component
public class AwemeIdFetcher {

    private static final Pattern DOUYIN_VIDEO_URL_PATTERN = Pattern.compile("video/([^/?]*)");
    private static final Pattern DOUYIN_NOTE_URL_PATTERN = Pattern.compile("note/([^/?]*)");
    @Autowired
    private OkHttpClientUtil okHttpClientUtil;

    public String getAwemeId(String url) throws CustomExceptions.APITimeoutException, CustomExceptions.APIConnectionException, CustomExceptions.APIUnauthorizedException, IOException, CustomExceptions.APINotFoundException {
        if (url == null || url.isEmpty()) {
            throw new CustomExceptions.APINotFoundException("提供的URL无效.");
        }

        url = Untils.extractValidUrl(url);
        if (url == null) {
            throw new CustomExceptions.APINotFoundException("URL格式无效。");
        }

        Pattern videoPattern = DOUYIN_VIDEO_URL_PATTERN;
        Pattern notePattern = DOUYIN_NOTE_URL_PATTERN;

        try {
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClientUtil.executeRequest(request, Response.class);
            if (response.code() == 200 || response.code() == 444) {
                Matcher matcher = videoPattern.matcher(response.request().url().toString());
                if (matcher.find()) {
                    return matcher.group(1);
                } else {
                    matcher = notePattern.matcher(response.request().url().toString());
                    if (matcher.find()) {
                        return matcher.group(1);
                    } else {
                        throw new CustomExceptions.APIResponseException("响应URL中未找到aweme_id。");
                    }
                }
            } else {
                throw new CustomExceptions.APIResponseException("意外的响应状态码：" + response.code());
            }
        } catch (IOException e) {
            CustomExceptions.handleException(e, url);
            return null;
        }
    }

    public List<String> getAllAwemeIds(List<String> urls) throws  CustomExceptions.APINotFoundException {
        if (urls == null || urls.isEmpty()) {
            throw new CustomExceptions.APINotFoundException("提供的URL无效.");
        }

        urls = Untils.extractValidUrls(urls);
        if (urls.isEmpty()) {
            throw new CustomExceptions.APINotFoundException("未找到有效的URL。");
        }

        List<CompletableFuture<String>> futures = urls.stream()
                .map(url -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return getAwemeId(url);
                    } catch (Exception e) {
                        log.error("获取URL的aweme_id时出错： " + url, e);
                        return null;
                    }
                }))
                .collect(Collectors.toList());

        return futures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
