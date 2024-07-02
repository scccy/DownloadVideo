package com.scccy.videoBase.untils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Untils {

    private static final Random random = new SecureRandom();
    private static final String BASE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+-";
    private static final Pattern URL_PATTERN = Pattern.compile("https?://\\S+");

    // 生成指定长度的随机字符串
    public static String genRandomStr(int randomLength) {
        StringBuilder sb = new StringBuilder(randomLength);
        for (int i = 0; i < randomLength; i++) {
            int randomIndex = random.nextInt(BASE_STRING.length());
            sb.append(BASE_STRING.charAt(randomIndex));
        }
        return sb.toString();
    }

    // 根据单位获取当前时间戳（毫秒、秒、分钟）
    public static long getTimestamp(String unit) {
        Instant now = Instant.now();
        switch (unit) {
            case "milli":
                return now.toEpochMilli();
            case "sec":
                return now.getEpochSecond();
            case "min":
                return now.truncatedTo(ChronoUnit.MINUTES).getEpochSecond() / 60;
            default:
                throw new IllegalArgumentException("不支持的时间单位");
        }
    }

    // 将时间戳转换为格式化字符串
    public static String timestampToStr(long timestamp, String format) {
        Instant instant = Instant.ofEpochSecond(timestamp);
        return instant.toString();  // 根据'format'参数实现格式化
    }

    // 将数字转换为36进制字符串
    public static String numToBase36(long num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            long remainder = num % 36;
            sb.append(BASE_STRING.charAt((int) remainder));
            num = num / 36;
        }
        return sb.reverse().toString();
    }

    // 分割Set-Cookie字符串并拼接
    public static String splitSetCookie(String cookieStr) {
        Pattern pattern = Pattern.compile("(?<=,)(?=[a-zA-Z])");
        String[] cookies = cookieStr.split(pattern.pattern());
        return String.join(";", cookies);
    }

    // 确保路径为Path对象
    public static Path ensurePath(String filePath) {
        return Paths.get(filePath);
    }

    // 替换文本中的非法字符
    public static String replaceT(String obj) {
        return obj.replaceAll("[^\\u4e00-\\u9fa5a-zA-Z0-9#]", "_");
    }

    // 根据操作系统限制分割文件名
    public static String splitFilename(String text, int filenameLengthLimit) {
        return text.substring(0, Math.min(text.length(), filenameLengthLimit));
    }

    // 根据浏览器选择和域名获取Cookie
    public static String getCookieFromBrowser(String browserChoice, String domain) {
        // 根据浏览器选择和域名实现获取Cookie
        return "cookie_value";
    }

    // 检查命名是否符合命名模板
    public static boolean checkInvalidNaming(String naming, String[] allowedPatterns, String[] allowedSeparators) {
        // 根据命名模板实现检查
        return true;
    }

    // 合并配置参数
    public static String mergeConfig(String mainConf, String customConf, String[] kwargs) {
        // 根据合并配置实现
        return "merged_config";
    }

    // 解码JSON文本


    // 模型到端点的转换
    public static String modelToEndpoint(String baseEndpoint, String params) {
        // 根据模型到端点转换实现
        return "endpoint_url";
    }
    /**
     * 从输入中提取有效的URL (Extract valid URLs from input)
     *
     * @param `inputs` 输入的字符串或字符串列表 (Input string or list of strings)
     * @return 提取出的有效URL或URL列表 (Extracted valid URL or list of URLs)
     */
    // 重载方法，用于处理单个字符串输入
    public static String extractValidUrl(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        Matcher matcher = URL_PATTERN.matcher(input);
        return matcher.find() ? matcher.group() : null;
    }

    // 重载方法，用于处理字符串列表输入
    public static List<String> extractValidUrls(List<String> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> validUrls = new ArrayList<>();
        for (String input : inputs) {
            Matcher matcher = URL_PATTERN.matcher(input);
            while (matcher.find()) {
                validUrls.add(matcher.group());
            }
        }
        return validUrls;
    }
    // 测试方法
    public static void main(String[] args) {
        // 示例使用Utils方法
        String randomStr = genRandomStr(10);
        System.out.println("随机字符串: " + randomStr);

        long timestampMillis = getTimestamp("milli");
        System.out.println("时间戳（毫秒）: " + timestampMillis);

        String base36Num = numToBase36(12345);
        System.out.println("36进制数字: " + base36Num);

        String cookie = splitSetCookie("cookie1, cookie2, cookie3");
        System.out.println("分割Cookie: " + cookie);

        Path path = ensurePath("/path/to/file.txt");
        System.out.println("路径: " + path.toString());

        String replacedText = replaceT("abc123!@#");
        System.out.println("替换后的文本: " + replacedText);

        String splitFilename = splitFilename("filename.txt", 10);
        System.out.println("分割文件名: " + splitFilename);

        String cookieFromBrowser = getCookieFromBrowser("chrome", "example.com");
        System.out.println("浏览器Cookie: " + cookieFromBrowser);

        boolean isValidNaming = checkInvalidNaming("naming123", new String[]{"pattern1", "pattern2"}, new String[]{"-", "_"});
        System.out.println("命名是否有效: " + isValidNaming);

        String mergedConfig = mergeConfig("mainConf", "customConf", new String[]{"arg1", "arg2"});
        System.out.println("合并配置: " + mergedConfig);

        String endpoint = modelToEndpoint("https://example.com/api", "param1=value1&param2=value2");
        System.out.println("端点URL: " + endpoint);
    }
}