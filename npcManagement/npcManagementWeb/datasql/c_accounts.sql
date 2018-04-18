/*
Navicat MySQL Data Transfer

Source Server         : npc
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : npc

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-07 09:33:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for c_accounts
-- ----------------------------
DROP TABLE IF EXISTS `c_accounts`;
CREATE TABLE `c_accounts` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `appid` varchar(100) DEFAULT NULL,
  `appsecert` varchar(100) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_accounts
-- ----------------------------
INSERT INTO `c_accounts` VALUES ('1', 'wx41a6970feceade54', '152c68cf23fef8901d1ec9fa8aa5a201', '宜昌人大服务号');
