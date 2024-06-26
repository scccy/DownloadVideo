package com.scccy.videoflowDy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videobase.untils.OkHttpClientUtil;
import com.scccy.videoflowDy.service.FlowDyService;
import com.scccy.videoModel.domain.GatherDay;
import com.scccy.videoModel.mapper.GatherDayMapper;
import com.scccy.videoModel.vo.*;
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
public class FlowDyServiceImpl extends ServiceImpl<GatherDayMapper, GatherDay> implements FlowDyService {

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
        String destinationFolder = "/home/project/DownLoadVideo/video";
//        根据url下载视频跟封面,并且保存在vide下一Dy_nickName/video_name 下
//        OkHttpClientUtil.downloadFiles(gatherDayList,destinationFolder);
        return gatherDayList.stream()
                .map(GatherDay::toDownloadReqVo)
                .collect(Collectors.toList());


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

    @Override
    public Page<GatherDay> getData(GetDataReqVo reqVo) {
        QueryWrapper<GatherDay> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(reqVo.getUid())) {
            queryWrapper.eq("uid", reqVo.getUid());
        }
        if (StringUtils.isNotBlank(reqVo.getNickName())) {
            queryWrapper.like("nickname", reqVo.getNickName());
        }
        if (StringUtils.isNotBlank(reqVo.getDesc())) {
            queryWrapper.like("`desc`", reqVo.getDesc());
        }
        if (StringUtils.isNotBlank(reqVo.getCollectTimeStart())) {
            queryWrapper.ge("collection_time", reqVo.getCollectTimeStart());
        }
        if (StringUtils.isNotBlank(reqVo.getCollectTimeEnd())) {
            queryWrapper.le("collection_time", reqVo.getCollectTimeEnd());
        }
        if (StringUtils.isNotBlank(reqVo.getType())) {
            queryWrapper.eq("type", reqVo.getType());
        }

        Page<GatherDay> page = new Page<>(1,1);
        return gatherDayMapper.selectPage(page, queryWrapper);
    }
}
