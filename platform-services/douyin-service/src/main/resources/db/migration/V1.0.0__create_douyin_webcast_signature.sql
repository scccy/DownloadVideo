CREATE TABLE IF NOT EXISTS `douyin_webcast_signature` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `room_id` varchar(64) NOT NULL COMMENT '直播间ID',
    `user_unique_id` varchar(64) NOT NULL COMMENT '用户唯一标识',
    `signature` varchar(255) NOT NULL COMMENT '生成的签名',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签名生成时间',
    `expire_time` datetime NOT NULL COMMENT '签名过期时间',
    `used` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已使用 (0:未使用 1:已使用)',
    `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标记 (0:未删除 1:已删除)',
    PRIMARY KEY (`id`),
    KEY `idx_room_id` (`room_id`),
    KEY `idx_user_unique_id` (`user_unique_id`),
    KEY `idx_create_time` (`create_time`),
    KEY `idx_expire_time` (`expire_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抖音直播间签名表'; 