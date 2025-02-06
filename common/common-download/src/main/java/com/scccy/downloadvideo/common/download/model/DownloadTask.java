package com.scccy.downloadvideo.common.download.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadTask {
    private String id;
    private String url;
    private String savePath;
    private String fileName;
    private Long contentLength;
    private Long downloadedSize;
    private TaskStatus status;
    private String error;


}

