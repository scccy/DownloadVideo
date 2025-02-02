package com.scccy.downloadvideo.platform.douyin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.baomidou.mybatisplus.extension.service.IService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DouyinDownloadConfigService extends IService<DouyinDownloadConfig>{


    Mono<Page<DouyinDownloadConfig>> findPage(int page, int size);

    Flux<DouyinDownloadConfig> findAll();

    Mono<DouyinDownloadConfig> findById(Long id);

    Mono<Void> delete(Long id);

    Mono<ResultData> saveData(DouyinDownloadConfig config);

    Mono<DouyinDownloadConfig> updateData(DouyinDownloadConfig config);
}
