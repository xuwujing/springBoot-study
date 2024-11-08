/*
 Navicat Premium Data Transfer

 Source Server         : 本地-mysql
 Source Server Type    : MySQL
 Source Server Version : 50744
 Source Host           : localhost:3306
 Source Schema         : pcm

 Target Server Type    : MySQL
 Target Server Version : 50744
 File Encoding         : 65001

 Date: 15/01/2024 15:40:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(1) NULL DEFAULT 1 COMMENT '性别',
  `age` int(4) NULL DEFAULT NULL COMMENT '年龄',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 1, 18, '2020-02-15 14:35:46', 'admin', '2021-01-15 14:36:28', 'admin');
INSERT INTO `user` VALUES (2, '小红', 2, 26, '2022-02-16 14:36:10', 'admin', '2021-01-15 14:36:39', 'admin');

SET FOREIGN_KEY_CHECKS = 1;
