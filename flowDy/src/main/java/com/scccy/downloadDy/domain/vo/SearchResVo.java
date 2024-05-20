package com.scccy.downloadDy.domain.vo;

import com.scccy.downloadDy.domain.BaseEntity;
import com.scccy.downloadDy.domain.GatherDay;
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
