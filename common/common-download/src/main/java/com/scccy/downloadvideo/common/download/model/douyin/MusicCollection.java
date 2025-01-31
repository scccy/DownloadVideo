package com.scccy.downloadvideo.common.download.model.douyin;

import lombok.Data;
import java.util.List;

@Data
public class MusicCollection {
    private List<MusicInfo> musicList;
    private boolean hasMore;
    private String cursor;
    
    @Data
    public static class MusicInfo {
        private String musicId;
        private String title;
        private String author;
        private String coverUrl;
        private String playUrl;
        private int duration;
    }
} 