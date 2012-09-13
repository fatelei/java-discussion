/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.5.27 : Database - discuss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`discuss` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `discuss`;

/*Table structure for table `answer` */

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
  `answer_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `answer_content` text COLLATE utf8_unicode_ci NOT NULL,
  `answer_user` int(40) unsigned NOT NULL,
  `answer_object` int(10) unsigned NOT NULL,
  `answer_relTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`answer_id`),
  KEY `FK_Answer_REF_User` (`answer_user`),
  KEY `FK_Answer_REF_object` (`answer_object`),
  CONSTRAINT `FK_Answer_REF_object` FOREIGN KEY (`answer_object`) REFERENCES `discussobject` (`disobj_id`),
  CONSTRAINT `FK_Answer_REF_User` FOREIGN KEY (`answer_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `answer` */

/*Table structure for table `discussobject` */

DROP TABLE IF EXISTS `discussobject`;

CREATE TABLE `discussobject` (
  `disobj_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `disobj_topic` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `disobj_content` text COLLATE utf8_unicode_ci NOT NULL,
  `disobj_user` int(10) unsigned DEFAULT NULL,
  `disobj_relTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `disobj_lookNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`disobj_id`),
  KEY `FK_DiscussObject_REF_User` (`disobj_user`),
  CONSTRAINT `FK_DiscussObject_REF_User` FOREIGN KEY (`disobj_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `discussobject` */

/*Table structure for table `seconddiscuss` */

DROP TABLE IF EXISTS `seconddiscuss`;

CREATE TABLE `seconddiscuss` (
  `secdis_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `secdis_content` text COLLATE utf8_unicode_ci NOT NULL,
  `secdis_supnum` int(11) NOT NULL DEFAULT '0',
  `secdis_oppnum` int(11) NOT NULL DEFAULT '0',
  `secdis_user` int(11) unsigned NOT NULL,
  `secdis_object` int(10) unsigned NOT NULL,
  `secdis_relTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`secdis_id`),
  KEY `FK_SecondDiscuss_REF_user` (`secdis_user`),
  KEY `FK_SecondDiscuss_REF_object` (`secdis_object`),
  CONSTRAINT `FK_SecondDiscuss_REF_object` FOREIGN KEY (`secdis_object`) REFERENCES `discussobject` (`disobj_id`),
  CONSTRAINT `FK_SecondDiscuss_REF_user` FOREIGN KEY (`secdis_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `seconddiscuss` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_password` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_rank` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
