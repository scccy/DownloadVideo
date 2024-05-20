package com.scccy.downloadDy.service;

import com.scccy.downloadDy.domain.vo.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FlowtkService {
    void search(SearchReqVo searchReqVo) throws IOException;

    List<DownloadReqVo> download(List<Long> idList);

    SettingsResVo setting(SettingsReqVo settings) throws IOException;

    SingleReqVo single(Map<String, String> urlMap) throws IOException;

    void test(SearchResVo searchResVo);
}
