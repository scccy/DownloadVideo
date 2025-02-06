package com.scccy.downloadvideo.platform.douyin.service;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import reactor.core.publisher.Mono;

public interface DouyinDownloadConfigService extends IService<DouyinDownloadConfig>{


    Mono<ResultData> getCookies(Long id);
}
