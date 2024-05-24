package com.scccy.videoDownloader;

import com.scccy.videoDownloader.pojo.settings.NetworkSettings;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@SpringBootTest
@Import({NetworkSettings.class})
public class test {
    @Resource
    NetworkSettings networkSettings;

    @Test
    public void test01(){
        System.out.println(networkSettings);
    }
}