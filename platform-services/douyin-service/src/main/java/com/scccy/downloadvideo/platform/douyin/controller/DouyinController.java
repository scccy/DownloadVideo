//package com.scccy.downloadvideo.platform.douyin.controller;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.scccy.downloadvideo.platform.douyin.service.DouyinService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequestMapping("/api/douyin")
//public class DouyinController {
//
//    private final DouyinService douyinService;
//
//    public DouyinController(DouyinService douyinService) {
//        this.douyinService = douyinService;
//    }
//
//    @GetMapping("/user/profile")
//    public JSONObject getUserProfile(@RequestParam String userId) {
//        return douyinService.getUserProfile(userId);
//    }
//
//    @GetMapping("/user/posts")
//    public JSONObject getUserPosts(@RequestParam String userId, @RequestParam int page) {
//        return douyinService.getUserPosts(userId, page);
//    }
//
//    @GetMapping("/user/likes")
//    public JSONObject getUserLikes(@RequestParam String userId, @RequestParam int page) {
//        return douyinService.getUserLikes(userId, page);
//    }
//
//    @GetMapping("/post/detail")
//    public JSONObject getPostDetail(@RequestParam String awemeId) {
//        return douyinService.getPostDetail(awemeId);
//    }
//
//    @GetMapping("/live/info")
//    public JSONObject getLiveInfo(@RequestParam String roomId) {
//        return douyinService.getLiveInfo(roomId);
//    }
//
//    @PostMapping("/download/user/posts")
//    public void downloadUserPosts(@RequestParam String userId, @RequestParam String savePath) {
//        douyinService.batchDownloadUserPosts(userId, savePath);
//    }
//
//    @PostMapping("/download/user/likes")
//    public void downloadUserLikes(@RequestParam String userId, @RequestParam String savePath) {
//        douyinService.batchDownloadUserLikes(userId, savePath);
//    }
//
//    @PostMapping("/download/live")
//    public void downloadLive(@RequestParam String roomId, @RequestParam String savePath) {
//        douyinService.downloadLiveStream(roomId, savePath);
//    }
//}