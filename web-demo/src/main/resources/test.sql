/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-03-24 12:45:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `t_operator_log`;
CREATE TABLE `t_operator_log` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operator_id` int(20) DEFAULT NULL COMMENT '用户id',
  `operator_name` varchar(60) DEFAULT NULL COMMENT '用户姓名',
  `content` varchar(255) DEFAULT NULL COMMENT '操作内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_operator_log
-- ----------------------------
INSERT INTO `t_operator_log` VALUES ('1', '1', 'admin', '登陆', '2019-03-23 08:45:48', '2019-03-23 08:45:48');
INSERT INTO `t_operator_log` VALUES ('2', '1', 'admin', '登陆', '2019-03-23 08:46:16', '2019-03-23 08:46:16');
INSERT INTO `t_operator_log` VALUES ('3', '1', 'admin', '登陆', '2019-03-23 08:46:25', '2019-03-23 08:46:25');
INSERT INTO `t_operator_log` VALUES ('4', '1', 'admin', '登陆', '2019-03-23 08:46:29', '2019-03-23 08:46:29');
INSERT INTO `t_operator_log` VALUES ('5', '1', 'admin', '登陆', '2019-03-23 08:46:44', '2019-03-23 08:46:44');
INSERT INTO `t_operator_log` VALUES ('6', '1', 'admin', '登陆', '2019-03-23 08:46:56', '2019-03-23 08:46:56');
INSERT INTO `t_operator_log` VALUES ('7', '1', 'admin', '登陆', '2019-03-23 08:47:12', '2019-03-23 08:47:12');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permit_name` varchar(60) DEFAULT NULL COMMENT '权限名',
  `permit_type` varchar(20) DEFAULT NULL COMMENT '权限类型[菜单menu/按钮button]',
  `parent_id` int(5) DEFAULT NULL COMMENT '父权限id',
  `permit_code` varchar(100) DEFAULT NULL COMMENT '权限编码',
  `url` varchar(255) DEFAULT NULL COMMENT '连接路径',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `available` char(2) DEFAULT NULL COMMENT '启用状态[0关闭1启用]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', '管理面板', 'menu', null, 'manage', null, '2019-03-23 20:38:02', null, null, '1', '1');
INSERT INTO `t_permission` VALUES ('2', '用户管理', 'menu', '1', 'user', null, '2019-03-23 20:38:29', null, null, '2', '1');
INSERT INTO `t_permission` VALUES ('3', '用户管理-添加', 'button', '2', 'user-add', '/manage/user/add', '2019-03-23 20:39:00', null, null, '1.1', '1');
INSERT INTO `t_permission` VALUES ('4', '角色管理', 'menu', '1', 'role', null, '2019-03-23 20:44:51', null, null, '2', '1');
INSERT INTO `t_permission` VALUES ('5', '角色管理-添加', 'button', '4', 'role-add', '/manage/role/add', '2019-03-23 20:45:23', null, null, '2.1', '1');
INSERT INTO `t_permission` VALUES ('6', '机构管理', 'menu', '1', 'organ', null, '2019-03-24 12:39:12', null, null, '3', '1');
INSERT INTO `t_permission` VALUES ('7', '研发部', 'menu', '6', 'organ-dev', null, '2019-03-24 12:39:58', null, null, '1', '1');
INSERT INTO `t_permission` VALUES ('8', '研发部-添加', 'button', '7', 'organ-dev-add', '/manage/organ/dev/add', '2019-03-24 12:41:30', null, null, '1', '1');
INSERT INTO `t_permission` VALUES ('9', '运维部', 'menu', '6', 'organ-ops', null, '2019-03-24 12:45:05', null, null, '2', '1');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(60) DEFAULT NULL COMMENT '角色名',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '超级管理员具有所以权限', '2019-03-23 16:57:01', null);
INSERT INTO `t_role` VALUES ('2', '普通用户', '一般权限', '2019-03-23 16:57:30', null);

-- ----------------------------
-- Table structure for t_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission_relation`;
CREATE TABLE `t_role_permission_relation` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(20) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(20) DEFAULT NULL COMMENT '权限id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission_relation
-- ----------------------------
INSERT INTO `t_role_permission_relation` VALUES ('1', '1', '1', '2019-03-23 17:19:13', null);
INSERT INTO `t_role_permission_relation` VALUES ('2', '1', '2', '2019-03-23 17:19:16', null);
INSERT INTO `t_role_permission_relation` VALUES ('3', '1', '3', '2019-03-23 17:19:21', null);
INSERT INTO `t_role_permission_relation` VALUES ('4', '1', '4', '2019-03-23 17:19:37', null);
INSERT INTO `t_role_permission_relation` VALUES ('5', '1', '5', '2019-03-23 17:19:42', null);
INSERT INTO `t_role_permission_relation` VALUES ('6', '2', '4', '2019-03-23 17:20:38', null);
INSERT INTO `t_role_permission_relation` VALUES ('7', '2', '5', '2019-03-23 17:20:43', null);
INSERT INTO `t_role_permission_relation` VALUES ('8', '1', '6', '2019-03-24 12:41:43', null);
INSERT INTO `t_role_permission_relation` VALUES ('9', '1', '7', '2019-03-24 12:41:46', null);
INSERT INTO `t_role_permission_relation` VALUES ('10', '1', '8', '2019-03-24 12:45:18', null);
INSERT INTO `t_role_permission_relation` VALUES ('11', '1', '9', '2019-03-24 12:45:21', null);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_account` varchar(60) DEFAULT NULL COMMENT '登录账号',
  `login_password` varchar(255) DEFAULT NULL COMMENT '登录密码',
  `user_name` varchar(60) DEFAULT NULL COMMENT '用户名',
  `gender` char(1) DEFAULT NULL COMMENT '性别[0男1]',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `user_status` char(2) DEFAULT NULL COMMENT '用户状态[0关闭1启用]',
  `organ_id` int(11) DEFAULT NULL COMMENT '组织机构id',
  `token` varchar(255) DEFAULT NULL COMMENT '登录token',
  `tenant_id` varchar(3) DEFAULT NULL COMMENT '租户号',
  `sys_id` varchar(3) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'admin', null, null, '0', null, null, null, null, '2019-03-23 16:42:54', '2019-03-23 16:42:54');
INSERT INTO `t_user` VALUES ('2', 'a', 'a', 'a', '0', null, '0', null, null, null, null, '2019-03-23 16:55:59', null);

-- ----------------------------
-- Table structure for t_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_relation`;
CREATE TABLE `t_user_role_relation` (
  `id` int(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `role_id` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role_relation
-- ----------------------------
INSERT INTO `t_user_role_relation` VALUES ('1', '1', '1', '2019-03-23 17:16:34', null);
INSERT INTO `t_user_role_relation` VALUES ('2', '2', '2', '2019-03-23 17:17:03', null);
