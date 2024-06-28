package com.scccy.videoflowDy.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videoModel.domain.FfmpegQueue;
import com.scccy.videoModel.mapper.FfmpegQueueMapper;
import com.scccy.videoflowDy.service.FfmpegQueueService;
@Service
public class FfmpegQueueServiceImpl extends ServiceImpl<FfmpegQueueMapper, FfmpegQueue> implements FfmpegQueueService{

}
