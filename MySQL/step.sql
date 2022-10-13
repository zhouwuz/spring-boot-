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

 Date: 02/06/2022 10:08:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for step
-- ----------------------------
DROP TABLE IF EXISTS `step`;
CREATE TABLE `step`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `date` datetime(0) NOT NULL,
  `step` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of step
-- ----------------------------
INSERT INTO `step` VALUES (2, 10001, '2021-04-28 00:00:00', 10000);
INSERT INTO `step` VALUES (3, 10001, '2021-04-26 00:00:00', 10000);
INSERT INTO `step` VALUES (5, 10001, '2021-04-29 00:00:00', 10000);
INSERT INTO `step` VALUES (6, 10001, '2021-04-30 00:00:00', 20000);
INSERT INTO `step` VALUES (7, 10008, '2021-05-12 00:00:00', 1331);
INSERT INTO `step` VALUES (8, 10008, '2021-05-13 00:00:00', 0);
INSERT INTO `step` VALUES (10, 10008, '2021-05-18 00:00:00', 3774);
INSERT INTO `step` VALUES (11, 10008, '2021-06-03 00:00:00', 0);
INSERT INTO `step` VALUES (12, 10008, '2022-05-24 00:00:00', 1900);
INSERT INTO `step` VALUES (13, 10009, '2022-05-24 00:00:00', 47);
INSERT INTO `step` VALUES (14, 10010, '2022-06-01 00:00:00', 3736);

SET FOREIGN_KEY_CHECKS = 1;
