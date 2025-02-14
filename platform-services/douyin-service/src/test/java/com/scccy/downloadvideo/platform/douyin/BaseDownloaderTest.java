package com.scccy.downloadvideo.platform.douyin;

import com.scccy.downloadvideo.common.core.enums.DouyinDownloadEnum;
import com.scccy.downloadvideo.common.download.core.BaseDownloader;
import com.scccy.downloadvideo.platform.douyin.model.DouyinBaseDownloadConfig;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@SpringBootTest
public class BaseDownloaderTest {

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;
    
    @Autowired
    BaseDownloader baseDownloader; // 直接注入BaseDownloader

    @Test
    public void testDownload() throws InterruptedException {
        String testUrl = "http://samples.mplayerhq.hu/MPEG-4/turn-on-off.mp4";
        String downloadPath = "/Volumes/project/github/DownloadVideo/download/" + UUID.randomUUID() + ".mp4";

        HashMap<String, String> headersMap = new HashMap<>();
        headersMap.put("User-Agent", DouyinDownloadEnum.USER_AGENT.getValue());
        // 创建下载配置
        DouyinBaseDownloadConfig config = DouyinBaseDownloadConfig.builder()
                .downloadUrl(testUrl)
                .headers(headersMap)
                .path(downloadPath)
                .build();
        System.out.println(config);
        // 发送下载任务到Kafka
        kafkaTemplate.send("download-tasks", config);
        log.info("Download task sent for file: {}", downloadPath);

        // 等待下载完成
        Thread.sleep(30000); // 等待30秒
            
        // 验证文件是否下载成功
        File downloadedFile = new File(downloadPath);
        assert downloadedFile.exists() : "文件未下载成功";
        assert downloadedFile.length() > 0 : "文件大小为0";
            
        log.info("Download completed. File size: {} bytes", downloadedFile.length());
    }
}

