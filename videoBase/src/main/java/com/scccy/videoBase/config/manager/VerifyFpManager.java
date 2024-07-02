package com.scccy.videoBase.config.manager;

import java.time.Instant;
import java.util.Random;

/**
 *
 * @implementation
 * 生成verifyFp 与 s_v_web_id (Generate verifyFp)
 * 直播相关
 * @author scccy
 * @date 2024/7/1上午9:32

 */
public class VerifyFpManager {
    public static String genVerifyFp() {
        String baseStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int t = baseStr.length();
        long milliseconds = Instant.now().toEpochMilli();
        StringBuilder base36 = new StringBuilder();

        while (milliseconds > 0) {
            long remainder = milliseconds % 36;
            if (remainder < 10) {
                base36.insert(0, remainder);
            } else {
                base36.insert(0, (char) ('a' + remainder - 10));
            }
            milliseconds /= 36;
        }

        String r = base36.toString();
        char[] o = new char[36];
        for (int i = 0; i < 36; i++) {
            o[i] = '\0';
        }
        o[8] = '_';
        o[13] = '_';
        o[18] = '_';
        o[23] = '_';
        o[14] = '4';

        Random random = new Random();
        for (int i = 0; i < 36; i++) {
            if (o[i] == '\0') {
                int n = random.nextInt(t);
                if (i == 19) {
                    n = (3 & n) | 8;
                }
                o[i] = baseStr.charAt(n);
            }
        }

        return "verify_" + r + "_" + new String(o);
    }

    public static void main(String[] args) {
        System.out.println(genVerifyFp());
    }
}
