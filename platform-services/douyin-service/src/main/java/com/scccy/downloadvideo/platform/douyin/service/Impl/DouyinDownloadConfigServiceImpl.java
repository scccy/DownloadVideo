package com.scccy.downloadvideo.platform.douyin.service.Impl;

import com.alibaba.fastjson2.JSONObject;
import com.scccy.downloadvideo.common.core.model.ResultData;
import com.scccy.downloadvideo.common.core.utils.CookieUtils;
import com.scccy.downloadvideo.common.core.utils.MonoUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.downloadvideo.platform.douyin.model.DouyinDownloadConfig;
import com.scccy.downloadvideo.platform.douyin.mapper.DouyinDownloadConfigMapper;
import com.scccy.downloadvideo.platform.douyin.service.DouyinDownloadConfigService;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class DouyinDownloadConfigServiceImpl extends ServiceImpl<DouyinDownloadConfigMapper, DouyinDownloadConfig> implements DouyinDownloadConfigService{

    @Override
    public Mono<ResultData> getCookies(Long id) {
        return MonoUtils.back(() -> {
            DouyinDownloadConfig config = super.getById(id);
            JSONObject jsonObject = CookieUtils.cookieToJson(config.getCookie());
            return jsonObject;  // 直接返回查询结果
        });
    }


}
