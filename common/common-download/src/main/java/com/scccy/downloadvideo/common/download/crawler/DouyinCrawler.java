package com.scccy.downloadvideo.common.download.crawler;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.config.manager.XBogusManager;
import com.scccy.downloadvideo.common.core.exception.ServiceException;
import com.scccy.downloadvideo.common.download.core.BaseCrawler;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import io.swagger.v3.core.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import javax.naming.directory.SearchResult;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.scccy.downloadvideo.common.core.annotation.ApiOperation;
import com.scccy.downloadvideo.common.core.context.ApiContext;
import com.scccy.downloadvideo.common.download.feign.CrawlerFeignClient;

@Slf4j
@Component
public class DouyinCrawler {

    private final CrawlerFeignClient crawlerClient;

    public DouyinCrawler(CrawlerFeignClient crawlerClient) {
        this.crawlerClient = crawlerClient;
    }

    public Mono<JSONObject> getUserProfile(String userId) {
        return Mono.fromCallable(() -> crawlerClient.fetchUserProfile(userId))
                .map(ResponseEntity::getBody);
    }

    public Mono<JSONObject> getUserPosts(String userId, int page) {
        return Mono.fromCallable(() -> crawlerClient.fetchUserPosts(userId, page))
                .map(ResponseEntity::getBody);
    }

    public Mono<JSONObject> getUserLikes(String userId, int page) {
        return Mono.fromCallable(() -> crawlerClient.fetchUserLikes(userId, page))
                .map(ResponseEntity::getBody);
    }

    // ... 其他方法
}