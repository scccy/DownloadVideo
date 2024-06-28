package com.scccy.videoDownloader.pojo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FFMPEG {
    private final String path;
    private final boolean state;
    private final List<String> command;
    private final boolean shell;

    public FFMPEG(String path) {
        this.path = checkFFmpegPath(Paths.get(path));
        this.state = this.path != null;
        var systemType = checkSystemType();
        this.command = systemType.command;
        this.shell = systemType.shell;
    }

    private static SystemType checkSystemType() {
        String os = System.getProperty("os.name");
        if (os.contains("Mac")) {  // macOS
            return new SystemType(List.of("open", "-a", "Terminal"), false);
        } else if (os.contains("Windows")) {  // Windows
            return new SystemType(List.of("cmd", "/c", "start"), true);
        } else {  // Linux
            return new SystemType(List.of("x-terminal-emulator"), false);
        }
    }

    private String checkFFmpegPath(Path path) {
        return checkSystemFFmpeg() != null ? checkSystemFFmpeg() : checkSystemFFmpeg(path);
    }

    public void download(List<Pair<String, String>> data, String proxies, int timeout, String userAgent) {
        for (var entry : data) {
            String url = entry.getFirst();
            String file = entry.getSecond();
            String command = generateCommand(url, file, proxies, timeout, userAgent);
            try {
                new ProcessBuilder(command.split(" ")).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateCommand(String url, String file, String proxies, int timeout, String userAgent) {
        List<String> cmd = new ArrayList<>(command);
        cmd.addAll(List.of(
                this.path,
                "-hide_banner",
                "-log_level", "error",
                "-protocol_whitelist", "rtmp,crypto,file,http,https,tcp,tls,udp,rtp",
                "-fflags", "+discardcorrupt",
                "-timeout", String.valueOf(timeout * 1000 * 1000),
                "-user_agent", "\"" + userAgent + "\"",
                "-i", "\"" + url + "\"",
                "-bufsize", "5120k",
                "-map", "0",
                "-c:v", "copy",
                "-c:a", "copy",
                "-sn", "-dn",
                "-reconnect_delay_max", "15",
                "-reconnect_streamed", "-reconnect_at_eof",
                "-max_muxing_queue_size", "64",
                "-correct_ts_overflow", "1"
        ));
        if (proxies != null && !proxies.isBlank()) {
            cmd.add(cmd.size() - 1, "-http_proxy");
            cmd.add(cmd.size() - 1, proxies);
        }
        cmd.add("\"" + file + "\"");
        return String.join(" ", cmd);
    }

    private static String checkSystemFFmpeg() {
        return checkSystemFFmpeg(null);
    }

    private static String checkSystemFFmpeg(Path path) {
        String ffmpeg = path != null ? path.toString() : "ffmpeg";
        String[] commands = System.getProperty("os.name").contains("Windows") ? new String[]{"where", ffmpeg} : new String[]{"which", ffmpeg};
        try {
            Process process = new ProcessBuilder(commands).start();
            process.waitFor();
            if (process.exitValue() == 0) {
                return ffmpeg;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class SystemType {
        final List<String> command;
        final boolean shell;

        SystemType(List<String> command, boolean shell) {
            this.command = command;
            this.shell = shell;
        }
    }

    public static class Pair<K, V> {
        private final K first;
        private final V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        public K getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }
}
