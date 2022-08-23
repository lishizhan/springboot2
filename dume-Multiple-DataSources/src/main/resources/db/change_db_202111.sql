/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql5.7
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : change_db_202111

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2022-01-04 11:39:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for change_table
-- ----------------------------
DROP TABLE IF EXISTS `change_table`;
CREATE TABLE `change_table` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `heroname` varchar(30) NOT NULL COMMENT '英雄名称',
  `age` int(3) NOT NULL COMMENT '英雄年纪',
  `changedbname` varchar(30) NOT NULL COMMENT '数据源名称',
  `operationtime` datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of change_table
-- ----------------------------
INSERT INTO `change_table` VALUES ('1', '貂蝉', '20', 'change_db_202111', '2021-11-01 09:15:00');
