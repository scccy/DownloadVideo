package com.scccy.downloadvideo.platform.douyin.service;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.download.feign.CrawlerFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class DouyinService {
    @Resource
    CrawlerFeignClient crawlerClient;
    

    /**
     * 获取用户信息
     */
    public JSONObject getUserProfile(String userId) {
        return crawlerClient.fetchUserProfile(userId).getBody();
    }
    
    /**
     * 获取用户作品列表
     */
    public JSONObject getUserPosts(String userId, int page) {
        return crawlerClient.fetchUserPosts(userId, page).getBody();
    }
    
    /**
     * 获取用户喜欢列表
     */
    public JSONObject getUserLikes(String userId, int page) {
        return crawlerClient.fetchUserLikes(userId, page).getBody();
    }
    
    /**
     * 获取用户收藏列表
     */
    public JSONObject getUserCollections(String userId, int page) {
        return crawlerClient.fetchUserCollection(userId, page).getBody();
    }
    
    /**
     * 获取作品详情
     */
    public JSONObject getPostDetail(String awemeId) {
        return crawlerClient.fetchPostDetail(awemeId).getBody();
    }
    
    /**
     * 获取作品评论
     */
    public JSONObject getPostComments(String awemeId, int page) {
        return crawlerClient.fetchPostComment(awemeId, page).getBody();
    }
    
    /**
     * 获取直播信息
     */
    public JSONObject getLiveInfo(String roomId) {
        return crawlerClient.fetchLiveInfo(roomId).getBody();
    }
    
    /**
     * 获取直播弹幕
     */
    public JSONObject getLiveDanmaku(String roomId) {
        return crawlerClient.fetchLiveImFetch(roomId).getBody();
    }
    
    /**
     * 批量下载用户作品
     */
    public void batchDownloadUserPosts(String userId, String savePath) {
        int page = 1;
        while (true) {
            JSONObject posts = getUserPosts(userId, page);
            if (!hasMorePosts(posts)) {
                break;
            }
            
            for (Object item : posts.getJSONArray("aweme_list")) {
                JSONObject post = (JSONObject) item;
                String videoUrl = extractVideoUrl(post);
                if (videoUrl != null) {
                    downloadVideo(videoUrl, savePath, generateFileName(post));
                }
            }
            
            page++;
        }
    }
    
    /**
     * 批量下载用户喜欢的作品
     */
    public void batchDownloadUserLikes(String userId, String savePath) {
        int page = 1;
        while (true) {
            JSONObject likes = getUserLikes(userId, page);
            if (!hasMoreLikes(likes)) {
                break;
            }
            
            for (Object item : likes.getJSONArray("aweme_list")) {
                JSONObject post = (JSONObject) item;
                String videoUrl = extractVideoUrl(post);
                if (videoUrl != null) {
                    downloadVideo(videoUrl, savePath, generateFileName(post));
                }
            }
            
            page++;
        }
    }
    
    /**
     * 下载直播流
     */
    public void downloadLiveStream(String roomId, String savePath) {
        JSONObject liveInfo = getLiveInfo(roomId);
        String streamUrl = extractStreamUrl(liveInfo);
        if (streamUrl != null) {
            downloadM3u8Stream(streamUrl, savePath, generateLiveFileName(liveInfo));
        }
    }
    
    // 辅助方法
    private boolean hasMorePosts(JSONObject response) {
        return response.getBoolean("has_more");
    }
    
    private boolean hasMoreLikes(JSONObject response) {
        return response.getBoolean("has_more");
    }
    
    private String extractVideoUrl(JSONObject post) {
        try {
            return post.getJSONObject("video")
                .getJSONObject("play_addr")
                .getJSONArray("url_list")
                .getString(0);
        } catch (Exception e) {
            log.error("Failed to extract video URL: {}", e.getMessage());
            return null;
        }
    }
    
    private String extractStreamUrl(JSONObject liveInfo) {
        try {
            return liveInfo.getJSONObject("room")
                .getJSONObject("stream_url")
                .getJSONArray("hls_pull_url")
                .getString(0);
        } catch (Exception e) {
            log.error("Failed to extract stream URL: {}", e.getMessage());
            return null;
        }
    }
    
    private String generateFileName(JSONObject post) {
        String desc = post.getString("desc");
        String createTime = post.getString("create_time");
        return String.format("%s_%s.mp4", createTime, desc);
    }
    
    private String generateLiveFileName(JSONObject liveInfo) {
        String title = liveInfo.getJSONObject("room").getString("title");
        String timestamp = String.valueOf(System.currentTimeMillis());
        return String.format("%s_%s.mp4", timestamp, title);
    }
    
    private void downloadVideo(String url, String savePath, String fileName) {
        // TODO: 调用下载器进行下载
    }
    
    private void downloadM3u8Stream(String url, String savePath, String fileName) {
        // TODO: 调用下载器进行M3U8流下载
    }
} 