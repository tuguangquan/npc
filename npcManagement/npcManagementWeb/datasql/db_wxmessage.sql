/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : npc

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2018-04-17 13:22:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_wxmessage
-- ----------------------------
DROP TABLE IF EXISTS `db_wxmessage`;
CREATE TABLE `db_wxmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `imgTitle` varchar(255) DEFAULT NULL,
  `imgPath` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `createUserId` int(11) DEFAULT NULL,
  `createTime` varchar(20) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `checkId` int(11) DEFAULT NULL,
  `checkTime` varchar(20) DEFAULT NULL,
  `checkResult` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_typeId` (`typeId`),
  KEY `FK_checkId` (`checkId`),
  KEY `db_createUserId` (`createUserId`),
  CONSTRAINT `db_createUserId` FOREIGN KEY (`createUserId`) REFERENCES `sys_user` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_checkId` FOREIGN KEY (`checkId`) REFERENCES `sys_adminrole` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FK_typeId` FOREIGN KEY (`typeId`) REFERENCES `db_wxmessage_type` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_wxmessage
-- ----------------------------
INSERT INTO `db_wxmessage` VALUES ('1', '三大研会换届', '三峡大学研究生会于去年一月换届', null, null, '1', '32', '2017-01-15 08:30:49', '100', '1', '2017-01-20 08:30:49', '通过');
INSERT INTO `db_wxmessage` VALUES ('2', '计信研会换届', '三峡大学计信研究生会于去年12月换届', null, null, '2', '33', '2017-12-15 08:30:49', '100001', '15', '2017-12-20 08:30:49', '通过');
INSERT INTO `db_wxmessage` VALUES ('3', '电气研会换届', '三峡大学研究生会于去年九月换届', null, null, '3', '34', '2017-09-15 08:30:49', '100002', '3', '2017-09-20 08:30:49', '不通过');
INSERT INTO `db_wxmessage` VALUES ('4', '经管研会换届', '三峡大学研究生会于去年十月换届', null, null, '4', '35', '2017-10-15 08:30:49', '100003', '4', '2017-10-20 08:30:49', '不通过');
