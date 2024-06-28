package com.scccy.videoflowDy.service.impl;


import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videoDownloader.untils.*;
import com.scccy.videoModel.domain.CollectData;
import com.scccy.videoModel.domain.CollectDataDetail;
import com.scccy.videoModel.domain.ConfigDouyin;
import com.scccy.videoModel.domain.Video;
import com.scccy.videoModel.mapper.ConfigDouyinMapper;
import com.scccy.videobase.common.ResultData;
import com.scccy.videobase.pojo.Global;
import com.scccy.videoflowDy.service.CollectDataDetailService;
import com.scccy.videoflowDy.service.CollectDataService;
import com.scccy.videoflowDy.service.DouYinService;
import com.scccy.videoflowDy.service.VideoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class DouYinServiceImpl extends ServiceImpl<ConfigDouyinMapper, ConfigDouyin> implements DouYinService {

    @Autowired
    private VideoService videoService;
//     开启线程数
    private ExecutorService exec = Executors.newFixedThreadPool(2);
    @Resource
    CollectDataService collectDataService;
    @Resource
    CollectDataDetailService collectDataDetailService;

    private static final String DOMAIN = "https://www.douyin.com/";
    private static final String LOGIN_QR_DOMAIN = "https://sso.douyin.com/get_qrcode/?";
    private static final String LOGIN_CHECK_DOMAIN = "https://sso.douyin.com/check_qrconnect/?";

    private static String fp = "";
    private static String cookieTtwid = "";

    public void putRecord(String awemeId, String desc, String playApi, String cover, String platform, String originalAddress, String type, String cookie) {
        String filename = StringUtil.getFileName(desc, awemeId);
        String videoFile = FileUtil.createDirFile(Global.DOWN_PATH, ".mp4", filename, Global.platform.douyin.name());
        String videoUnrealAddr = FileUtil.createDirFile(Global.SAVE_FILE, ".mp4", filename, Global.platform.douyin.name());
        String coverUnAddr = FileUtil.createDirFile(Global.SAVE_FILE, ".jpg", filename, Global.platform.douyin.name());

        String downloadUrl = type.equals("client") ? "https:" + playApi : playApi;
        String temporaryDir = FileUtil.createTemporaryDirectory(Global.platform.douyin.name(), filename, Global.DOWN_PATH);

        if (type.equals("client")) {
            log.info("已使用htmlunit进行解析,下载器类型为:" + Global.DOWN_TYPE);
            handleDownload(downloadUrl, filename, temporaryDir, cookie, cover);
        } else if (type.equals("api")) {
            log.info("已使用api进行解析,下载器类型为:" + Global.DOWN_TYPE);
            handleDownload(downloadUrl, filename, temporaryDir, cookie, cover);
        }

        videoFile = FileUtil.createDirFile(Global.UPLOAD_PATH, ".mp4", filename, Global.platform.douyin.name());
// todo:下载记录入库
        //        BizVideo videoDataEntity = new BizVideo(awemeId, platform, coverUnAddr, videoFile, videoUnrealAddr, originalAddress);
//        bizVideoService.save(videoDataEntity);
        log.info("下载流程结束");
    }

    private void handleDownload(String downloadUrl, String filename, String temporaryDir, String cookie, String cover) {
        if (Global.DOWN_TYPE.equals("a2")) {
            Aria2Util.sendMessage(Global.A2_LINK, Aria2Util.createDyParameter(downloadUrl, temporaryDir, filename + ".mp4", Global.A2_TOKEN, cookie));
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


    public void saveSettings(ConfigDouyin configDouyin) {
        this.saveOrUpdate(configDouyin);
        if (configDouyin.getCookies() != null && !configDouyin.getCookies().isEmpty()) {
            Global.Douyin_Cookie = configDouyin.getCookies();
        }
        if (configDouyin.getAnalysisserver() != null && !configDouyin.getAnalysisserver().isEmpty()) {
            Global.ANALY_SIS_SERVER = configDouyin.getAnalysisserver();
        }
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

    private void initializeFpAndTtwid() {
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

    @Override
    public ResultData FavoritesData(CollectData collectData) {
        if(collectData.getOriginaladdress().contains("post") || collectData.getOriginaladdress().contains("like")) {
            try {
                //进线程前创建collectDataEntity
                collectData.setTaskstatus("已提交待处理");
                collectData.setCreatetime(DateUtils.formatDateTime(new Date()));
                collectData.setCount("0");
                collectData.setCarriedout("0");  //归零
//                保存失败返回
              
               if(!collectDataService.save(collectData)){return ResultData.fail();};
                //提交线程
                exec.execute(() -> {
                    try {
                        this.createDyData(collectData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                return  ResultData.ok("已提交线程处理,如填错但线程已开启请重启容器解决", null);

            } catch (Exception e) {
                log.error("异常"+e.getMessage());
            }

        }else {
            return  ResultData.fail(Global.AJAX_URI_ERROR, "请按页面要求填写地址", null);
        }
        return null;
    }

//    抖音下载任务
public void createDyData(CollectData collectData) throws Exception {
    log.info("任务开始" + collectData.getOriginaladdress());
    JSONArray allDYData = this.getAllDYData(collectData);

    collectData.setCount(String.valueOf(allDYData.size()));
    collectData.setTaskstatus("已开始处理");
    collectDataService.save(collectData);

    for (int i = 0; i < allDYData.size(); i++) {
        log.info(collectData.getOriginaladdress() + "任务中第" + i + "个");
        JSONObject aweme_detail = allDYData.getJSONObject(i);
        String awemeId = aweme_detail.getString("aweme_id");
        String status = processAwemeDetail(collectData, aweme_detail, awemeId);

        Thread.sleep(2500);
        saveCollectDataDetail(collectData, awemeId, status, aweme_detail);
        updateCollectData(collectData);
    }

    collectData.setTaskstatus("处理完成");
    collectData.setEndtime(DateUtils.formatDateTime(new Date()));
    collectDataService.save(collectData);
    System.gc();
    log.info("任务结束" + collectData.getOriginaladdress());
}

    private String processAwemeDetail(CollectData collectData, JSONObject aweme_detail, String awemeId) {
        String status = "";
        try {
            String aweme_type = aweme_detail.getString("aweme_type");
            if (aweme_type.equals("68")) {
                status = "图集不支持下载";
                saveCollectDataDetail(collectData, awemeId, status, aweme_detail);
                updateCollectData(collectData);
                return status;
            }
        } catch (Exception e) {
            status = "视频异常";
            saveCollectDataDetail(collectData, awemeId, status, aweme_detail);
            updateCollectData(collectData);
            return status;
        }

        String coverUri = getCoverUri(aweme_detail);
        String videoPlay = getVideoPlay(aweme_detail);
        String desc = aweme_detail.getString("desc");

        List<Video> findByVideoId = videoService.list(Wrappers.lambdaQuery(Video.class).eq(Video::getVideoid, awemeId));
        if (findByVideoId.isEmpty()) {
            String filename = StringUtil.getFileName(desc, awemeId);
            String videofile = FileUtil.createDirFile(Global.DOWN_PATH, ".mp4", filename, Global.platform.douyin.name());
            String videoUnreaLaddr = FileUtil.createDirFile(Global.SAVE_FILE, ".mp4", filename, Global.platform.douyin.name());
            String coverUnaddr = FileUtil.createDirFile(Global.SAVE_FILE, ".jpg", filename, Global.platform.douyin.name());

            log.info("已使用批量下载,下载器类型为:" + Global.DOWN_TYPE);
            if (Global.DOWN_TYPE.equals("a2")) {
                Aria2Util.sendMessage(Global.A2_LINK, Aria2Util.createDyParameter(videoPlay, FileUtil.createTemporaryDirectory(Global.platform.douyin.name(), filename, Global.DOWN_PATH), filename + ".mp4", Global.A2_TOKEN, Global.tiktokCookie));
            }
            if (Global.DOWN_TYPE.equals("http")) {
                HttpUtil.downDouFromUrl(videoPlay, filename + ".mp4", FileUtil.createTemporaryDirectory(Global.platform.douyin.name(), filename, Global.UPLOAD_PATH), Global.Douyin_Cookie);
            }
            videofile = FileUtil.createDirFile(Global.UPLOAD_PATH, ".mp4", filename, Global.platform.douyin.name());
            HttpUtil.downLoadFromUrl(coverUri, filename + ".jpg", FileUtil.createTemporaryDirectory(Global.platform.douyin.name(), filename, Global.UPLOAD_PATH) + "/");
            Video videoDataEntity = Video.createVideo(awemeId, coverUnaddr, videofile, videoUnreaLaddr, collectData.getOriginaladdress());
            videoService.save(videoDataEntity);
            log.info("下载流程结束");
        }
        return findByVideoId.isEmpty() ? "已完成" : "已完成(未下载已存在)";
    }

    private String getCoverUri(JSONObject aweme_detail) {
        JSONArray cover = aweme_detail.getJSONObject("video").getJSONObject("cover").getJSONArray("url_list");
        return cover.size() >= 2 ? cover.getString(cover.size() - 1) : cover.getString(0);
    }

    private String getVideoPlay(JSONObject aweme_detail) {
        JSONArray jsonArray = aweme_detail.getJSONObject("video").getJSONObject("play_addr").getJSONArray("url_list");
        return jsonArray.size() >= 2 ? jsonArray.getString(jsonArray.size() - 1) : jsonArray.getString(0);
    }

    private void saveCollectDataDetail(CollectData collectData, String awemeId, String status, JSONObject aweme_detail) {
        CollectDataDetail collectDataDetail = new CollectDataDetail();
        collectDataDetail.setDataid(collectData.getId());
        collectDataDetail.setVideoid(awemeId);
        collectDataDetail.setOriginaladdress(awemeId);
        collectDataDetail.setStatus(status);
        collectDataDetail.setCreatetime(DateUtils.formatDateTime(new Date()));
        if (!status.equals("图集不支持下载") && !status.equals("视频异常")) {
            collectDataDetail.setVideoname(aweme_detail.getString("desc"));
        }
        collectDataDetailService.save(collectDataDetail);
    }

    private void updateCollectData(CollectData collectData) {
        String carriedout = collectData.getCarriedout() == null ? "1" : String.valueOf(Integer.parseInt(collectData.getCarriedout()) + 1);
        collectData.setCarriedout(carriedout);
        collectDataService.save(collectData);
    }

    public JSONArray getAllDYData(CollectData collectData) throws Exception {
        String api ="";
        String sign = "aid=6383&sec_user_id=#uid#&count=35&max_cursor=#max_cursor#&cookie_enabled=true&platform=PC&downlink=10";
        if(collectData.getOriginaladdress().contains("post")) {
            api = "https://www.douyin.com/aweme/v1/web/aweme/post/?";
        }
        if(collectData.getOriginaladdress().contains("like")) {
            api = "https://www.douyin.com/aweme/v1/web/aweme/favorite/?";
        }
        String sec_user_id = collectData.getOriginaladdress().replaceAll("post", "").replaceAll("like", "");
        String singnew = sign.replaceAll("#uid#", sec_user_id);
        api =api+singnew;
        JSONArray dyNextData = this.getDYNextData(api, new JSONArray(),"0",singnew);
        return dyNextData;

    }

    public JSONArray  getDYNextData(String api,JSONArray data,String max_cursor,String sign) throws Exception {

        String newsign = sign.replaceAll("#max_cursor#", max_cursor);
        String apiaddt = api.replaceAll("#max_cursor#", max_cursor);
        String xbogus = XbogusUtil.getXBogus(newsign);
        apiaddt = apiaddt+"&X-Bogus="+xbogus;
        String httpGet = DouUtil.httpget(apiaddt, Global.Douyin_Cookie);

        JSONObject parseObject = JSONObject.parseObject(httpGet);
        JSONArray jsonArray = parseObject.getJSONArray("aweme_list");
        max_cursor = parseObject.getString("max_cursor");
        if(!max_cursor.equals("0")) {
            //需要递归
            data.addAll(jsonArray);
            Thread.sleep(2500);
            return this.getDYNextData(api, data, max_cursor,sign);
        }else {
            data.addAll(jsonArray);
            return data;
        }
    }

}
