package com.scccy.downloader;

import com.scccy.downloader.pojo.settings.NetworkSettings;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestApplicationTests {

	@Resource
	NetworkSettings networkSettings;
	@Test
	void contextLoads() {
		System.out.println(networkSettings.getTiktok());
	}

}
