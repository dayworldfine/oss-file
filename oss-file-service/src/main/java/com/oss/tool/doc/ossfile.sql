/*
 Navicat Premium Data Transfer

 Source Server         : wang
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ossfile

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 02/11/2020 00:33:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_file
-- ----------------------------
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `suffix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '后缀',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问路径',
  `upload_user_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '上传用户id',
  `download_statistics` bigint(0) NOT NULL DEFAULT 0 COMMENT '下载次数统计',
  `preview_statistics` bigint(0) NOT NULL DEFAULT 0 COMMENT '预览次数统计',
  `zone_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '分区id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file
-- ----------------------------
INSERT INTO `t_file` VALUES (1, 0, 0, 0, '文件A', '12', '12', 1, 1, 12, 0);
INSERT INTO `t_file` VALUES (2, 0, 0, 0, '文件B', '123', '213', 3, 12, 12, 0);
INSERT INTO `t_file` VALUES (3, 0, 0, 0, '文件C', '123', '213', 3, 1221, 1212, 0);
INSERT INTO `t_file` VALUES (52406319780462592, 1604248042071, 1604248042071, 1, '新建文本文档 (4)', '.txt', 'visitor/新建文本文档 (4).txt', 1, 0, 0, 1);

-- ----------------------------
-- Table structure for t_file_log
-- ----------------------------
DROP TABLE IF EXISTS `t_file_log`;
CREATE TABLE `t_file_log`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `file_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '文件id',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `operation_type` tinyint(0) NOT NULL DEFAULT 0 COMMENT '操作类型（文件操作枚举）',
  `zone_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '分区id',
  `user_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '操作用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_file_log
-- ----------------------------
INSERT INTO `t_file_log` VALUES (52390070518284288, 1604244167946, 1604244167946, 1, 52385252626137088, '新建文本文档 (4)', 3, 1, 1);
INSERT INTO `t_file_log` VALUES (52396365392642048, 1604245668760, 1604245668760, 1, 52385252626137088, '新建文本文档 (4)', 3, 1, 1);
INSERT INTO `t_file_log` VALUES (52396682075176960, 1604245744263, 1604245744263, 1, 52385252626137088, '新建文本文档 (4)', 3, 1, 1);
INSERT INTO `t_file_log` VALUES (52396937051111424, 1604245805054, 1604245805054, 1, 52385252626137088, '新建文本文档 (4)', 3, 1, 1);
INSERT INTO `t_file_log` VALUES (52397216484032512, 1604245871676, 1604245871676, 1, 52385252626137088, '新建文本文档 (4)', 4, 1, 1);
INSERT INTO `t_file_log` VALUES (52406319834988544, 1604248042084, 1604248042084, 1, 52406319780462592, '新建文本文档 (4)', 2, 1, 1);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 1, 1, 1, '添加分区', '/zone/getZoneList');
INSERT INTO `t_permission` VALUES (2, 1, 1, 1, '根据分区ids删除分区', '/zone/deleteZoneByIds');
INSERT INTO `t_permission` VALUES (3, 1, 1, 1, '上传文件', '/file/uploadFile');
INSERT INTO `t_permission` VALUES (4, 1, 1, 1, '根据前缀删除文件', '/file/delPrefixFile');
INSERT INTO `t_permission` VALUES (5, 1, 1, 1, '查询角色列表', '/role/getRoleList');
INSERT INTO `t_permission` VALUES (6, 1, 1, 1, '给用户分配角色', '/role/allotRole');
INSERT INTO `t_permission` VALUES (7, 1, 1, 1, '查询分区列表', '/role/getZoneList');
INSERT INTO `t_permission` VALUES (8, 1, 1, 1, '给用户分配区域', '/role/allotZone');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限密码',
  `is_open` tinyint(0) NOT NULL COMMENT '是否开放密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 1, 1, 1, '超级管理员', '', 0);
INSERT INTO `t_role` VALUES (2, 1, 1, 1, '管理员', '', 1);
INSERT INTO `t_role` VALUES (3, 1, 1, 1, '上传员', ' ', 1);
INSERT INTO `t_role` VALUES (4, 1, 1, 1, '普通用户', '', 1);

-- ----------------------------
-- Table structure for t_role_info_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_info_permission`;
CREATE TABLE `t_role_info_permission`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `role_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '角色id',
  `permission_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_info_permission
-- ----------------------------
INSERT INTO `t_role_info_permission` VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `t_role_info_permission` VALUES (2, 1, 1, 1, 1, 2);
INSERT INTO `t_role_info_permission` VALUES (3, 1, 1, 1, 1, 5);
INSERT INTO `t_role_info_permission` VALUES (4, 1, 1, 1, 1, 6);
INSERT INTO `t_role_info_permission` VALUES (5, 1, 1, 1, 1, 7);
INSERT INTO `t_role_info_permission` VALUES (6, 1, 1, 1, 1, 8);
INSERT INTO `t_role_info_permission` VALUES (7, 1, 1, 1, 2, 3);
INSERT INTO `t_role_info_permission` VALUES (8, 1, 1, 1, 2, 4);
INSERT INTO `t_role_info_permission` VALUES (9, 1, 1, 1, 3, 3);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `account` bigint(0) NOT NULL DEFAULT 0 COMMENT '手机号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 1, 1, 0, 'wbh', 'wbh', '头像1', 0, '12');
INSERT INTO `t_user` VALUES (2, 1, 1, 0, '郑梦思', '郑梦思', '头像2', 0, '12');
INSERT INTO `t_user` VALUES (3, 1, 1, 0, '吴萧萧', '吴萧萧', '头像3', 0, '12');

-- ----------------------------
-- Table structure for t_user_info_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info_role`;
CREATE TABLE `t_user_info_role`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `user_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '用户id',
  `role_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_info_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_user_info_zone
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info_zone`;
CREATE TABLE `t_user_info_zone`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `user_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '用户id',
  `zone_id` bigint(0) NOT NULL DEFAULT 0 COMMENT '分区id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关联分区' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_info_zone
-- ----------------------------

-- ----------------------------
-- Table structure for t_zone
-- ----------------------------
DROP TABLE IF EXISTS `t_zone`;
CREATE TABLE `t_zone`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `zone_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分区名称',
  `zone_prefix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分区前缀',
  `zone_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分区密钥',
  `is_open` tinyint(0) NOT NULL DEFAULT 0 COMMENT '是否公开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分区' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_zone
-- ----------------------------
INSERT INTO `t_zone` VALUES (1, 1, 1, 1, '游客专区', 'visitor/', '', 1);

SET FOREIGN_KEY_CHECKS = 1;
