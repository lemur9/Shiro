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

 Date: 23/03/2023 11:09:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_date` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, 'admin', '保存定时任务', 'io.renren.modules.job.controller.ScheduleJobController.save()', '[{\"jobId\":2,\"beanName\":\"lemurTask\",\"params\":\"lemur\",\"cronExpression\":\"*/30 * * * * ?\",\"status\":0,\"remark\":\"研究定时器\",\"createTime\":\"Feb 27, 2023 11:37:29 AM\"}]', 266, '0:0:0:0:0:0:0:1', '2023-02-27 11:37:30');
INSERT INTO `sys_log` VALUES (2, 'admin', '暂停定时任务', 'io.renren.modules.job.controller.ScheduleJobController.pause()', '[[2]]', 24, '0:0:0:0:0:0:0:1', '2023-02-27 11:41:22');
INSERT INTO `sys_log` VALUES (3, 'admin', '保存菜单', 'com.lemur.shiro.controller.SysMenuController.save()', '[{\"menuId\":31,\"parentId\":0,\"name\":\"商品系统\",\"url\":\"\",\"perms\":\"\",\"type\":0,\"icon\":\"editor\",\"orderNum\":0,\"list\":[]}]', 39, '0:0:0:0:0:0:0:1', '2023-03-04 00:31:12');
INSERT INTO `sys_log` VALUES (4, 'admin', '保存菜单', 'com.lemur.shiro.controller.SysMenuController.save()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"分类维护\",\"url\":\"product/category\",\"perms\":\"\",\"type\":1,\"icon\":\"\",\"orderNum\":0,\"list\":[]}]', 51, '0:0:0:0:0:0:0:1', '2023-03-04 00:32:15');
INSERT INTO `sys_log` VALUES (5, 'admin', '修改菜单', 'com.lemur.shiro.controller.SysMenuController.update()', '[{\"menuId\":32,\"parentId\":31,\"name\":\"分类维护\",\"url\":\"product/category\",\"perms\":\"\",\"type\":1,\"icon\":\"bianji\",\"orderNum\":0,\"list\":[]}]', 47, '0:0:0:0:0:0:0:1', '2023-03-04 00:33:06');
INSERT INTO `sys_log` VALUES (6, 'admin', '保存菜单', 'com.lemur.shiro.controller.SysMenuController.save()', '[{\"menuId\":33,\"parentId\":0,\"name\":\"品牌管理\",\"url\":\"product/brand\",\"perms\":\"\",\"type\":1,\"icon\":\"editor\",\"orderNum\":0,\"list\":[]}]', 38, '0:0:0:0:0:0:0:1', '2023-03-06 23:09:43');
INSERT INTO `sys_log` VALUES (7, 'admin', '修改菜单', 'com.lemur.shiro.controller.SysMenuController.update()', '[{\"menuId\":33,\"parentId\":31,\"name\":\"品牌管理\",\"url\":\"product/brand\",\"perms\":\"\",\"type\":1,\"icon\":\"editor\",\"orderNum\":0,\"list\":[]}]', 54, '0:0:0:0:0:0:0:1', '2023-03-06 23:10:19');

SET FOREIGN_KEY_CHECKS = 1;
