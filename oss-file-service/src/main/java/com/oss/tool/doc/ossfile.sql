/*
 Navicat Premium Data Transfer

 Source Server         : wbh
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : ossfile

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 25/11/2020 20:56:52
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
INSERT INTO `t_file` VALUES (52406319780462592, 1604248042071, 1604248042071, 1, '新建文本文档 (4)', '.txt', 'visitor/新建文本文档 (4).txt', 1, 0, 1, 1);

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
INSERT INTO `t_file_log` VALUES (54825882467958784, 1604824910769, 1604824910769, 1, 52406319780462592, '新建文本文档 (4)', 3, 1, 1);

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
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限编码',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限url',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 1606055709030, 1606055709030, 1, '添加分区', 'addZone', '/zone/addZone');
INSERT INTO `t_permission` VALUES (2, 1606055709030, 1606055709030, 1, '根据分区ids删除分区', 'deleteZoneByIds', '/zone/deleteZoneByIds');
INSERT INTO `t_permission` VALUES (3, 1606055709030, 1606055709030, 1, '给用户分配角色', 'allotRole', '/role/allotRole');
INSERT INTO `t_permission` VALUES (4, 1606055709030, 1606055709030, 1, '给用户分配区域', 'allotZone', '/role/allotZone');
INSERT INTO `t_permission` VALUES (5, 1606055709030, 1606055709030, 1, '上传文件', 'uploadFile', '/file/uploadFile');
INSERT INTO `t_permission` VALUES (6, 1606055709030, 1606055709030, 1, '根据前缀删除文件', 'delPrefixFile', '/file/delPrefixFile');

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
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限密码',
  `is_open` tinyint(0) NOT NULL COMMENT '是否开放密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 1606055304539, 1606055304539, 1, '超级管理员', 'superAdmin', '', 0);
INSERT INTO `t_role` VALUES (2, 1606055304539, 1606055304539, 1, '管理员', 'admin', '3L79Tuj000', 1);
INSERT INTO `t_role` VALUES (3, 1606055304539, 1606055304539, 1, '上传员', 'uploadGeneral', '3L79TuM000', 1);
INSERT INTO `t_role` VALUES (4, 1606055304539, 1606055304539, 1, '普通用户', 'general', '', 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_info_permission
-- ----------------------------
INSERT INTO `t_role_info_permission` VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `t_role_info_permission` VALUES (2, 1, 1, 1, 1, 2);
INSERT INTO `t_role_info_permission` VALUES (3, 1, 1, 1, 1, 3);
INSERT INTO `t_role_info_permission` VALUES (4, 1, 1, 1, 1, 4);
INSERT INTO `t_role_info_permission` VALUES (5, 1, 1, 1, 1, 5);
INSERT INTO `t_role_info_permission` VALUES (6, 1, 1, 1, 1, 6);
INSERT INTO `t_role_info_permission` VALUES (7, 1, 1, 1, 2, 5);
INSERT INTO `t_role_info_permission` VALUES (8, 1, 1, 1, 2, 6);
INSERT INTO `t_role_info_permission` VALUES (9, 1, 1, 1, 3, 5);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL DEFAULT 0 COMMENT 'id',
  `create_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` bigint(0) NOT NULL DEFAULT 0 COMMENT '修改时间',
  `version` bigint(0) NOT NULL DEFAULT 0 COMMENT '版本号',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `account` bigint(0) NOT NULL DEFAULT 0 COMMENT '手机号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 1606061069768, 1606061069768, 1, '213213', '/headImg/60881017069633536u=2146406568,1565449453&fm=26&gp=0.jpg', 13738700108, '4b81dd5e3abca610e5105bb2bcf148f0');
INSERT INTO `t_user` VALUES (59814635099914240, 1606014322083, 1606014322083, 1, '威少', '/headImg/defaultUserImg.png', 13736766788, 'e10adc3949ba59abbe56e057f20f883e');

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
INSERT INTO `t_user_info_role` VALUES (1, 1606271897152, 1606271897152, 1, 1, 1);
INSERT INTO `t_user_info_role` VALUES (2, 1606271897152, 1606271897152, 1, 1, 2);
INSERT INTO `t_user_info_role` VALUES (3, 1606271897152, 1606271897152, 1, 1, 3);
INSERT INTO `t_user_info_role` VALUES (4, 1606271897152, 1606271897152, 1, 1, 4);
INSERT INTO `t_user_info_role` VALUES (61050552309514240, 1606308987708, 1606308987708, 1, 59814635099914240, 2);

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
INSERT INTO `t_user_info_zone` VALUES (54838911423217664, 1604828017114, 1604828017114, 1, 1, 54845492894367744);
INSERT INTO `t_user_info_zone` VALUES (54838918100549632, 1604828018706, 1604828018706, 1, 1, 54831882184949760);
INSERT INTO `t_user_info_zone` VALUES (54849259622629376, 1604830484317, 1604830484317, 1, 1, 54845833861922816);
INSERT INTO `t_user_info_zone` VALUES (61033518951563264, 1606304926639, 1606304926639, 1, 59814635099914240, 2123);

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
INSERT INTO `t_zone` VALUES (1, 1, 1, 1, '游客专区', 'visitor', '', 1);
INSERT INTO `t_zone` VALUES (2123, 1604829667552, 1604829667552, 1, '测试分区4', 'sdasdsad', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (23223, 1604829667552, 1604829667552, 1, '测dsfdsf', '测324', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (1231231, 1604829667552, 1604829667552, 1, '测234zx', 'cxvxv', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (12312312, 1604829667552, 1604829667552, 1, 'dssa', 'zxczxc', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (12321312, 1604829667552, 1604829667552, 1, '测213', '23123123', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (123123123, 16048296675544, 1604829667552, 1, '拉到底的数据', '23123', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (123213213, 1604829667552, 1604829667552, 1, 'cvxcv', 'dxcvxcvxc', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (54831882184949760, 1604826341213, 1604826341213, 1, '测试分区', 'asdasd', 'zxczxc', 0);
INSERT INTO `t_zone` VALUES (54845492894367744, 1604829586259, 1604829586259, 1, '测试分区2', 'asdasd2', 'zxczxc2', 0);
INSERT INTO `t_zone` VALUES (54845833861922816, 1604829667552, 1604829667552, 1, '测试分区3', 'asdasd3', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (548458338619228133, 1604829667552, 1604829667552, 1, '测zxczxa', 'dsaa', 'zxczxc3', 0);
INSERT INTO `t_zone` VALUES (5484583386192281233, 1604829667552, 1604829667552, 1, '测12312', '23123', 'zxczxc3', 0);

SET FOREIGN_KEY_CHECKS = 1;
