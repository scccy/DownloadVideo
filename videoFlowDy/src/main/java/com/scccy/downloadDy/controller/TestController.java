package com.scccy.downloadDy.controller;


import com.scccy.common.ResultData;
import com.scccy.downloadDy.controller.testPojo.UrlTest;
import com.scccy.downloadDy.domain.vo.SearchResVo;
import com.scccy.downloadDy.domain.vo.SingleReqVo;
import com.scccy.downloadDy.service.FlowDyService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    FlowDyService flowDyService;

    @PostMapping("/test01")
    public ResultData test01(@RequestBody UrlTest urlTest) {
        System.out.println(urlTest);
        return ResultData.ok().setData(urlTest);
    }

    @PostMapping("/test02")
    public ResultData test02(@RequestBody SearchResVo searchResVo) {
        flowDyService.test(searchResVo);
        return ResultData.ok().setData(searchResVo);
    }

    @PostMapping("/test03")
    public ResultData test03(@RequestBody SingleReqVo singleReqVo) {
        flowDyService.test03(singleReqVo);
        return ResultData.ok().setData(singleReqVo);
    }



}