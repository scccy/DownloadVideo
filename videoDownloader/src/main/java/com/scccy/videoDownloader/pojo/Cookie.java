package com.scccy.videoDownloader.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cookie {
    private static final Pattern pattern = Pattern.compile("(?<key>[^=;,]+)=(?<value>[^;,]+)");
    private static final Map<String, String> cookieKeys = new HashMap<>() {{
        put("passport_csrf_token", null);
        put("passport_csrf_token_default", null);
        put("my_rd", null);
        put("passport_auth_status", null);
        put("passport_auth_status_ss", null);
        put("d_ticket", null);
        put("publish_badge_show_info", null);
        put("volume_info", null);
        put("__live_version__", null);
        put("download_guide", null);
        put("EnhanceDownloadGuide", null);
        put("pwa2", null);
        put("live_can_add_dy_2_desktop", null);
        put("live_use_vvc", null);
        put("store-region", null);
        put("store-region-src", null);
        put("strategyABtestKey", null);
        put("FORCE_LOGIN", null);
        put("LOGIN_STATUS", null);
        put("__security_server_data_status", null);
        put("_bd_ticket_crypt_doamin", null);
        put("n_mh", null);
        put("passport_assist_user", null);
        put("sid_ucp_sso_v1", null);
        put("ssid_ucp_sso_v1", null);
        put("sso_uid_tt", null);
        put("sso_uid_tt_ss", null);
        put("toutiao_sso_user", null);
        put("toutiao_sso_user_ss", null);
        put("sessionid", null);
        put("sessionid_ss", null);
        put("sid_guard", null);
        put("sid_tt", null);
        put("sid_ucp_v1", null);
        put("ssid_ucp_v1", null);
        put("uid_tt", null);
        put("uid_tt_ss", null);
        put("FOLLOW_NUMBER_YELLOW_POINT_INFO", null);
        put("vdg_s", null);
        put("_bd_ticket_crypt_cookie", null);
        put("FOLLOW_LIVE_POINT_INFO", null);
        put("bd_ticket_guard_client_data", null);
        put("bd_ticket_guard_client_web_domain", null);
        put("home_can_add_dy_2_desktop", null);
        put("odin_tt", null);
        put("stream_recommend_feed_params", null);
        put("IsDouyinActive", null);
        put("stream_player_status_params", null);
        put("s_v_web_id", null);
        put("__ac_nonce", null);
        put("dy_sheight", null);
        put("dy_swidth", null);
        put("ttcid", null);
        put("xgplayer_user_id", null);
        put("__ac_signature", null);
        put("tt_scid", null);
    }};

    public final CookiesSettings cookiesSettings;
    public final ColorfulConsole console;

    public Cookie(CookiesSettings cookiesSettings, ColorfulConsole console) {
        this.cookiesSettings = cookiesSettings;
        this.console = console;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请粘贴 Cookie 内容: ");
        String cookie = scanner.nextLine();
        if (cookie.isEmpty()) {
            return;
        }
        extract(cookie);
    }

    public void extract(String cookie) {
        extract(cookie, true, false);
    }

    public void extract(String cookie, boolean clean, boolean return_) {
        Map<String, String> keys = new HashMap<>(cookieKeys);
        if (clean) {
            Matcher matcher = pattern.matcher(cookie);
            while (matcher.find()) {
                String key = matcher.group("key").trim();
                String value = matcher.group("value").trim();
                if (keys.containsKey(key)) {
                    keys.put(key, value);
                }
            }
            checkKey(keys);
        } else {
            keys.putAll(parseCookieString(cookie));
        }
        if (return_) {
            return;
        }
        write(keys);
        console.print("写入 Cookie 成功！");
    }

    private Map<String, String> parseCookieString(String cookie) {
        Map<String, String> keys = new HashMap<>();
        Matcher matcher = pattern.matcher(cookie);
        while (matcher.find()) {
            keys.put(matcher.group("key").trim(), matcher.group("value").trim());
        }
        return keys;
    }

    private void checkKey(Map<String, String> items) {
        if (items.get("sessionid_ss") == null) {
            console.print("当前 Cookie 未登录");
        } else {
            console.print("当前 Cookie 已登录");
        }
        items.entrySet().removeIf(entry -> entry.getValue() == null);
    }

    public void write(Map<String, String> text) {
        Map<String, Object> data = cookiesSettings.read();
        data.put("cookie", text);
        cookiesSettings.update(data);
    }

    public static void main(String[] args) {
        Path root = Paths.get("."); // 根据需要设置根路径
        ColorfulConsole console = new ColorfulConsole();
        CookiesSettings cookiesSettings = new CookiesSettings(root, console);
        Cookie cookie = new Cookie(cookiesSettings, console);
        cookie.run();
    }
}


class CookiesSettings {
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

    public CookiesSettings(Path root, ColorfulConsole console) {
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
        CookiesSettings cookiesSettings = new CookiesSettings(root, console);
        System.out.println(cookiesSettings.read());
        cookiesSettings.update(new HashMap<>(DEFAULT));
    }
}
