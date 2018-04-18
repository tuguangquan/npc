/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : npc

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-04-17 13:22:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_wxmessage_type
-- ----------------------------
DROP TABLE IF EXISTS `db_wxmessage_type`;
CREATE TABLE `db_wxmessage_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(100) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_createUserId` (`createUserId`),
  CONSTRAINT `FK_createUserId` FOREIGN KEY (`createUserId`) REFERENCES `sys_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_wxmessage_type
-- ----------------------------
INSERT INTO `db_wxmessage_type` VALUES ('1', '要闻公告', '100', '32', '2017-04-05 08:30:49');
INSERT INTO `db_wxmessage_type` VALUES ('2', '立法选举', '100001', '33', '2017-05-05 14:30:49');
INSERT INTO `db_wxmessage_type` VALUES ('3', '决议决定', '100002', '34', '2017-06-05 17:30:49');
INSERT INTO `db_wxmessage_type` VALUES ('4', '监督工作', '100003', '35', '2017-07-05 20:30:49');
