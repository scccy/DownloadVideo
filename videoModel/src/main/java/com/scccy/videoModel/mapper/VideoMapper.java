package com.scccy.videoModel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scccy.videoModel.domain.Video;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}