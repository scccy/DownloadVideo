package com.scccy.downloadvideo.common.download.model.analysis;

import lombok.Data;
import java.util.Map;

@Data
public class UserActivityAnalysis {
    private Map<String, Long> postFrequency;
    private double averageEngagementRate;
    private Map<String, Double> weekdayEngagement;
    private Map<String, Long> interactionTrend;
} 