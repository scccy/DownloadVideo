-- conf.yaml 相关的表结构

-- Bark配置表
CREATE TABLE IF NOT EXISTS `bark_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `headers_user_agent` varchar(512) DEFAULT 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0' COMMENT 'User-Agent头',
    `headers_referer` varchar(512) DEFAULT 'https://api.day.app/' COMMENT 'Referer头',
    `encryption_algorithm` varchar(32) DEFAULT 'AES128' COMMENT '加密算法',
    `encryption_enable` tinyint(1) DEFAULT 1 COMMENT '是否启用加密',
    `encryption_mode` varchar(32) DEFAULT 'CBC' COMMENT '加密模式',
    `encryption_padding` varchar(32) DEFAULT 'PKCS7' COMMENT '填充方式',
    `encryption_key` varchar(128) DEFAULT '' COMMENT '加密密钥',
    `proxies_http` varchar(255) DEFAULT NULL COMMENT 'HTTP代理',
    `proxies_https` varchar(255) DEFAULT NULL COMMENT 'HTTPS代理',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Bark配置表';

-- 抖音配置表
CREATE TABLE IF NOT EXISTS `douyin_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `encryption` varchar(255) DEFAULT 'ab' COMMENT '加密参数',
    `base_request_model_version_code` varchar(50) DEFAULT '290100' COMMENT '版本号',
    `base_request_model_version_name` varchar(50) DEFAULT '29.1.0' COMMENT '版本名称',
    `base_request_model_browser_language` varchar(50) DEFAULT 'zh-CN' COMMENT '浏览器语言',
    `base_request_model_browser_platform` varchar(50) DEFAULT 'Win32' COMMENT '浏览器平台',
    `base_request_model_browser_name` varchar(50) DEFAULT 'Edge' COMMENT '浏览器名称',
    `base_request_model_browser_version` varchar(50) DEFAULT '130.0.0.0' COMMENT '浏览器版本',
    `base_request_model_engine_name` varchar(50) DEFAULT 'Blink' COMMENT '引擎名称',
    `base_request_model_engine_version` varchar(50) DEFAULT '130.0.0.0' COMMENT '引擎版本',
    `base_request_model_os_name` varchar(50) DEFAULT 'Windows' COMMENT '操作系统名称',
    `base_request_model_os_version` varchar(50) DEFAULT '10' COMMENT '操作系统版本',
    `headers_user_agent` varchar(500) DEFAULT 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0' COMMENT 'User-Agent',
    `headers_referer` varchar(255) DEFAULT 'https://www.douyin.com/' COMMENT 'Referer',
    `proxies_http` varchar(255) DEFAULT NULL COMMENT 'HTTP代理',
    `proxies_https` varchar(255) DEFAULT NULL COMMENT 'HTTPS代理',
    `wss_domain` varchar(255) DEFAULT 'localhost' COMMENT 'WSS域名',
    `wss_port` int DEFAULT 8765 COMMENT 'WSS端口',
    `wss_verify` tinyint(1) DEFAULT 0 COMMENT 'WSS验证',
    `ms_token_url` varchar(255) DEFAULT 'https://mssdk.bytedance.com/web/report' COMMENT 'msToken URL',
    `ms_token_magic` bigint(20) DEFAULT 538969122 COMMENT 'msToken magic',
    `ms_token_version` int DEFAULT 1 COMMENT 'msToken 版本',
    `ms_token_data_type` int DEFAULT 8 COMMENT 'msToken 数据类型',
    `ms_token_str_data` text COMMENT 'msToken 字符串数据',
    `ttwid_url` varchar(255) DEFAULT 'https://ttwid.bytedance.com/ttwid/union/register/' COMMENT 'ttwid URL',
    `ttwid_data` text COMMENT 'ttwid 数据',
    `webid_url` varchar(255) DEFAULT 'https://mcs.zijieapi.com/webid' COMMENT 'webid URL',
    `webid_app_id` int DEFAULT 6383 COMMENT 'webid 应用ID',
    `webid_sdk_version` varchar(50) DEFAULT '5.1.18_zip' COMMENT 'webid SDK版本',
    `webid_device_platform` varchar(50) DEFAULT 'web' COMMENT 'webid 设备平台',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抖音配置表';

