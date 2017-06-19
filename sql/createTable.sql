/*
Navicat MySQL Data Transfer

Source Server         : MySQLRoot
Source Server Version : 50173
Source Host           : 123.206.55.89:3306
Source Database       : db_yun_splider

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-19 03:02:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for UserInfo
-- ----------------------------
DROP TABLE IF EXISTS `UserInfo`;
CREATE TABLE `UserInfo` (
  `uk` char(20) NOT NULL,
  `uname` varchar(50) DEFAULT NULL,
  `avatar_url` varchar(100) DEFAULT NULL,
  `album_craw` char(1) DEFAULT NULL,
  `fans_craw` char(1) DEFAULT NULL,
  `follow_craw` char(1) DEFAULT NULL,
  `pubshare_craw` char(1) DEFAULT NULL,
  `album_count` int(11) DEFAULT NULL,
  `fans_count` int(11) DEFAULT NULL,
  `follow_count` int(11) DEFAULT NULL,
  `pubshare_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`uk`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
/*
Navicat MySQL Data Transfer

Source Server         : MySQLRoot
Source Server Version : 50173
Source Host           : 123.206.55.89:3306
Source Database       : db_yun_splider

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-19 03:02:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for FileInfo
-- ----------------------------
DROP TABLE IF EXISTS `FileInfo`;
CREATE TABLE `FileInfo` (
  `md5` varchar(50) NOT NULL,
  `shareid` char(20) DEFAULT NULL,
  `uk` char(20) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `server_filename` varchar(20) DEFAULT NULL,
  `size` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`md5`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;