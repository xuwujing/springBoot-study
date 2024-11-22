/*
 Navicat Premium Data Transfer

 Source Server         : dev-kms
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : base-dev.udb.bnqoa.com:3306
 Source Schema         : base_kms

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 22/11/2024 17:52:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for log_operation
-- ----------------------------
DROP TABLE IF EXISTS `log_operation`;
CREATE TABLE `log_operation`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `trace_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求唯一id',
                                  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用唯一标识',
                                  `tenant_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户',
                                  `business_no` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务主键',
                                  `operation` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作',
                                  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '类名',
                                  `method_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
                                  `from_ip` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
                                  `result` tinyint(1) NULL DEFAULT NULL COMMENT '操作结果 1 成功, 0 失败',
                                  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
                                  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                  `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `uk_trace_id`(`trace_id`) USING BTREE,
                                  INDEX `idx_operation`(`operation`) USING BTREE,
                                  INDEX `idx_class_name`(`class_name`) USING BTREE,
                                  INDEX `idx_from_ip`(`from_ip`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_operation
-- ----------------------------

-- ----------------------------
-- Table structure for log_operation_details
-- ----------------------------
DROP TABLE IF EXISTS `log_operation_details`;
CREATE TABLE `log_operation_details`  (
                                          `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                          `trace_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求唯一id',
                                          `req_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '请求参数',
                                          `rep_data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '响应参数',
                                          `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '错误信息',
                                          `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                          `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                          `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                          `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                          `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          UNIQUE INDEX `uk_trace_id`(`trace_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
                                   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `parent_id` int(11) NOT NULL COMMENT '父ID',
                                   `permission_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限编码',
                                   `permission_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
                                   `permission_route` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '权限路由',
                                   `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                   `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                   `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'UserInfoController', '用户', '/userInfo', '2024-11-21 16:37:15', '1', '2024-11-21 16:38:35', NULL, 0);
INSERT INTO `sys_permission` VALUES (2, 1, 'UserInfoController#add', '用户新增', '/userInfo/add', '2024-11-21 16:41:57', '1', '2024-11-21 16:53:28', NULL, 0);
INSERT INTO `sys_permission` VALUES (3, 1, 'UserInfoController#update', '用户修改', '/userInfo/update', '2024-11-21 16:41:57', '1', '2024-11-21 16:53:29', NULL, 0);
INSERT INTO `sys_permission` VALUES (4, 1, 'UserInfoController#enable', '用户启用/禁用', '/userInfo/enable', '2024-11-21 16:41:57', '1', '2024-11-21 16:53:32', NULL, 0);
INSERT INTO `sys_permission` VALUES (5, 1, 'UserInfoController#get', '用户详情', '/userInfo/get', '2024-11-21 16:41:57', '1', '2024-11-21 16:53:33', NULL, 0);
INSERT INTO `sys_permission` VALUES (6, 1, 'UserInfoController#queryPage', '用户分页查询', '/userInfo/queryPage', '2024-11-21 16:41:57', '1', '2024-11-21 16:53:36', NULL, 0);
INSERT INTO `sys_permission` VALUES (7, 0, 'UserRoleController', '用户角色', '/userRole', '2024-11-22 17:36:40', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (8, 7, 'UserRoleController#add', '用户角色新增', '/userRole/add', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (9, 7, 'UserRoleController#update', '用户角色修改', '/userRole/update', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (10, 7, 'UserRoleController#enable', '用户角色启用/禁用', '/userRole/enable', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (11, 7, 'UserRoleController#grant', '用户角色授权', '/userRole/grant', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (12, 7, 'UserRoleController#get', '用户角色详情', '/userRole/get', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (13, 7, 'UserRoleController#queryPage', '用户角色分页查询', '/userRole/queryPage', '2024-11-22 17:38:06', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (14, 0, 'PermissionController', '权限', '/permission', '2024-11-22 17:42:39', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (15, 14, 'PermissionController#add', '权限新增', '/permission/add', '2024-11-22 17:43:43', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (16, 14, 'PermissionController#update', '权限修改', '/permission/update', '2024-11-22 17:43:43', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (17, 14, 'PermissionController#get', '权限详情', '/permission/get', '2024-11-22 17:43:43', '1', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (18, 14, 'PermissionController#queryPage', '权限分页查询', '/permission/queryPage', '2024-11-22 17:43:43', '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_info`;
CREATE TABLE `sys_user_info`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
                                  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '昵称',
                                  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
                                  `user_enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '启用状态 0 否 1是',
                                  `role_id` tinyint(1) NOT NULL DEFAULT 2 COMMENT '角色ID 1 管理员 2 普通用户',
                                  `job_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工号',
                                  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工联系方式',
                                  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                  `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `uk_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_info
-- ----------------------------
INSERT INTO `sys_user_info` VALUES (1, 'admin', 'admin', '123456', 1, 1, NULL, NULL, '2024-11-21 15:48:34', '1', '2024-11-21 15:48:51', NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
                                  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
                                  `user_enable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '启用状态 0 否 1是',
                                  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                  `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  UNIQUE INDEX `uk_role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '管理员', 1, '2024-11-21 16:33:55', '1', NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_permission`;
CREATE TABLE `sys_user_role_permission`  (
                                             `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                             `role_id` int(11) NOT NULL COMMENT '角色ID',
                                             `permission_id` int(11) NOT NULL COMMENT '权限ID',
                                             `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `creator_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建人',
                                             `modify_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                             `modifier_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
                                             `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态',
                                             PRIMARY KEY (`id`) USING BTREE,
                                             UNIQUE INDEX `uk_role_id_and_per_id`(`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_permission
-- ----------------------------
INSERT INTO `sys_user_role_permission` VALUES (2, 1, 1, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (3, 1, 2, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (4, 1, 3, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (5, 1, 4, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (6, 1, 5, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (7, 1, 6, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (8, 1, 7, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (9, 1, 8, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (10, 1, 9, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (11, 1, 10, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (12, 1, 11, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (13, 1, 12, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (14, 1, 13, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (15, 1, 14, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (16, 1, 15, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (17, 1, 16, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (18, 1, 17, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (19, 1, 18, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (20, 1, 19, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (21, 1, 20, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (22, 1, 21, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (23, 1, 22, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (24, 1, 23, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (25, 1, 24, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (26, 1, 25, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (27, 1, 26, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (28, 1, 27, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (29, 1, 28, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (30, 1, 29, '2024-11-22 17:50:54', '1', NULL, NULL, 0);
INSERT INTO `sys_user_role_permission` VALUES (31, 1, 30, '2024-11-22 17:50:54', '1', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
