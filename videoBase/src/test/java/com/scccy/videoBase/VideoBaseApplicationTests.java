package com.scccy.videoBase;

import com.scccy.videoBase.config.manager.TokenManager;
import com.scccy.videoBase.domain.ConfigAppConfig;
import com.scccy.videoBase.untils.OkHttpClientUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;

public class VideoBaseApplicationTests {

    @Autowired
    @Qualifier("configAppConfigDouyin")
    ConfigAppConfig configAppConfigDouyinBean;

    @Resource
    OkHttpClientUtil okHttpClientUtil;

    @Test
    void applicationStarts() throws IOException {

        TokenManager tokenManager = new TokenManager(configAppConfigDouyinBean, okHttpClientUtil);
        System.out.println(tokenManager.getFalseMsToken());

    }
}