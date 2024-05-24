package com.scccy.downloadDy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scccy.downloadDy.domain.GatherDay;
import com.scccy.downloadDy.domain.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FlowDyService {
    void search(SearchReqVo searchReqVo) throws IOException;

    List<DownloadReqVo> download(List<Long> idList);

    SettingsResVo setting(SettingsReqVo settings) throws IOException;

    SingleReqVo single(Map<String, String> urlMap) throws IOException;

    void test(SearchResVo searchResVo);

    void test03(SingleReqVo singleReqVo);

    Page<GatherDay> getData(GetDataReqVo getDataReqVo);


}
