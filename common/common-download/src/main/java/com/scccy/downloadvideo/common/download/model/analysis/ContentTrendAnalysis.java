package com.scccy.downloadvideo.common.download.model.analysis;

import lombok.Data;
import java.util.Map;
import java.util.List;

@Data
public class ContentTrendAnalysis {
    private Map<String, Long> tagFrequency;
    private Map<Integer, Double> hourlyEngagement;
    private List<String> recommendedTags;
    private Map<String, Double> contentTypePerformance;
} 