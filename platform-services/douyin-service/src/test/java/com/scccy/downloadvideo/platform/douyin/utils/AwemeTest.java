package com.scccy.downloadvideo.platform.douyin.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@DisplayName("抖音工具")
@Slf4j
public class AwemeTest {

    @Test
    @DisplayName("测试获取单个视频ID")
    public void testGetAwemeId_videoUrl() {
        String url = "https://v.douyin.com/iRNBho6u/";
        String expectedAwemeId = "7298145681699622182";

        StepVerifier.create(AwemeUtils.getAwemeId(url))
                .expectNext(expectedAwemeId)
                .verifyComplete();
    }
    
    @Test
    @DisplayName("测试获取多个视频ID")
    public void testGetAwemeId_noteUrl() {
        List<String> urlList = Arrays.asList(
                "0.53 02/26 I@v.sE Fus:/ 你别太帅了郑润泽# 现场版live # 音乐节 # 郑润泽  https://v.douyin.com/iRNBho6u/ 复制此链接，打开Dou音搜索，直接观看视频!",
                "https://v.douyin.com/iRNBho6u/",
                "https://v.douyin.com/iNUBcHxM/",
                "https://www.iesdouyin.com/share/video/7298145681699622182/?region=CN&mid=7298145762238565171&u_code=l1j9bkbd&did=MS4wLjABAAAAtqpCx0hpOERbdSzQdjRZw-wFPxaqdbAzsKDmbJMUI3KWlMGQHC-n6dXAqa-dM2EP&iid=MS4wLjABAAAANwkJuWIRFOzg5uCpDRpMj4OX-QryoDgn-yYlXQnRwQQ&with_sec_did=1&titleType=title&share_sign=05kGlqGmR4_IwCX.ZGk6xuL0osNA..5ur7b0jbOx6cc-&share_version=170400&ts=1699262937&from_aid=6383&from_ssr=1&from=web_code_link",
                "https://www.douyin.com/video/7298145681699622182?previous_page=web_code_link",
                "https://www.douyin.com/video/7298145681699622182",
                "https://www.douyin.com/note/7330042216045464883"
        );

        List<String> expectedAwemeIds = Arrays.asList(
                "7298145681699622182",
                "7298145681699622182",
                "7330042216045464883",
                "7298145681699622182",
                "7298145681699622182",
                "7298145681699622182",
                "7330042216045464883"
        );

        StepVerifier.create(AwemeUtils.getAwemeIds(urlList))
                .expectNextSequence(expectedAwemeIds.stream().distinct().toList())
                .verifyComplete();
    }

    @Test
    @DisplayName("测试空URL")
    public void testEmptyUrl() {
        StepVerifier.create(AwemeUtils.getAwemeId(""))
                .verifyComplete();
    }

    @Test
    @DisplayName("测试空URL列表")
    public void testEmptyUrlList() {
        StepVerifier.create(AwemeUtils.getAwemeIds(null))
                .verifyComplete();
    }


}
