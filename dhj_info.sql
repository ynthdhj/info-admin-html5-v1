/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.10 : Database - dhj_info
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dhj_info` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dhj_info`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='admin用户的密码为  ';

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`createdate`) values 
(1099112515322908672,'admin','$2a$10$EabpPDx5szRwXytJexY8e.rZ2FzVsqdYU5Os9C0mOvmHPTQURPI/2','2023-06-04 12:37:43'),
(1165774143208165377,'admin1','$2a$10$Cio1BlFqf6/Q6B6LE1hLXeslSt4XvONrpLgX0gRdr/FCmsZzdRVJ.','2023-10-22 22:10:15');

/*Table structure for table `basepage` */

DROP TABLE IF EXISTS `basepage`;

CREATE TABLE `basepage` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `message` text,
  `createdate` datetime DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createDate` (`createdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='信息表';

/*Data for the table `basepage` */

insert  into `basepage`(`id`,`title`,`message`,`createdate`,`path`) values 
(1136032661274497024,'sdgs','<p>dsg</p>','2023-08-01 20:28:13',NULL),
(1136378596127019008,'pp','<p>pp</p>','2023-08-02 19:22:50',NULL),
(1136396685514248192,'sssss','<p>ssss</p>','2023-08-02 20:34:43',NULL),
(1136765004561911808,'dddd','<p>dddd</p>','2023-08-03 20:58:17',NULL),
(1136766182309892097,'88','<p>88</p>','2023-08-03 21:02:58',NULL),
(1136766389613367296,'99','<p>99</p>','2023-08-03 21:03:47',NULL),
(1136767070692839424,'pp','<p>pp</p>','2023-08-03 21:06:30',NULL),
(1136767949944786944,'00','<p>000</p>','2023-08-03 21:09:59',NULL),
(1136768227943256064,'11','<p>111</p>','2023-08-03 21:11:06',NULL),
(1136768313175707649,'22','<p>222</p>','2023-08-03 21:11:26','/static/upload/basepage/images/2023-08-03/eb275563517e44c58f0584e9690b0dc0/aa.png'),
(1136769637007101953,'cc','<p>cc</p>','2023-08-03 21:16:42',NULL),
(1136769716992479233,'ddd','<p>dddddd</p>','2023-08-03 21:17:01','/static/upload/basepage/images/2023-08-03/67f845eefd8742b6ab8a7965a3fbd4eb/aa.png'),
(1141095594983559169,'test','<p>test<img>mm</p><p>&nbsp;</p>','2023-08-15 19:46:30',NULL),
(1141098353619243009,'标题','<p>内容<img>kkk</p><figure class=\"image image-style-side\"><img></figure>','2023-08-15 19:57:28',NULL),
(1141109942598635521,'理基础页','<p>sssss<img><img></p>','2023-08-15 20:43:31',NULL),
(1142416775879725057,'aaaa','<p>aa<img>bb</p>','2023-08-19 11:16:24',NULL),
(1142417384783613953,'yyy','<figure class=\"image\"><img></figure>','2023-08-19 11:18:50',NULL),
(1142441537171492865,'aaa','<p>fgg<img alt=\"sss\" height=\"120\" src=\"http://127.0.0.1:8080/static/upload/public_ckeditor_image/images/2023-08-19/eba61f3315e847e5971f5d4cacab1a46/b.jpg\" width=\"150\" /></p>\r\n','2023-08-19 12:54:48',NULL),
(1145025243383992321,'标题标题标题','<p>标题标题标题标题标题</p>\r\n','2023-08-26 16:01:32','/static/upload/basepage/images/2023-08-26/750a160d2f6b4b14be327a0ec9f1c1c3/e.jpg'),
(1145027022532251649,'a1','<p>b1</p>\r\n','2023-08-26 16:08:36','/static/upload/basepage/images/2023-08-26/0ebdad53d22f404dbd198ac36458eae1/d.jpg'),
(1145027063737094145,'aa1','<p>bb1</p>\r\n','2023-08-26 16:08:46','/static/upload/basepage/images/2023-08-26/9beb6e134740491fa46b24f2d18a3eb3/a.jpg'),
(1145027152668921856,'aaa','<p>bbb<img alt=\"\" src=\"/static/upload/public_ckeditor_image/images/2023-08-26/50cdb0a46e2f4283b4fffdd43e5bc653/e.jpg\" style=\"height:180px; width:180px\" /><img alt=\"\" src=\"/static/upload/public_ckeditor_image/images/2023-08-26/5fa744c4ae7b4ea1ae685e32b9246db5/b.jpg\" style=\"height:120px; width:150px\" /></p>\r\n','2023-08-26 16:09:07','/static/upload/basepage/images/2023-08-26/a3066122621a448bb205cd712f39d4cf/a.jpg'),
(1156324185572773889,'标题','<h1>基础页<img alt=\"\" src=\"/static/upload/public_ckeditor_image/images/2023-09-26/6331e962fd1c47b1a4d96a45ba0b9144/微信截图_1.jpg\" style=\"height:64px; width:102px\" /></h1>\r\n','2023-09-26 20:19:29','/static/upload/basepage/images/2023-09-26/4abcaa6ea64448ba9c4adf75de90f427/微信截图_3.jpg');

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='一级分类，管理员手工修改表定制内容';

