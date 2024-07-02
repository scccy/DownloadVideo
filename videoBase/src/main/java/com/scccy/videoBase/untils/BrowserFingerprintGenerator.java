package com.scccy.videoBase.untils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * BrowserFingerprintGenerator 用于生成模拟的浏览器指纹信息，用于在不同浏览器环境中进行测试和数据采集。
 */
public class BrowserFingerprintGenerator {
    private static final Map<String, Supplier<String>> BROWSER_MAP = new HashMap<>();

    static {
        BROWSER_MAP.put("Chrome", BrowserFingerprintGenerator::generateChromeFingerprint);
        BROWSER_MAP.put("Firefox", BrowserFingerprintGenerator::generateFirefoxFingerprint);
        BROWSER_MAP.put("Safari", BrowserFingerprintGenerator::generateSafariFingerprint);
        BROWSER_MAP.put("Edge", BrowserFingerprintGenerator::generateEdgeFingerprint);
    }

    /**
     * 根据指定的浏览器类型生成浏览器指纹。
     *
     * @param browserType 浏览器类型
     * @return 生成的浏览器指纹字符串
     */
    public static String init(String browserType) {
        return BROWSER_MAP.getOrDefault(browserType, BrowserFingerprintGenerator::generateChromeFingerprint).get();
    }

    private static String generateChromeFingerprint() {
        return generateFingerprint("Win32");
    }

    private static String generateFirefoxFingerprint() {
        return generateFingerprint("Win32");
    }

    private static String generateSafariFingerprint() {
        return generateFingerprint("MacIntel");
    }

    private static String generateEdgeFingerprint() {
        return generateFingerprint("Win32");
    }

    /**
     * 根据给定的平台生成浏览器指纹字符串。
     *
     * @param platform 操作系统平台
     * @return 生成的浏览器指纹字符串
     */
    private static String generateFingerprint(String platform) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int innerWidth = random.nextInt(1024, 1921);
        int innerHeight = random.nextInt(768, 1081);
        int outerWidth = innerWidth + random.nextInt(24, 33);
        int outerHeight = innerHeight + random.nextInt(75, 91);
        int screenX = 0;
        int screenY = random.nextBoolean() ? 0 : 30;
        int availWidth = random.nextInt(1280, 1921);
        int availHeight = random.nextInt(800, 1081);

        return String.format("%d|%d|%d|%d|%d|%d|0|0|%d|%d|%d|%d|%d|%d|24|24|%s",
                innerWidth, innerHeight, outerWidth, outerHeight,
                screenX, screenY, availWidth, availHeight, availWidth, availHeight,
                innerWidth, innerHeight, platform);
    }

    public static void main(String[] args) {
        // 示例用法
        String chromeFingerprint = BrowserFingerprintGenerator.generateFingerprint("Chrome");
        System.out.println(chromeFingerprint);
    }
}
