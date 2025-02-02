package com.scccy.downloadvideo.platform.douyin.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scccy.downloadvideo.common.core.model.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.scccy.downloadvideo.platform.douyin.mapper.DouyinDownloadConfigMapper;
import com.scccy.downloadvideo.platform.douyin.service.DouyinDownloadConfigService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class DouyinDownloadConfigServiceImpl extends ServiceImpl<DouyinDownloadConfigMapper, DouyinDownloadConfig> implements DouyinDownloadConfigService{
    @Autowired
    private DouyinDownloadConfigMapper configMapper;

    @Override
    public Mono<ResultData> saveData(DouyinDownloadConfig config) {
        return Mono.fromCallable(() -> {
            return ResultData.ok( configMapper.insert(config));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<DouyinDownloadConfig> updateData(DouyinDownloadConfig config) {
        return Mono.fromCallable(() -> {
            configMapper.updateById(config);
            return config;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Void> delete(Long id) {
        return Mono.fromRunnable(() -> configMapper.deleteById(id))
                .subscribeOn(Schedulers.boundedElastic())
                .then();
    }


    @Override
    public Mono<DouyinDownloadConfig> findById(Long id) {
        return Mono.fromCallable(() -> configMapper.selectById(id))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<DouyinDownloadConfig> findAll() {
        return Flux.fromIterable(configMapper.selectList(null))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Page<DouyinDownloadConfig>> findPage(int page, int size) {
        return Mono.fromCallable(() -> {
            Page<DouyinDownloadConfig> pageObj = new Page<>(page, size);
            configMapper.selectPage(pageObj, null);
            return pageObj;
        }).subscribeOn(Schedulers.boundedElastic());
    }
}
