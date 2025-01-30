-- app.yaml 相关的表结构

-- 抖音应用配置表
CREATE TABLE IF NOT EXISTS `douyin_app_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `cookie` text COMMENT 'Cookie',
    `naming` varchar(255) DEFAULT '{create}_{desc}' COMMENT '命名格式',
    `path` varchar(255) DEFAULT 'Download' COMMENT '下载路径',
    `timeout` int DEFAULT 10 COMMENT '超时时间',
    `max_retries` int DEFAULT 5 COMMENT '最大重试次数',
    `lyric` tinyint(1) DEFAULT 1 COMMENT '是否下载歌词',
    `max_connections` int DEFAULT 5 COMMENT '最大连接数',
    `max_counts` int DEFAULT 0 COMMENT '最大下载数',
    `max_tasks` int DEFAULT 10 COMMENT '最大任务数',
    `page_counts` int DEFAULT 20 COMMENT '每页数量',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抖音应用配置表';

-- TikTok应用配置表
CREATE TABLE IF NOT EXISTS `tiktok_app_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `cookie` text COMMENT 'Cookie',
    `naming` varchar(255) DEFAULT '{create}_{desc}' COMMENT '命名格式',
    `path` varchar(255) DEFAULT 'Download' COMMENT '下载路径',
    `timeout` int DEFAULT 10 COMMENT '超时时间',
    `max_retries` int DEFAULT 5 COMMENT '最大重试次数',
    `max_connections` int DEFAULT 5 COMMENT '最大连接数',
    `max_counts` int DEFAULT 0 COMMENT '最大下载数',
    `max_tasks` int DEFAULT 5 COMMENT '最大任务数',
    `page_counts` int DEFAULT 5 COMMENT '每页数量',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='TikTok应用配置表';

-- Twitter应用配置表
CREATE TABLE IF NOT EXISTS `twitter_app_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `path` varchar(255) DEFAULT 'Download' COMMENT '下载路径',
    `folderize` tinyint(1) DEFAULT 1 COMMENT '是否建文件夹',
    `mode` varchar(32) DEFAULT 'one' COMMENT '模式',
    `naming` varchar(255) DEFAULT '{create}_{desc}' COMMENT '命名格式',
    `cookie` text COMMENT 'Cookie',
    `interval_field` varchar(32) DEFAULT 'all' COMMENT '间隔',
    `timeout` int DEFAULT 10 COMMENT '超时时间',
    `max_retries` int DEFAULT 5 COMMENT '最大重试次数',
    `max_connections` int DEFAULT 5 COMMENT '最大连接数',
    `max_counts` int DEFAULT 0 COMMENT '最大下载数',
    `max_tasks` int DEFAULT 5 COMMENT '最大任务数',
    `page_counts` int DEFAULT 20 COMMENT '每页数量',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Twitter应用配置表';

-- 微博应用配置表
CREATE TABLE IF NOT EXISTS `weibo_app_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `path` varchar(255) DEFAULT 'Download' COMMENT '下载路径',
    `folderize` tinyint(1) DEFAULT 1 COMMENT '是否建文件夹',
    `mode` varchar(32) DEFAULT 'post' COMMENT '模式',
    `naming` varchar(255) DEFAULT '{create}_{desc}' COMMENT '命名格式',
    `cookie` text COMMENT 'Cookie',
    `interval_field` varchar(32) DEFAULT 'all' COMMENT '间隔',
    `timeout` int DEFAULT 10 COMMENT '超时时间',
    `max_retries` int DEFAULT 5 COMMENT '最大重试次数',
    `max_connections` int DEFAULT 5 COMMENT '最大连接数',
    `max_counts` int DEFAULT 0 COMMENT '最大下载数',
    `max_tasks` int DEFAULT 5 COMMENT '最大任务数',
    `page_counts` int DEFAULT 20 COMMENT '每页数量',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微博应用配置表';

-- Bark应用配置表
CREATE TABLE IF NOT EXISTS `bark_app_config` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `key_value` varchar(255) COMMENT '密钥',
    `token` varchar(255) COMMENT '令牌',
    `mode` varchar(32) DEFAULT 'get' COMMENT '模式',
    `url` varchar(255) DEFAULT 'https://f2.wiki/' COMMENT 'URL',
    `retry` int DEFAULT 3 COMMENT '重试次数',
    `ringtones` int DEFAULT 1 COMMENT '铃声',
    `icon` varchar(255) DEFAULT 'https://f2.wiki/f2-logo-with-shadow.png' COMMENT '图标',
    `level` varchar(32) DEFAULT 'active' COMMENT '级别',
    `volume` int DEFAULT 5 COMMENT '音量',
    `call` tinyint(1) DEFAULT 0 COMMENT '是否呼叫',
    `is_archive` tinyint(1) DEFAULT 1 COMMENT '是否归档',
    `sound` varchar(32) DEFAULT 'birdsong' COMMENT '声音',
    `auto_copy` tinyint(1) DEFAULT 1 COMMENT '自动复制',
    `title` varchar(255) DEFAULT 'F2' COMMENT '标题',
    `body` text COMMENT '内容',
    `copy` text COMMENT '复制内容',
    `group_name` varchar(255) DEFAULT 'F2下载统计' COMMENT '分组',
    `badge` int DEFAULT 1 COMMENT '角标',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '更新时间',
    `create_node` varchar(64) NOT NULL COMMENT '创建节点',
    `update_node` varchar(64) NOT NULL COMMENT '更新节点',
    `del_flag` tinyint(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Bark应用配置表';