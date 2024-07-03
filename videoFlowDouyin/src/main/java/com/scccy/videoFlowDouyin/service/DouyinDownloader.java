package com.scccy.videoFlowDouyin.service;

import com.scccy.videoBase.untils.downloader.OkHttpClientUtil;
import com.scccy.videoFlowDouyin.domain.InfoUserWeb;
import com.scccy.videoFlowDouyin.domain.InfoVideo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DouyinDownloader {
    @Autowired
    private OkHttpClientUtil okHttpClientUtil;
    @Autowired
    private InfoUserWebService infoUserWebService;

    public DouyinDownloader(Map<String, Object> kwargs) {
        if (kwargs == null || kwargs.get("cookie") == null) {
            throw new IllegalArgumentException("cookie不能为空。请提供有效的 cookie 参数，或自动从浏览器获取。如 `--auto-cookie edge`");
        }
    }

    public CompletableFuture<Void> saveLastAwemeId(String secUserId, String awemeId) {
        return CompletableFuture.runAsync(() -> {
            try {
                InfoUserWeb infoUserWeb = new InfoUserWeb();
                infoUserWeb.setSecUserId(secUserId);
                infoUserWeb.setLastAwemeId(awemeId);
                infoUserWebService.saveOrUpdate(infoUserWeb);
            } catch (Exception e) {
                // 处理异常，例如记录日志
                log.error("Error updating user info for secUserId: " + secUserId, e);
                throw new RuntimeException(e);
            }
        });
    }

    public List<InfoVideo> filterAwemeDatasByInterval(List<InfoVideo> awemeDatas, String interval) {
        LocalDateTime[] intervalDates = parseInterval(interval);
        if (awemeDatas == null || intervalDates == null) {
            return null;
        }
        LocalDateTime startDate = intervalDates[0];
        LocalDateTime endDate = intervalDates[1];

        return awemeDatas.stream()
                .filter(data -> isWithinInterval(data, startDate, endDate))
                .collect(Collectors.toList());
    }

    public InfoVideo filterAwemeDatasByInterval(InfoVideo awemeData, String interval) {
        LocalDateTime[] intervalDates = parseInterval(interval);
        if (awemeData == null || intervalDates == null) {
            return null;
        }
        LocalDateTime startDate = intervalDates[0];
        LocalDateTime endDate = intervalDates[1];

        return isWithinInterval(awemeData, startDate, endDate) ? awemeData : null;
    }

    private LocalDateTime[] parseInterval(String interval) {
        if (interval == null || interval.isEmpty()) {
            return null;
        }

        String[] intervals = interval.split("\\|");
        if (intervals.length != 2) {
            log.info("Invalid interval format. Please check the documentation.");
            return null;
        }

        String startStr = intervals[0] + " 00:00:00";
        String endStr = intervals[1] + " 23:59:59";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            LocalDateTime startDate = LocalDateTime.parse(startStr, formatter);
            LocalDateTime endDate = LocalDateTime.parse(endStr, formatter);
            log.info(String.format("Filtering date interval: %s to %s", startDate, endDate));
            return new LocalDateTime[]{startDate, endDate};
        } catch (DateTimeParseException e) {
            log.warn("Invalid date interval format. Please check the documentation.");
            return null;
        }
    }

    private boolean isWithinInterval(InfoVideo data, LocalDateTime startDate, LocalDateTime endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String awemeDateStr = String.valueOf(data.getCreatedAt());
        try {
            LocalDateTime awemeDate = LocalDateTime.parse(awemeDateStr, formatter);
            return !awemeDate.isBefore(startDate) && !awemeDate.isAfter(endDate);
        } catch (DateTimeParseException e) {
            log.info(String.format("Unable to parse aweme creation time: %s", awemeDateStr));
            return false;
        }
    }


}
