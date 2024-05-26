package com.scccy.videoDownloader.controller;

import com.scccy.common.ResultData;
import com.scccy.videoDownloader.service.DouYinService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flowDy")
public class DouyinController {
    @Resource
    DouYinService douYinService;

    @GetMapping(value = "/getCodeLogin")
    public ResultData getDouYinCodeLogin() throws Exception {
        return douYinService.getDouYinCodeLogin();
    }
}
