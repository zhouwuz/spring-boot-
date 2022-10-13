/*
 Navicat Premium Data Transfer

 Source Server         : Hsq
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : tree

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 03/05/2022 22:42:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tree
-- ----------------------------
DROP TABLE IF EXISTS `tree`;
CREATE TABLE `tree`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tree_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `life` int(11) NOT NULL,
  `longitude` double(10, 6) NOT NULL,
  `latitude` double(10, 6) NOT NULL,
  `time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
