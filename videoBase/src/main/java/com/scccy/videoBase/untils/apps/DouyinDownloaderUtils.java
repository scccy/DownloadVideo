package com.scccy.videoBase.untils.apps;

import io.lindstrom.m3u8.model.MediaPlaylist;
import io.lindstrom.m3u8.parser.MediaPlaylistParser;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.Proxy;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class DouyinDownloaderUtils {
    private static final OkHttpClient client = new OkHttpClient();

    public static CompletableFuture<Integer> getContentLength(String url, Headers headers, Proxy proxy) {
        return CompletableFuture.supplyAsync(() -> {
            Request request = new Request.Builder()
                    .url(url)
                    .head()
                    .headers(headers)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.isSuccessful()) {
                    String contentLengthHeader = response.header("Content-Length");
                    if (contentLengthHeader != null) {
                        return Integer.parseInt(contentLengthHeader);
                    }
                }
                return 0;
            } catch (IOException e) {
                log.error("Failed to fetch Content-Length for URL: {}", url, e);
                return 0;
            }
        });
    }

    public static String trimFilename(String filename, int maxLength) {
        Path path = Paths.get(filename);
        String fullPath = path.toString();
        int prefixSuffixLen = maxLength / 2 - 2;

        return fullPath.length() > maxLength
                ? fullPath.substring(0, prefixSuffixLen) + "..." + fullPath.substring(fullPath.length() - prefixSuffixLen)
                : fullPath;
    }

    public static int getChunkSize(long fileSize) {
        if (fileSize < 10 * 1024) {
            return (int) fileSize;
        } else if (fileSize < 1024 * 1024) {
            return (int) (fileSize / 10);
        } else if (fileSize < 10 * 1024 * 1024) {
            return (int) (fileSize / 20);
        } else if (fileSize < 100 * 1024 * 1024) {
            return (int) (fileSize / 50);
        } else {
            return 1024 * 1024;
        }
    }

    public static CompletableFuture<List<String>> getSegmentsFromM3u8(String url) {
        return CompletableFuture.supplyAsync(() -> {
            MediaPlaylistParser parser = new MediaPlaylistParser();
            List<String> segments = new ArrayList<>();

            try {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    if (response.isSuccessful() && response.body() != null) {
                        String responseBody = response.body().string();
                        MediaPlaylist playlist = parser.readPlaylist(responseBody);

                        playlist.mediaSegments().forEach(segment -> {
                            segments.add(segment.uri());
                        });
                    }
                }
            } catch (IOException e) {
                log.error("Failed to fetch or parse m3u8 segments for URL: {}", url, e);
            }
            return segments;
        });
    }

    public static CompletableFuture<List<Double>> getSegmentsDuration(String url) {
        return getSegmentsFromM3u8(url).thenApply(segments -> {
            List<Double> durations = new ArrayList<>();
            MediaPlaylistParser parser = new MediaPlaylistParser();

            for (String segment : segments) {
                try {
                    Request request = new Request.Builder()
                            .url(segment)
                            .build();

                    try (Response response = client.newCall(request).execute()) {
                        if (response.isSuccessful() && response.body() != null) {
                            String responseBody = response.body().string();
                            MediaPlaylist playlist = parser.readPlaylist(responseBody);

                            playlist.mediaSegments().forEach(mediaSegment -> {
                                durations.add(mediaSegment.duration());
                            });
                        }
                    }
                } catch (IOException e) {
                    log.error("Failed to parse segment duration for URL: {}", segment, e);
                }
            }
            return durations;
        });
    }
}
