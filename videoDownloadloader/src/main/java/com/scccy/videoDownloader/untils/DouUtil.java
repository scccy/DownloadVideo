//package com.scccy.videoDownloader.untils;
//
//import com.alibaba.fastjson2.JSONArray;
//import com.alibaba.fastjson2.JSONObject;
//import com.scccy.videoDownloader.common.Global;
//import lombok.extern.slf4j.Slf4j;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//@Slf4j
//public class DouUtil {
//
//    // 备用
//    private static final String ttwid = "https://ttwid.bytedance.com/ttwid/union/register/";  // ttwid申请
//    public static final String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
//    public static final String odin_tt = "324fb4ea4a89c0c05827e18a1ed9cf9bf8a17f7705fcc793fec935b637867e2a5a9b8168c885554d029919117a18ba69";
//    public static final String passport_csrf_token = "2f142a9bb5db1f81f249d6fc997fe4a1";
//    public static final String referer = "https://www.douyin.com/";
//
//    private static final OkHttpClient client = new OkHttpClient();
//
//    public static Map<String, String> downVideo(String url) {
//        Document document = null;
//        Map<String, String> data = new HashMap<>();
//        try {
//            document = Jsoup.connect(url).userAgent(ua).get();
//            String baseUri = document.baseUri();
//            String pattern_v1 = "(?<=/video/).*?(?=/)";
//            String pattern_v2 = "/video/(\\d+)";
//            Pattern compile_v1 = Pattern.compile(pattern_v1);
//            Pattern compile_v2 = Pattern.compile(pattern_v2);
//            Matcher matcher_v1 = compile_v1.matcher(baseUri);
//            Matcher matcher_v2 = compile_v2.matcher(baseUri);
//            if (matcher_v1.find()) {
//                String vedioId = matcher_v1.group(0);
//                log.info("DouYin vedioId=" + vedioId);
//                data = DouUtil.getBogus(vedioId, "local");
//                if (data != null) {
//                    log.info("接口解析数据" + data);
//                    return data;
//                } else {
//                    return null;
//                }
//            }
//            if (matcher_v2.find()) {
//                String vedioId = matcher_v2.group(0).replaceAll("video", "").replaceAll("/", "");
//                log.info("DouYin vedioId=" + vedioId);
//                data = DouUtil.getBogus(vedioId, "local");
//                if (data != null) {
//                    log.info("接口解析数据" + data);
//                    return data;
//                } else {
//                    return null;
//                }
//            }
//            if (!matcher_v2.find() && !matcher_v1.find()) {
//                return null;
//            }
//
//        } catch (IOException e1) {
//            log.info("解析异常" + e1.getMessage());
//            return null;
//        }
//        return null;
//    }
//
//    public static Map<String, String> getBogus(String aweme_id, String type) throws IOException {
//        Map<String, String> res = new HashMap<>();
//        if (null != Global.TIKTOK_COOKIE && !"".equals(Global.TIKTOK_COOKIE)) {
//            String cookie = simplifycookie(Global.TIKTOK_COOKIE.toString());
//            Map<String, String> generatetoken = generatetoken(aweme_id);
//            String httpget = DouUtil.httpget(generatetoken.get("url").trim(), cookie);
//            JSONObject data = JSONObject.parseObject(httpget);
//            JSONObject aweme_detail = data.getJSONObject("aweme_detail");
//            String coveruri = "";
//            JSONArray cover = aweme_detail.getJSONObject("video").getJSONObject("cover").getJSONArray("url_list");
//            if (cover.size() >= 2) {
//                coveruri = cover.getString(cover.size() - 1);
//            } else {
//                coveruri = cover.getString(0);
//            }
//            JSONArray jsonArray = aweme_detail.getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list");
//            String videoplay = "";
//            if (jsonArray.size() >= 2) {
//                videoplay = jsonArray.getString(jsonArray.size() - 1);
//            } else {
//                videoplay = jsonArray.getString(0);
//            }
//            String desc = aweme_detail.getString("desc");
//            res.put("awemeid", aweme_id);
//            res.put("videoplay", videoplay);
//            res.put("desc", desc);
//            res.put("cover", coveruri);
//            res.put("cookie", cookie.trim());
//            res.put("type", "api");
//            return res;
//        }
//        return null;
//    }
//
//    @Deprecated
//    public static Map<String, String> getBogusDiscard(String aweme_id, String type) throws IOException {
//        Map<String, String> res = new HashMap<>();
//        String url = "";
//        String cookie = "";
//        String code = "";
//        if (type.equals("local")) {
//            Map<String, String> generatetoken = generatetoken(aweme_id);
//            try {
//                if (generatetoken != null) {
//                    log.info("使用本地生成xBogus");
//                    code = "200";
//                    url = generatetoken.get("url");
//                    cookie = generatetoken.get("cookie");
//                } else {
//                    log.info("本地生成异常(空信息)--正在使用remote模式");
//                    return getBogus(aweme_id, "remote");
//                }
//            } catch (Exception e) {
//                log.info("本地生成异常--正在使用remote模式");
//                return getBogus(aweme_id, "remote");
//            }
//        } else {
//            log.info("使用远程生成xBogus");
//            JSONObject token = HttpUtil.doPostNew(Global.ANALYSIS_SERVER + "/spirit-token", DouUtil.generateparameters(aweme_id));
//            code = token.getString("code");
//            if (code.equals("200")) {
//                code = "200";
//                url = token.getJSONObject("data").getString("url");
//                cookie = token.getJSONObject("data").getString("cookie");
//            }
//        }
//        if (code.equals("200")) {
//            String httpget = DouUtil.httpget(url.trim(), cookie.trim());
//            JSONObject data = JSONObject.parseObject(httpget);
//            if (null == data) {
//                return getBogus(aweme_id, "remote");
//            }
//            JSONObject aweme_detail = data.getJSONObject("aweme_detail");
//            if (null == aweme_detail && type.equals("local")) {
//                return getBogus(aweme_id, "remote");
//            }
//            String coveruri = "";
//            JSONArray cover = aweme_detail.getJSONObject("video").getJSONObject("cover").getJSONArray("url_list");
//            if (cover.size() >= 2) {
//                coveruri = cover.getString(cover.size() - 1);
//            } else {
//                coveruri = cover.getString(0);
//            }
//            JSONArray jsonArray = aweme_detail.getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list");
//            String videoplay = "";
//            if (jsonArray.size() >= 2) {
//                videoplay = jsonArray.getString(jsonArray.size() - 1);
//            } else {
//                videoplay = jsonArray.getString(0);
//            }
//            String desc = aweme_detail.getString("desc");
//            res.put("awemeid", aweme_id);
//            res.put("videoplay", videoplay);
//            res.put("desc", desc);
//            res.put("cover", coveruri);
//            res.put("cookie", cookie.trim());
//            res.put("type", "api");
//            return res;
//        }
//        return null;
//    }
//
//    public static String httpget(String addr, String ck) throws IOException {
//        Request request = new Request.Builder()
//                .url(addr)
//                .addHeader("User-Agent", ua)
//                .addHeader("Referer", referer)
//                .addHeader("Cookie", ck)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//            return response.body().string();
//        }
//    }
//
//    public static JSONObject generateparameters(String aweme_id) {
//        JSONObject data = new JSONObject();
//        data.put("awemeid", aweme_id);
//        data.put("ua", ua);
//        return data;
//    }
//
//    public static boolean isJSONString(String str) {
//        try {
//            JSONObject.parseObject(str);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static Map<String, String> generatetoken(String aid) {
//        String url = "https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=#awemeid#&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333&X-Bogus=#bogus#";
//        Map<String, String> res = new HashMap<>();
//        String urlPath = "aweme_id=" + aid + "&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333";
//        String replace = url.replace("#awemeid#", aid).replace("#bogus#", getXbogus(urlPath));
//        res.put("url", replace);
//        res.put("cookie", simplifycookie(Global.TIKTOK_COOKIE.toString()));
//        return res;
//    }
//
//    public static String simplifycookie(String source) {
//        StringBuilder res = new StringBuilder();
//        String[] arr = source.split(";");
//        for (String s : arr) {
//            if (s.contains("ttwid") || s.contains("passport_csrf_token") || s.contains("MONITOR_WEB_ID") || s.contains("odin_tt")) {
//                res.append(s).append(";");
//            }
//        }
//        return res.toString();
//    }
//
//    public static String getXbogus(String param) {
//        String result = null;
//        try {
//            String paramArr = param;
//            int r1 = 5381;
//            for (int i = 0; i < paramArr.length(); i++) {
//                r1 += (r1 << 5) + paramArr.charAt(i);
//            }
//            Random random = new Random();
//            int r2 = 100000 + random.nextInt(900000);
//            result = "4N8N" + String.valueOf((r1 & 2147483647) * (r2 & 2147483647) % 1_000_000_000);
//        } catch (Exception e) {
//            log.error("生成x-bogus出错，错误信息：" + e.getMessage());
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        Map<String, String> stringStringMap = DouUtil.downVideo("https://www.douyin.com/video/7249930146060121358");
//        System.out.println(stringStringMap);
//    }
//}
