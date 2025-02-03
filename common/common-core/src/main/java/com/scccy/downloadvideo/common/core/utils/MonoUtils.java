package com.scccy.downloadvideo.common.core.utils;

import com.scccy.downloadvideo.common.core.model.ResultData;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.function.Supplier;

public class MonoUtils {

    /**
     * 将阻塞操作转换为响应式操作，并包装为 ResultData
     *
     * @param supplier 提供数据的阻塞操作
     * @return 包装了 ResultData 的 Mono
     * @param <T> 数据类型
     */
    public static <T> Mono<ResultData> back(Supplier<T> supplier) {
        return Mono.fromCallable(() -> ResultData.ok(supplier.get()))
                .onErrorResume(e -> Mono.just(ResultData.fail(e.getMessage())))
                .subscribeOn(Schedulers.boundedElastic());
    }
}