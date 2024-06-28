package com.scccy.videoDownloader.api;

import com.scccy.videoDownloader.base.Acquirer;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Search extends Acquirer {
    private static class SearchParams {
        public final String api;
        public final int count;
        public final String channel;
        public final String type;

        public SearchParams(String api, int count, String channel, String type) {
            this.api = api;
            this.count = count;
            this.channel = channel;
            this.type = type;
        }
    }

    private static final SearchParams[] searchParams = new SearchParams[]{
            new SearchParams("https://www.douyin.com/aweme/v1/web/general/search/single/", 15, "aweme_general", "general"),
            new SearchParams("https://www.douyin.com/aweme/v1/web/search/item/", 20, "aweme_video_web", "video"),
            new SearchParams("https://www.douyin.com/aweme/v1/web/discover/search/", 12, "aweme_user_web", "user"),
            new SearchParams("https://www.douyin.com/aweme/v1/web/live/search/", 15, "aweme_live", "live")
    };

    private final String keyword;
    private int tab;
    private int page;
    private final int sortType;
    private final int publishTime;

    public Search(Parameter params, String keyword, int tab, int page, int sortType, int publishTime, String cookie) {
        super(params, cookie);
        this.keyword = keyword;
        this.tab = tab;
        this.page = page;
        this.sortType = sortType;
        this.publishTime = publishTime;
    }

    public List<Map<String, Object>> run() {
        SearchParams data = searchParams[this.tab];
        super.pcHeaders.put("Referer", "https://www.douyin.com/search/" + URLEncoder.encode(this.keyword) + "?source=switch_tab&type=" + data.type);
        Runnable deal;
        if (this.tab == 2 || this.tab == 3) {
            deal = () -> _runUserLive(data, this.tab);
        } else if (this.tab == 0 || this.tab == 1) {
            deal = () -> _runGeneral(data, this.tab);
        } else {
            throw new IllegalArgumentException();
        }
        while (!this.finished && this.page > 0) {
            deal.run();
            this.page--;
        }

        return this.response;
    }

    private void _runUserLive(SearchParams data, int type) {
        Map<String, String> params = new HashMap<>();
        params.put("device_platform", "webapp");
        params.put("aid", "6383");
        params.put("channel", "channel_pc_web");
        params.put("search_channel", data.channel);
        params.put("keyword", this.keyword);
        params.put("search_source", "switch_tab");
        params.put("query_correct_type", "1");
        params.put("is_filter_search", "0");
        params.put("from_group_id", "");
        params.put("offset", Integer.toString(this.cursor));
        params.put("count", Integer.toString(this.cursor == 0 ? data.count : 10));
        params.put("pc_client_type", "1");
        params.put("version_code", "170400");
        params.put("version_name", "17.4.0");
        params.put("cookie_enabled", "true");
        params.put("platform", "PC");
        params.put("downlink", "10");
        this.dealUrlParams(params, this.cursor == 0 ? 8 : 4);
        this._getSearchData(data.api, params, type == 2 ? "user_list" : "data");
    }

    private void _runGeneral(SearchParams data, int type) {
        Map<String, String> params = new HashMap<>();
        params.put("device_platform", "webapp");
        params.put("aid", "6383");
        params.put("channel", "channel_pc_web");
        params.put("search_channel", data.channel);
        params.put("sort_type", Integer.toString(this.sortType));
        params.put("publish_time", Integer.toString(this.publishTime));
        params.put("keyword", this.keyword);
        params.put("search_source", "tab_search");
        params.put("query_correct_type", "1");
        params.put("is_filter_search", Boolean.toString(this.sortType != 0 || this.publishTime != 0));
        params.put("from_group_id", "");
        params.put("offset", Integer.toString(this.cursor));
        params.put("count", Integer.toString(this.cursor == 0 ? data.count : 10));
        params.put("pc_client_type", "1");
        params.put("version_code", type == 0 ? "170400" : "190600");
        params.put("version_name", type == 0 ? "17.4.0" : "19.6.0");
        params.put("cookie_enabled", "true");
        params.put("platform", "PC");
        params.put("downlink", "10");
        this.dealUrlParams(params, this.cursor == 0 ? 8 : 4);
        this._getSearchData(data.api, params, "data");
    }


    private boolean _getSearchData(String api, Map<String, String> params, String key) {
        Map<String, Object> data = this.sendRequest(api, params, "GET", super.pcHeaders);
        if (data == null || data.isEmpty()) {
            log.warn("获取搜索数据失败");
            return false;
        }
        try {
            this.dealItemData((List<Map<String, Object>>) data.get(key), 0, ((List<Map<String, Object>>) data.get(key)).size());
            this.cursor = (int) data.get("cursor");
            this.finished = !(boolean) data.get("has_more");
            return true;
        } catch (Exception e) {
            log.warn("搜索数据响应内容异常: " + data);
            this.finished = true;
            return false;
        }
    }
}