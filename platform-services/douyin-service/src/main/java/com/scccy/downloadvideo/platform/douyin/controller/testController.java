package com.scccy.downloadvideo.platform.douyin.controller;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.service.Impl.TestServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class testController {
    @Resource
    TestServiceImpl testService;


    @GetMapping("/test")
    public ResultData post(Integer id) {
        return ResultData.ok(testService.returnCookie(id));
    }

}
