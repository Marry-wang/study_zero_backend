/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : zero

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 28/07/2023 15:26:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父id',
  `menu_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(0) NULL DEFAULT NULL,
  `dele_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除(0false/1true)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, NULL, '组件应用', 'el-icon-location', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (2, 4, '/mesageUser', '用户管理', 'el-icon-s-marketing', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (3, 4, '/mesageRoule', '角色管理', 'el-icon-s-opportunity', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (4, NULL, NULL, '权限管理', 'el-icon-star-off', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (5, 4, '/mesageMenu', '菜单管理', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (8, 1, '/add', '添加页', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (9, 1, '/table', '展示页', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (10, 1, '/upload', '上传页', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (11, 1, '/download', '下载页', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, 'www', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role` VALUES (3, 'aaaaa', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role` VALUES (4, 'aaa', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role` VALUES (5, 'qq', NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(0) NULL DEFAULT NULL,
  `menu_id` int(0) NULL DEFAULT NULL,
  `create_by` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 5, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (3, 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (3, 5, NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `dele_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user` VALUES (3, 'adminOne1', NULL, NULL, NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` int(0) NULL DEFAULT NULL,
  `role_id` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES (1, 4, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES (1, 2, NULL, NULL, NULL, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
