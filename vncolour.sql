-- MySQL dump 10.13  Distrib 8.0.34, for macos13 (arm64)
--
-- Host: 127.0.0.1    Database: vietnamesecolour
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `combo`
--

DROP TABLE IF EXISTS `combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `combo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `combo_name` varchar(50) DEFAULT NULL,
  `combo_price` float DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `combo_unq` (`combo_name`),
  KEY `fk_combo_created_by` (`created_by`),
  KEY `fk_combo_updated_by` (`updated_by`),
  CONSTRAINT `fk_combo_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_combo_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `combo`
--

LOCK TABLES `combo` WRITE;
/*!40000 ALTER TABLE `combo` DISABLE KEYS */;
INSERT INTO `combo` VALUES (1,'test',NULL,'2023-11-29 15:31:35','2023-11-29 15:31:35',1,1),(3,'test 2 edited',NULL,'2023-11-29 15:48:46','2023-11-29 15:49:11',1,1),(4,'ettt',NULL,'2023-11-29 22:01:41',NULL,NULL,NULL);
/*!40000 ALTER TABLE `combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dish_name` varchar(200) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `dish_description` varchar(450) DEFAULT NULL,
  `dish_image_path` varchar(100) DEFAULT NULL,
  `dish_image_name` varchar(100) DEFAULT NULL,
  `dish_group_id` int(11) DEFAULT NULL,
  `unit_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dish_created_by` (`created_by`),
  KEY `fk_dish_updated_by` (`updated_by`),
  KEY `fk_dish_unit_id` (`unit_id`),
  KEY `fk_dish_dish_group_id` (`dish_group_id`),
  CONSTRAINT `fk_dish_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dish_dish_group_id` FOREIGN KEY (`dish_group_id`) REFERENCES `dish_group` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dish_unit_id` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dish_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (1,'test dish',100,NULL,NULL,NULL,1,1,'2023-11-28 15:24:27','2023-11-28 15:24:27',1,1),(2,'asd',34,NULL,NULL,NULL,3,2,'2023-11-28 15:24:27','2023-11-28 15:24:27',1,1),(3,'123e',4,NULL,NULL,NULL,3,3,'2023-11-28 15:24:27','2023-11-28 15:24:27',1,1),(4,'asgxc',43,NULL,NULL,NULL,4,4,'2023-11-28 15:24:27','2023-11-28 15:24:27',1,1),(5,'bxcbbx',23,NULL,NULL,NULL,5,5,'2023-11-28 15:24:27','2023-11-28 15:24:27',1,1),(6,'test upload',12.3,'test upload desc','uploads\\5ec291a2-0319-497a-8d8c-b055665ec7d8.jfif',NULL,3,2,'2023-11-28 16:45:37','2023-11-28 16:45:37',1,1),(7,'test upload',12.3,'test upload desc','uploads\\cac-buoc-xin-visa-uc-vem.png',NULL,3,2,'2023-11-28 17:28:19','2023-11-28 17:28:19',1,1),(8,'test upload',12.3,'test upload desc','F:\\Work\\cac-buoc-xin-visa-uc-vem.png',NULL,3,2,'2023-11-29 08:49:37','2023-11-29 08:49:37',1,1),(9,'test upload',12.3,'test upload desc','F:\\Work\\cac-buoc-xin-visa-uc-vem.png',NULL,3,2,'2023-11-29 08:51:06','2023-11-29 08:51:06',1,1),(10,'test upload edit 222',12.3,'test upload desc','uploads\\cac-buoc-xin-visa-uc-vem.png','cac-buoc-xin-visa-uc-vem.png',1,2,'2023-11-29 09:25:41','2023-11-29 13:55:53',1,1),(11,'test upload edit',12.3,'test upload desc','uploads\\cac-buoc-xin-visa-uc-vem.png','cac-buoc-xin-visa-uc-vem.png',1,2,'2023-11-29 11:45:57','2023-11-29 13:41:54',1,1);
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_combo`
--

DROP TABLE IF EXISTS `dish_combo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_combo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `combo_id` int(11) DEFAULT NULL,
  `dish_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_combo_id` (`combo_id`),
  KEY `fk_dish_id` (`dish_id`),
  CONSTRAINT `fk_combo_id` FOREIGN KEY (`combo_id`) REFERENCES `combo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_combo`
--

