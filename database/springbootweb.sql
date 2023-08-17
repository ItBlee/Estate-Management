-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: springbootweb
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `assignmentbuilding`
--

DROP TABLE IF EXISTS `assignmentbuilding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentbuilding` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `buildingid` bigint NOT NULL,
  `staffid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkk3mdegrmfcdlsxqds1m6q238` (`buildingid`),
  KEY `FKf4ibbod44h32ao1o6pb4yq98p` (`staffid`),
  CONSTRAINT `FKf4ibbod44h32ao1o6pb4yq98p` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`),
  CONSTRAINT `FKkk3mdegrmfcdlsxqds1m6q238` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentbuilding`
--

LOCK TABLES `assignmentbuilding` WRITE;
/*!40000 ALTER TABLE `assignmentbuilding` DISABLE KEYS */;
INSERT INTO `assignmentbuilding` VALUES (1,NULL,NULL,NULL,NULL,2,2),(2,NULL,NULL,NULL,NULL,2,4),(3,NULL,NULL,NULL,NULL,3,2),(4,NULL,NULL,NULL,NULL,3,5),(5,NULL,NULL,NULL,NULL,3,3),(14,'admin','2023-05-26','admin','2023-05-26',59,3),(15,'admin','2023-05-26','admin','2023-05-26',59,4);
/*!40000 ALTER TABLE `assignmentbuilding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignmentcustomer`
--

DROP TABLE IF EXISTS `assignmentcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentcustomer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `customerid` bigint NOT NULL,
  `staffid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4sygo3a6twd6tkay7em8f1lgg` (`customerid`),
  KEY `FKco26n95l1hpuq1suv0briljor` (`staffid`),
  CONSTRAINT `FK4sygo3a6twd6tkay7em8f1lgg` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKco26n95l1hpuq1suv0briljor` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentcustomer`
--

LOCK TABLES `assignmentcustomer` WRITE;
/*!40000 ALTER TABLE `assignmentcustomer` DISABLE KEYS */;
INSERT INTO `assignmentcustomer` VALUES (7,NULL,NULL,NULL,NULL,2,3),(14,NULL,NULL,NULL,NULL,3,2),(15,NULL,NULL,NULL,NULL,5,2),(16,NULL,NULL,NULL,NULL,5,4);
/*!40000 ALTER TABLE `assignmentcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `brokeragefee` double DEFAULT NULL,
  `carfee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `decorationtime` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `deposit` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `direction` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `district` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `electricityfee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `floorarea` int DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  `level` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `linkofbuilding` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `managername` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `managerphone` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `map` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `motorbikefee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `note` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `numberofbasement` int DEFAULT NULL,
  `overtimefee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `payment` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `rentprice` int NOT NULL,
  `rentpricedescription` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `renttime` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `servicefee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `street` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `structure` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `ward` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `waterfee` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,500,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Nam Giao Building Tower',NULL,2,NULL,NULL,15,'15 triệu/m2',NULL,NULL,'59 phan xích long',NULL,'Phường 2',NULL,'TANG_TRET,NGUYEN_CAN'),(2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_2',NULL,650,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'ACM Tower',NULL,2,NULL,NULL,18,'18 triệu/m2',NULL,NULL,'96 cao thắng',NULL,'Phường 4',NULL,'NGUYEN_CAN'),(3,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Alpha 2 Building Tower',NULL,1,NULL,NULL,20,'20 triệu/m2',NULL,NULL,'153 nguyễn đình chiểu',NULL,'Phường 6',NULL,'NOI_THAT'),(4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_4',NULL,200,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'IDD 1 Building',NULL,1,NULL,NULL,12,'12 triệu/m2',NULL,NULL,'111 Lý Chính Thắng',NULL,'Phường 7',NULL,'TANG_TRET,NGUYEN_CAN,NOI_THAT'),(56,'admin','2023-05-26','admin','2023-05-26',NULL,'','','','',NULL,'',NULL,NULL,'','','','','','','ACM 3','',NULL,'','',12,'','','','','','','',''),(57,'admin','2023-05-26','admin','2023-05-26',NULL,'','','','',NULL,'',NULL,NULL,'','','','','','','acM 4','',NULL,'','',12,'','','','','','','',''),(58,'admin','2023-05-26','admin','2023-05-26',NULL,'','','','',NULL,'',NULL,NULL,'','','','','','','aCM 5','',NULL,'','',54,'','','','','','','',''),(59,'admin','2023-05-26','admin','2023-05-26',NULL,'','','','',NULL,'',NULL,NULL,'','','','','','','acm 6','',NULL,'','',78,'','','','','','','',''),(61,NULL,NULL,'admin','2023-06-15',NULL,'','','','',NULL,'',NULL,'rabbit.png','','','','','','','nfgnfgngf','',NULL,'','',32,'','','','','','','',''),(63,'admin','2023-05-30','admin','2023-05-30',NULL,'','','','',NULL,'',NULL,NULL,'','','','','','','qsad','',NULL,'','',12,'','','','','','','','');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `fullname` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `company` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `demand` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,NULL,NULL,'admin','2023-06-22','helllooooo@gmail.com','Trần Long Tuấn Vũ','03976312323','acade','Mua đất vinland 2','đã thanh toán rồi',1),(2,NULL,NULL,'admin','2023-06-22','sdasdsadsa','test','213213','sadasd','sadsada','',0),(3,'admin','2023-06-22','admin','2023-06-25','dasdasd','Nguyen Van Thanh','sadasdsa','asdas','asdsd','asdsad',1),(4,'admin','2023-06-25','admin','2023-06-25','thaomai@gmail.com','Nguyễn Thu Thảo','01237492222','asdsada','Mua nhà','Chờ',0),(5,'admin','2023-06-25','nguyenvana','2023-06-25','truongxuan@gmail.com','Bùi Xuân Trường','098731235','asdasdasdas','Mua bán nhà','Chờ',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentarea`
--

DROP TABLE IF EXISTS `rentarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentarea` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `value` int NOT NULL,
  `buildingid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqhqoxlvm1iblaew5s0v8n3ut4` (`buildingid`),
  CONSTRAINT `FKqhqoxlvm1iblaew5s0v8n3ut4` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
/*!40000 ALTER TABLE `rentarea` DISABLE KEYS */;
INSERT INTO `rentarea` VALUES (1,NULL,NULL,NULL,NULL,100,1),(2,NULL,NULL,NULL,NULL,200,1),(3,NULL,NULL,NULL,NULL,200,2),(4,NULL,NULL,NULL,NULL,300,2),(5,NULL,NULL,NULL,NULL,400,2),(6,NULL,NULL,NULL,NULL,300,3),(7,NULL,NULL,NULL,NULL,400,3),(8,NULL,NULL,NULL,NULL,500,3),(9,NULL,NULL,NULL,NULL,100,4),(10,NULL,NULL,NULL,NULL,400,4),(11,NULL,NULL,NULL,NULL,250,4);
/*!40000 ALTER TABLE `rentarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `name` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c36say97xydpmgigg38qv5l2p` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,'MANAGER','Quản trị hệ thống'),(2,NULL,NULL,NULL,NULL,'STAFF','người dùng');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `note` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `type` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `customerid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKldobv9jeuxje0fjqnhrw6e23v` (`customerid`),
  CONSTRAINT `FKldobv9jeuxje0fjqnhrw6e23v` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'admin','2023-06-22','admin','2023-06-22','tư vấn về tòa nhà mới','CSKH',1),(2,'admin','2023-06-22','admin','2023-06-22','xem trực tiếp mảnh đất','VIEW',1),(3,'admin','2023-06-22','admin','2023-06-22','testr','CSKH',1),(4,'admin','2023-06-25','admin','2023-06-25','Chờ yêu cầu','CSKH',5);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  `phone` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `status` int NOT NULL DEFAULT '1',
  `username` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,NULL,NULL,NULL,'admin','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',NULL,1,'admin'),(2,NULL,NULL,NULL,NULL,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',NULL,1,'nguyenvana'),(3,NULL,NULL,NULL,NULL,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',NULL,1,'nguyenvanb'),(4,NULL,NULL,NULL,NULL,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',NULL,1,'nguyenvanc'),(5,NULL,NULL,NULL,NULL,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',NULL,1,'nguyenvand');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `modifiedby` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifieddate` date DEFAULT NULL,
  `roleid` bigint NOT NULL,
  `userid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbo5ik0bthje7hum554xb17ry6` (`roleid`),
  KEY `FKd0xwi6psywvnj59btfns0alnm` (`userid`),
  CONSTRAINT `FKbo5ik0bthje7hum554xb17ry6` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `FKd0xwi6psywvnj59btfns0alnm` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,NULL,NULL,NULL,NULL,1,1),(2,NULL,NULL,NULL,NULL,2,2),(3,NULL,NULL,NULL,NULL,2,3),(4,NULL,NULL,NULL,NULL,2,4),(5,NULL,NULL,NULL,NULL,2,5);
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

-- Dump completed on 2023-08-01 10:01:04
