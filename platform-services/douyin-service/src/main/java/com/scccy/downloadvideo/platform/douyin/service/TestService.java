package com.scccy.downloadvideo.platform.douyin.service;

import com.scccy.downloadvideo.common.core.model.dto.CookieDto;
import com.scccy.downloadvideo.common.core.utils.CookieUtils;
import com.scccy.downloadvideo.platform.douyin.service.Impl.TestServiceImpl;

import javax.annotation.Resource;

public class TestService implements TestServiceImpl {
    @Resource
    DouyinDownloadConfigServiceImpl douyinDownloadConfigService;

    @Override
    public CookieDto returnCookie(Integer id) {
        String cookie = douyinDownloadConfigService.getById(id).getCookie();
        System.out.println(cookie);
        CookieDto cookieDto = new CookieDto();
        return cookieDto;
    }
}
