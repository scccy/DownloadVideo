package com.scccy.downloadvideo.common.download.core;

import com.scccy.downloadvideo.common.core.exception.CustomExceptions;
import com.scccy.downloadvideo.common.core.model.dy.BaseRequestModel;
import com.scccy.downloadvideo.common.core.model.message.ProgressMessage;
import com.scccy.downloadvideo.common.core.utils.MonoUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Slf4j
@Component
public class BaseDownloader {

    @Autowired
    private BaseHttpClient baseHttpClient;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    private static final String PROGRESS_TOPIC = "download-progress";
    private static final String DOWNLOAD_TOPIC = "download-tasks";

    @KafkaListener(topics = DOWNLOAD_TOPIC, containerFactory = "kafkaListenerContainerFactory")
    public Mono<Void> handleDownloadTask(BaseRequestModel config) {
        return MonoUtils.fromBlocking(() -> {
            processDownload(config);
            return null;
        });
    }

    private void processDownload(BaseRequestModel config) {
        String url = config.getDownloadUrl();
        String savePath = config.getPath();

        baseHttpClient.downloadFile(url, savePath, config.getHeaders(), config.getProxyHost(), config.getProxyPort())
            .subscribe(
                success -> sendProgress(url, 100, "下载完成"),
                error -> {
                    log.error("下载失败: {}", error.getMessage(), error);
                    sendProgress(url, -1, "下载失败: " + error.getMessage());
                }
            );
    }

    private Mono<Void> sendProgress(String url, int progress, String message) {
        return MonoUtils.fromBlocking(() -> {
            ProgressMessage progressMessage = new ProgressMessage();
            progressMessage.setUrl(url);
            progressMessage.setProgress(progress);
            progressMessage.setMessage(message);

            kafkaTemplate.send(PROGRESS_TOPIC, progressMessage);
            return null;
        });
    }
}
