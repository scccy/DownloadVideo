package com.scccy.downloadvideo.platform.douyin.service.Impl;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.service.BaseDataService;

public class BaseDataServiceImpl implements BaseDataService {
    @Override
    public ResultData getCookies(Integer id) {
        return ResultData.ok();
    }
}
