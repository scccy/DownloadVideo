-- defaults.yaml 相关的表结构
-- 抖音默认配置表
CREATE TABLE IF NOT EXISTS `douyin_defaults` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL',
  `music` tinyint(1) DEFAULT NULL COMMENT '音乐',
  `lyric` tinyint(1) DEFAULT NULL COMMENT '歌词',
  `cover` tinyint(1) DEFAULT NULL COMMENT '封面',
  `desc_field` text DEFAULT NULL COMMENT '描述',
  `path` varchar(255) DEFAULT 'Download' COMMENT '下载路径',
  `folderize` tinyint(1) DEFAULT NULL COMMENT '是否建文件夹',
  `mode` varchar(32) DEFAULT NULL COMMENT '模式',
  `naming` varchar(255) DEFAULT NULL COMMENT '命名格式',
  `cookie` text DEFAULT NULL COMMENT 'Cookie',
  `interval_field` varchar(32) DEFAULT 'all' COMMENT '间隔',
  `timeout` int DEFAULT NULL COMMENT '超时时间',
  `max_retries` int DEFAULT NULL COMMENT '最大重试次数',
  `max_connections` int DEFAULT NULL COMMENT '最大连接数',
  `max_counts` int DEFAULT NULL COMMENT '最大下载数',
  `max_tasks` int DEFAULT NULL COMMENT '最大任务数',
  `page_counts` int DEFAULT NULL COMMENT '每页数量',
  `languages` varchar(255) DEFAULT NULL COMMENT '语言',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_node` varchar(64) DEFAULT NULL COMMENT '创建节点',
  `update_node` varchar(64) DEFAULT NULL COMMENT '更新节点',
  `del_flag` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抖音默认配置表';

-- TikTok默认配置表
CREATE TABLE IF NOT EXISTS tiktok_defaults (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    url VARCHAR(255) COMMENT 'URL',
    music TINYINT(1) COMMENT '音乐',
    cover TINYINT(1) COMMENT '封面',
    desc_field TEXT COMMENT '描述',
    path VARCHAR(255) DEFAULT 'Download' COMMENT '下载路径',
    folderize TINYINT(1) COMMENT '是否建文件夹',
    mode VARCHAR(32) COMMENT '模式',
    naming VARCHAR(255) COMMENT '命名格式',
    cookie TEXT COMMENT 'Cookie',
    keyword VARCHAR(255) COMMENT '关键词',
    interval_field VARCHAR(32) DEFAULT 'all' COMMENT '间隔',
    timeout INT COMMENT '超时时间',
    max_connections INT COMMENT '最大连接数',
    max_counts INT COMMENT '最大下载数',
    max_retries INT COMMENT '最大重试次数',
    max_tasks INT COMMENT '最大任务数',
    page_counts INT COMMENT '每页数量',
    languages VARCHAR(255) COMMENT '语言',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    create_node VARCHAR(64) COMMENT '创建节点',
    update_node VARCHAR(64) COMMENT '更新节点',
    del_flag TINYINT(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='TikTok默认配置表';

-- Twitter默认配置表
CREATE TABLE IF NOT EXISTS twitter_defaults (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    url VARCHAR(255) COMMENT 'URL',
    path VARCHAR(255) DEFAULT 'Download' COMMENT '下载路径',
    folderize TINYINT(1) COMMENT '是否建文件夹',
    mode VARCHAR(32) COMMENT '模式',
    naming VARCHAR(255) COMMENT '命名格式',
    cookie TEXT COMMENT 'Cookie',
    interval_field VARCHAR(32) DEFAULT 'all' COMMENT '间隔',
    timeout INT COMMENT '超时时间',
    max_retries INT COMMENT '最大重试次数',
    max_connections INT COMMENT '最大连接数',
    max_counts INT COMMENT '最大下载数',
    max_tasks INT COMMENT '最大任务数',
    page_counts INT COMMENT '每页数量',
    languages VARCHAR(255) COMMENT '语言',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    create_node VARCHAR(64) COMMENT '创建节点',
    update_node VARCHAR(64) COMMENT '更新节点',
    del_flag TINYINT(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Twitter默认配置表';

-- 微博默认配置表
CREATE TABLE IF NOT EXISTS weibo_defaults (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    url VARCHAR(255) COMMENT 'URL',
    path VARCHAR(255) DEFAULT 'Download' COMMENT '下载路径',
    folderize TINYINT(1) COMMENT '是否建文件夹',
    mode VARCHAR(32) COMMENT '模式',
    naming VARCHAR(255) COMMENT '命名格式',
    cookie TEXT COMMENT 'Cookie',
    keyword VARCHAR(255) COMMENT '关键词',
    interval_field VARCHAR(32) DEFAULT 'all' COMMENT '间隔',
    timeout INT COMMENT '超时时间',
    max_retries INT COMMENT '最大重试次数',
    max_connections INT COMMENT '最大连接数',
    max_counts INT COMMENT '最大下载数',
    max_tasks INT COMMENT '最大任务数',
    page_counts INT COMMENT '每页数量',
    languages VARCHAR(255) COMMENT '语言',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    create_node VARCHAR(64) COMMENT '创建节点',
    update_node VARCHAR(64) COMMENT '更新节点',
    del_flag TINYINT(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微博默认配置表';

-- Bark默认配置表
CREATE TABLE IF NOT EXISTS bark_defaults (
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    key_value VARCHAR(255) COMMENT '密钥',
    token VARCHAR(255) COMMENT '令牌',
    title VARCHAR(255) COMMENT '标题',
    body TEXT COMMENT '内容',
    mode VARCHAR(32) COMMENT '模式',
    retry INT COMMENT '重试次数',
    ringtones INT COMMENT '铃声',
    icon VARCHAR(255) COMMENT '图标',
    level VARCHAR(32) COMMENT '级别',
    volume INT COMMENT '音量',
    call TINYINT(1) COMMENT '是否呼叫',
    is_archive TINYINT(1) COMMENT '是否归档',
    sound VARCHAR(32) COMMENT '声音',
    auto_copy TINYINT(1) COMMENT '自动复制',
    copy TEXT COMMENT '复制内容',
    group_name VARCHAR(255) COMMENT '分组',
    badge INT COMMENT '角标',
    create_time DATETIME COMMENT '创建时间',
    update_time DATETIME COMMENT '更新时间',
    create_node VARCHAR(64) COMMENT '创建节点',
    update_node VARCHAR(64) COMMENT '更新节点',
    del_flag TINYINT(1) DEFAULT 0 COMMENT '删除标记',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Bark默认配置表';

-- 其他默认配置表... 