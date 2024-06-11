package com.scccy.videoDownloader.service;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.common.ResultData;
import com.scccy.videoDownloader.common.Global;
import com.scccy.videoDownloader.untils.DouUtil;
import com.scccy.videoDownloader.untils.OkHttpClientFactory;
import com.scccy.videoDownloader.untils.XbogusUtil;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Service
public class DouYinService {

    private static String domain = "https://www.douyin.com/";

    private static String loginQRDomain = "https://sso.douyin.com/get_qrcode/?";

    private static String loginCheckDomain = "https://sso.douyin.com/check_qrconnect/?";

    private static String fp ="";

    private static String cookie_ttwid ="";


    public ResultData getDouYinCodeLogin() throws IOException, NoSuchAlgorithmException {
        if (fp.equals("")) {
            fp = DouUtil.getFp();
        }
        if (cookie_ttwid.equals("")) {
            cookie_ttwid = "ttwid=" + DouUtil.getTtwid();
        }
        String xBogusString = "service=https%3A%2F%2Fwww.douyin.com&need_logo=false&need_short_url=true&device_platform=web_app&aid=6383&account_sdk_source=sso&sdk_version=2.2.5&language=zh&verifyFp=" + fp + "&fp=" + fp;
        String xBogus = xBogusString + "&X-Bogus=" + XbogusUtil.getXBogus(xBogusString);

        Request request = new Request.Builder()
                .url(loginQRDomain + xBogus)
                .addHeader("User-Agent", DouUtil.ua)
                .addHeader("Referer", DouUtil.referer)
                .addHeader("Cookie", cookie_ttwid)
                .build();

        try (Response response = OkHttpClientFactory.createClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return new ResultData(200, Global.AJAX_SUCCESS.getValue(), JSONObject.parseObject(responseData));
            } else {
                return new ResultData(500, Global.AJAX_LOGIN_ERR.getValue(), "二维码异常");
            }
        }
    }

    public ResultData checkLoginStatus(String token) throws IOException, NoSuchAlgorithmException {
        if (fp.equals("")) {
            fp = DouUtil.getFp();
        }
        if (cookie_ttwid.equals("")) {
            cookie_ttwid = "ttwid=" + DouUtil.getTtwid();
        }
        String xBogusString = "token=" + token + "&service=https%3A%2F%2Fwww.douyin.com&need_logo=false&need_short_url=true&device_platform=web_app&aid=6383&account_sdk_source=sso&sdk_version=2.2.5&language=zh&verifyFp=" + fp + "&fp=" + fp;
        String xBogus = xBogusString + "&X-Bogus=" + XbogusUtil.getXBogus(xBogusString);
        Request request = new Request.Builder()
                .url(loginCheckDomain + xBogus)
                .addHeader("User-Agent", DouUtil.ua)
                .addHeader("Referer", DouUtil.referer)
                .addHeader("Cookie", cookie_ttwid)
                .build();

        try (Response response = OkHttpClientFactory.createClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                JSONObject jsonObject = JSONObject.parseObject(responseData).getJSONObject("data");
                String status = jsonObject.getString("status");
                String redirect_url = jsonObject.getString("redirect_url");
                if (status.equals("3")) {
                    // 如果登录状态为3，需要进行重定向
                    String setCookieHeader = response.header("Set-Cookie");
                    Request redirectRequest = new Request.Builder()
                            .url(redirect_url)
                            .addHeader("User-Agent", DouUtil.ua)
                            .addHeader("Referer", DouUtil.referer)
                            .addHeader("Cookie", setCookieHeader)
                            .build();
                    try (Response redirectResponse = OkHttpClientFactory.createClient().newCall(redirectRequest).execute()) {
                        if (redirectResponse.isSuccessful()) {
                            // 执行重定向成功
                            // 这里可以根据需要处理重定向后的逻辑
                            // 比如解析重定向后的响应，更新用户状态等
                            // 这里暂时返回空，你可以根据实际情况修改
                            return null;
                        } else {
                            // 执行重定向失败
                            // 这里可以根据需要处理重定向失败的情况
                            // 比如记录日志，返回错误信息等
                            return new ResultData(Integer.parseInt(Global.AJAX_LOGIN_ERR.getValue()), "error", "重定向失败");
                        }
                    }
                } else {
                    // 其他状态，根据实际情况处理
                    fp = "";
                    cookie_ttwid = "";
                    return new ResultData(Integer.parseInt(Global.AJAX_LOGIN_ERR.getValue()), "error", "登录状态异常");
                }
            } else {
                // 请求失败
                fp = "";
                cookie_ttwid = "";
                return new ResultData(Integer.parseInt(Global.AJAX_URI_ERROR.getValue()), "error", "二维码异常");
            }
        }
    }
}