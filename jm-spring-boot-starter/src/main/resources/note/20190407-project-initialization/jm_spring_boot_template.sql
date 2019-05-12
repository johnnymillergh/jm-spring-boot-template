-- MySQL dump 10.13  Distrib 5.7.25, for macos10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: jm_spring_boot_template
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `url` varchar(200) NOT NULL COMMENT 'URL. If record''s type is page, URL stands for route; if record''s type is button, URL stands for API',
  `description` varchar(100) NOT NULL COMMENT 'Permission description',
  `type` tinyint(4) NOT NULL COMMENT 'Permission type. 1 - page; 2 - button',
  `permission_expression` varchar(50) DEFAULT NULL COMMENT 'Permission expression',
  `method` enum('GET','HEAD','POST','PUT','DELETE','CONNECT','OPTIONS','TRACE','PATCH') DEFAULT NULL COMMENT 'HTTP method of API',
  `sort` int(11) unsigned NOT NULL DEFAULT '0' COMMENT 'Sort',
  `parent_id` bigint(64) unsigned NOT NULL DEFAULT '0' COMMENT 'Primary key of parent',
  `gmt_created` datetime NOT NULL COMMENT 'Created time',
  `gmt_modified` datetime NOT NULL COMMENT 'Modified time',
  `deleted` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `t_permission_url_uindex` (`url`),
  UNIQUE KEY `t_permission_permission_expression_uindex` (`permission_expression`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Permission Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `name` varchar(50) NOT NULL COMMENT 'Role name',
  `description` varchar(100) DEFAULT NULL COMMENT 'Role description',
  `gmt_created` datetime NOT NULL COMMENT 'Create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'Modify time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Role Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'root','Super User','2019-03-23 13:12:41','2019-03-23 13:12:41'),(2,'common_user','Common User','2019-03-23 13:12:41','2019-03-23 13:12:41');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(64) NOT NULL COMMENT 'Part of primary key: foreign key of role',
  `permission_id` bigint(64) NOT NULL COMMENT 'Part of primary key: foreign key of permission',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Role-permission Relation Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `username` varchar(50) NOT NULL COMMENT 'Username',
  `email` varchar(50) NOT NULL COMMENT 'Email',
  `cellphone` varchar(11) DEFAULT NULL COMMENT 'Cellphone number',
  `password` varchar(60) NOT NULL COMMENT 'Password',
  `full_name` varchar(255) DEFAULT NULL COMMENT 'Nickname',
  `birthday` date DEFAULT NULL COMMENT 'Birthday (yyyy-MM-dd)',
  `gender` enum('Agender','Androgyne','Androgynous','Bigender','Cis','Cisgender','Cis Female','Cis Male','Cis Man','Cis Woman','Cisgender Female','Cisgender Male','Cisgender Man','Cisgender Woman','Female to Male','FTM','Gender Fluid','Gender Nonconforming','Gender Questioning','Gender Variant','Genderqueer','Intersex','Male to Female','MTF','Neither','Neutrois','Non-binary','Other','Pangender','Trans','Trans*','Trans Female','Trans* Female','Trans Male','Trans* Male','Trans Man','Trans* Man','Trans Person','Trans* Person','Trans Woman','Trans* Woman','Transfeminine','Transgender','Transgender Female','Transgender Male','Transgender Man','Transgender Person','Transgender Woman','Transmasculine','Transsexual','Transsexual Female','Transsexual Male','Transsexual Man','Transsexual Person','Transsexual Woman','Two-Spirit') NOT NULL COMMENT '58 gender options',
  `gmt_created` datetime NOT NULL COMMENT 'Create time',
  `gmt_modified` datetime NOT NULL COMMENT 'Modify time',
  `status` int(2) NOT NULL DEFAULT '1' COMMENT 'Status. Enabled-1，Disenabled-0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phone` (`cellphone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='User Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'admin','admin@gmail.com','17300000000','$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq','Super User','2019-05-12','Agender','2019-05-12 07:52:19','2019-05-12 07:52:19',1),(2,'johnny','johnnysviva@outlook.com','17300001111','$2a$10$OUDl4thpcHfs7WZ1kMUOb.ZO5eD4QANW5E.cexBLiKDIzDNt87QbO','Johnny Miller','2019-05-12','Agender','2019-05-12 07:52:19','2019-05-12 07:52:19',1);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT 'Part of primary key: foreign key of user table',
  `role_id` bigint(64) NOT NULL COMMENT 'Part of primary key: foreign key of role table',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User-role Relation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-12 17:15:52
