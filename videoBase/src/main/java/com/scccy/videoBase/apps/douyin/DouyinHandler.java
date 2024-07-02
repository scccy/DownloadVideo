//package com.scccy.videoBase.apps.douyin;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
//public class DouyinHandler {
//    // 需要忽略的字段（需过滤掉有时效性的字段）
//    private static final Set<String> IGNORE_FIELDS = Set.of("video_play_addr", "images", "video_bit_rate", "cover");
//
//    private Map<String, Object> kwargs;
//    private DouyinDownloader downloader;
//
//    public DouyinHandler(Map<String, Object> kwargs) {
//        this.kwargs = new HashMap<>(kwargs);
//        this.kwargs.keySet().removeAll(IGNORE_FIELDS);
//        this.downloader = new DouyinDownloader(this.kwargs);
//    }
//}
