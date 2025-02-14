package com.scccy.downloadvideo.common.core.model.message;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadMessage {
    private String taskId;
    private String url;
    private String fileName;
} 