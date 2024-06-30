package com.scccy.videoBase;

import com.scccy.videoBase.config.manager.TokenManager;
import com.scccy.videoBase.domain.ConfigAppConfig;
import com.scccy.videoBase.untils.OkHttpClientUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class VideoBaseApplicationTests {

    @Resource
    TokenManager tokenManager;

    @Autowired
    ConfigAppConfig configAppConfigDouyinBean;

    @Autowired
    ConfigAppConfig configAppConfigTikTokBean;
    @Autowired
    OkHttpClientUtil okHttpClientUtil;

    @Test
    void applicationStarts() throws IOException {

    TokenManager(configAppConfigDouyinBean,okHttpClientUtil)

}
1