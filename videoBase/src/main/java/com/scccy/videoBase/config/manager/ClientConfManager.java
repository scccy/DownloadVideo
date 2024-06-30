package com.scccy.videoBase.config.manager;

import com.scccy.videoBase.domain.*;
import com.scccy.videoBase.service.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @implementation 初始化默认参数
 * @author scccy
 * @date 2024/6/29下午4:33
 */
@Configuration
public class ClientConfManager {

    private static final Logger logger = LoggerFactory.getLogger(ClientConfManager.class);

    @Resource
    private ConfigDouyinService configDouyinService;
    @Resource
    private ConfigAppConfigService configAppConfigService;
    @Resource
    private ConfigBaseService configBaseService;
    @Resource
    private ConfigTiktokService configTiktokService;
    @Resource
    private ConfigTwitterService configTwitterService;

    private List<ConfigBase> configBaseList = Collections.emptyList();
    private List<ConfigAppConfig> configAppConfigList = Collections.emptyList();
    private List<ConfigDouyin> configDouyinList = Collections.emptyList();
    private List<ConfigTiktok> configTiktokList = Collections.emptyList();
    private List<ConfigTwitter> configTwitterList = Collections.emptyList();

    @PostConstruct
    public void initializeConfigs() {
        try {
            configDouyinList = Optional.ofNullable(configDouyinService.list()).orElse(Collections.emptyList());
            configAppConfigList = Optional.ofNullable(configAppConfigService.list()).orElse(Collections.emptyList());
            configBaseList = Optional.ofNullable(configBaseService.list()).orElse(Collections.emptyList());
            configTiktokList = Optional.ofNullable(configTiktokService.list()).orElse(Collections.emptyList());
            configTwitterList = Optional.ofNullable(configTwitterService.list()).orElse(Collections.emptyList());
        } catch (Exception e) {
            logger.error("Error initializing configs", e);
        }
    }

    @Bean
    public List<ConfigBase> configBaseList() {
        return configBaseList;
    }

    @Bean
    public List<ConfigAppConfig> configAppConfigList() {
        return configAppConfigList;
    }

    @Bean
    public List<ConfigDouyin> configDouyinList() {
        return configDouyinList;
    }

    @Bean
    public List<ConfigTiktok> configTiktokList() {
        return configTiktokList;
    }

    @Bean
    public List<ConfigTwitter> configTwitterList() {
        return configTwitterList;
    }

    @Bean
    public ConfigAppConfig configAppConfigDouyinBean() {
        return configAppConfigList.isEmpty() ? null : configAppConfigList.get(0);
    }

    @Bean
    public ConfigAppConfig configAppConfigTikTokBean() {
        return configAppConfigList.size() > 1 ? configAppConfigList.get(1) : null;
    }

    @Bean
    public ConfigAppConfig configAppConfigTwitterBean() {
        return configAppConfigList.size() > 2 ? configAppConfigList.get(2) : null;
    }

    @Bean
    public ConfigBase configBaseBean() {
        return configBaseList.isEmpty() ? null : configBaseList.get(0);
    }
}
