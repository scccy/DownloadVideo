package com.scccy.downloadDy.service.impl;

import com.scccy.downloadDy.domain.GatherDay;
import com.scccy.downloadDy.domain.vo.*;
import com.scccy.downloadDy.mapper.GatherDayMapper;
import com.scccy.downloadDy.service.FlowtkService;
import com.scccy.downloadDy.untils.OkHttpClientUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class FlowtkServiceImpl extends GatherDayService implements FlowtkService {

    String baseurl = "http://192.168.32.166:11002";


    @Resource
    private GatherDayMapper gatherDayMapper;

    @Override
    public void search(SearchReqVo searchReqVo) throws IOException {
        SearchResVo post = OkHttpClientUtil.post(baseurl + "/search/", searchReqVo,SearchResVo.class);
         super.saveOrUpdateBatch(post.getData());
    }


    public  List<DownloadReqVo> download(List<Long> idList) {
        List<GatherDay> gatherDayList = gatherDayMapper.selectUsersByIdList(idList);
        return gatherDayList.stream()
                .map(GatherDay::toDownloadReqVo)
                .collect(Collectors.toList());
//        OkHttpClientUtil.downloadFiles(downloadReqVoList,path);

    }

    @Override
    public SettingsResVo setting(SettingsReqVo settings) throws IOException {
        return  OkHttpClientUtil.post(baseurl + "/settings/", settings, SettingsResVo.class);

    }

    @Override
    public SingleReqVo single(Map<String, String> urlMap) throws IOException {
        return OkHttpClientUtil.post(baseurl + "/single/", urlMap, SingleReqVo.class);
    }

    @Override
    public void test(SearchResVo searchResVo) {

        super.saveOrUpdateBatch(searchResVo.getData());
    }

    @Override
    public void test03(SingleReqVo singleReqVo) {
        super.saveOrUpdate(singleReqVo.toGatherDay());
    }
}
