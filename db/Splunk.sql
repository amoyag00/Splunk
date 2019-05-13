-- MySQL dump 10.13  Distrib 5.7.25, for Linux (x86_64)
--
-- Host: localhost    Database: Splunk
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.16.04.2

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
-- Table structure for table `AUTHORS`
--

DROP TABLE IF EXISTS `AUTHORS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AUTHORS` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `AUTHORS_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AUTHORS`
--

LOCK TABLES `AUTHORS` WRITE;
/*!40000 ALTER TABLE `AUTHORS` DISABLE KEYS */;
INSERT INTO `AUTHORS` VALUES (1,1,'Akira Toriyama','Art & Script'),(2,2,'Masashi Kishimoto','Art & Script'),(3,3,'Eiichiro Oda','Art'),(4,3,'Eiichiro Oda','Script'),(5,4,'Takehiko Inoue','Art & Script'),(7,5,'Kohei Horikoshi','Art & Script');
/*!40000 ALTER TABLE `AUTHORS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHAPTERS`
--

DROP TABLE IF EXISTS `CHAPTERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHAPTERS` (
  `chapterId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `chapterNumber` int(11) NOT NULL,
  `addedDate` datetime NOT NULL,
  `contentPath` varchar(500) DEFAULT NULL,
  `chapterName` varchar(100) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`chapterId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `CHAPTERS_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHAPTERS`
--

LOCK TABLES `CHAPTERS` WRITE;
/*!40000 ALTER TABLE `CHAPTERS` DISABLE KEYS */;
INSERT INTO `CHAPTERS` VALUES (1,1,1,'2019-04-16 19:17:55','dragonball/dragonBall1.pdf','Bloomers and the Monkey King',NULL),(2,1,2,'2019-04-16 19:18:31','dragonball/dragonBall2.pdf','No Balls!',NULL),(4,1,3,'2019-04-16 19:19:20','dragonball/dragonBall3.pdf','Sea Monkeys!',NULL),(5,2,1,'2019-04-16 19:20:17','naruto/naruto1.pdf','Uzumaki Naruto!',NULL),(7,2,2,'2019-04-16 19:21:08','naruto/naruto2.pdf','Konohamaru!',NULL),(8,2,3,'2019-04-16 19:21:22','naruto/naruto3.pdf','Sasuke Uchiha!!',NULL),(10,3,1,'2019-04-16 19:23:22','onePiece/onePiece1.pdf','Romance Down',NULL),(11,3,2,'2019-04-16 19:25:20','onePiece/onePiece2.pdf','That Guy, Straw Hat Luffy',NULL),(12,3,3,'2019-04-16 19:25:39','onePiece/onePiece3.pdf','Introducing Pirate Hunter Zoro',NULL),(13,4,1,'2019-04-16 19:26:24','vagabond/vagabond1.pdf','Shinmen Takezo',NULL),(15,4,2,'2019-04-16 19:26:49','vagabond/vagabond2.pdf','Akemi',NULL),(16,4,3,'2019-04-16 19:27:00','vagabond/vagabond3.pdf','Oko',NULL),(17,5,1,'2019-04-16 19:27:55','bokuNoHero/bokuNoHero1.pdf','Izuku Midoriya: Origin',NULL),(18,5,2,'2019-04-16 19:28:15','bokuNoHero/bokuNoHero2.pdf','Roaring muscles',NULL),(19,5,3,'2019-04-16 19:28:30','bokuNoHero/bokuNoHero3.pdf','Entrance exam',NULL);
/*!40000 ALTER TABLE `CHAPTERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMICS`
--

DROP TABLE IF EXISTS `COMICS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMICS` (
  `comicId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `numberChapters` smallint(6) DEFAULT NULL,
  `statusComic` varchar(20) DEFAULT NULL,
  `publishDate` datetime NOT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMICS`
--

LOCK TABLES `COMICS` WRITE;
/*!40000 ALTER TABLE `COMICS` DISABLE KEYS */;
INSERT INTO `COMICS` VALUES (1,'Dragon Ball',519,'finished','2019-04-16 13:24:22','img/dragonBall.jpg',NULL),(2,'Naruto',700,'finished','2019-04-16 13:24:54','img/Naruto.jpg',NULL),(3,'One Piece',939,'publishing','2019-04-16 13:25:11','img/OnePiece.jpg',NULL),(4,'Vagabond',327,'hiatus','2019-04-16 13:26:22','img/Vagabond.jpg',NULL),(5,'Boku no hero academia',224,'publishing','2019-04-16 13:28:56','img/BokuNoHero.jpg',NULL);
/*!40000 ALTER TABLE `COMICS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GENRES`
--

DROP TABLE IF EXISTS `GENRES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GENRES` (
  `genreId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`genreId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `GENRES_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GENRES`
--

LOCK TABLES `GENRES` WRITE;
/*!40000 ALTER TABLE `GENRES` DISABLE KEYS */;
INSERT INTO `GENRES` VALUES (1,1,'adventures'),(2,2,'adventures'),(3,3,'adventures'),(4,4,'adventures'),(5,5,'adventures'),(7,1,'fighting'),(8,2,'ninjas'),(10,4,'samurais'),(11,3,'pirates'),(12,5,'superheros');
/*!40000 ALTER TABLE `GENRES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LISTS`
--

DROP TABLE IF EXISTS `LISTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LISTS` (
  `listId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `comicId` int(11) NOT NULL,
  `comicStatus` enum('P','L','R') DEFAULT NULL,
  `score` tinyint(4) DEFAULT NULL,
  `progress` int(11) DEFAULT NULL,
  `addedDate` datetime NOT NULL,
  PRIMARY KEY (`listId`),
  UNIQUE KEY `comicId` (`comicId`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `LISTS_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USERS` (`userId`),
  CONSTRAINT `LISTS_ibfk_2` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LISTS`
--

LOCK TABLES `LISTS` WRITE;
/*!40000 ALTER TABLE `LISTS` DISABLE KEYS */;
INSERT INTO `LISTS` VALUES (1,1,1,'P',9,87,'2019-04-16 13:33:49'),(9,2,1,NULL,50,NULL,'2019-04-17 01:08:08'),(10,2,2,NULL,40,NULL,'2019-04-17 01:08:19'),(11,2,3,NULL,90,NULL,'2019-04-17 01:08:25'),(12,2,4,NULL,100,NULL,'2019-04-17 01:08:32'),(13,2,5,NULL,20,NULL,'2019-04-17 01:08:39'),(14,3,1,NULL,10,NULL,'2019-04-17 01:08:48'),(15,3,2,NULL,35,NULL,'2019-04-17 01:08:59'),(16,3,3,NULL,75,NULL,'2019-04-17 01:09:08'),(17,3,4,NULL,80,NULL,'2019-04-17 01:09:16'),(19,3,5,NULL,98,NULL,'2019-04-17 01:11:52'),(20,1,3,'P',50,40,'2019-04-17 13:33:27');
/*!40000 ALTER TABLE `LISTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MENUS`
--

DROP TABLE IF EXISTS `MENUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MENUS` (
  `menuId` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('S','I') DEFAULT NULL,
  `rolId` int(11) DEFAULT NULL,
  `menu_menuId` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `nombre` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`menuId`),
  KEY `rolId` (`rolId`),
  KEY `menu_menuId` (`menu_menuId`),
  CONSTRAINT `MENUS_ibfk_1` FOREIGN KEY (`rolId`) REFERENCES `ROL` (`rolId`),
  CONSTRAINT `MENUS_ibfk_2` FOREIGN KEY (`menu_menuId`) REFERENCES `MENUS` (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MENUS`
--

LOCK TABLES `MENUS` WRITE;
/*!40000 ALTER TABLE `MENUS` DISABLE KEYS */;
INSERT INTO `MENUS` VALUES (1,'I',1,NULL,'/private/user/home.xhtml','Home'),(2,'I',1,NULL,'/private/user/searcher.xhtml','Buscar'),(3,'I',1,NULL,'/private/user/premium.xhtml','Premium'),(4,'I',2,NULL,'/private/admin/user.xhtml','Usuarios'),(5,'I',2,NULL,'/private/admin/comics.xhtml','Comics');
/*!40000 ALTER TABLE `MENUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REVIEWS`
--

DROP TABLE IF EXISTS `REVIEWS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REVIEWS` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `comicId` int(11) NOT NULL,
  `writtenDate` datetime NOT NULL,
  `reviewText` varchar(500) DEFAULT NULL,
  `visible` bit(1) DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `userId` (`userId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `REVIEWS_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USERS` (`userId`),
  CONSTRAINT `REVIEWS_ibfk_2` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REVIEWS`
--

LOCK TABLES `REVIEWS` WRITE;
/*!40000 ALTER TABLE `REVIEWS` DISABLE KEYS */;
INSERT INTO `REVIEWS` VALUES (1,1,1,'2019-04-16 22:34:09','Nice',NULL),(2,1,2,'2019-04-16 22:34:18','Nice!',NULL),(3,1,3,'2019-04-16 22:34:25','Nice!!',NULL),(4,1,4,'2019-04-16 22:34:30','Nice!!!',NULL),(5,1,5,'2019-04-16 22:34:35','Nice!!!!',NULL),(6,2,1,'2019-04-16 22:34:46','Wow',NULL),(7,2,2,'2019-04-16 22:34:51','Wow!',NULL),(8,2,3,'2019-04-16 22:35:06','Wow!!',NULL),(9,2,4,'2019-04-16 22:35:10','Wow!!!',NULL),(10,2,5,'2019-04-16 22:35:15','Wow!!!!',NULL);
/*!40000 ALTER TABLE `REVIEWS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROL`
--

DROP TABLE IF EXISTS `ROL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROL` (
  `rolId` int(11) NOT NULL AUTO_INCREMENT,
  `rol` enum('A','U') DEFAULT NULL,
  PRIMARY KEY (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROL`
--

LOCK TABLES `ROL` WRITE;
/*!40000 ALTER TABLE `ROL` DISABLE KEYS */;
INSERT INTO `ROL` VALUES (1,'U'),(2,'A');
/*!40000 ALTER TABLE `ROL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(10) DEFAULT NULL,
  `pass` varchar(20) NOT NULL,
  `private` bit(1) NOT NULL,
  `expirationDate` datetime NOT NULL,
  `banned` bit(1) DEFAULT b'0',
  `rol` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `rol` (`rol`),
  CONSTRAINT `USERS_ibfk_1` FOREIGN KEY (`rol`) REFERENCES `ROL` (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (1,'Johnny','Johnny',_binary '\0','2019-04-16 13:31:16',_binary '\0',1,NULL),(2,'Salty','Salty',_binary '\0','2019-04-16 13:31:54',_binary '\0',1,NULL),(3,'Blue','Blue',_binary '','2019-04-16 13:32:10',_binary '\0',1,NULL),(4,'admin','admin',_binary '','2019-05-05 11:05:16',_binary '\0',2,NULL),(5,'usuario','Usuario1',_binary '\0','2019-05-06 01:08:36',_binary '\0',1,NULL),(6,'usuario2','Usuario2',_binary '','2019-05-06 01:09:01',_binary '\0',1,NULL),(7,'usuario3','Usuario3',_binary '\0','2019-05-06 01:16:32',_binary '\0',1,'usuario3@gmail.com');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-13 19:17:32
