package com.scccy.videoflowDy.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.videoModel.domain.Config;
import com.scccy.videoModel.mapper.ConfigMapper;
import com.scccy.videoflowDy.service.ConfigService;
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService{

}
