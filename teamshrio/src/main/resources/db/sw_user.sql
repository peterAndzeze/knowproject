/*
 Navicat Premium Data Transfer

 Source Server         : 本地测试
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : sw_user

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 29/08/2018 18:05:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_org_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_org_info`;
CREATE TABLE `tb_org_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ' 虚拟主键',
  `orgcode` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构编号',
  `orgname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `orgdesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构描述',
  `state` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据状态(1:有效。0：无效)',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级机构',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  ` version` bigint(32) NULL DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `opera_time` datetime(0) NULL DEFAULT NULL COMMENT ' 操作人',
  `opera_id` bigint(20) NULL DEFAULT NULL COMMENT '操作时间',
  `verson` bigint(32) NULL DEFAULT NULL COMMENT '数据版本',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
