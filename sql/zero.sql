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

 Date: 21/06/2024 17:38:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书名称',
  `in_time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `press` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '出版社',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `book_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书编码',
  `create_by` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (1, '三国演义', NULL, '中华出版社', 1.00, '1', NULL, NULL, NULL, NULL);
INSERT INTO `book` VALUES (2, '孤独野狼', NULL, '中华出版社', 1.00, '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for book_borrowing_record
-- ----------------------------
DROP TABLE IF EXISTS `book_borrowing_record`;
CREATE TABLE `book_borrowing_record`  (
  `book_id` int(0) NOT NULL COMMENT '图书id',
  `borrowing_time` datetime(0) NULL DEFAULT NULL COMMENT '借出时间',
  `return_time` datetime(0) NULL DEFAULT NULL COMMENT '归还时间',
  `borrowing_by` int(0) NULL DEFAULT NULL COMMENT '借书人',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '状态(0未还，1已还)',
  `manager_by` int(0) NULL DEFAULT NULL COMMENT '记录人员',
  `create_by` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书借阅记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `book_id` int(0) NOT NULL,
  `book_type_id` int(0) NOT NULL,
  `create_by` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (1, 3, NULL, NULL, NULL, NULL);
INSERT INTO `book_type` VALUES (2, 4, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for book_type_summary
-- ----------------------------
DROP TABLE IF EXISTS `book_type_summary`;
CREATE TABLE `book_type_summary`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `book_type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图书类别',
  `create_by` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '图书类别汇总' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type_summary
-- ----------------------------
INSERT INTO `book_type_summary` VALUES (3, '历史类', NULL, NULL, NULL, NULL);
INSERT INTO `book_type_summary` VALUES (4, '文艺类', NULL, NULL, NULL, NULL);
INSERT INTO `book_type_summary` VALUES (5, '国学类', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parent_id` int(0) NULL DEFAULT NULL COMMENT '父id',
  `menu_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单跳转',
  `menu_component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `menu_icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` int(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` int(0) NULL DEFAULT NULL,
  `dele_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否删除(0false/1true)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, NULL, '/home', '首页', '/first', '@/view/homez/Home', 'el-icon-star-off', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (2, NULL, '1', '组件应用', NULL, NULL, 'el-icon-location', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (3, NULL, '2', '权限管理', NULL, NULL, 'el-icon-star-off', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (4, 3, '/messageUser', '用户管理', NULL, '@/view/system/user/index', 'el-icon-s-marketing', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (5, 3, '/messageRole', '角色管理', NULL, '@/view/system/role/index', 'el-icon-s-opportunity', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (6, 3, '/messageMenu', '菜单管理', NULL, '@/view/system/menu/index', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (7, 2, '/add', '添加页', NULL, '@/view/message/addMsg', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (8, 2, '/table', '展示页', NULL, '@/view/message/tableShow', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (9, 2, '/upload', '上传页', NULL, '@/view/message/Fileupload', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (10, 2, '/download', '下载页', NULL, '@/view/message/downloadFile', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (18, NULL, '3', '图书管理', NULL, NULL, 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (19, 18, '/bookType', '图书类别管理', NULL, '@/view/book/bookType/index', 'el-icon-umbrella', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES (20, 18, '/book', '图书管理', NULL, '@/view/book/book/index', '', NULL, NULL, NULL, NULL, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (2, '管理员', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role` VALUES (3, '用户管理员', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role` VALUES (4, '组件编辑', NULL, NULL, NULL, NULL, '0');

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
INSERT INTO `sys_role_menu` VALUES (4, 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (4, 7, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (4, 8, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (4, 9, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (4, 10, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 1, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 2, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 7, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 8, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 9, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 10, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 4, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 5, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 6, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 18, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 19, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES (2, 20, NULL, NULL, NULL, NULL, '0');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user` VALUES (6, 'marry', NULL, NULL, NULL, NULL, '0');

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
INSERT INTO `sys_user_role` VALUES (6, 4, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES (1, 3, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES (1, 4, NULL, NULL, NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES (1, 2, NULL, NULL, NULL, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