/*Data for the table `board` */

insert  into `board`(`id`,`name`,`createdate`) values 
(1124644866802978817,'云南新闻','2023-07-01 10:17:11'),
(1124647786114060289,'国际新闻1','2023-07-01 10:28:47'),
(1124997767706578945,'国际新闻4','2023-07-02 09:39:29');

/*Table structure for table `circle` */

DROP TABLE IF EXISTS `circle`;

CREATE TABLE `circle` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='一级分类，管理员手工修改表定制内容';

/*Data for the table `circle` */

insert  into `circle`(`id`,`name`,`createdate`) values 
(1126222141377875968,'圈子','2023-07-05 18:44:43');

/*Table structure for table `info` */

DROP TABLE IF EXISTS `info`;

CREATE TABLE `info` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `board_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `circle_id` bigint(20) unsigned DEFAULT NULL,
  `key0_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `key1_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `key2_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `author` varchar(50) DEFAULT '' COMMENT '上传者',
  `message` text,
  `path` varchar(255) DEFAULT NULL,
  `isauditing` int(1) unsigned NOT NULL DEFAULT '0',
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `boardId` (`board_id`),
  KEY `createDate` (`createdate`),
  KEY `key0Id` (`key0_id`),
  KEY `key1Id` (`key1_id`),
  KEY `key2Id` (`key2_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='信息表';

/*Data for the table `info` */

/*Table structure for table `key0` */

DROP TABLE IF EXISTS `key0`;

CREATE TABLE `key0` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='一级分类，管理员手工修改表定制内容';

/*Data for the table `key0` */

insert  into `key0`(`id`,`name`,`createdate`) values 
(1126245979872432128,'分类0','2023-07-05 20:19:26');

/*Table structure for table `key1` */

DROP TABLE IF EXISTS `key1`;

CREATE TABLE `key1` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='一级分类，管理员手工修改表定制内容';

/*Data for the table `key1` */

insert  into `key1`(`id`,`name`,`createdate`) values 
(1126248782078545920,'分类1','2023-07-05 20:30:34');

/*Table structure for table `key2` */

DROP TABLE IF EXISTS `key2`;

CREATE TABLE `key2` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='一级分类，管理员手工修改表定制内容';

/*Data for the table `key2` */

insert  into `key2`(`id`,`name`,`createdate`) values 
(1142809792100831232,'管理分类2','2023-08-20 13:18:07');

/*Table structure for table `manage` */

DROP TABLE IF EXISTS `manage`;

CREATE TABLE `manage` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `message` text,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createDate` (`createdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `manage` */

insert  into `manage`(`id`,`phone`,`name`,`password`,`message`,`createdate`) values 
(1159592285902606336,'18288292540','uu','$2a$10$xImQFLTVMLzQ1mh/Y4idd.HMu3pflxA8bK5fetIMgM0Jrt773Y8u2','<p>11</p>\r\n','2023-10-05 20:45:45'),
(1159594197762838529,'18288292541','张三0','$2a$10$AlmVagTJYhU1MH6QGTx2teNlQAy/FxJiiiS8L6Lc/4X71isDexzc2','<p>sddasfaf <strong>你好</strong> ！！！！0</p>\r\n','2023-10-05 20:53:21');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `createDate` (`createdate`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表，用于权限控制。不设权限表。本表开发人员定制。每个角色有一个后台，角色英文名为后台目录。';

/*Data for the table `role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
