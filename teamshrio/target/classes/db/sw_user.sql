/*
 Navicat Premium Data Transfer

 Source Server         : 本地库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : sw_user

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 10/12/2018 17:32:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_business_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_business_system`;
CREATE TABLE `tb_business_system`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `sys_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务系统名称',
  `sys_des` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务系统描述',
  `sys_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '业务系统url',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '组织机构编号',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效，0:无效)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `version` bigint(20) NULL DEFAULT NULL COMMENT '数据状态',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '业务系统信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_business_system
-- ----------------------------
INSERT INTO `tb_business_system` VALUES (1, '知识学习', '对于新知识或者其它的知识点总结。', '', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL);
INSERT INTO `tb_business_system` VALUES (3, '知识曲线', '你号', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_business_system` VALUES (4, '心灵测试', '阿萨德', '阿萨德', NULL, NULL, NULL, NULL, NULL, '1', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_button_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_button_info`;
CREATE TABLE `tb_button_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `button_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮标识',
  `button_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '按钮名称',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '所属菜单',
  `sys_id` bigint(20) NULL DEFAULT NULL COMMENT '所属系统',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '所属角色',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效，0:无效)',
  `version` bigint(20) NULL DEFAULT NULL COMMENT '数据版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '按钮、字段信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_department_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_department_info`;
CREATE TABLE `tb_department_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `depart_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `depart_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编码',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属机构',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效,0:无效)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `version` bigint(20) NULL DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_department_info
-- ----------------------------
INSERT INTO `tb_department_info` VALUES (1, '移动互联创新部', 'net', 1, 0, '1', 1, '2018-08-31 10:03:59', 1, '2018-08-31 10:04:04', '新创建', 0);

-- ----------------------------
-- Table structure for tb_dictionary_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary_info`;
CREATE TABLE `tb_dictionary_info`  (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `display` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '描述',
  `key_name` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT 'key',
  `value` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '值',
  `parent_id` bigint(32) NULL DEFAULT NULL COMMENT '父节点',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `version` int(10) NULL DEFAULT 0 COMMENT '版本',
  `remark` varchar(256) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '备注',
  `state` char(2) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效，0：无效)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `operator_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_menu_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu_info`;
CREATE TABLE `tb_menu_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效，0:无效)',
  `leaf` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否末级节点（1:是,0:否）',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级节点',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `version` bigint(20) NULL DEFAULT NULL COMMENT '数据版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属机构',
  `sys_id` bigint(20) NULL DEFAULT NULL COMMENT '所属业务系统',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色编号',
  `path` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图片名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_menu_info
-- ----------------------------
INSERT INTO `tb_menu_info` VALUES (1, '功能菜单配置', '0', '1', -1, 10001, '2018-09-21 17:33:40', 10001, '2018-09-21 17:33:48', 0, '最高节点', 1, 1, 1, ' menu/main', NULL);
INSERT INTO `tb_menu_info` VALUES (2, '业务系统', '0', '1', -1, NULL, NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, 'appname/main', NULL);
INSERT INTO `tb_menu_info` VALUES (3, '组织机构信息', '0', '1', -1, NULL, NULL, NULL, NULL, NULL, '添加组织机构信息', NULL, NULL, NULL, 'organzation/main', NULL);
INSERT INTO `tb_menu_info` VALUES (5, '用户管理', '0', '1', -1, NULL, NULL, NULL, NULL, NULL, '管理登录用户信息', NULL, NULL, NULL, '', NULL);
INSERT INTO `tb_menu_info` VALUES (6, '角色管理', '0', '1', -1, NULL, NULL, NULL, NULL, NULL, '管理角色', NULL, NULL, NULL, '', NULL);

-- ----------------------------
-- Table structure for tb_org_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_org_info`;
CREATE TABLE `tb_org_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' 虚拟主键',
  `org_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `org_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构描述',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效。0：无效)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级机构',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `version` bigint(32) NULL DEFAULT NULL COMMENT '数据版本',
  `leaf` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否末级，1:是，0:否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织机构信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_org_info
-- ----------------------------
INSERT INTO `tb_org_info` VALUES (1, 'newcapec', '新开普', '现代化互联网', '1', 0, 1, '2018-08-31 10:02:57', 1, '2018-08-31 10:03:02', 0, NULL);
INSERT INTO `tb_org_info` VALUES (2, NULL, '朋友圈', NULL, '0', -1, NULL, NULL, NULL, NULL, NULL, '1');
INSERT INTO `tb_org_info` VALUES (3, NULL, '博客组', NULL, '0', -1, NULL, NULL, NULL, NULL, NULL, '1');

-- ----------------------------
-- Table structure for tb_role_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_info`;
CREATE TABLE `tb_role_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效，0：无效)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `version` bigint(32) NULL DEFAULT NULL COMMENT '数据版本',
  `role_type` int(20) NULL DEFAULT NULL COMMENT '角色类型',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `role_classify` int(20) NULL DEFAULT NULL COMMENT '角色分类',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_role_info
-- ----------------------------
INSERT INTO `tb_role_info` VALUES (1, '高级管理员', '1', '2018-08-31 10:05:31', 1, '2018-08-31 10:05:34', 1, 0, 1, 'manager', 1, '角色');

-- ----------------------------
-- Table structure for tb_role_org_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_org_relation`;
CREATE TABLE `tb_role_org_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键 ',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '用户编号',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '机构编号',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效,0:无效)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `version` bigint(20) NULL DEFAULT NULL COMMENT '数据版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与机构关系信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' 虚拟主键',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户名',
  `user_pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 用户密码',
  `iphone` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 手机号',
  `is_real` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 是否实名认证(1:是，0：否)',
  `real_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' 真实姓名',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态（1：有效，0：否）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间\r\n',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作人',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作时间',
  `verson` bigint(32) NULL DEFAULT NULL COMMENT '数据版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属机构信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
INSERT INTO `tb_user_info` VALUES (1, 'admin', '26524bdf4ea266f131566a89e8f4972c', '13718085143', '1', 'peter', '1', '2018-08-31 10:04:56', 1, '2018-08-31 10:05:00', 1, 0, '最高权限用户', 1);

-- ----------------------------
-- Table structure for tp_role_depart_relation
-- ----------------------------
DROP TABLE IF EXISTS `tp_role_depart_relation`;
CREATE TABLE `tp_role_depart_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '虚拟主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色编号',
  `depart_id` bigint(20) NULL DEFAULT NULL COMMENT '部门编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效,0:无效)',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门角色信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
