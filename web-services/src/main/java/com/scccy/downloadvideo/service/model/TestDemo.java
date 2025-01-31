package com.scccy.downloadvideo.service.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TestDemo {
    private Long id;
    private String name;
    private String description;
} 