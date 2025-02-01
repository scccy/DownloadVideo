package com.scccy.downloadvideo.common.download.feign;

import com.scccy.downloadvideo.common.core.annotation.DouyinApi;
import com.scccy.downloadvideo.common.core.enums.DouyinApiEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import com.alibaba.fastjson2.JSONObject;


@FeignClient(name = "crawler-client", url = "${douyin.api.base-url:}")
@RequestMapping("/api/v1")
public interface CrawlerFeignClient {

    /**
     * GET请求
     */
    @GetMapping("/request")
    ResponseEntity<String> get(@RequestParam("url") String url,
                             @RequestHeader Map<String, String> headers,
                             @RequestParam(required = false) Map<String, String> proxies);

    /**
     * POST请求
     */
    @PostMapping("/request")
    ResponseEntity<String> post(@RequestParam("url") String url,
                              @RequestBody String body,
                              @RequestHeader Map<String, String> headers,
                              @RequestParam(required = false) Map<String, String> proxies);

//    /**
//     * 获取JSON响应
//     */
//    @GetMapping("/json")
//    ResponseEntity<String> getJson(@RequestParam("url") String url,
//                                 @RequestHeader Map<String, String> headers,
//                                 @RequestParam(required = false) Map<String, String> proxies);
//
//    /**
//     * 带Cookie的请求
//     */
//    @GetMapping("/with-cookie")
//    ResponseEntity<String> getWithCookie(@RequestParam("url") String url,
//                                       @RequestHeader Map<String, String> headers,
//                                       @CookieValue Map<String, String> cookies,
//                                       @RequestParam(required = false) Map<String, String> proxies);
//
//    /**
//     * 自定义请求方法
//     */
//    @RequestMapping(value = "/request", method = RequestMethod.GET)
//    ResponseEntity<String> request(@RequestParam("url") String url,
//                                 @RequestParam("method") String method,
//                                 @RequestHeader Map<String, String> headers,
//                                 @RequestBody(required = false) String body,
//                                 @RequestParam(required = false) Map<String, String> proxies);
//
//    /**
//     * 用户信息
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_PROFILE)
//    ResponseEntity<JSONObject> fetchUserProfile(@RequestParam("user_id") String userId);
//
//    /**
//     * 用户作品列表
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_POST)
//    ResponseEntity<JSONObject> fetchUserPosts(@RequestParam("user_id") String userId,
//                                            @RequestParam("page") int page);
//
//    /**
//     * 用户喜欢列表
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_FAVORITE)
//    ResponseEntity<JSONObject> fetchUserLikes(@RequestParam("user_id") String userId,
//                                            @RequestParam("page") int page);
//
//    /**
//     * 用户收藏列表
//     */
//    @PostMapping
//    @DouyinApi(value = DouyinApiEnum.USER_COLLECTION, method = RequestMethod.POST)
//    ResponseEntity<JSONObject> fetchUserCollection(@RequestParam("user_id") String userId,
//                                                 @RequestParam("page") int page);
//
//    /**
//     * 用户音乐收藏
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_MUSIC_COLLECTION)
//    ResponseEntity<JSONObject> fetchUserMusicCollection(@RequestParam("user_id") String userId,
//                                                      @RequestParam("page") int page);
//
//    /**
//     * 合集作品列表
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.MIX_AWEME)
//    ResponseEntity<JSONObject> fetchUserMix(@RequestParam("mix_id") String mixId,
//                                          @RequestParam("page") int page);
//
//    /**
//     * 作品详情
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.POST_DETAIL)
//    ResponseEntity<JSONObject> fetchPostDetail(@RequestParam("aweme_id") String awemeId);
//
//    /**
//     * 作品评论
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.POST_COMMENT)
//    ResponseEntity<JSONObject> fetchPostComment(@RequestParam("aweme_id") String awemeId,
//                                              @RequestParam("page") int page);
//
//    /**
//     * 首页推荐
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.TAB_FEED)
//    ResponseEntity<JSONObject> fetchPostFeed(@RequestParam("page") int page);
//
//    /**
//     * 关注作品
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.FOLLOW_FEED)
//    ResponseEntity<JSONObject> fetchFollowFeed(@RequestParam("page") int page);
//
//    /**
//     * 朋友作品
//     */
//    @PostMapping
//    @DouyinApi(value = DouyinApiEnum.FRIEND_FEED, method = RequestMethod.POST)
//    ResponseEntity<JSONObject> fetchFriendFeed();
//
//    /**
//     * 相关推荐
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.POST_RELATED)
//    ResponseEntity<JSONObject> fetchPostRelated(@RequestParam("aweme_id") String awemeId);
//
//    /**
//     * 直播信息
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.LIVE_INFO)
//    ResponseEntity<JSONObject> fetchLiveInfo(@RequestParam("room_id") String roomId);
//
//    /**
//     * 直播间ID
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.LIVE_ROOM_ID, needXBogus = false)
//    ResponseEntity<JSONObject> fetchLiveRoomId(@RequestParam("sec_user_id") String secUserId);
//
//    /**
//     * 关注用户直播
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.FOLLOW_USER_LIVE)
//    ResponseEntity<JSONObject> fetchFollowingLive(@RequestParam("page") int page);
//
//    /**
//     * 用户关注列表
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_FOLLOWING)
//    ResponseEntity<JSONObject> fetchUserFollowing(@RequestParam("user_id") String userId,
//                                                @RequestParam("page") int page);
//
//    /**
//     * 用户粉丝列表
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_FOLLOWER)
//    ResponseEntity<JSONObject> fetchUserFollower(@RequestParam("user_id") String userId,
//                                               @RequestParam("page") int page);
//
//    /**
//     * 直播弹幕
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.LIVE_IM_FETCH)
//    ResponseEntity<JSONObject> fetchLiveImFetch(@RequestParam("room_id") String roomId);
//
//    /**
//     * 查询用户
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.QUERY_USER)
//    ResponseEntity<JSONObject> fetchQueryUser(@RequestParam("keyword") String keyword);
//
//    /**
//     * 作品统计
//     */
//    @PostMapping
//    @DouyinApi(value = DouyinApiEnum.POST_STATS, method = RequestMethod.POST)
//    ResponseEntity<JSONObject> fetchPostStats(@RequestBody JSONObject params);
//
//    // 登录相关
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.SSO_LOGIN_QRCODE)
//    ResponseEntity<JSONObject> fetchLoginQrcode();
//
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.SSO_CHECK_QRCODE)
//    ResponseEntity<JSONObject> checkQrcodeStatus(@RequestParam("token") String token);
//
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.SSO_CHECK_LOGIN)
//    ResponseEntity<JSONObject> checkLoginStatus(@RequestParam("token") String token);
//
//    /**
//     * 用户收藏夹
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_COLLECTS)
//    ResponseEntity<JSONObject> fetchUserCollects(@RequestParam("user_id") String userId,
//                                               @RequestParam("page") int page);
//
//    /**
//     * 收藏夹作品
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.USER_COLLECTS_VIDEO)
//    ResponseEntity<JSONObject> fetchUserCollectsVideo(@RequestParam("user_id") String userId,
//                                                    @RequestParam("page") int page);
//
//    /**
//     * 定位上一次作品
//     */
//    @GetMapping
//    @DouyinApi(value = DouyinApiEnum.LOCATE_POST)
//    ResponseEntity<JSONObject> fetchLocatePost(@RequestParam("user_id") String userId,
//                                             @RequestParam("page") int page);
} 