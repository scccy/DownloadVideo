package com.scccy.videoModel.vo;


import com.scccy.videoModel.domain.GatherDay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchResVo extends BaseEntity {
    public List<GatherDay> data;
    public String message;
}
