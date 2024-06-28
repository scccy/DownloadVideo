package com.scccy.videoflowDy.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videoModel.domain.Video;
import com.scccy.videoModel.mapper.VideoMapper;
import com.scccy.videoflowDy.service.VideoService;
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService{

}
