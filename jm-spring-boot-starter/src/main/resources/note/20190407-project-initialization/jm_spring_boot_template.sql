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

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `url` varchar(200) NOT NULL COMMENT 'URL. If type of record is page (1), URL stands for route; if type of record is button (2), URL stands for API',
  `description` varchar(100) NOT NULL COMMENT 'Permission description',
  `type` tinyint(2) NOT NULL COMMENT 'Permission type. 1 - page; 2 - button',
  `permission_expression` varchar(50) DEFAULT NULL COMMENT 'Permission expression',
  `method` enum('GET','HEAD','POST','PUT','DELETE','CONNECT','OPTIONS','TRACE','PATCH') DEFAULT NULL COMMENT 'HTTP method of API',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT 'Sort number',
  `parent_id` bigint(64) DEFAULT '0' COMMENT 'Primary key of parent',
  `deleted` tinyint(2) NOT NULL DEFAULT '0' COMMENT 'Deleted flag',
  `gmt_created` datetime NOT NULL COMMENT 'Created time',
  `gmt_modified` datetime NOT NULL COMMENT 'Modified time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_expression_UNIQUE` (`permission_expression`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='Permission Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
  `name` varchar(50) NOT NULL COMMENT 'Role name',
  `description` varchar(100) NOT NULL COMMENT 'Role description',
  `gmt_created` datetime NOT NULL COMMENT 'Created time',
  `gmt_modified` datetime NOT NULL COMMENT 'Modified time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='Role Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
INSERT INTO `t_role` (`id`, `name`, `description`, `gmt_created`, `gmt_modified`) VALUES (1,'superuser','Superuser has no restrictions to access the system\'s resources',now(),now());
INSERT INTO `t_role` (`id`, `name`, `description`, `gmt_created`, `gmt_modified`) VALUES (2,'common_user','Common user with limited access permission',now(),now());
INSERT INTO `t_role` (`id`, `name`, `description`, `gmt_created`, `gmt_modified`) VALUES (3,'test_role','Test role',now(),now());
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `role_id` bigint(64) NOT NULL COMMENT 'Primary key of role',
  `permission_id` varchar(45) NOT NULL COMMENT 'Primary key of permission',
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Role-permission Relation Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `t_user`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(64) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Primary key of user',
  `username` varchar(50) NOT NULL COMMENT 'Username',
  `email` varchar(100) NOT NULL COMMENT 'Email',
  `cellphone` varchar(11) DEFAULT NULL COMMENT 'Cellphone number',
  `password` varchar(60) NOT NULL COMMENT 'Password',
  `full_name` varchar(255) DEFAULT NULL COMMENT 'Full name',
  `birthday` date DEFAULT NULL COMMENT 'Birthday',
  `gender` enum('Agender','Androgyne','Bigender','Cisgender','Cisgender Female','Cisgender Male','Female to Male','Gender Fluid','Gender Nonconforming','Gender Questioning','Gender Variant','Genderqueer','Intersex','Male to Female','Neither','Neutrois','Non-binary','Other','Pangender','Transfeminine','Transgender','Transgender Female','Transgender Male','Transgender Person','Transmasculine','Two-Spirit') DEFAULT NULL COMMENT '26 gender options',
  `status` tinyint(2) NOT NULL DEFAULT '1' COMMENT 'Status. 1 - enabled, 2 - disabled',
  `gmt_modified` datetime NOT NULL COMMENT 'Modified time',
  `gmt_created` datetime NOT NULL COMMENT 'Created time',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `cellphone_UNIQUE` (`cellphone`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='User Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
INSERT INTO `t_user` (`id`, `username`, `email`, `cellphone`, `password`, `full_name`, `birthday`, `gender`, `status`, `gmt_modified`, `gmt_created`) VALUES (1,'admin','admin@gmail.com','17300000000','$2a$10$64iuSLkKNhpTN19jGHs7xePvFsub7ZCcCmBqEYw8fbACGTE3XetYq','Superuser','1996-04-29','Cisgender Male',1,now(),now());
INSERT INTO `t_user` (`id`, `username`, `email`, `cellphone`, `password`, `full_name`, `birthday`, `gender`, `status`, `gmt_modified`, `gmt_created`) VALUES (2,'johnny','johnnysviva@outlook.com','17300001111','$2a$10$OUDl4thpcHfs7WZ1kMUOb.ZO5eD4QANW5E.cexBLiKDIzDNt87QbO','Johnny Miller','1996-04-29','Cisgender Male',1,now(),now());
INSERT INTO `t_user` (`id`, `username`, `email`, `cellphone`, `password`, `full_name`, `birthday`, `gender`, `status`, `gmt_modified`, `gmt_created`) VALUES (3,'test','test@gmail.com','19100000000','$2a$10$UCRMTPiv8xKlIQZqkdly3eUyC0BwtHivrLgq9jcMyZrnbs4pASH.O','Test User 01','1996-01-01','Cisgender Female',1,now(),now());
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_id` bigint(64) NOT NULL COMMENT 'Primary key of user',
  `role_id` bigint(65) NOT NULL COMMENT 'Primary key of role',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='User-role Relation Table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
INSERT INTO `t_user_role` (`user_id`, `role_id`) VALUES (1,1);
INSERT INTO `t_user_role` (`user_id`, `role_id`) VALUES (2,2);
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01 10:29:00
