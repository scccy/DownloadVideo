package com.scccy.videoDownloader.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Settings {
    private static final String ENCODE = System.getProperty("os.name").contains("Windows") ? "UTF-8-SIG" : "UTF-8";
    private static final Map<String, Object> DEFAULT = new HashMap<>() {{
        put("accounts_urls", new Object[]{
                new HashMap<>() {{
                    put("mark", "账号标识，可以设置为空字符串");
                    put("url", "账号主页链接");
                    put("tab", "账号主页类型");
                    put("earliest", "作品最早发布日期");
                    put("latest", "作品最晚发布日期");
                }}
        });
        put("mix_urls", new Object[]{
                new HashMap<>() {{
                    put("mark", "合集标识，可以设置为空字符串");
                    put("url", "合集链接或者作品链接");
                }}
        });
        put("owner_url", new HashMap<>() {{
            put("mark", "账号标识，可以设置为空字符串");
            put("url", "账号主页链接");
        }});
        put("root", "");
        put("folder_name", "Download");
        put("name_format", "create_time type nickname desc");
        put("date_format", "%Y-%m-%d %H:%M:%S");
        put("split", "-");
        put("folder_mode", false);
        put("music", false);
        put("storage_format", "");
        put("cookie", "");
        put("dynamic_cover", false);
        put("original_cover", false);
        put("proxies", "");
        put("download", true);
        put("max_size", 0);
        put("chunk", 1024 * 1024);  // 每次从服务器接收的数据块大小
        put("max_retry", 5);  // 重试最大次数
        put("max_pages", 0);
        put("default_mode", "");
        put("ffmpeg", "");
    }};

    private final Path file;
    private final ColorfulConsole console;
    private final ObjectMapper mapper;

    public Settings(Path root, ColorfulConsole console) {
        this.file = root.resolve("settings.json");
        this.console = console;
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    }

    private Map<String, Object> create() throws IOException {
        Files.writeString(file, mapper.writeValueAsString(DEFAULT), StandardCharsets.UTF_8);
        console.print("创建默认配置文件 settings.json 成功！\n请参考项目文档的快速入门部分，设置 Cookie 后重新运行程序！\n建议根据实际使用需求修改配置文件 settings.json！\n");
        return DEFAULT;
    }

    public Map<String, Object> read() {
        if (Files.exists(file)) {
            try {
                return check(mapper.readValue(Files.readString(file), Map.class));
            } catch (JsonMappingException e) {
                console.print("配置文件 settings.json 格式错误，请检查 JSON 格式！", ColorfulConsole.ERROR);
            } catch (IOException e) {
                console.print("读取配置文件时出错：" + e.getMessage(), ColorfulConsole.ERROR);
            }
        }
        try {
            return create(); // 生成的默认配置文件必须设置 cookie 才可以正常运行
        } catch (IOException e) {
            console.print("生成默认配置文件时出错：" + e.getMessage(), ColorfulConsole.ERROR);
            return DEFAULT;
        }
    }

    private Map<String, Object> check(Map<String, Object> data) {
        if (DEFAULT.keySet().stream().allMatch(data::containsKey)) {
            return data;
        }
        Scanner scanner = new Scanner(System.in);
        console.print("配置文件 settings.json 缺少必要的参数，是否需要生成默认配置文件(YES/NO): ", ColorfulConsole.ERROR);
        if (scanner.nextLine().trim().equalsIgnoreCase("YES")) {
            try {
                return create();
            } catch (IOException e) {
                console.print("生成默认配置文件时出错：" + e.getMessage(), ColorfulConsole.ERROR);
            }
        }
        console.print("本次运行将会使用各项参数默认值，程序功能可能无法正常使用！");
        return DEFAULT;
    }

    public void update(Map<String, Object> settings) {
        try {
            Files.writeString(file, mapper.writeValueAsString(settings), StandardCharsets.UTF_8);
            console.print("保存配置成功！", ColorfulConsole.INFO);
        } catch (JsonProcessingException e) {
            console.print("更新配置文件时 JSON 处理出错：" + e.getMessage(), ColorfulConsole.ERROR);
        } catch (IOException e) {
            console.print("更新配置文件时出错：" + e.getMessage(), ColorfulConsole.ERROR);
        }
    }

    public static void main(String[] args) {
        Path root = Paths.get("."); // 根据需要设置根路径
        ColorfulConsole console = new ColorfulConsole();
        Settings settings = new Settings(root, console);
        System.out.println(settings.read());
        settings.update(new HashMap<>(DEFAULT));
    }
}

