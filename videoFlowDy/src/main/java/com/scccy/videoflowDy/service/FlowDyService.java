package com.scccy.videoflowDy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scccy.videoModel.domain.GatherDay;
import com.scccy.videoModel.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FlowDyService extends IService<GatherDay> {
    void search(SearchReqVo searchReqVo) throws IOException;

    List<DownloadReqVo> download(List<Long> idList);

    SettingsResVo setting(SettingsReqVo settings) throws IOException;

    SingleReqVo single(Map<String, String> urlMap) throws IOException;

    void test(SearchResVo searchResVo);

    void test03(SingleReqVo singleReqVo);

    Page<GatherDay> getData(GetDataReqVo getDataReqVo);


}
