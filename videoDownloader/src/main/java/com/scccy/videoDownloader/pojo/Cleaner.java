package com.scccy.videoDownloader.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cleaner {
    private Map<String, String> rule;

    public Cleaner() {
        // 默认非法字符字典
        this.rule = defaultRule();
    }

    private Map<String, String> defaultRule() {
        String os = System.getProperty("os.name");
        Map<String, String> rule = new HashMap<>();

        if (os.contains("Windows") || os.contains("Mac")) {
            // Windows 系统和 Mac 系统
            rule.put("/", "");
            rule.put("\\", "");
            rule.put("|", "");
            rule.put("<", "");
            rule.put(">", "");
            rule.put("\"", "");
            rule.put("?", "");
            rule.put(":", "");
            rule.put("*", "");
            rule.put("\0", "");
        } else if (os.contains("Linux")) {
            // Linux 系统
            rule.put("/", "");
            rule.put("\0", "");
        } else {
            System.out.println("不受支持的操作系统类型，可能无法正常去除非法字符！");
        }

        // 补充换行符等非法字符
        String whitespace = " \t\n\r\f";
        for (int i = 1; i < whitespace.length(); i++) {
            rule.put(String.valueOf(whitespace.charAt(i)), "");
        }

        return rule;
    }

    public void setRule(Map<String, String> rule, boolean update) {
        if (update) {
            this.rule.putAll(rule);
        } else {
            this.rule = rule;
        }
    }

    public String filter(String text) {
        for (Map.Entry<String, String> entry : rule.entrySet()) {
            text = text.replace(entry.getKey(), entry.getValue());
        }
        return text;
    }

    public String filterName(String text, boolean inquire, String defaultText) {
        text = text.replace(":", ".");

        text = filter(text);

        text = replaceEmoji(text);

        text = text.strip().stripTrailing();

        if (inquire) {
            return text.isEmpty() ? filterName(illegalNickname(), false, defaultText) : text;
        } else {
            return text.isEmpty() ? defaultText : text;
        }
    }

    public static String clearSpaces(String string) {
        return String.join(" ", string.split("\\s+"));
    }

    private String replaceEmoji(String text) {
        // 需要实现替换emoji的逻辑，这里是占位
        return text.replaceAll("[^\\p{ASCII}]", "");
    }

    private String illegalNickname() {
        // 需要实现生成非法昵称的逻辑，这里是占位
        return "defaultNickname";
    }

    public static void main(String[] args) {
        Cleaner demo = new Cleaner();
        System.out.println(demo.rule);
        System.out.println(demo.filterName("", true, ""));
    }
}
