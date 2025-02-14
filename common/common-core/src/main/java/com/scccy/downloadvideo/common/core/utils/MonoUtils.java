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
        return Mono.fromCallable(() -> {
            try {
                return ResultData.ok(supplier.get());
            } catch (Exception e) {
                return ResultData.fail("请求失败: " + e.getMessage());
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将阻塞操作转换为响应式操作，不包装为 ResultData
     *
     * @param supplier 提供数据的阻塞操作
     * @return 直接返回数据的 Mono
     * @param <T> 数据类型
     */
    public static <T> Mono<T> fromBlocking(Supplier<T> supplier) {
        return Mono.fromCallable(supplier::get)
                  .subscribeOn(Schedulers.boundedElastic());
    }

    /**
     * 将阻塞操作转换为响应式操作，支持自定义错误处理
     *
     * @param supplier 提供数据的阻塞操作
     * @param errorHandler 自定义错误处理函数
     * @return 返回数据的 Mono
     * @param <T> 数据类型
     */
    public static <T> Mono<T> fromBlocking(Supplier<T> supplier, java.util.function.Function<Exception, T> errorHandler) {
        return Mono.fromCallable(() -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                return errorHandler.apply(e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}