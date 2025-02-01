package com.scccy.downloadvideo.platform.douyin.service.Impl;

import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.platform.douyin.service.BaseDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseDataServiceImpl implements BaseDataService {
    private static final Logger log = LoggerFactory.getLogger(BaseDataServiceImpl.class);

    @Override
    public ResultData getCookies(Integer id) {
        log.info("Getting cookies for id: {}", id);
        return ResultData.ok();
    }
}
