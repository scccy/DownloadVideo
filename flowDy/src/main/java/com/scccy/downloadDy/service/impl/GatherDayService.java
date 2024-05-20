package com.scccy.downloadDy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scccy.downloadDy.domain.GatherDay;
import com.scccy.downloadDy.mapper.GatherDayMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatherDayService extends ServiceImpl<GatherDayMapper, GatherDay> {
    public boolean saveBatchGatherDays(List<GatherDay> gatherDays) {
        return this.saveBatch(gatherDays);
    }
    public boolean updateBatchGatherDaysById(List<GatherDay> gatherDays) {
        return this.updateBatchById(gatherDays);
    }
    public boolean removeBatchGatherDaysByIds(List<Long> ids) {
        return this.removeByIds(ids);
    }
}
