package com.scccy.downloadvideo.common.core.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class IpUtil {
    
    /**
     * 获取主机名
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            log.error("获取主机名失败", e);
            return "unknown";
        }
    }
    
    /**
     * 获取IP地址
     */
    public static String getIpAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取IP地址失败", e);
            return "unknown";
        }
    }
} 