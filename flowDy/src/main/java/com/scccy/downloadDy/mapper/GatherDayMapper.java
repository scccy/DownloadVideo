package com.scccy.downloadDy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scccy.downloadDy.domain.GatherDay;
import com.scccy.downloadDy.domain.vo.DownloadReqVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GatherDayMapper extends BaseMapper<GatherDay> {
    List<GatherDay> selectUsersByIdList(@Param("idList") List<Long> idList);
}