LOCK TABLES `dish_combo` WRITE;
/*!40000 ALTER TABLE `dish_combo` DISABLE KEYS */;
INSERT INTO `dish_combo` VALUES (1,1,9,'2023-11-29 15:32:28','2023-11-29 15:32:28'),(2,1,10,'2023-11-29 15:32:28','2023-11-29 15:32:28'),(3,1,11,'2023-11-29 15:32:28','2023-11-29 15:32:28');
/*!40000 ALTER TABLE `dish_combo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dish_group`
--

DROP TABLE IF EXISTS `dish_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dish_group_name` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dish_group_unq` (`dish_group_name`),
  KEY `fk_dish_group_created_by` (`created_by`),
  KEY `fk_dish_group_updated_by` (`updated_by`),
  CONSTRAINT `fk_dish_group_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dish_group_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish_group`
--

LOCK TABLES `dish_group` WRITE;
/*!40000 ALTER TABLE `dish_group` DISABLE KEYS */;
INSERT INTO `dish_group` VALUES (1,'test group 1 edited 7','2023-11-28 13:36:05','2023-11-28 13:38:24',1,1),(3,'Appertizer','2023-11-28 13:45:33','2023-11-28 13:45:33',1,1),(4,'Pho and Rice noodle','2023-11-28 13:45:36','2023-11-28 13:45:36',1,1),(5,'Main meal with Rice','2023-11-28 13:45:44','2023-11-28 13:45:44',1,1),(6,'For Vegetarian','2023-11-28 13:45:51','2023-11-28 13:45:51',1,1),(7,'Dessert','2023-11-28 13:45:58','2023-11-28 13:45:58',1,1);
/*!40000 ALTER TABLE `dish_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) DEFAULT NULL,
  `cust_name` varchar(200) NOT NULL,
  `cust_email` varchar(100) NOT NULL,
  `cust_mobile` varchar(45) NOT NULL,
  `cust_address` varchar(450) DEFAULT NULL,
  `note` varchar(450) DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `total_amount` float DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_time` varchar(45) DEFAULT NULL,
  `order_status_id` int(11) DEFAULT NULL,
  `order_type_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orddetail_created_by` (`created_by`),
  KEY `fk_orddetail_cust_id` (`cust_id`),
  KEY `fk_orddetail_status` (`order_status_id`),
  KEY `fk_orddetail_type` (`order_type_id`),
  KEY `fk_orddetail_updated_by` (`updated_by`),
  CONSTRAINT `fk_orddetail_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orddetail_cust_id` FOREIGN KEY (`cust_id`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orddetail_status` FOREIGN KEY (`order_status_id`) REFERENCES `order_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orddetail_type` FOREIGN KEY (`order_type_id`) REFERENCES `order_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orddetail_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (12,NULL,'aoigh skgh','agofi@skjdhg.co','1897518957','qrwtewet','note đơn tổng',0,73.8,'2022-10-23','13:22',1,1,'2023-12-12 22:47:23','2023-12-12 22:47:23',1,1);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_dish`
--

DROP TABLE IF EXISTS `order_dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_dish` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `dish_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orddish_dish_id` (`dish_id`),
  KEY `fk_orddish_order_id` (`order_id`),
  KEY `fk_orddish_created_by` (`created_by`),
  KEY `fk_orddish_updated_by` (`updated_by`),
  CONSTRAINT `fk_orddish_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orddish_dish_id` FOREIGN KEY (`dish_id`) REFERENCES `dish` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orddish_order_id` FOREIGN KEY (`order_id`) REFERENCES `order_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_orddish_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_dish`
--

LOCK TABLES `order_dish` WRITE;
/*!40000 ALTER TABLE `order_dish` DISABLE KEYS */;
INSERT INTO `order_dish` VALUES (17,12,10,3,12.3,'note item 1','2023-12-12 22:47:23','2023-12-12 22:47:23',1,1),(18,12,11,3,12.3,'note item 2','2023-12-12 22:47:23','2023-12-12 22:47:23',1,1);
/*!40000 ALTER TABLE `order_dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_status_name` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_status_unq` (`order_status_name`),
  KEY `fk_ordstt_created_by` (`created_by`),
  KEY `fk_ordstt_updated_by` (`updated_by`),
  CONSTRAINT `fk_ordstt_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_ordstt_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES (1,'Open','2023-12-08 09:52:19','2023-12-08 09:52:19',1,1),(2,'Confirmed','2023-12-08 09:52:19','2023-12-08 09:52:19',1,1),(3,'Completed','2023-12-08 09:52:19','2023-12-08 09:52:19',1,1),(4,'Cancelled','2023-12-08 09:52:19','2023-12-08 09:52:19',1,1);
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_type`
--

DROP TABLE IF EXISTS `order_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_type_name` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_type_unq` (`order_type_name`),
  KEY `fk_ordtyp_created_by` (`created_by`),
  KEY `fk_ordtyp_updated_by` (`updated_by`),
  CONSTRAINT `fk_ordtyp_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_ordtyp_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_type`
--

LOCK TABLES `order_type` WRITE;
/*!40000 ALTER TABLE `order_type` DISABLE KEYS */;
INSERT INTO `order_type` VALUES (1,'Pickup','2023-12-06 10:48:43','2023-12-06 10:48:43',1,1),(2,'Delivery','2023-12-06 10:48:43','2023-12-06 10:48:43',1,1),(3,'At the counter','2023-12-08 10:13:46','2023-12-08 10:13:46',1,1);
/*!40000 ALTER TABLE `order_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_reset_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(450) DEFAULT NULL,
  `expired_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pwd_rs_token_usrid` (`user_id`),
  CONSTRAINT `fk_pwd_rs_token_usrid` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
INSERT INTO `password_reset_token` VALUES (1,'8sFv8bDL3Rkgnz6UsRcumyDsu1hZXU','2023-12-05 17:22:13',1),(2,'OvvgnQMUkvuQmn2TBqaQuIXHyEEnpy','2023-12-05 17:30:58',1),(3,'jX0FkB1e6iIa2nwwhroZVT0iGnFEFv','2023-12-06 09:57:36',1),(4,'APtsYHVrIUPVU8fV6DMIh1LvUzrXh9','2023-12-06 10:01:09',1),(5,'9EwaEFDy6T0E1sD58wJOs0QvAy0nxr','2023-12-06 10:13:16',1),(6,'mtYRRA0sSHO7UytY9OBI86Rau63u95','2023-12-06 10:21:24',1),(7,'ExzQGXn8z89RPPDNLC9lhWYYSZuEJd','2023-12-06 10:22:41',1),(8,'z3YELJlPQwqJHTJIjkoZ9cqc0FsmiU','2023-12-06 10:24:55',1),(9,'blP6pPIulzNDTGJhi3cLXNYo1okipm','2023-12-06 10:26:14',1),(10,'2xOExmWpUQoZi9n3OAqqUc1WzTsDts','2023-12-06 10:29:38',1),(11,'jRpRp02Ly7c2lEvF5KbTxbH3ZUP7o1','2023-12-06 10:30:51',1),(12,'heJWcrJbmGCdm8blRxCy4EN5VDwVth','2023-12-06 10:32:59',1),(13,'YXa92mHbI9TKlVKCNS8Iiz4pay288G','2023-12-06 10:38:09',1),(14,'nfD9SzHFAXbyx3Wz9EhBkkFBQA37WO','2023-12-06 10:41:22',1);
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_method_name` varchar(50) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `payment_method_unq` (`payment_method_name`),
  KEY `fk_payment_method_created_by` (`created_by`),
  KEY `fk_payment_method_updated_by` (`updated_by`),
  CONSTRAINT `fk_payment_method_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_payment_method_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
INSERT INTO `payment_method` VALUES (1,'Cash',1,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(2,'Bank Transfer',1,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(3,'Credit card',0,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(4,'Paypal',0,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(5,'Refill card',0,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(6,'Other',0,'2023-11-30 22:45:42','2023-11-30 22:45:42',1,1),(8,'hề lú',0,'2023-11-30 23:38:37','2023-11-30 23:38:37',1,1);
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_status`
--

DROP TABLE IF EXISTS `payment_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_status_name` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `payment_status_unq` (`payment_status_name`),
  KEY `fk_payment_status_created_by` (`created_by`),
  KEY `fk_payment_status_updated_by` (`updated_by`),
  CONSTRAINT `fk_payment_status_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_payment_status_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
INSERT INTO `payment_status` VALUES (1,'Unpaid','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1),(2,'Failed','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1),(3,'Expired','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1),(4,'Paid','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1),(5,'Refunding','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1),(6,'Refunded','2023-11-30 22:44:29','2023-11-30 22:44:29',1,1);
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'STAFF'),(3,'CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_booking`
--

DROP TABLE IF EXISTS `table_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) DEFAULT NULL,
  `mobile_number` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `number_of_people` int(11) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `booking_time` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `booking_status_id` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_table_booking_created_by` (`created_by`),
  KEY `fk_table_booking_updated_by` (`updated_by`),
  KEY `fk_booking_status_id` (`booking_status_id`),
  CONSTRAINT `fk_booking_status_id` FOREIGN KEY (`booking_status_id`) REFERENCES `table_booking_status` (`id`),
  CONSTRAINT `fk_table_booking_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_table_booking_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_booking`
--

LOCK TABLES `table_booking` WRITE;
/*!40000 ALTER TABLE `table_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `table_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_booking_status`
--

DROP TABLE IF EXISTS `table_booking_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_booking_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `table_booking_status_name` varchar(50) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `booking_status_unq` (`table_booking_status_name`),
  KEY `fk_booking_status_created_by` (`created_by`),
  KEY `fk_booking_status_updated_by` (`updated_by`),
  CONSTRAINT `fk_booking_status_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`),
  CONSTRAINT `fk_booking_status_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_booking_status`
--

LOCK TABLES `table_booking_status` WRITE;
/*!40000 ALTER TABLE `table_booking_status` DISABLE KEYS */;
INSERT INTO `table_booking_status` VALUES (1,'Request','2023-11-29 22:23:54','2023-11-29 22:23:54',1,1),(2,'Approved','2023-11-29 22:23:54','2023-11-29 22:23:54',1,1),(3,'Canceled','2023-11-29 22:23:54','2023-11-29 22:23:54',1,1),(4,'Declined','2023-11-29 22:23:54','2023-11-29 22:23:54',1,1);
/*!40000 ALTER TABLE `table_booking_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(450) DEFAULT NULL,
  `token_type` varchar(45) DEFAULT NULL,
  `revoked` tinyint(4) DEFAULT NULL,
  `expired` tinyint(4) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_token_user_id` (`user_id`),
  CONSTRAINT `fk_token_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYyNzYxOCwiZXhwIjoxNzAwNzE0MDE4fQ.s7LbDb17J9eh2PcRg1cvMBBnEdOE0EiP4mZ1HzfMVnw','BEARER',1,1,1),(2,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzMzc1OCwiZXhwIjoxNzAwNzIwMTU4fQ.rgGCqk-pZedy2o0wgtkh5nicufv8ulQgMGxkjGt1CGs','BEARER',1,1,1),(3,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzMzc2MSwiZXhwIjoxNzAwNzIwMTYxfQ.ipKBnTLywlsdjN9kyJa3BVHDwfsXMQJtbVDU7_PeeeA','BEARER',1,1,1),(4,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzNTMwNSwiZXhwIjoxNzAwNzIxNzA1fQ.RzhSWkMDaKBU1OvLdSpC152ulnP0BG-4Kps8rb6V4RI','BEARER',1,1,1),(5,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzNTY2NywiZXhwIjoxNzAwNzIyMDY3fQ.xenMR5DK8WeHLBp1iyIjmKTdFoLp7YmXTtxxD2OkI78','BEARER',1,1,1),(6,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzNTcxMCwiZXhwIjoxNzAwNzIyMTEwfQ.yWU0ZS7Tq5USSXERjhzSBxIqyF1pldsIaj3euHylLvc','BEARER',1,1,1),(7,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzNTc2MiwiZXhwIjoxNzAwNzIyMTYyfQ.y4r4rz-FstVHAkcOF_ElfPOGPOcFUjdrRWMnIJNz5_w','BEARER',1,1,1),(8,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzNTkzOSwiZXhwIjoxNzAwNzIyMzM5fQ.ColjQqMO4YmIBxPKMdmMe9OFhOy1XFLOuXuQhhF62VY','BEARER',1,1,1),(10,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzOTUyNSwiZXhwIjoxNzAwNzI1OTI1fQ.4_c81NNAUgA-dvbQfbdb0RxB9oHHWuBs7wRquFSec10','BEARER',1,1,1),(11,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDYzOTU5OSwiZXhwIjoxNzAwNzI1OTk5fQ.Y3o-5vLNliaGON83z2Bhq5wt7LLhpudtHblsfZcccoA','BEARER',1,1,1),(12,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDY0MjQzMCwiZXhwIjoxNzAwNzI4ODMwfQ.X_c9E18g2Uu1WZBOlTivQBn6IBo3D23Prjw3iLAAx6c','BEARER',1,1,1),(14,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzAwNjQ4ODU1LCJleHAiOjE3MDA3MzUyNTV9.3zMDEqF329eg9RcAG7ObpsW-VwP2Wnjesc52q0jjGn0','BEARER',1,1,8),(15,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MiIsImlhdCI6MTcwMDcxMDUyMywiZXhwIjoxNzAwNzk2OTIzfQ.zsNxepOq7X6p1tH7czm8DpFND5uh3s9mwAFnjgBJYeA','BEARER',1,1,9),(16,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDc5NTUxNSwiZXhwIjoxNzAwODgxOTE1fQ.O15iyCqYLUDTKqIV2ZjrdVKXRYT5ausoyLYyPA5R7fg','BEARER',1,1,1),(17,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDc5NTUzMiwiZXhwIjoxNzAwODgxOTMyfQ.-1RMarlJR4VgC_VvY9wYIrVKYzvtvktzMb0KGEUWu6g','BEARER',1,1,1),(18,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDc5NTYxOCwiZXhwIjoxNzAwODgyMDE4fQ.sB4Wf2lHoTksHiL8_7UoW3cJTXzTPfWqEDT-XJbhlM4','BEARER',1,1,1),(19,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMDgxMzE1MywiZXhwIjoxNzAwODk5NTUzfQ.ZPiK7usjy4w7f2EJFaRWSjcrow6kVymIffZ5Yshg6OA','BEARER',1,1,1),(20,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTA1NzU5MSwiZXhwIjoxNzAxMTQzOTkxfQ.piHBsmdh-F0ysNwon9ThB7FNEn8iSriNhnSX4iaV2uc','BEARER',1,1,1),(21,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDc1OSwiZXhwIjoxNzAxMjMxMTU5fQ.yGN5OKM9244Jw8JUa4IASHp8xneGbtAjqH3Wve84dCY','BEARER',1,1,1),(22,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDc5NSwiZXhwIjoxNzAxMjMxMTk1fQ.aazQoD8HQsZOVaBxabvcAHAR-CJw9jH94h1GhF7IYwU','BEARER',1,1,1),(23,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDgxNSwiZXhwIjoxNzAxMjMxMjE1fQ.63KlqLumHjstH-ikxJthPLzBtVf3EQj4aJqIic5vDuY','BEARER',1,1,1),(24,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDg0MCwiZXhwIjoxNzAxMjMxMjQwfQ.q18rEyFGn-kk0BqMYl-pvZNw3_8BQVlzubDDfL0KNQo','BEARER',1,1,1),(25,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDkxMiwiZXhwIjoxNzAxMjMxMzEyfQ.MbikMV5CMa944LnVbS-FrKUNdDzlyP47H7nxUxX6e2U','BEARER',1,1,1),(26,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDkzMywiZXhwIjoxNzAxMjMxMzMzfQ.pSrAF8YECoHFbBR5VpRjJaZMhaaIit-sMqx42szVUYY','BEARER',1,1,1),(27,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDk1MiwiZXhwIjoxNzAxMjMxMzUyfQ.NUheME31pfd2M3la_FrjGGtoq2sD5a46IskEAge1vmw','BEARER',1,1,1),(28,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDk2MCwiZXhwIjoxNzAxMjMxMzYwfQ.xcSVin1GYNQFWwcugHFbfmN1mpUR-jT9eIOg2c5iD0M','BEARER',1,1,1),(29,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NDk3MCwiZXhwIjoxNzAxMjMxMzcwfQ.PxUM2BgrZ9lYPwunkQr4xFgype4RvupQ8AZx2TGgaKI','BEARER',1,1,1),(30,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE0NTcyNywiZXhwIjoxNzAxMjMyMTI3fQ.TcU8gWWiAgwevOvBqt17s6SRzc5hZwrhmifLHhpWZ2A','BEARER',1,1,1),(31,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE1MzE2OSwiZXhwIjoxNzAxMjM5NTY5fQ.VyjD_QLq9YvR4M5MmTKBEq2SUCvr5KhJsUAxB7PbYzs','BEARER',1,1,1),(32,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTE2NzcwMiwiZXhwIjoxNzAxMjU0MTAyfQ.2FJqaKwN9lkWnaZJsr7_K3Xj18PCORaxwSBM82KyqNY','BEARER',1,1,1),(33,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzAxMjUzNTc4LCJleHAiOjE3MDEzMzk5Nzh9.Rw7pIywzFXkSkxIRumc1Y1np04fzo3TjPoHDZdIl8yc','BEARER',1,1,8),(34,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MiIsImlhdCI6MTcwMTI1MzU4NSwiZXhwIjoxNzAxMzM5OTg1fQ.PMzk1MzwkHsXQBCiGFPPhiF07Ub5QEYQGZuEMQsFI38','BEARER',1,1,9),(35,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzAxMjUzNjA3LCJleHAiOjE3MDEzNDAwMDd9.DhxRG86sTHuW6PU_oKIyahkPKafNG6b40lAYcO-LN0c','BEARER',0,0,8),(36,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0MiIsImlhdCI6MTcwMTI1MzcwMiwiZXhwIjoxNzAxMzQwMTAyfQ.MpYWL3SaGTTmI3HwrU_Hjz5selucwGJOmFjuS8cevvE','BEARER',0,0,9),(37,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTM1OTY5MSwiZXhwIjoxNzAxNDQ2MDkxfQ.nfOYOCzmpWQJZeLwjlrDEKMi4IwQLLn4SAWPKbpXU-w','BEARER',1,1,1),(38,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTM1OTgzMywiZXhwIjoxNzAxNDQ2MjMzfQ.LbwzjFujW5pYXUl2HmrqmcQQ8RYXVnmHLHdDzdiPtOM','BEARER',1,1,1),(39,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTM1OTg1OSwiZXhwIjoxNzAxNDQ2MjU5fQ.EvFC89CrXNAm6UmtcDBtN8X7edPf9QW0jrdDeOazPgo','BEARER',1,1,1),(40,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMTY1NjY4NywiZXhwIjoxNzAxNzQzMDg3fQ.kBwUBkoGiq6boDjuD8T8bNcd6oTepCFgmj0rBpzuqp0','BEARER',1,1,1),(41,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMjMwNzkxOCwiZXhwIjoxNzAyMzk0MzE4fQ.EVnTaSVUUCRlyEv4Xg9RZmvu38meqZ27tUbSP79jKpk','BEARER',1,1,1),(42,'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcwMjM5NDg1NCwiZXhwIjoxNzAyNDgxMjU0fQ.RinupBX4Gx9BLGzP7RflHcKvI4d8Cf9ykMhQU_CDtiI','BEARER',0,0,1);
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(45) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unit_name_unq` (`unit_name`),
  KEY `fk_unit_created_by` (`created_by`),
  KEY `fk_unit_updated_by` (`updated_by`),
  CONSTRAINT `fk_unit_created_by` FOREIGN KEY (`created_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_unit_updated_by` FOREIGN KEY (`updated_by`) REFERENCES `user_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'Ly','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(2,'Cái','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(3,'Chén','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(4,'Chiếc','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(5,'Lon','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(6,'Cốc','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(7,'Chai','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(8,'Hộp','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(9,'Thùng','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(10,'Bộ','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(11,'Phần','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(12,'Gói','2023-11-24 15:29:52','2023-11-24 15:29:52',1,1),(13,'test','2023-11-28 11:16:15','2023-11-28 11:16:15',1,1),(19,'test 2 edited','2023-11-28 11:29:06','2023-11-28 11:29:54',1,1);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_detail`
--

DROP TABLE IF EXISTS `user_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(450) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_detail`
--

LOCK TABLES `user_detail` WRITE;
/*!40000 ALTER TABLE `user_detail` DISABLE KEYS */;
INSERT INTO `user_detail` VALUES (1,'Test','Test','anhnv@nhsv.vn','admin','$2a$12$zhrAbElsfx4zm7zOPAnwhuLXozzIHE04P6FaAmb9DsapYubXau31a',NULL,NULL,1),(8,'alo',NULL,NULL,'test','$2a$10$xdn2iHmI5nu0.Ga8M0trHuC8NSvZ8l3MKA/vaMN68U7dOD3jTcHee',NULL,NULL,1),(9,'alo',NULL,NULL,'test2','$2a$10$.foB1a5ZABFVomEHpKmaY.Dj1Bk1Jl5SFJZ9Gt16dMpR7aXN5X0Xm',NULL,NULL,1);
/*!40000 ALTER TABLE `user_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_role_id` (`role_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(5,8,2),(6,9,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-13 23:44:31
