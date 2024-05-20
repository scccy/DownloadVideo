package com.scccy.downloadDy.controller;

import com.scccy.downloadDy.common.ResultData;
import com.scccy.downloadDy.domain.vo.*;
import com.scccy.downloadDy.service.FlowtkService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flowDy")
public class FlowDyController {

    @Resource
    FlowtkService flowtkService ;

//   根据关键词搜索抖音数据
    @PostMapping("/search")
    public ResultData search(@RequestBody SearchReqVo searchReqVo) throws IOException {
        flowtkService.search(searchReqVo);
        return ResultData.ok();
    }

    @PostMapping("/download")
    public ResultData download(@RequestBody List<Long> idList){
        List<DownloadReqVo> download = flowtkService.download(idList);
        return ResultData.ok().setData(download);
    }

    @PostMapping("/settings")
    public ResultData setting(@RequestBody SettingsReqVo settings) throws IOException {
        SettingsResVo setting = flowtkService.setting(settings);
        return ResultData.ok().setData(setting);
    }

    @PostMapping("/single/")
    public ResultData single(@RequestBody Map<String,String> urlMap) throws IOException {
        SingleReqVo singleReqVo = flowtkService.single(urlMap);
        return ResultData.ok().setData(singleReqVo);
    }

}