-- TikTok配置表
CREATE TABLE IF NOT EXISTS `tiktok_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `base_request_model_browser_language` varchar(50) DEFAULT 'zh-CN' COMMENT '浏览器语言',
    `base_request_model_browser_name` varchar(50) DEFAULT 'Mozilla' COMMENT '浏览器名称',
    `base_request_model_browser_platform` varchar(50) DEFAULT 'Win32' COMMENT '浏览器平台',
    `base_request_model_browser_version` varchar(500) DEFAULT '5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36' COMMENT '浏览器版本',
    `base_request_model_device_id` varchar(50) DEFAULT '7377772863376426514' COMMENT '设备ID',
    `base_request_model_device_platform` varchar(50) DEFAULT 'web_pc' COMMENT '设备平台',
    `base_request_model_os` varchar(50) DEFAULT 'windows' COMMENT '操作系统',
    `base_request_model_region` varchar(50) DEFAULT 'SG' COMMENT '地区',
    `base_request_model_priority_region` varchar(50) DEFAULT '' COMMENT '优先地区',
    `base_request_model_webcast_language` varchar(50) DEFAULT 'zh-Hans' COMMENT '直播语言',
    `base_request_model_tz_name` varchar(50) DEFAULT 'Asia/Hong_Kong' COMMENT '时区名称',
    `headers_user_agent` varchar(500) DEFAULT 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36' COMMENT 'User-Agent',
    `headers_referer` varchar(255) DEFAULT 'https://www.tiktok.com/' COMMENT 'Referer',
    `proxies_http` varchar(255) DEFAULT NULL COMMENT 'HTTP代理',
    `proxies_https` varchar(255) DEFAULT NULL COMMENT 'HTTPS代理',
    `wss_domain` varchar(255) DEFAULT 'localhost' COMMENT 'WSS域名',
    `wss_port` int DEFAULT 8766 COMMENT 'WSS端口',
    `wss_verify` tinyint(1) DEFAULT 0 COMMENT 'WSS验证',
    `ms_token_url` varchar(255) COMMENT 'msToken URL',
    `ms_token_url2` varchar(255) COMMENT 'msToken URL2',
    `ms_token_magic` bigint(20) DEFAULT 538969122 COMMENT 'msToken magic',
    `ms_token_version` int DEFAULT 1 COMMENT 'msToken 版本',
    `ms_token_data_type` int DEFAULT 8 COMMENT 'msToken 数据类型',
    `ms_token_str_data` text COMMENT 'msToken 字符串数据',
    `ttwid_url` varchar(255) DEFAULT 'https://www.tiktok.com/ttwid/check/' COMMENT 'ttwid URL',
    `ttwid_data` text COMMENT 'ttwid 数据',
    `ttwid_cookie` text COMMENT 'ttwid cookie',
    `odin_tt_url` varchar(1024) COMMENT 'odin_tt URL',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='TikTok配置表';

-- Twitter配置表
CREATE TABLE IF NOT EXISTS `twitter_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `headers_user_agent` varchar(500) DEFAULT 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0' COMMENT 'User-Agent',
    `headers_referer` varchar(255) DEFAULT 'https://twitter.com/' COMMENT 'Referer',
    `headers_authorization` varchar(500) DEFAULT 'Bearer AAAAAAAAAAAAAAAAAAAAANRILgAAAAAAnNwIzUejRCOuH5E6I8xnZz4puTs%3D1Zv7ttfk8LF81IUq16cHjhLTvJu4FA33AGWWjCpTnA' COMMENT '授权token',
    `headers_x_csrf_token` varchar(255) COMMENT 'CSRF Token',
    `proxies_http` varchar(255) DEFAULT NULL COMMENT 'HTTP代理',
    `proxies_https` varchar(255) DEFAULT NULL COMMENT 'HTTPS代理',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Twitter配置表';

-- 微博配置表
CREATE TABLE IF NOT EXISTS `weibo_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `headers_user_agent` varchar(500) DEFAULT 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0' COMMENT 'User-Agent',
    `headers_referer` varchar(255) DEFAULT 'https://weibo.com/' COMMENT 'Referer',
    `proxies_http` varchar(255) DEFAULT NULL COMMENT 'HTTP代理',
    `proxies_https` varchar(255) DEFAULT NULL COMMENT 'HTTPS代理',
    `visitor_url` varchar(255) DEFAULT 'https://passport.weibo.com/visitor/genvisitor2' COMMENT '访客URL',
    `visitor_cb` varchar(255) DEFAULT 'visitor_gray_callback' COMMENT '访客回调',
    `visitor_tid` varchar(255) COMMENT '访客TID',
    `visitor_from` varchar(255) DEFAULT 'weibo' COMMENT '访客来源',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微博配置表';

-- 其他配置表... 