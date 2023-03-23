/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 47.100.46.162:3306
 Source Schema         : gulimall_admin

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 23/03/2023 11:08:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha`  (
  `uuid` char(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统验证码' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
INSERT INTO `sys_captcha` VALUES ('23a389a0-9290-431c-8865-5bd25a19f83e', '7538d', '2023-02-27 09:17:36');
INSERT INTO `sys_captcha` VALUES ('53d4b68e-486a-4aa4-8919-5fb33469b67e', 'yncwg', '2023-03-12 22:36:11');
INSERT INTO `sys_captcha` VALUES ('97ddbf6c-cbce-4d97-8d42-f9886cfab59a', '72cpc', '2023-03-07 22:50:32');
INSERT INTO `sys_captcha` VALUES ('d32b805b-84cb-4c00-8954-bbee89cb4bf7', '3ya4y', '2023-03-04 19:46:29');
INSERT INTO `sys_captcha` VALUES ('e6f93709-8260-4ed1-86fd-02663b7b9adf', '4da3a', '2023-03-11 23:12:20');

SET FOREIGN_KEY_CHECKS = 1;
