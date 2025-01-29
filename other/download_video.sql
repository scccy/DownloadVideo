/*
 Navicat Premium Dump SQL

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80404 (8.4.4)
 Source Host           : localhost:3306
 Source Schema         : download_video

 Target Server Type    : MySQL
 Target Server Version : 80404 (8.4.4)
 File Encoding         : 65001

 Date: 30/01/2025 00:20:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for biz_config
-- ----------------------------
DROP TABLE IF EXISTS `biz_config`;
CREATE TABLE `biz_config` (
  `id` int DEFAULT NULL,
  `apptoken` tinytext,
  `ipauth` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for biz_downloader
-- ----------------------------
DROP TABLE IF EXISTS `biz_downloader`;
CREATE TABLE `biz_downloader` (
  `id` int DEFAULT NULL,
  `downloadertype` tinytext,
  `downloaderlink` tinytext,
  `username` tinytext,
  `password` tinytext,
  `token` tinytext,
  `downloaderport` tinytext,
  `downloadername` tinytext,
  `status` tinytext,
  `downloadpath` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for biz_user
-- ----------------------------
DROP TABLE IF EXISTS `biz_user`;
CREATE TABLE `biz_user` (
  `id` int DEFAULT NULL,
  `lasttime` tinytext,
  `password` tinytext,
  `username` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for biz_video
-- ----------------------------
DROP TABLE IF EXISTS `biz_video`;
CREATE TABLE `biz_video` (
  `id` int DEFAULT NULL,
  `videoaddr` tinytext,
  `videocover` tinytext,
  `videoname` tinytext,
  `videoplatform` tinytext,
  `createtime` datetime DEFAULT NULL,
  `videodesc` tinytext,
  `videounrealaddr` tinytext,
  `originaladdress` tinytext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for seq_common
-- ----------------------------
DROP TABLE IF EXISTS `seq_common`;
CREATE TABLE `seq_common` (
  `seq_id` tinytext,
  `seq_count` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for sqlite_master
-- ----------------------------
DROP TABLE IF EXISTS `sqlite_master`;
CREATE TABLE `sqlite_master` (
  `type` text,
  `name` text,
  `tbl_name` text,
  `rootpage` int DEFAULT NULL,
  `sql` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
