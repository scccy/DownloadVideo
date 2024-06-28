package com.scccy.videobase.pojo;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface DownloadFather {

    String getDynamicCover();

    String getOriginCover();

    String getDownloads();

    String getNickname();
}
