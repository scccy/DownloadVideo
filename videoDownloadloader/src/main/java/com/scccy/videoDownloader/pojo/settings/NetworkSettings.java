package com.scccy.videoDownloader.pojo.settings;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component // 将该类标记为 Spring Bean
public class NetworkSettings {
    private Douyin douyin;
//    private Tiktok tiktok;

    @Data
    public  class Douyin {
        private Headers headers;
        private Proxies proxies;
        private MsToken msToken;
        private Ttwid ttwid;
    }

    @Data
    public  class Headers {
        @Value("${network.douyin.headers.User-Agent}")
        private String userAgent;

        @Value("${network.douyin.headers.Referer}")
        private String referer;
    }

    @Data
    public  class Proxies {
        @Value("${network.douyin.proxies.http}")
        private String http;
        @Value("${network.douyin.proxies.https}")
        private String https;
    }

    @Data
    public  class MsToken {
        @Value("${msToken.url}")
        private String url;
        @Value("${msToken.magic}")
        private String magic;
        @Value("${msToken.version}")
        private int version;
        @Value("${msToken.dataType}")
        private int dataType;
        @Value("${msToken.strData}")
        private String strData;
        @Value("${msToken.userAgent}")
        private String userAgent;
    }

    @Data
    public class Ttwid{
        @Value("${ttwid.url}")
        private String url;
        @Value("${ttwid.data}")
        private String data;
    }


}