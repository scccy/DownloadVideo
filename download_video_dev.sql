/*
 Navicat Premium Data Transfer

 Source Server         : master
 Source Server Type    : MySQL
 Source Server Version : 80037 (8.0.37-0ubuntu0.22.04.3)
 Source Host           : scccy6.cn:11001
 Source Schema         : download_video_dev

 Target Server Type    : MySQL
 Target Server Version : 80037 (8.0.37-0ubuntu0.22.04.3)
 File Encoding         : 65001

 Date: 30/06/2024 16:18:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_app_config
-- ----------------------------
DROP TABLE IF EXISTS `config_app_config`;
CREATE TABLE `config_app_config` (
                                     `config_app_id` int NOT NULL AUTO_INCREMENT,
                                     `config_app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                     `base_request_model_version_code` varchar(255) DEFAULT NULL,
                                     `base_request_model_version_name` varchar(255) DEFAULT NULL,
                                     `base_request_model_browser_language` varchar(255) DEFAULT NULL,
                                     `base_request_model_browser_platform` varchar(255) DEFAULT NULL,
                                     `base_request_model_browser_name` varchar(255) DEFAULT NULL,
                                     `base_request_model_browser_version` varchar(255) DEFAULT NULL,
                                     `base_request_model_engine_name` varchar(255) DEFAULT NULL,
                                     `base_request_model_engine_version` varchar(255) DEFAULT NULL,
                                     `base_request_model_os_name` varchar(255) DEFAULT NULL,
                                     `base_request_model_os_version` varchar(255) DEFAULT NULL,
                                     `base_request_model_region` varchar(255) DEFAULT NULL,
                                     `base_request_model_priority_region` varchar(255) DEFAULT NULL,
                                     `base_request_model_webcast_language` varchar(255) DEFAULT NULL,
                                     `base_request_model_tz_name` varchar(255) DEFAULT NULL,
                                     `base_request_model_device_id` varchar(255) DEFAULT NULL,
                                     `base_request_model_device_platform` varchar(255) DEFAULT NULL,
                                     `base_live_model_language` varchar(255) DEFAULT NULL,
                                     `base_live_model_browser_language` varchar(255) DEFAULT NULL,
                                     `base_live_model_browser_platform` varchar(255) DEFAULT NULL,
                                     `base_live_model_browser_name` varchar(255) DEFAULT NULL,
                                     `base_live_model_browser_version` varchar(255) DEFAULT NULL,
                                     `headers_user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                     `headers_referer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                     `ms_token_url` varchar(255) DEFAULT NULL,
                                     `ms_token_url2` varchar(255) DEFAULT NULL,
                                     `ms_token_magic` int DEFAULT NULL,
                                     `ms_token_version` int DEFAULT NULL,
                                     `ms_token_data_type` int DEFAULT NULL,
                                     `ms_token_str_data` text,
                                     `ttwid_url` varchar(255) DEFAULT NULL,
                                     `ttwid_data` text,
                                     `ttwid_cookie` varchar(255) DEFAULT NULL,
                                     `webid_url` varchar(255) DEFAULT NULL,
                                     `webid_body_app_id` int DEFAULT NULL,
                                     `webid_body_referer` varchar(255) DEFAULT NULL,
                                     `webid_body_url` varchar(255) DEFAULT NULL,
                                     `webid_body_user_agent` varchar(255) DEFAULT NULL,
                                     `webid_body_user_unique_id` varchar(255) DEFAULT NULL,
                                     `odin_tt_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
                                     `visitor_url` varchar(255) DEFAULT NULL,
                                     `visitor_cb` varchar(255) DEFAULT NULL,
                                     `visitor_tid` varchar(255) DEFAULT NULL,
                                     `visitor_from` varchar(255) DEFAULT NULL,
                                     `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                     `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     `del_flag` int DEFAULT NULL,
                                     `created_by` varchar(255) DEFAULT NULL,
                                     `updated_by` varchar(255) DEFAULT NULL,
                                     PRIMARY KEY (`config_app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of config_app_config
-- ----------------------------
BEGIN;
INSERT INTO `config_app_config` (`config_app_id`, `config_app_name`, `base_request_model_version_code`, `base_request_model_version_name`, `base_request_model_browser_language`, `base_request_model_browser_platform`, `base_request_model_browser_name`, `base_request_model_browser_version`, `base_request_model_engine_name`, `base_request_model_engine_version`, `base_request_model_os_name`, `base_request_model_os_version`, `base_request_model_region`, `base_request_model_priority_region`, `base_request_model_webcast_language`, `base_request_model_tz_name`, `base_request_model_device_id`, `base_request_model_device_platform`, `base_live_model_language`, `base_live_model_browser_language`, `base_live_model_browser_platform`, `base_live_model_browser_name`, `base_live_model_browser_version`, `headers_user_agent`, `headers_referer`, `ms_token_url`, `ms_token_url2`, `ms_token_magic`, `ms_token_version`, `ms_token_data_type`, `ms_token_str_data`, `ttwid_url`, `ttwid_data`, `ttwid_cookie`, `webid_url`, `webid_body_app_id`, `webid_body_referer`, `webid_body_url`, `webid_body_user_agent`, `webid_body_user_unique_id`, `odin_tt_url`, `visitor_url`, `visitor_cb`, `visitor_tid`, `visitor_from`, `created_at`, `updated_at`, `del_flag`, `created_by`, `updated_by`) VALUES (1, 'douyin', '190500', '19.5.0', 'zh-CN', 'Win32', 'Edge', '122.0.0.0', 'Blink', '122.0.0.0', 'Windows', '10', NULL, NULL, NULL, NULL, NULL, NULL, 'zh-CN', 'zh-CN', 'Win32', 'Edge', '119.0.0.0', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', 'https://www.douyin.com/', 'https://mssdk.bytedance.com/web/report', NULL, 538969122, 1, 8, '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', 'https://ttwid.bytedance.com/ttwid/union/register/', '{\"region\":\"cn\",\"aid\":1768,\"needFid\":false,\"service\":\"www.ixigua.com\",\"migrate_info\":{\"ticket\":\"\",\"source\":\"node\"},\"cbUrlProtocol\":\"https\",\"union\":true}', NULL, 'https://mcs.zijieapi.com/webid?aid=6383&sdk_version=5.1.18_zip&device_platform=web', 6383, 'https://www.douyin.com/', 'https://www.douyin.com/', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '', NULL, NULL, NULL, NULL, NULL, '2024-06-29 15:26:49', '2024-06-29 15:35:00', 0, NULL, NULL);
INSERT INTO `config_app_config` (`config_app_id`, `config_app_name`, `base_request_model_version_code`, `base_request_model_version_name`, `base_request_model_browser_language`, `base_request_model_browser_platform`, `base_request_model_browser_name`, `base_request_model_browser_version`, `base_request_model_engine_name`, `base_request_model_engine_version`, `base_request_model_os_name`, `base_request_model_os_version`, `base_request_model_region`, `base_request_model_priority_region`, `base_request_model_webcast_language`, `base_request_model_tz_name`, `base_request_model_device_id`, `base_request_model_device_platform`, `base_live_model_language`, `base_live_model_browser_language`, `base_live_model_browser_platform`, `base_live_model_browser_name`, `base_live_model_browser_version`, `headers_user_agent`, `headers_referer`, `ms_token_url`, `ms_token_url2`, `ms_token_magic`, `ms_token_version`, `ms_token_data_type`, `ms_token_str_data`, `ttwid_url`, `ttwid_data`, `ttwid_cookie`, `webid_url`, `webid_body_app_id`, `webid_body_referer`, `webid_body_url`, `webid_body_user_agent`, `webid_body_user_unique_id`, `odin_tt_url`, `visitor_url`, `visitor_cb`, `visitor_tid`, `visitor_from`, `created_at`, `updated_at`, `del_flag`, `created_by`, `updated_by`) VALUES (2, 'tiktok', NULL, NULL, 'zh-CN', 'Win32', 'Mozilla', '5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', NULL, NULL, 'windows', NULL, 'SG', '', 'zh-Hans', 'Asia/Hong_Kong', '7377772863376426514', 'web_pc', NULL, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36', 'https://www.tiktok.com/', 'https://mssdk-sg.tiktok.com/web/common?msToken=QnC7zMMh1cpaDTxHDHnabNOrqaWv49JwA1IAq3AIFvrdaqQi8Rs_YlXSya1vN-4b6C1MgpWpS2cL1oakaUEDe3pUDMLpCbdSc3b3V98Fux0AuwXn_9Ns3FyMTnFRmSOOOVeGg6bVXMSGoMG6dq3k', NULL, 538969122, 1, 8, '3g+ZrAO5JQgfqCRzb689RAbiueodAexjAjn2plyPVBdOArbRM66UOTnZPoVTDDw8GZ8TL/CO13bWedlvWSA8kCODHA6mjO1Af5hgak+1NHbb8EvfuahWFDL8x8hruWn4t6qb79DhOy64REx1nsO6ub4SX4cKdgs+ZXTjUItB2WKddXo7CKikuywT8SJ0TcwZ+z/9hSsawqJXawopS5H8bOF0o3qPn+CwkoWkXBUu4iwkI5UzL9/k18Gwq1BxAiCbyl2kCHJaox7md1H+KfQ0lU9fBhfVBuXa3jNkT5GfyASaBNeJXdhHwuPfEGI6a+4FOaO0MEGzOWDzhu6joo89ooEPo1XV+UJE2KLC5tStxSBibUvn+wZSfGmcpPfYl0jB0H2vLqyaveYraOx8WRE/G/Y9DkbBLJvz7E8sDZ/v1fCUVs1JDDewE/yn2aqOHr3NxUjOnylKl5WWSCPeSR4ZTXRR1ZJhvpGKP7XYc3tkOjBSQ70cXnl53dAQ6aouUVMcztSlkXh4goI46tgJDjTiJJILHBr03dM/1KwVzqoEEO8f7JBhfQo4OR6Y48PXomshJmZhDiEmtkqLndpZY2I/B8q0EShuPlDCFRW7iob6kFahyqDfpOmK15kzCX2n7eu7zqDGgpJuHEtULF9803lR5QBt104ET49RbnDJ+ipbz1bFvS2FJQ2rfktNlwjqdBT8UzfFPHyZk6z4cSxYIfPRj1lB8fDwTwv3PUSRxUZSwkuGoZbc6j/6hxwZaY0xxIEbb9DlOmQZY4qyiqSziZ8HGWsQ9uD95dO4lX6GlZaeh7mKsjLSKSdUt13YeGAciqjTvSPyO/pZ5xc3+i+5hY4A290/JiTmIyFBOrRrlmIhPEQe9OEJn0DPXYgliYUO1nljEi6q/4HMFdXFmlw6nC8/kL9EeinisX7g6PbdHuRcKONXZpXxBevL8xD5Z8SvFKH/uQmYHrJlybiKHjDIMq7vc59NBw2VDQBmRqa8NTh2XLwIryjoBIHuNSBngSZwJE==', 'https://www.tiktok.com/ttwid/check/', '{\"aid\":1988,\"service\":\"www.tiktok.com\",\"union\":false,\"unionHost\":\"\",\"needFid\":false,\"fid\":\"\",\"migrate_priority\":0}', 'ttwid=1%7C3uOVjidbOFBmdS6Aci5oeBb8Ta-HWpeyp2dvhl2Ib2E%7C1716637053%7C462867ee452aecd60a854225b854ff4498e9b99c13d4826a3e2a4e58a4c55134;', NULL, NULL, NULL, NULL, NULL, NULL, 'https://www.tiktok.com/passport/web/account/info/?WebIdLastTime=1716637053&aid=1459&app_language=zh-Hans&app_name=tiktok_web&browser_language=zh-CN&browser_name=Mozilla&browser_online=true&browser_platform=Win32&browser_version=5.0%20%28Windows%20NT%2010.0%3B%20Win64%3B%20x64%29%20AppleWebKit%2F537.36%20%28KHTML%2C%20like%20Gecko%29%20Chrome%2F124.0.0.0%20Safari%2F537.36&channel=tiktok_web&cookie_enabled=true&device_id=7372899909097571857&device_platform=web_pc&focus_state=true&from_page=fyp&history_len=2&is_fullscreen=false&is_page_visible=true&odinId=7372898697492972561&os=windows&priority_region=&referer=&region=SG&screen_height=1080&screen_width=1920&tz_name=Asia%2FHong_Kong&webcast_language=zh-Hans', NULL, NULL, NULL, NULL, '2024-06-29 15:26:49', '2024-06-29 15:39:21', 0, NULL, NULL);
INSERT INTO `config_app_config` (`config_app_id`, `config_app_name`, `base_request_model_version_code`, `base_request_model_version_name`, `base_request_model_browser_language`, `base_request_model_browser_platform`, `base_request_model_browser_name`, `base_request_model_browser_version`, `base_request_model_engine_name`, `base_request_model_engine_version`, `base_request_model_os_name`, `base_request_model_os_version`, `base_request_model_region`, `base_request_model_priority_region`, `base_request_model_webcast_language`, `base_request_model_tz_name`, `base_request_model_device_id`, `base_request_model_device_platform`, `base_live_model_language`, `base_live_model_browser_language`, `base_live_model_browser_platform`, `base_live_model_browser_name`, `base_live_model_browser_version`, `headers_user_agent`, `headers_referer`, `ms_token_url`, `ms_token_url2`, `ms_token_magic`, `ms_token_version`, `ms_token_data_type`, `ms_token_str_data`, `ttwid_url`, `ttwid_data`, `ttwid_cookie`, `webid_url`, `webid_body_app_id`, `webid_body_referer`, `webid_body_url`, `webid_body_user_agent`, `webid_body_user_unique_id`, `odin_tt_url`, `visitor_url`, `visitor_cb`, `visitor_tid`, `visitor_from`, `created_at`, `updated_at`, `del_flag`, `created_by`, `updated_by`) VALUES (3, 'twitter', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', 'https://twitter.com/', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2024-06-29 15:28:49', '2024-06-29 15:34:27', 0, NULL, NULL);
INSERT INTO `config_app_config` (`config_app_id`, `config_app_name`, `base_request_model_version_code`, `base_request_model_version_name`, `base_request_model_browser_language`, `base_request_model_browser_platform`, `base_request_model_browser_name`, `base_request_model_browser_version`, `base_request_model_engine_name`, `base_request_model_engine_version`, `base_request_model_os_name`, `base_request_model_os_version`, `base_request_model_region`, `base_request_model_priority_region`, `base_request_model_webcast_language`, `base_request_model_tz_name`, `base_request_model_device_id`, `base_request_model_device_platform`, `base_live_model_language`, `base_live_model_browser_language`, `base_live_model_browser_platform`, `base_live_model_browser_name`, `base_live_model_browser_version`, `headers_user_agent`, `headers_referer`, `ms_token_url`, `ms_token_url2`, `ms_token_magic`, `ms_token_version`, `ms_token_data_type`, `ms_token_str_data`, `ttwid_url`, `ttwid_data`, `ttwid_cookie`, `webid_url`, `webid_body_app_id`, `webid_body_referer`, `webid_body_url`, `webid_body_user_agent`, `webid_body_user_unique_id`, `odin_tt_url`, `visitor_url`, `visitor_cb`, `visitor_tid`, `visitor_from`, `created_at`, `updated_at`, `del_flag`, `created_by`, `updated_by`) VALUES (4, 'weibo', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36 Edg/121.0.0.0', 'https://weibo.com/', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://passport.weibo.com/visitor/genvisitor2', 'visitor_gray_callback', NULL, 'weibo', '2024-06-29 15:28:49', '2024-06-29 15:34:27', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_base
-- ----------------------------
DROP TABLE IF EXISTS `config_base`;
CREATE TABLE `config_base` (
                               `config_base_id` int NOT NULL AUTO_INCREMENT,
                               `naming` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                               `path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                               `lyric` enum('yes','no') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                               `timeout` int DEFAULT NULL,
                               `max_retries` int DEFAULT NULL,
                               `max_connections` int DEFAULT NULL,
                               `max_counts` int DEFAULT NULL,
                               `max_tasks` int DEFAULT NULL,
                               `page_counts` int DEFAULT NULL,
                               `created_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                               `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                               `created_by` varchar(255) DEFAULT NULL,
                               `updated_by` varchar(255) DEFAULT NULL,
                               `del_flag` varchar(255) DEFAULT NULL,
                               PRIMARY KEY (`config_base_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of config_base
-- ----------------------------
BEGIN;
INSERT INTO `config_base` (`config_base_id`, `naming`, `path`, `lyric`, `timeout`, `max_retries`, `max_connections`, `max_counts`, `max_tasks`, `page_counts`, `created_at`, `updated_at`, `created_by`, `updated_by`, `del_flag`) VALUES (1, NULL, '/home/download/video', NULL, 10, 5, 5, 0, 10, 20, NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_douyin
-- ----------------------------
DROP TABLE IF EXISTS `config_douyin`;
CREATE TABLE `config_douyin` (
                                 `config_douyin_id` int NOT NULL AUTO_INCREMENT,
                                 `cookie` text,
                                 `config_base_id` int DEFAULT NULL,
                                 `config_app_id` int DEFAULT NULL,
                                 `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                 `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_by` varchar(255) DEFAULT NULL,
                                 `del_flag` int DEFAULT NULL,
                                 PRIMARY KEY (`config_douyin_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of config_douyin
-- ----------------------------
BEGIN;
INSERT INTO `config_douyin` (`config_douyin_id`, `cookie`, `config_base_id`, `config_app_id`, `created_by`, `updated_at`, `created_at`, `updated_by`, `del_flag`) VALUES (1, 'ttwid=1%7CQmuQwANJtxNz2MkvA9DoTCaecyR0c4KcBFUM1dvsdcs%7C1712659329%7Ca92475a4901be8e824cb7ca608556aab3264c98f9c86ab8f3c76d6ff6c8f2168; xgplayer_user_id=8443777764; bd_ticket_guard_client_web_domain=2; passport_assist_user=CkFOjm_TkumBrDEUNCFH02-K309Moj5qyAQjN4V6GmRnMuzYKsV43lpmN3q-W7cSsE3sOlIQj1CFO5juuHvWJjgm1xpKCjw1upyBsAQyC5g7hSy4jEYzRu6zI_ONb-bA9fPnKe8wZFCWKGVzqiB5wqKxE4mCfCFLADT9PSoUagS-yv8Q2pnODRiJr9ZUIAEiAQNXMHIk; n_mh=srr5-XsiHKsTJQVBOHi26Q-zsWbvz-SD55KDiJUvnIM; sso_uid_tt=4dada74646686b57b68a36397a254930; sso_uid_tt_ss=4dada74646686b57b68a36397a254930; toutiao_sso_user=21f990d82836f619895fbde454f54d7b; toutiao_sso_user_ss=21f990d82836f619895fbde454f54d7b; uid_tt=b66c271ad1f60a5a760050d028fb2598; uid_tt_ss=b66c271ad1f60a5a760050d028fb2598; sid_tt=2abb01d2ee84b1956f8c9977c24c3d96; sessionid=2abb01d2ee84b1956f8c9977c24c3d96; sessionid_ss=2abb01d2ee84b1956f8c9977c24c3d96; LOGIN_STATUS=1; store-region=cn-js; store-region-src=uid; SEARCH_RESULT_LIST_TYPE=%22single%22; my_rd=2; sid_ucp_sso_v1=1.0.0-KDJjNzcwNzYxM2VmODk3N2U0MmIzODM3YjNmN2JhNjVjM2ZiYzdjNzQKHwiM1NDYmoyHAxD08dyxBhjvMSAMMLDk6pgGOAZA9AcaAmxxIiAyMWY5OTBkODI4MzZmNjE5ODk1ZmJkZTQ1NGY1NGQ3Yg; ssid_ucp_sso_v1=1.0.0-KDJjNzcwNzYxM2VmODk3N2U0MmIzODM3YjNmN2JhNjVjM2ZiYzdjNzQKHwiM1NDYmoyHAxD08dyxBhjvMSAMMLDk6pgGOAZA9AcaAmxxIiAyMWY5OTBkODI4MzZmNjE5ODk1ZmJkZTQ1NGY1NGQ3Yg; s_v_web_id=verify_lwbq0avs_gBr0oPkn_R4zh_4Ez8_92GR_5il846DR0U9O; douyin.com; device_web_cpu_core=2; device_web_memory_size=8; dy_swidth=1680; dy_sheight=1050; csrf_session_id=1b9773e2b0853227486e035197f14454; strategyABtestKey=%221718365893.778%22; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A0.7%7D; passport_csrf_token=2ef37ff5b2554913fc6a4f3d7fd49930; passport_csrf_token_default=2ef37ff5b2554913fc6a4f3d7fd49930; sid_guard=2abb01d2ee84b1956f8c9977c24c3d96%7C1718365900%7C5184000%7CTue%2C+13-Aug-2024+11%3A51%3A40+GMT; sid_ucp_v1=1.0.0-KGNkMDhmZWNhM2JmODZjNDM1M2RhYzRhNTJlNjNjNGI1NTc2MTgxZGMKGwiM1NDYmoyHAxDM3bCzBhjvMSAMOAZA9AdIBBoCbGYiIDJhYmIwMWQyZWU4NGIxOTU2ZjhjOTk3N2MyNGMzZDk2; ssid_ucp_v1=1.0.0-KGNkMDhmZWNhM2JmODZjNDM1M2RhYzRhNTJlNjNjNGI1NTc2MTgxZGMKGwiM1NDYmoyHAxDM3bCzBhjvMSAMOAZA9AdIBBoCbGYiIDJhYmIwMWQyZWU4NGIxOTU2ZjhjOTk3N2MyNGMzZDk2; pwa2=%220%7C0%7C1%7C0%22; FOLLOW_NUMBER_YELLOW_POINT_INFO=%22MS4wLjABAAAAI8kHNczjmKhh_NUBUFGijIY6kHVW633dpD9b5PdZ2A68QtZKUEHsXJwIfnzdD3Si%2F1718380800000%2F0%2F0%2F1718369661534%22; __ac_nonce=0666c42cb00f8f9c897ca; __ac_signature=_02B4Z6wo00f01NtgH4gAAIDB6VqS1-oT1ljbQBsAAFC5bQIs2lKyeLtyhXzZaugSDFXQ96eEt5fJXmH3B0Ri0yiKNygrVYJQC4ssHufZp7kUGcPg2H7vJFAK7KJIdtcuPlQ0yCHP9aOyEyEOe6; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1680%2C%5C%22screen_height%5C%22%3A1050%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A2%2C%5C%22device_memory%5C%22%3A8%2C%5C%22downlink%5C%22%3A10%2C%5C%22effective_type%5C%22%3A%5C%224g%5C%22%2C%5C%22round_trip_time%5C%22%3A100%7D%22; passport_fe_beating_status=true; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCRG1PTnF6WENJdmNralN1Y2V3UXBmS1JqaXVvWDR4eHJDR3Q1M1hhaEI0Ry9pZlNGTE5jL0JXaE9uUXQ2VklEMkpRZzRUUFJrdXN2ZmtnUzRxcHlKRnc9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoxfQ%3D%3D; publish_badge_show_info=%220%2C0%2C0%2C1718371035496%22; xg_device_score=6.331309762381608; odin_tt=16aa3ed144cdbdd2adca1fa4aaa86d175e5497c03077be3571431f262bde263dc524028c5c2bc87fa6c460bd050fa90a; home_can_add_dy_2_desktop=%221%22; stream_player_status_params=%22%7B%5C%22is_auto_play%5C%22%3A0%2C%5C%22is_full_screen%5C%22%3A0%2C%5C%22is_full_webscreen%5C%22%3A0%2C%5C%22is_mute%5C%22%3A1%2C%5C%22is_speed%5C%22%3A1%2C%5C%22is_visible%5C%22%3A1%7D%22; FOLLOW_LIVE_POINT_INFO=%22MS4wLjABAAAAI8kHNczjmKhh_NUBUFGijIY6kHVW633dpD9b5PdZ2A68QtZKUEHsXJwIfnzdD3Si%2F1718380800000%2F1718371132628%2F1718371022167%2F0%22; IsDouyinActive=true; msToken=Uti2U4r-BCSRMpxmxquLObFPPGUi4LGqDdsfBNXwTrpLY5tyb7BYk28mXlCWSBYKxpYL9t39HKyRWMdB6DQJzaq1fFiPu894le0pDdl0psUtDICIawmGpsO9XHBykeU=', 1, 1, NULL, '2024-06-29 15:44:08', '2024-06-29 15:43:24', NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for config_tiktok
-- ----------------------------
DROP TABLE IF EXISTS `config_tiktok`;
CREATE TABLE `config_tiktok` (
                                 `config_tiktok_id` int NOT NULL AUTO_INCREMENT,
                                 `cookie` text,
                                 `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                 `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                 `updated_by` varchar(255) DEFAULT NULL,
                                 `del_flag` int DEFAULT NULL,
                                 `config_base_id` int DEFAULT NULL,
                                 `config_app_id` int DEFAULT NULL,
                                 PRIMARY KEY (`config_tiktok_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of config_tiktok
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for config_twitter
-- ----------------------------
DROP TABLE IF EXISTS `config_twitter`;
CREATE TABLE `config_twitter` (
                                  `config_twitter_id` int NOT NULL AUTO_INCREMENT,
                                  `cookie` text,
                                  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
                                  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                  `updated_by` varchar(255) DEFAULT NULL,
                                  `del_flag` int DEFAULT NULL,
                                  `config_base_id` int DEFAULT NULL,
                                  `config_app_id` int DEFAULT NULL,
                                  `folderize` int DEFAULT NULL,
                                  `mode` int DEFAULT NULL,
                                  PRIMARY KEY (`config_twitter_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of config_twitter
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
