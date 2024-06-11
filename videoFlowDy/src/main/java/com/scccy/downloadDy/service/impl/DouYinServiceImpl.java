package com.scccy.downloadDy.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.common.ResultData;
import com.scccy.downloadDy.domain.BizVideo;
import com.scccy.downloadDy.domain.TiktokConfig;
import com.scccy.downloadDy.mapper.TiktokConfigMapper;
import com.scccy.downloadDy.service.BizVideoService;
import com.scccy.downloadDy.service.DouYinService;
import com.scccy.pojo.Global;
import com.scccy.videoDownloader.untils.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DouYinServiceImpl extends ServiceImpl<TiktokConfigMapper, TiktokConfig> implements DouYinService {

    @Value("${file.save}")
    private String savefile;

    @Value("${file.save.path}")
    private String uploadRealPath;

    @Autowired
    private BizVideoService bizVideoService;

    private static final String DOMAIN = "https://www.douyin.com/";
    private static final String LOGIN_QR_DOMAIN = "https://sso.douyin.com/get_qrcode/?";
    private static final String LOGIN_CHECK_DOMAIN = "https://sso.douyin.com/check_qrconnect/?";

    private static String fp = "";
    private static String cookieTtwid = "";

    public void putRecord(String awemeId, String desc, String playApi, String cover, String platform, String originalAddress, String type, String cookie) {
        String filename = StringUtil.getFileName(desc, awemeId);
        String videoFile = FileUtil.createDirFile(Global.DOWN_PATH, ".mp4", filename, Global.platform.douyin.name());
        String videoUnrealAddr = FileUtil.createDirFile(FileUtil.savefile, ".mp4", filename, Global.platform.douyin.name());
        String coverUnAddr = FileUtil.createDirFile(FileUtil.savefile, ".jpg", filename, Global.platform.douyin.name());

        String downloadUrl = type.equals("client") ? "https:" + playApi : playApi;
        String temporaryDir = FileUtil.createTemporaryDirectory(Global.platform.douyin.name(), filename, Global.DOWN_PATH);

        if (type.equals("client")) {
            log.info("已使用htmlunit进行解析,下载器类型为:" + Global.DOWN_TYPE);
            handleDownload(downloadUrl, filename, temporaryDir, cookie, cover);
        } else if (type.equals("api")) {
            log.info("已使用api进行解析,下载器类型为:" + Global.DOWN_TYPE);
            handleDownload(downloadUrl, filename, temporaryDir, cookie, cover);
        }

        videoFile = FileUtil.createDirFile(FileUtil.uploadRealPath, ".mp4", filename, Global.platform.douyin.name());
// todo:下载记录入库
        //        BizVideo videoDataEntity = new BizVideo(awemeId, platform, coverUnAddr, videoFile, videoUnrealAddr, originalAddress);
//        bizVideoService.save(videoDataEntity);
        log.info("下载流程结束");
    }

    private void handleDownload(String downloadUrl, String filename, String temporaryDir, String cookie, String cover) {
        if (Global.DOWN_TYPE.equals("a2")) {
            Aria2Util.sendMessage(Global.A2_LINK, Aria2Util.createDoParameter(downloadUrl, temporaryDir, filename + ".mp4", Global.A2_TOKEN, cookie));
        } else if (Global.DOWN_TYPE.equals("http")) {
            HttpUtil.downDouFromUrl(downloadUrl, filename + ".mp4", temporaryDir, cookie);
        }
        HttpUtil.downLoadFromUrl(cover, filename + ".jpg", temporaryDir + "/");
    }

    public String getUrl(String input) {
        String regex = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find() ? matcher.group() : "";
    }

    @Override
    public void saveSettings(TiktokConfig tikTokConfig) {
        this.saveOrUpdate(tikTokConfig);
        if (tikTokConfig.getCookies() != null && !tikTokConfig.getCookies().isEmpty()) {
            Global.tiktokCookie = tikTokConfig.getCookies();
        }
        if (tikTokConfig.getAnalysisserver() != null && !tikTokConfig.getAnalysisserver().isEmpty()) {
            Global.ANALY_SIS_SERVER = tikTokConfig.getAnalysisserver();
        }
    }

    @Override
    public void search(String url) {
        Map<String, String> downVideo = DouUtil.downVideo(url);
        this.putRecord(
                downVideo.get("awemeid"),
                downVideo.get("desc"),
                downVideo.get("videoplay"),
                downVideo.get("cover"),
                "douyin",
                url,
                downVideo.get("type"),
                downVideo.get("cookie")
        );
        System.gc();
    }

    public ResultData getDouYinCodeLogin() throws NoSuchAlgorithmException, IOException {
        initializeFpAndTtwid();
        String xBogusString = "service=https%3A%2F%2Fwww.douyin.com&need_logo=false&need_short_url=true&device_platform=web_app&aid=6383&account_sdk_source=sso&sdk_version=2.2.5&language=zh&verifyFp=" + fp + "&fp=" + fp;
        String xBogus = xBogusString + "&X-Bogus=" + XbogusUtil.getXBogus(xBogusString);

        Request request = new Request.Builder()
                .url(LOGIN_QR_DOMAIN + xBogus)
                .addHeader("User-Agent", DouUtil.ua)
                .addHeader("Referer", DouUtil.referer)
                .addHeader("Cookie", cookieTtwid)
                .build();

        return executeRequest(request, "二维码异常");
    }

    public ResultData checkLoginStatus(String token) throws IOException, NoSuchAlgorithmException {
        initializeFpAndTtwid();
        String xBogusString = "token=" + token + "&service=https%3A%2F%2Fwww.douyin.com&need_logo=false&need_short_url=true&device_platform=web_app&aid=6383&account_sdk_source=sso&sdk_version=2.2.5&language=zh&verifyFp=" + fp + "&fp=" + fp;
        String xBogus = xBogusString + "&X-Bogus=" + XbogusUtil.getXBogus(xBogusString);

        Request request = new Request.Builder()
                .url(LOGIN_CHECK_DOMAIN + xBogus)
                .addHeader("User-Agent", DouUtil.ua)
                .addHeader("Referer", DouUtil.referer)
                .addHeader("Cookie", cookieTtwid)
                .build();

        try (Response response = OkHttpClientFactory.createClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject jsonObject = JSONObject.parseObject(response.body().string()).getJSONObject("data");
                String status = jsonObject.getString("status");
                String redirectUrl = jsonObject.getString("redirect_url");
                if ("3".equals(status)) {
                    return handleRedirectLogin(redirectUrl, response.header("Set-Cookie"));
                } else {
                    resetFpAndTtwid();
                    return new ResultData(Integer.parseInt(Global.AJAX_LOGIN_ERR), "error", "登录状态异常");
                }
            } else {
                resetFpAndTtwid();
                return new ResultData(Integer.parseInt(Global.AJAX_URI_ERROR), "error", "二维码异常");
            }
        }
    }

    private ResultData handleRedirectLogin(String redirectUrl, String setCookieHeader) throws IOException {
        Request redirectRequest = new Request.Builder()
                .url(redirectUrl)
                .addHeader("User-Agent", DouUtil.ua)
                .addHeader("Referer", DouUtil.referer)
                .addHeader("Cookie", setCookieHeader)
                .build();

        try (Response redirectResponse = OkHttpClientFactory.createClient().newCall(redirectRequest).execute()) {
            if (redirectResponse.isSuccessful()) {
                // 处理重定向后的逻辑
                return null;
            } else {
                return new ResultData(Integer.parseInt(Global.AJAX_LOGIN_ERR), "error", "重定向失败");
            }
        }
    }

    private void initializeFpAndTtwid() throws NoSuchAlgorithmException, IOException {
        if (fp.isEmpty()) {
            fp = DouUtil.getFp();
        }
        if (cookieTtwid.isEmpty()) {
            cookieTtwid = "ttwid=" + DouUtil.getTtwid();
        }
    }

    private void resetFpAndTtwid() {
        fp = "";
        cookieTtwid = "";
    }

    private ResultData executeRequest(Request request, String errorMessage) throws IOException {
        try (Response response = OkHttpClientFactory.createClient().newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseData = response.body().string();
                return new ResultData(200, Global.AJAX_SUCCESS, JSONObject.parseObject(responseData));
            } else {
                return new ResultData(500, Global.AJAX_LOGIN_ERR, errorMessage);
            }
        }
    }
}
