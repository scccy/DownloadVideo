package com.scccy.videoBase.untils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StringProcessor {
    /**
     * 将字符串转换为 ASCII 码字符串 (Convert a string to an ASCII code string).
     *
     * @param s 输入字符串 (Input string).
     * @return 转换后的 ASCII 码字符串 (Converted ASCII code string).
     */
    public static String toOrdStr(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            result.append((int) c);
        }
        return result.toString();
    }

    /**
     * 将字符串转换为 ASCII 码列表 (Convert a string to a list of ASCII codes).
     *
     * @param s 输入字符串 (Input string).
     * @return 转换后的 ASCII 码列表 (Converted list of ASCII codes).
     */
    public static List<Integer> toOrdArray(String s) {
        List<Integer> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add((int) c);
        }
        return result;
    }

    /**
     * 将 ASCII 码列表转换为字符串 (Convert a list of ASCII codes to a string).
     *
     * @param asciiList ASCII 码列表 (List of ASCII codes).
     * @return 转换后的字符串 (Converted string).
     */
    public static String toCharStr(List<Integer> asciiList) {
        StringBuilder result = new StringBuilder();
        for (int ascii : asciiList) {
            result.append((char) ascii);
        }
        return result.toString();
    }

    /**
     * 将字符串转换为 ASCII 码列表 (Convert a string to a list of ASCII codes).
     *
     * @param s 输入字符串 (Input string).
     * @return 转换后的 ASCII 码列表 (Converted list of ASCII codes).
     */
    public static List<Integer> toCharArray(String s) {
        List<Integer> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add((int) c);
        }
        return result;
    }

    /**
     * 实现 JavaScript 中的无符号右移运算 (Implement the unsigned right shift operation in JavaScript).
     *
     * @param val 输入值 (Input value).
     * @param n   右移位数 (Number of bits to shift right).
     * @return 右移后的值 (Value after right shift).
     */
    public static int jsShiftRight(int val, int n) {
        return val >>> n;
    }

    /**
     * 生成一组伪随机字节字符串，用于混淆数据 (Generate a pseudo-random byte string to obfuscate the data).
     *
     * @param length 生成的字节序列长度 (Length of the byte sequence to generate).
     * @return 生成的伪随机字节字符串 (Generated pseudo-random byte string).
     */
    public static String generateRandomBytes(int length) {
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(generateByteSequence(random));
        }

        return result.toString();
    }

    private static String generateByteSequence(Random random) {
        int _rd = random.nextInt(10000);
        return "" +
                (char)(((_rd & 255) & 170) | 1) +
                (char)(((_rd & 255) & 85) | 2) +
                (char)((jsShiftRight(_rd, 8) & 170) | 5) +
                (char)((jsShiftRight(_rd, 8) & 85) | 40);
    }

    public static void main(String[] args) {
        // 使用示例
        String input = "Hello, World!";

        // 将字符串转换为 ASCII 码字符串
        String ordStr = StringProcessor.toOrdStr(input);
        System.out.println("Ord String: " + ordStr);

        // 将字符串转换为 ASCII 码列表
        List<Integer> ordArray = StringProcessor.toOrdArray(input);
        System.out.println("Ord Array: " + ordArray);

        // 将 ASCII 码列表转换为字符串
        String charStr = StringProcessor.toCharStr(ordArray);
        System.out.println("Char String: " + charStr);

        // 将字符串转换为 ASCII 码列表
        List<Integer> charArray = StringProcessor.toCharArray(input);
        System.out.println("Char Array: " + charArray);

        // 实现 JavaScript 中的无符号右移运算
        int shiftedVal = StringProcessor.jsShiftRight(10, 2);
        System.out.println("Shifted Value: " + shiftedVal);

        // 生成一组伪随机字节字符串
        String randomBytes = StringProcessor.generateRandomBytes(3);
        System.out.println("Random Bytes: " + randomBytes);
    }
}
