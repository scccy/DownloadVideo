package com.scccy.downloadvideo.common.download.feign;

import com.scccy.downloadvideo.common.core.annotation.DouyinApi;
import com.scccy.downloadvideo.common.core.enums.DouyinApiEndpoint;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.alibaba.fastjson2.JSONObject;


@FeignClient(name = "crawler-client", url = "${douyin.api.base-url:}")
public interface CrawlerFeignClient {

    /**
     * GET请求
     */
    @GetMapping
    ResponseEntity<String> get(@RequestParam("url") String url,
                             @RequestHeader Map<String, String> headers,
                             @RequestParam(required = false) Map<String, String> proxies);

    /**
     * POST请求
     */
    @PostMapping
    ResponseEntity<String> post(@RequestParam("url") String url,
                              @RequestBody String body,
                              @RequestHeader Map<String, String> headers,
                              @RequestParam(required = false) Map<String, String> proxies);

    /**
     * 获取JSON响应
     */
    @GetMapping("/json")
    ResponseEntity<String> getJson(@RequestParam("url") String url,
                                 @RequestHeader Map<String, String> headers,
                                 @RequestParam(required = false) Map<String, String> proxies);

    /**
     * 带Cookie的请求
     */
    @GetMapping("/with-cookie")
    ResponseEntity<String> getWithCookie(@RequestParam("url") String url,
                                       @RequestHeader Map<String, String> headers,
                                       @CookieValue Map<String, String> cookies,
                                       @RequestParam(required = false) Map<String, String> proxies);

    /**
     * 自定义请求方法
     */
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<String> request(@RequestParam("url") String url,
                                 @RequestParam("method") String method,
                                 @RequestHeader Map<String, String> headers,
                                 @RequestBody(required = false) String body,
                                 @RequestParam(required = false) Map<String, String> proxies);

    /**
     * 用户信息
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_PROFILE)
    ResponseEntity<JSONObject> fetchUserProfile(@RequestParam("user_id") String userId);
    
    /**
     * 用户作品列表
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_POST)
    ResponseEntity<JSONObject> fetchUserPosts(@RequestParam("user_id") String userId, 
                                            @RequestParam("page") int page);
    
    /**
     * 用户喜欢列表
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_FAVORITE)
    ResponseEntity<JSONObject> fetchUserLikes(@RequestParam("user_id") String userId, 
                                            @RequestParam("page") int page);
    
    /**
     * 用户收藏列表
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_COLLECTION, method = RequestMethod.POST)
    ResponseEntity<JSONObject> fetchUserCollection(@RequestParam("user_id") String userId, 
                                                 @RequestParam("page") int page);
    
    /**
     * 用户音乐收藏
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_MUSIC_COLLECTION)
    ResponseEntity<JSONObject> fetchUserMusicCollection(@RequestParam("user_id") String userId, 
                                                      @RequestParam("page") int page);
    
    /**
     * 合集作品列表
     */
    @DouyinApi(value = DouyinApiEndpoint.MIX_AWEME)
    ResponseEntity<JSONObject> fetchUserMix(@RequestParam("mix_id") String mixId, 
                                          @RequestParam("page") int page);
    
    /**
     * 作品详情
     */
    @DouyinApi(value = DouyinApiEndpoint.POST_DETAIL)
    ResponseEntity<JSONObject> fetchPostDetail(@RequestParam("aweme_id") String awemeId);
    
    /**
     * 作品评论
     */
    @DouyinApi(value = DouyinApiEndpoint.POST_COMMENT)
    ResponseEntity<JSONObject> fetchPostComment(@RequestParam("aweme_id") String awemeId, 
                                              @RequestParam("page") int page);
    
    /**
     * 首页推荐
     */
    @DouyinApi(value = DouyinApiEndpoint.TAB_FEED)
    ResponseEntity<JSONObject> fetchPostFeed(@RequestParam("page") int page);
    
    /**
     * 关注作品
     */
    @DouyinApi(value = DouyinApiEndpoint.FOLLOW_FEED)
    ResponseEntity<JSONObject> fetchFollowFeed(@RequestParam("page") int page);
    
    /**
     * 朋友作品
     */
    @DouyinApi(value = DouyinApiEndpoint.FRIEND_FEED, method = RequestMethod.POST)
    ResponseEntity<JSONObject> fetchFriendFeed();
    
    /**
     * 相关推荐
     */
    @DouyinApi(value = DouyinApiEndpoint.POST_RELATED)
    ResponseEntity<JSONObject> fetchPostRelated(@RequestParam("aweme_id") String awemeId);
    
    /**
     * 直播信息
     */
    @DouyinApi(value = DouyinApiEndpoint.LIVE_INFO)
    ResponseEntity<JSONObject> fetchLiveInfo(@RequestParam("room_id") String roomId);
    
    /**
     * 直播间ID
     */
    @DouyinApi(value = DouyinApiEndpoint.LIVE_ROOM_ID, needXBogus = false)
    ResponseEntity<JSONObject> fetchLiveRoomId(@RequestParam("sec_user_id") String secUserId);
    
    /**
     * 关注用户直播
     */
    @DouyinApi(value = DouyinApiEndpoint.FOLLOW_USER_LIVE)
    ResponseEntity<JSONObject> fetchFollowingLive(@RequestParam("page") int page);
    
    /**
     * 用户关注列表
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_FOLLOWING)
    ResponseEntity<JSONObject> fetchUserFollowing(@RequestParam("user_id") String userId, 
                                                @RequestParam("page") int page);
    
    /**
     * 用户粉丝列表
     */
    @DouyinApi(value = DouyinApiEndpoint.USER_FOLLOWER)
    ResponseEntity<JSONObject> fetchUserFollower(@RequestParam("user_id") String userId, 
                                               @RequestParam("page") int page);
    
    /**
     * 直播弹幕
     */
    @DouyinApi(value = DouyinApiEndpoint.LIVE_IM_FETCH)
    ResponseEntity<JSONObject> fetchLiveImFetch(@RequestParam("room_id") String roomId);
    
    /**
     * 查询用户
     */
    @DouyinApi(value = DouyinApiEndpoint.QUERY_USER)
    ResponseEntity<JSONObject> fetchQueryUser(@RequestParam("keyword") String keyword);
    
    /**
     * 作品统计
     */
    @DouyinApi(value = DouyinApiEndpoint.POST_STATS, method = RequestMethod.POST)
    ResponseEntity<JSONObject> fetchPostStats(@RequestBody JSONObject params);

    // 登录相关
    @DouyinApi(value = DouyinApiEndpoint.SSO_LOGIN_QRCODE)
    ResponseEntity<JSONObject> fetchLoginQrcode();

    @DouyinApi(value = DouyinApiEndpoint.SSO_CHECK_QRCODE)
    ResponseEntity<JSONObject> checkQrcodeStatus(@RequestParam("token") String token);

    @DouyinApi(value = DouyinApiEndpoint.SSO_CHECK_LOGIN)
    ResponseEntity<JSONObject> checkLoginStatus(@RequestParam("token") String token);
} 