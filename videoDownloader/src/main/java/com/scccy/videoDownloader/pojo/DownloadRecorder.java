package com.scccy.videoDownloader.pojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class DownloadRecorder {
    private static final String ENCODE = System.getProperty("os.name").contains("Windows") ? "UTF-8-SIG" : "UTF-8";
    private static final Pattern WORKS_ID = Pattern.compile("\\d{19}");
    private final boolean switchState;
    private final boolean state;
    private final Path backup;
    private final Path path;
    private BufferedWriter file;
    private final ColorfulConsole console;
    private final Set<String> record;

    public DownloadRecorder(boolean switchState, Path folder, boolean state, ColorfulConsole console) {
        this.switchState = switchState;
        this.state = state;
        this.backup = folder.resolve("IDRecorder_backup.txt");
        this.path = folder.resolve("IDRecorder.txt");
        this.console = console;
        this.record = getSet();
    }

    private Set<String> getSet() {
        return switchState ? readFile() : new HashSet<>();
    }

    private Set<String> readFile() {
        Set<String> blacklist = new HashSet<>();
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    blacklist.add(line.trim());
                }
                blacklist = restoreData(blacklist);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blacklist;
    }

    private void saveFile(BufferedWriter file) throws IOException {
        for (String id : record) {
            file.write(id);
            file.newLine();
        }
    }

    public void updateId(String id) {
        if (switchState) {
            record.add(id);
        }
    }

    private List<String> extractIds(String ids) {
        return WORKS_ID.matcher(ids).results()
                .map(matchResult -> matchResult.group())
                .toList();
    }

    public void deleteIds(String ids) {
        if (ids.equalsIgnoreCase("ALL")) {
            record.clear();
        } else {
            List<String> extractedIds = extractIds(ids);
            extractedIds.forEach(record::remove);
        }
    }

    public void backupFile() {
        if (file != null && !record.isEmpty()) {
            try (BufferedWriter backupWriter = Files.newBufferedWriter(backup, StandardCharsets.UTF_8)) {
                saveFile(backupWriter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (file != null) {
            try {
                saveFile(file);
                file.close();
                file = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Set<String> restoreData(Set<String> ids) {
        if (state) {
            return ids;
        }
        console.print(String.format(
                "程序检测到上次运行可能没有正常结束，您的作品下载记录数据可能已经丢失！\n数据文件路径：%s",
                path.toAbsolutePath()), ColorfulConsole.ERROR);
        if (Files.exists(backup)) {
            if (console.input("检测到 IDRecorder 备份文件，是否恢复最后一次备份的数据(YES/NO): ", ColorfulConsole.WARNING).equalsIgnoreCase("YES")) {
                try {
                    Files.writeString(path, Files.readString(backup, StandardCharsets.UTF_8), StandardCharsets.UTF_8);
                    console.print("IDRecorder 已恢复最后一次备份的数据，请重新运行程序！", ColorfulConsole.INFO);
                    return new HashSet<>(Files.readAllLines(backup, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                console.print("IDRecorder 数据未恢复，下载任意作品之后，备份数据会被覆盖导致无法恢复！", ColorfulConsole.ERROR);
            }
        } else {
            console.print("未检测到 IDRecorder 备份文件，您的作品下载记录数据无法恢复！", ColorfulConsole.ERROR);
        }
        return new HashSet<>();
    }
}