package com.scccy.downloadvideo.platform.douyin.controller;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.service.BaseDataService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/baseData")
public class BaseDataController {
    @Autowired
    BaseDataService baseDataService;

    @PostMapping("/getCookies")
    @Operation(summary = "获取Cookies")
    public ResultData getCookies(Integer id) {
        return baseDataService.getCookies(id);
    }
}
