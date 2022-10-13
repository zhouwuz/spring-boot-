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

 Date: 06/05/2022 07:55:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for water
-- ----------------------------
DROP TABLE IF EXISTS `water`;
CREATE TABLE `water`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `tree_id` int(11) NOT NULL,
  `time` datetime(0) NOT NULL,
  `water` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
