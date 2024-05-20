package com.scccy.downloadDy.controller;

import com.scccy.downloadDy.common.ResultData;
import com.scccy.downloadDy.controller.testPojo.UrlTest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @PostMapping("/test01")
    public ResultData test01(@RequestBody UrlTest urlTest){
        System.out.println(urlTest);
        return ResultData.ok().setData(urlTest);
    }
}
