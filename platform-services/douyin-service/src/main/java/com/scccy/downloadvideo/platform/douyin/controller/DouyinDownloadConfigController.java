package com.scccy.downloadvideo.platform.douyin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.scccy.downloadvideo.platform.douyin.service.DouyinDownloadConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/douyin/config")
@Tag(name = "抖音下载配置管理", description = "抖音下载配置相关接口")
public class DouyinDownloadConfigController {

    @Autowired
    DouyinDownloadConfigService douyinDownloadConfigServiceimpl;

    @PostMapping
    @Operation(summary = "新增配置", description = "新增抖音下载配置")
    public Mono<ResultData> save(@RequestBody DouyinDownloadConfig config) {
        return douyinDownloadConfigServiceimpl.saveData(config);
    }

    @PutMapping
    @Operation(summary = "更新配置", description = "更新抖音下载配置")
    public Mono<DouyinDownloadConfig> update(@RequestBody DouyinDownloadConfig config) {
        return douyinDownloadConfigServiceimpl.updateData(config);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除配置", description = "根据ID删除抖音下载配置")
    public Mono<Void> delete(@PathVariable @Parameter(description = "配置ID") Long id) {
        return douyinDownloadConfigServiceimpl.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "查询单个配置", description = "根据ID查询抖音下载配置")
    public Mono<DouyinDownloadConfig> findById(@PathVariable @Parameter(description = "配置ID") Long id) {
        return douyinDownloadConfigServiceimpl.findById(id);
    }

    @GetMapping
    @Operation(summary = "查询所有配置", description = "查询所有抖音下载配置")
    public Flux<DouyinDownloadConfig> findAll() {
        return douyinDownloadConfigServiceimpl.findAll();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询配置", description = "分页查询抖音下载配置")
    public Mono<Page<DouyinDownloadConfig>> findPage(
            @RequestParam(defaultValue = "1") @Parameter(description = "当前页码") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "每页数量") int size) {
        return douyinDownloadConfigServiceimpl.findPage(page, size);
    }
} 