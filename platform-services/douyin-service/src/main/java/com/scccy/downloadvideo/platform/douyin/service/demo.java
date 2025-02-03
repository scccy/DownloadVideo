package com.scccy.downloadvideo.platform.douyin.service;//package com.scccy.downloadvideo.platform.douyin.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.scccy.downloadvideo.platform.douyin.mapper.DouyinDownloadConfigMapper;
//import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.core.scheduler.Schedulers;
//
//public class demo {
//    @Autowired
//    private DouyinDownloadConfigMapper configMapper;
//
//    @Override
//    public Mono<DouyinDownloadConfig> save(DouyinDownloadConfig config) {
//        return Mono.fromCallable(() -> {
//            configMapper.insert(config);
//            return config;
//        }).subscribeOn(Schedulers.boundedElastic());
//    }
//
//    @Override
//    public Mono<DouyinDownloadConfig> update(DouyinDownloadConfig config) {
//        return Mono.fromCallable(() -> {
//            configMapper.updateById(config);
//            return config;
//        }).subscribeOn(Schedulers.boundedElastic());
//    }
//
//    @Override
//    public Mono<Void> delete(Long id) {
//        return Mono.fromRunnable(() -> configMapper.deleteById(id))
//                .subscribeOn(Schedulers.boundedElastic())
//                .then();
//    }
//
//    @Override
//    public Mono<DouyinDownloadConfig> findById(Long id) {
//        return Mono.fromCallable(() -> configMapper.selectById(id))
//                .subscribeOn(Schedulers.boundedElastic());
//    }
//
//    @Override
//    public Flux<DouyinDownloadConfig> findAll() {
//        return Flux.fromIterable(configMapper.selectList(null))
//                .subscribeOn(Schedulers.boundedElastic());
//    }
//
//    @Override
//    public Mono<Page<DouyinDownloadConfig>> findPage(int page, int size) {
//        return Mono.fromCallable(() -> {
//            Page<DouyinDownloadConfig> pageObj = new Page<>(page, size);
//            configMapper.selectPage(pageObj, null);
//            return pageObj;
//        }).subscribeOn(Schedulers.boundedElastic());
//    }
//}
