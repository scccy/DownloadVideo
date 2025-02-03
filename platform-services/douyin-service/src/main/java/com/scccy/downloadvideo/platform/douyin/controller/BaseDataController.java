package com.scccy.downloadvideo.platform.douyin.controller;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.service.DouyinDownloadConfigService;
import com.scccy.downloadvideo.platform.douyin.service.Impl.DouyinDownloadConfigServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/douyin/ck")
@Tag(name = "基础数据")
public class BaseDataController {
    @Autowired
    DouyinDownloadConfigService douyinDownloadConfigServiceImpl;
    @GetMapping("/id")
    @Operation(summary ="查询抖音ck")
    public Mono<ResultData> getCookie(Long id){
       return douyinDownloadConfigServiceImpl.getCookies(id);
    }
}
