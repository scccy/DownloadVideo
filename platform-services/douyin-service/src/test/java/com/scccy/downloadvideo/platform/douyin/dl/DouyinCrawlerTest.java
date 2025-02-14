package com.scccy.downloadvideo.platform.douyin.dl;

import com.scccy.downloadvideo.common.core.enums.DouyinApiEnum;
import com.scccy.downloadvideo.common.core.enums.DouyinDownloadEnum;
import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
import com.scccy.downloadvideo.common.core.utils.CookieUtils;
import com.scccy.downloadvideo.common.core.utils.manager.XBogusManager;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.scccy.downloadvideo.platform.douyin.service.DouyinDownloadConfigService;
import com.scccy.downloadvideo.platform.douyin.utils.SecUserIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@SpringBootTest
public class DouyinCrawlerTest {
    @Autowired
    DouyinDownloadConfigService douyinDownloadConfigServiceImpl; ;
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
    
    @Test
    public void fetchUserProfile(){
        String url = "https://v.douyin.com/abc123/";
        String SecUserId = SecUserIdUtils.getSecUserId(url).block();
        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("User-Agent", DouyinDownloadEnum.USER_AGENT.getValue());
        headersMap.put("Cookie", CookieUtils.cookieToJson(douyinDownloadConfigServiceImpl.getById(1).getCookie()).toJSONString());
        XBogusManager.model_2_endpoint(DouyinDownloadEnum.USER_AGENT.getValue(), DouyinApiEnum.USER_DETAIL.getValue(),new BaseRequestModel());
        System.out.println(CookieUtils.cookieToJson(douyinDownloadConfigServiceImpl.getById(1).getCookie()).toJSONString());
    }
}
