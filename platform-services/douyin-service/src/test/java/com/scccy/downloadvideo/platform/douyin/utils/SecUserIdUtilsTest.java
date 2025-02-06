package com.scccy.downloadvideo.platform.douyin.utils;

import com.scccy.downloadvideo.common.core.exception.CustomExceptions.APINotFoundException;
import com.scccy.downloadvideo.common.core.exception.CustomExceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.time.Duration;

@SpringBootTest
@DisplayName("抖音用户ID工具")
@Slf4j
public class SecUserIdUtilsTest {

    @Test
    @DisplayName("测试获取单个用户ID")
    public void testGetSecUserId() {
        String url = "https://v.douyin.com/abc123/";
        
        StepVerifier.create(SecUserIdUtils.getSecUserId(url))
                .expectNextMatches(id -> id != null && !id.isEmpty())
                .expectComplete()
                .verify(Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("测试获取多个用户ID")
    public void testGetAllSecUserId() {
        List<String> urls = Arrays.asList(
            "https://www.douyin.com/user/MS4wLjABAAAA5ZrIrbgva_HMeHuNn64goOD2",
            "https://v.douyin.com/abc123/"
        );

        StepVerifier.create(SecUserIdUtils.getAllSecUserId(urls))
                .expectNextMatches(id -> id != null && !id.isEmpty())
                .thenCancel()
                .verify(Duration.ofSeconds(10));
    }

} 