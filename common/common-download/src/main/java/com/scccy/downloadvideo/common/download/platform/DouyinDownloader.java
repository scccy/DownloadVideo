package com.scccy.downloadvideo.common.download.platform;



import com.scccy.downloadvideo.common.core.model.entity.DownloadConfig;
import com.scccy.downloadvideo.common.download.core.BaseDownloader;
import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

@Slf4j
public class DouyinDownloader extends BaseDownloader {

    private Map<String, String> headers;
    private String proxyHost;
    private Integer proxyPort;

    public DouyinDownloader(DownloadFeignClient downloadClient, DownloadConfig downloadConfig, Map<String, String> headers) {
        super(downloadClient, downloadConfig, headers);
    }


    /**
     * 下载视频
     */
    public Mono<Path> downloadVideo(String videoUrl, String savePath, String fileName) throws IOException {
        return downloadFile(videoUrl, savePath, fileName + "_video.mp4");
    }

    /**
     * 下载音乐
     */
    public Mono<Path> downloadMusic(String musicUrl, String savePath, String fileName) throws IOException {
        return downloadFile(musicUrl, savePath, fileName + "_music.mp3");
    }

    /**
     * 下载封面
     */
    public Mono<Path> downloadCover(String coverUrl, String savePath, String fileName) throws IOException {
        String suffix = coverUrl.contains(".webp") ? ".webp" : ".jpeg";
        return downloadFile(coverUrl, savePath, fileName + "_cover" + suffix);
    }

}