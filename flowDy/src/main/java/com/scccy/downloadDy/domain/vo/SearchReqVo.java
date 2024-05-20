package com.scccy.downloadDy.domain.vo;

import com.scccy.downloadDy.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchReqVo  extends BaseEntity {
    public String keyword;
    public String type;
    public String pages;
    public String sortType;
    public String publishTime;
    public boolean source;
}
