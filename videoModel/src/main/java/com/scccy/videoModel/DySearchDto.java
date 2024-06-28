package com.scccy.videoModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DySearchDto {

    private String SearchKeyWords;
    private Integer sort;
    private Integer sortText;
    private Integer publishText;
}
