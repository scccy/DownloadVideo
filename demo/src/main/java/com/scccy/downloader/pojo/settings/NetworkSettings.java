package com.scccy.downloader.pojo.settings;

import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkSettings {
    @Resource
    public Douyin douyin;
    @Resource
    public Tiktok tiktok;

    @Component
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Douyin {
        @Resource
        public Headers headers;
        @Resource
        public Proxies proxies;
        @Resource
        public MsToken msToken;
        @Resource
        public Ttwid ttwid;


        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Headers {
            @Value("${network.douyin.headers.User-Agent}")
            public String userAgent;
            @Value("${network.douyin.headers.Referer}")
            public String referer;
        }

        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Proxies {
            @Value("${network.douyin.proxies.http}")
            public String http;
            @Value("${network.douyin.proxies.https}")
            public String https;
        }

        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MsToken {
            @Value("${network.douyin.msToken.url}")
            public String url;
            @Value("${network.douyin.msToken.magic}")
            public String magic;
            @Value("${network.douyin.msToken.version}")
            public int version;
            @Value("${network.douyin.msToken.dataType}")
            public int dataType;
            @Value("${network.douyin.msToken.strData}")
            public String strData;
            @Value("${network.douyin.msToken.User-Agent}")
            public String userAgent;
        }

        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Ttwid {
            @Value("${network.douyin.ttwid.url}")
            public String url;
            @Value("${network.douyin.ttwid.data}")
            public String data;
        }
    }


    @Component
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Tiktok {
        @Resource
        public Headers headers;
        @Resource
        public Proxies proxies;
        @Resource
        public MsToken msToken;
        @Resource
        public Ttwid ttwid;
        @Resource
        public OdinTt odinTt;


        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Headers {
            @Value("${network.tiktok.headers.User-Agent}")
            public String userAgent;
            @Value("${network.tiktok.headers.Referer}")
            public String referer;
        }

        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Proxies {
            @Value("${network.tiktok.proxies.http}")
            public String http;
            @Value("${network.tiktok.proxies.https}")
            public String https;
        }
        //        msToken
//                url
//                magic
//                version
//                dataType
//                strData
//                 User
        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class MsToken {
            @Value("${network.tiktok.msToken.url}")
            public String url;
            @Value("${network.tiktok.msToken.magic}")
            public String magic;
            @Value("${network.tiktok.msToken.version}")
            public int version;
            @Value("${network.tiktok.msToken.dataType}")
            public int dataType;
            @Value("${network.tiktok.msToken.strData}")
            public String strData;
            @Value("${network.tiktok.msToken.User-Agent}")
            public String userAgent;
        }

        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Ttwid {
            @Value("${network.tiktok.ttwid.url}")
            public String url;
            @Value("${network.tiktok.ttwid.data}")
            public String data;
            @Value("${network.tiktok.ttwid.cookie}")
            public String cookie;
        }
        @Component
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class OdinTt{
            @Value("${network.tiktok.odin_tt.url}")
            public String url;
        }
    }

}