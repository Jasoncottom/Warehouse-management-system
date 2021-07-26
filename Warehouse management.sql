/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : Warehouse management

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 02/07/2021 11:08:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 仓库
-- ----------------------------
DROP TABLE IF EXISTS `仓库`;
CREATE TABLE `仓库` (
  `商品编号` varchar(25) NOT NULL,
  `商品名` varchar(30) DEFAULT NULL,
  `供货商` varchar(25) DEFAULT NULL,
  `库存量` int(11) DEFAULT NULL,
  PRIMARY KEY (`商品编号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 仓库
-- ----------------------------
BEGIN;
INSERT INTO `仓库` VALUES ('1422', '《C语言程序设计》', '清华大学出版社', 25);
INSERT INTO `仓库` VALUES ('1433', '《Java从入门到入土》', '黑莓程序袁', 2);
INSERT INTO `仓库` VALUES ('9940', '剃须刀', '飞科', 15);
INSERT INTO `仓库` VALUES ('9941', '剃须刀', '小米有品', 30);
COMMIT;

-- ----------------------------
-- Table structure for 入库单
-- ----------------------------
DROP TABLE IF EXISTS `入库单`;
CREATE TABLE `入库单` (
  `商品编号` varchar(25) NOT NULL,
  `账号` varchar(25) NOT NULL,
  `入库时间` date NOT NULL,
  `入库数量` int(11) NOT NULL,
  PRIMARY KEY (`商品编号`,`账号`,`入库时间`),
  KEY `账号` (`账号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 入库单
-- ----------------------------
BEGIN;
INSERT INTO `入库单` VALUES ('1422', '1', '2021-07-01', 30);
INSERT INTO `入库单` VALUES ('1433', '1', '2021-06-25', 1);
INSERT INTO `入库单` VALUES ('1433', '1', '2021-06-29', 2);
INSERT INTO `入库单` VALUES ('1433', '1', '2021-06-30', 2);
INSERT INTO `入库单` VALUES ('9940', '1', '2020-07-09', 20);
INSERT INTO `入库单` VALUES ('9941', 'admin', '2021-07-02', 30);
COMMIT;

-- ----------------------------
-- Table structure for 出库单
-- ----------------------------
DROP TABLE IF EXISTS `出库单`;
CREATE TABLE `出库单` (
  `商品编号` varchar(25) NOT NULL,
  `账号` varchar(25) NOT NULL,
  `出库时间` date NOT NULL,
  `出库数量` int(11) NOT NULL,
  PRIMARY KEY (`商品编号`,`账号`,`出库时间`),
  KEY `账号` (`账号`),
  CONSTRAINT `出库单_ibfk_1` FOREIGN KEY (`商品编号`) REFERENCES `仓库` (`商品编号`),
  CONSTRAINT `出库单_ibfk_2` FOREIGN KEY (`账号`) REFERENCES `管理员` (`账号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 出库单
-- ----------------------------
BEGIN;
INSERT INTO `出库单` VALUES ('1422', 'admin', '2021-07-01', 5);
INSERT INTO `出库单` VALUES ('1433', '1', '2021-06-04', 1);
INSERT INTO `出库单` VALUES ('1433', '1', '2021-06-15', 2);
INSERT INTO `出库单` VALUES ('9940', '1', '2021-07-01', 5);
COMMIT;

-- ----------------------------
-- Table structure for 管理员
-- ----------------------------
DROP TABLE IF EXISTS `管理员`;
CREATE TABLE `管理员` (
  `账号` varchar(25) NOT NULL,
  `密码` varchar(30) NOT NULL,
  `姓名` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`账号`,`密码`),
  KEY `账号` (`账号`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of 管理员
-- ----------------------------
BEGIN;
INSERT INTO `管理员` VALUES ('1', '1', '1');
INSERT INTO `管理员` VALUES ('admin', 'password', 'test');
INSERT INTO `管理员` VALUES ('GAVT', 'root', '小萌');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
