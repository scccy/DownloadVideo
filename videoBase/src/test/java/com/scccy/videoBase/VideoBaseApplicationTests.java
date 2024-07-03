package com.scccy.videoBase;

import com.scccy.videoBase.domain.ConfigAppConfig;
import com.scccy.videoBase.untils.ABogusUtil;
import com.scccy.videoBase.untils.BrowserFingerprintGenerator;
import com.scccy.videoBase.untils.XbogusUtil;
import com.scccy.videoBase.untils.downloader.OkHttpClientUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
public class VideoBaseApplicationTests {

    @Autowired
    ConfigAppConfig configAppConfigDouyinBean;

    @Resource
    OkHttpClientUtil okHttpClientUtil;

    @Test
    void applicationStarts() throws IOException {

//        TokenManager tokenManager = new TokenManager(configAppConfigDouyinBean, okHttpClientUtil);
//        System.out.println(tokenManager.getFalseMsToken());

    }
    @Test
    void  ABogusUtil(){
        try {
            System.out.println("ABogusUtil 测试开始");
            String  user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0";
            String url = "https://www.douyin.com/aweme/v1/web/aweme/detail/?";
            String params = "device_platform=webapp&aid=6383&channel=channel_pc_web&aweme_id=7380308675841297704&update_version_code=170400&pc_client_type=1&version_code=190500&version_name=19.5.0&cookie_enabled=true&screen_width=1920&screen_height=1080&browser_language=zh-CN&browser_platform=Win32&browser_name=Edge&browser_version=125.0.0.0&browser_online=true&engine_name=Blink&engine_version=125.0.0.0&os_name=Windows&os_version=10&cpu_core_num=12&device_memory=8&platform=PC&downlink=10&effective_type=4g&round_trip_time=50&webid=7376294349792396827";
            String request = "GET";

            String chrome = BrowserFingerprintGenerator.init("Chrome");
            ABogusUtil aBogusUtil = new ABogusUtil(user_agent, chrome);
            System.out.println(aBogusUtil.generateAbogus(params, request));
        } catch (Exception e) {
            System.out.println("ABogusUtil 测失败");
            throw new RuntimeException(e);
        }
    }
    @Test
    public void test_get_xbogus() {
        XbogusUtil xb = new XbogusUtil(null);
        String result = null;
        try {
            result = xb.getXBogus("aweme_id=7196239141472980280&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333");
            System.out.printf(result);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }


}