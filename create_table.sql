/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50635
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2020-06-02 12:38:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
-- auto-generated definition
create table sys_params
(
    id          int auto_increment
        primary key,
    label       varchar(32)                         null comment 'element规范中的 label，通常为中文描述',
    value       varchar(16)                         null comment 'element规范中的 value，通常为数字',
    type        varchar(64)                         null comment '用来区分数据类型。二级三级四级类型用type和parent_id来区分',
    state       char      default '1'               null comment '0：无效。1：有效',
    parent_id   int                                 null comment '父级ID',
    parent_ids  varchar(128)                        null comment '多个父级ID都好分割',
    remarks     varchar(128)                        null comment '备注信息',
    create_time timestamp default CURRENT_TIMESTAMP null
)
    comment 'gis系统参数表';

