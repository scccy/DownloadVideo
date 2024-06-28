package com.scccy.videoflowDy.controller;


import com.scccy.videoModel.DySearchDto;
import com.scccy.videoModel.domain.ConfigDouyin;
import com.scccy.videobase.common.ResultData;
import com.scccy.videoflowDy.service.DouYinService;
import com.scccy.videoModel.domain.CollectData;
import com.scccy.videoModel.domain.ConfigTiktok;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

//@Tag(name = "抖音")
@RestController
@RequestMapping("/flowDy_test")
public class DouyinController {
    @Resource
    DouYinService douYinService;


    @PostMapping("/settings")
    public ResultData settings(@RequestBody ConfigDouyin configDouyin){
        douYinService.saveSettings(configDouyin);
        return  ResultData.ok();
    }
    @GetMapping(value = "/getCodeLogin")
    public ResultData getDouYinCodeLogin() throws Exception {
        return douYinService.getDouYinCodeLogin();
    }
// todo:f2还没抄
    @GetMapping("/search")
    public  ResultData search(@RequestBody DySearchDto dySearchDto){
//        douYinService.search(dySearchDto);
        return ResultData.ok();
    }


    /**
     * @param collectData
     * @return
     */
    @PostMapping("/FavoritesData")
    public  ResultData FavoritesData(@RequestBody  CollectData collectData){
         douYinService.FavoritesData(collectData);
        return  ResultData.ok();
    }

}
