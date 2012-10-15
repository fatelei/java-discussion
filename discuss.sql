-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: discuss
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussobject`
--

DROP TABLE IF EXISTS `discussobject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussobject`
--

LOCK TABLES `discussobject` WRITE;
/*!40000 ALTER TABLE `discussobject` DISABLE KEYS */;
INSERT INTO `discussobject` VALUES (13,'我电话覅中','电脑覅哈佛的',14,'2012-09-22 13:08:29',1),(14,'dsadsdasdassadsad','dasdasd',15,'2012-09-23 12:45:00',0),(15,'dasdsad','asdasdsad',15,'2012-09-23 12:58:48',1),(16,'dasdasdasdasd','dasdasdasdad',15,'2012-09-23 12:59:01',1),(17,'dsadasdasd','dasdasdasdd',15,'2012-09-23 12:59:14',1);
/*!40000 ALTER TABLE `discussobject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seconddiscuss`
--

DROP TABLE IF EXISTS `seconddiscuss`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seconddiscuss`
--

LOCK TABLES `seconddiscuss` WRITE;
/*!40000 ALTER TABLE `seconddiscuss` DISABLE KEYS */;
INSERT INTO `seconddiscuss` VALUES (1,'asdgrthyjukil',210,521,13,13,'2012-09-23 12:04:54'),(2,'asdasdasd',0,0,13,13,'2012-09-23 12:15:20'),(3,'dsadasdasdasd',0,0,13,13,'2012-09-23 12:17:49'),(4,'dasdasdasd',0,0,13,13,'2012-09-23 12:20:19'),(5,'dsadasdasd',1,0,15,14,'2012-09-23 12:45:13');
/*!40000 ALTER TABLE `seconddiscuss` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `secvoip`
--

DROP TABLE IF EXISTS `secvoip`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `secvoip` (
  `ip_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ip_ad` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `ip_sedis` int(10) unsigned NOT NULL,
  `ip_user` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ip_id`),
  KEY `FK_secvoip_ref_user` (`ip_user`),
  KEY `FK_secvoip_ref_secdis` (`ip_sedis`),
  CONSTRAINT `FK_secvoip_ref_secdis` FOREIGN KEY (`ip_sedis`) REFERENCES `seconddiscuss` (`secdis_id`),
  CONSTRAINT `FK_secvoip_ref_user` FOREIGN KEY (`ip_user`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `secvoip`
--

LOCK TABLES `secvoip` WRITE;
/*!40000 ALTER TABLE `secvoip` DISABLE KEYS */;
INSERT INTO `secvoip` VALUES (1,'127.0.0.1',1,13),(2,'127.0.0.2',1,13),(3,'127.0.0.1',5,15);
/*!40000 ALTER TABLE `secvoip` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_password` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `user_rank` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (13,'ocean','ocean',1),(14,'hai','hai',1),(15,'hai1','123',2),(16,'hai2','hai',1),(17,'hai3','hai',1),(18,'fate','123',3),(19,'fate1','202cb962ac59075b964b07152d234b70',3);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-15 10:10:20
