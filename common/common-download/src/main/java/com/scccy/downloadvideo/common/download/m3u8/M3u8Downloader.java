package com.scccy.downloadvideo.common.download.m3u8;

import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
import com.scccy.downloadvideo.common.download.listener.DownLoadProgressListener;
import com.scccy.downloadvideo.common.download.model.TaskStatus;
import com.scccy.downloadvideo.common.download.model.DownloadTask;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import reactor.core.publisher.Mono;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;

@Slf4j
public class M3u8Downloader {

    private final DownloadFeignClient downloadClient;
    private final Map<String, String> headers;
    private static final int MAX_SEGMENT_COUNT = 1000;
    
    private final ConcurrentHashMap<String, Boolean> downloadedSegments = new ConcurrentHashMap<>();
    private final AtomicInteger segmentCount = new AtomicInteger(0);
    private final ConcurrentHashMap<String, DownloadTask> downloadTasks = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, DownLoadProgressListener> listeners = new ConcurrentHashMap<>();

    public M3u8Downloader(DownloadFeignClient downloadClient, Map<String, String> headers) {
        this.downloadClient = downloadClient;
        this.headers = headers;
    }

    /**
     * 解析M3U8文件
     */
    public Mono<List<M3u8Segment>> parseM3u8(String url) {
        return Mono.fromCallable(() -> {
            Mono<ResponseEntity<String>> response = downloadClient.download(url, headers);
            String content = response.block().getBody();
            
            List<M3u8Segment> segments = new ArrayList<>();
            String[] lines = content.split("\n");
            double duration = 0;
            for (String line : lines) {
                if (line.startsWith("#EXTINF:")) {
                    duration = Double.parseDouble(line.substring(8, line.length() - 1));
                } else if (!line.startsWith("#") && line.trim().length() > 0) {
                    M3u8Segment segment = new M3u8Segment(line.trim());
                    segment.setDuration(duration);
                    segments.add(segment);
                }
            }
            return segments;
        });
    }

    /**
     * 下载单个片段
     */
    private Mono<byte[]> downloadSegment(String url) {
        return Mono.fromCallable(() -> {
            ResponseEntity<byte[]> response = downloadClient.download2Byte(url, headers);
            return response.getBody();
        }).onErrorResume(e -> {
            log.error("Error downloading segment: {}", url, e);
            return Mono.error(e);
        });
    }

    /**
     * 添加下载进度监听器
     */
    public void addListener(String taskId, DownLoadProgressListener listener) {
        listeners.put(taskId, listener);
    }

    /**
     * 下载M3U8流
     */
    public Mono<Path> download(String url, String savePath, String fileName) {
        String taskId = UUID.randomUUID().toString();
        Path fullPath = Paths.get(savePath, fileName);
        
        return Mono.fromCallable(() -> {
            Files.createDirectories(Paths.get(savePath));
            return fullPath;
        }).flatMap(path -> parseM3u8(url)
            .flatMap(segments -> {
                long totalSize = (long) (segments.stream()
                    .mapToDouble(M3u8Segment::getDuration).sum() * 1000);
                
                return Flux.fromIterable(segments)
                    .flatMap(segment -> downloadSegment(segment.getUrl())
                        .doOnNext(bytes -> {
                            try {
                                Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }))
                    .then(Mono.just(path));
            }));
    }
}