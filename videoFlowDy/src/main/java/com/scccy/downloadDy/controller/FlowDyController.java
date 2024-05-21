package com.scccy.downloadDy.controller;


import com.scccy.common.ResultData;
import com.scccy.downloadDy.domain.GatherDay;
import com.scccy.downloadDy.domain.vo.*;
import com.scccy.downloadDy.service.FlowDyService;
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
    FlowDyService flowDyService;

//   根据关键词搜索抖音数据
    @PostMapping("/search")
    public ResultData search(@RequestBody SearchReqVo searchReqVo) throws IOException {
        flowDyService.search(searchReqVo);
        return ResultData.ok();
    }
//返回下载连接
    @PostMapping("/download")
    public ResultData download(@RequestBody List<Long> idList){
        List<DownloadReqVo> download = flowDyService.download(idList);
        return ResultData.ok().setData(download);
    }

//    修改原始配置
    @PostMapping("/settings")
    public ResultData setting(@RequestBody SettingsReqVo settings) throws IOException {
        SettingsResVo setting = flowDyService.setting(settings);
        return ResultData.ok().setData(setting);
    }

//    提供分享的url直接返回解析结果
    @PostMapping("/single")
    public ResultData single(@RequestBody Map<String,String> urlMap) throws IOException {
        SingleReqVo singleReqVo = flowDyService.single(urlMap);
        return ResultData.ok().setData(singleReqVo);
    }
//    根据条件所有分页查询
    @PostMapping("/getData")
    public ResultData getData(@RequestBody GetDataReqVo getDataReqVo){
       List<GatherDay> gatherDay =  flowDyService.getData(getDataReqVo);
       return ResultData.ok().setData(gatherDay);
    }

}
