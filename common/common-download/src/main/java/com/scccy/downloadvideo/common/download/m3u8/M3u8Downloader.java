package com.scccy.downloadvideo.common.download.m3u8;

import com.scccy.downloadvideo.common.download.feign.DownloadFeignClient;
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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class M3u8Downloader {

    private final DownloadFeignClient downloadClient;
    private final Map<String, String> headers;
    private static final int MAX_SEGMENT_COUNT = 1000;
    
    private final ConcurrentHashMap<String, Boolean> downloadedSegments = new ConcurrentHashMap<>();
    private final AtomicInteger segmentCount = new AtomicInteger(0);

    public M3u8Downloader(DownloadFeignClient downloadClient, Map<String, String> headers) {
        this.downloadClient = downloadClient;
        this.headers = headers;
    }

    /**
     * 解析M3U8文件
     */
    public List<M3u8Segment> parseM3u8(String url) throws IOException {
        ResponseEntity<String> response = downloadClient.download(url, headers);
        String content = response.getBody();
        
        List<M3u8Segment> segments = new ArrayList<>();
        // 解析M3U8内容,提取ts片段URL
        String[] lines = content.split("\n");
        double duration = 0;
        for (String line : lines) {
            if (line.startsWith("#EXTINF:")) {
                // 提取分片时长
                duration = Double.parseDouble(line.substring(8, line.length() - 1));
            } else if (!line.startsWith("#") && line.trim().length() > 0) {
                // 创建分片对象
                M3u8Segment segment = new M3u8Segment(line.trim());
                segment.setDuration(duration);
                segments.add(segment);
            }
        }
        return segments;
    }

    /**
     * 下载单个片段
     */
    private byte[] downloadSegment(String url) throws IOException {
        ResponseEntity<byte[]> response = downloadClient.download2Byte(url, headers);
        return response.getBody();
    }

    /**
     * 下载M3U8流
     */
    public Path download(String url, String savePath, String fileName) throws IOException {
        Path fullPath = Paths.get(savePath, fileName);
        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        List<M3u8Segment> segments = parseM3u8(url);
        
        try (FileOutputStream fos = new FileOutputStream(fullPath.toFile())) {
            for (M3u8Segment segment : segments) {
                if (!downloadedSegments.containsKey(segment.getUrl())) {
                    try {
                        byte[] bytes = downloadSegment(segment.getUrl());
                        fos.write(bytes);
                        downloadedSegments.put(segment.getUrl(), true);

                        // 清理缓存
                        if (segmentCount.incrementAndGet() > MAX_SEGMENT_COUNT) {
                            downloadedSegments.clear();
                            segmentCount.set(0);
                        }

                        // 等待片段时长，避免过快下载
                        Thread.sleep((long) (segment.getDuration() * 1000));

                    } catch (IOException e) {
                        log.error("Error downloading segment: " + segment.getUrl(), e);
                        throw e;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new IOException("Download interrupted", e);
                    }
                }
            }
        }

        return fullPath;
    }
} 