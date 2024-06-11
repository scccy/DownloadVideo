package com.scccy.downloadDy.controller;


import com.scccy.common.ResultData;
import com.scccy.downloadDy.domain.TiktokConfig;
import com.scccy.downloadDy.service.DouYinService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "抖音")
@RestController
@RequestMapping("/flowDy_test")
public class DouyinController {
    @Resource
    DouYinService douYinService;


    @PostMapping("/settings")
    public ResultData settings(@RequestBody TiktokConfig tiktokConfig){
        douYinService.saveSettings(tiktokConfig);
        return  ResultData.ok();
    }
    @GetMapping(value = "/getCodeLogin")
    public ResultData getDouYinCodeLogin() throws Exception {
        return douYinService.getDouYinCodeLogin();
    }

    @GetMapping("/search")
    public  ResultData search(String url){
        douYinService.search(url);
        return ResultData.ok();
    }


}
