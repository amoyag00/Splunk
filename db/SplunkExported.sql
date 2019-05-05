-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.25-0ubuntu0.16.04.2 - (Ubuntu)
-- SO del servidor:              Linux
-- HeidiSQL Versión:             9.4.0.5189
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para Splunk
CREATE DATABASE IF NOT EXISTS `Splunk` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `Splunk`;

-- Volcando estructura para tabla Splunk.AUTHORS
CREATE TABLE IF NOT EXISTS `AUTHORS` (
  `authorId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `category` varchar(50) NOT NULL,
  PRIMARY KEY (`authorId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `AUTHORS_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.AUTHORS: ~6 rows (aproximadamente)
DELETE FROM `AUTHORS`;
/*!40000 ALTER TABLE `AUTHORS` DISABLE KEYS */;
INSERT INTO `AUTHORS` (`authorId`, `comicId`, `name`, `category`) VALUES
	(1, 1, 'Akira Toriyama', 'Art & Script'),
	(2, 2, 'Masashi Kishimoto', 'Art & Script'),
	(3, 3, 'Eiichiro Oda', 'Art'),
	(4, 3, 'Eiichiro Oda', 'Script'),
	(5, 4, 'Takehiko Inoue', 'Art & Script'),
	(7, 5, 'Kohei Horikoshi', 'Art & Script');
/*!40000 ALTER TABLE `AUTHORS` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.CHAPTERS
CREATE TABLE IF NOT EXISTS `CHAPTERS` (
  `chapterId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `chapterNumber` int(11) NOT NULL,
  `addedDate` datetime NOT NULL,
  `contentPath` varchar(500) DEFAULT NULL,
  `chapterName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chapterId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `CHAPTERS_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.CHAPTERS: ~15 rows (aproximadamente)
DELETE FROM `CHAPTERS`;
/*!40000 ALTER TABLE `CHAPTERS` DISABLE KEYS */;
INSERT INTO `CHAPTERS` (`chapterId`, `comicId`, `chapterNumber`, `addedDate`, `contentPath`, `chapterName`) VALUES
	(1, 1, 1, '2019-04-16 19:17:55', 'dragonball/dragonBall1.pdf', 'Bloomers and the Monkey King'),
	(2, 1, 2, '2019-04-16 19:18:31', 'dragonball/dragonBall2.pdf', 'No Balls!'),
	(4, 1, 3, '2019-04-16 19:19:20', 'dragonball/dragonBall3.pdf', 'Sea Monkeys!'),
	(5, 2, 1, '2019-04-16 19:20:17', 'naruto/naruto1.pdf', 'Uzumaki Naruto!'),
	(7, 2, 2, '2019-04-16 19:21:08', 'naruto/naruto2.pdf', 'Konohamaru!'),
	(8, 2, 3, '2019-04-16 19:21:22', 'naruto/naruto3.pdf', 'Sasuke Uchiha!!'),
	(10, 3, 1, '2019-04-16 19:23:22', 'onePiece/onePiece1.pdf', 'Romance Down'),
	(11, 3, 2, '2019-04-16 19:25:20', 'onePiece/onePiece2.pdf', 'That Guy, Straw Hat Luffy'),
	(12, 3, 3, '2019-04-16 19:25:39', 'onePiece/onePiece3.pdf', 'Introducing Pirate Hunter Zoro'),
	(13, 4, 1, '2019-04-16 19:26:24', 'vagabond/vagabond1.pdf', 'Shinmen Takezo'),
	(15, 4, 2, '2019-04-16 19:26:49', 'vagabond/vagabond2.pdf', 'Akemi'),
	(16, 4, 3, '2019-04-16 19:27:00', 'vagabond/vagabond3.pdf', 'Oko'),
	(17, 5, 1, '2019-04-16 19:27:55', 'bokuNoHero/bokuNoHero1.pdf', 'Izuku Midoriya: Origin'),
	(18, 5, 2, '2019-04-16 19:28:15', 'bokuNoHero/bokuNoHero2.pdf', 'Roaring muscles'),
	(19, 5, 3, '2019-04-16 19:28:30', 'bokuNoHero/bokuNoHero3.pdf', 'Entrance exam');
/*!40000 ALTER TABLE `CHAPTERS` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.COMICS
CREATE TABLE IF NOT EXISTS `COMICS` (
  `comicId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `numberChapters` smallint(6) DEFAULT NULL,
  `statusComic` varchar(20) DEFAULT NULL,
  `publishDate` datetime NOT NULL,
  `imagePath` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.COMICS: ~5 rows (aproximadamente)
DELETE FROM `COMICS`;
/*!40000 ALTER TABLE `COMICS` DISABLE KEYS */;
INSERT INTO `COMICS` (`comicId`, `name`, `numberChapters`, `statusComic`, `publishDate`, `imagePath`) VALUES
	(1, 'Dragon Ball', 519, 'finished', '2019-04-16 13:24:22', 'img/dragonBall.jpg'),
	(2, 'Naruto', 700, 'finished', '2019-04-16 13:24:54', 'img/Naruto.jpg'),
	(3, 'One Piece', 939, 'publishing', '2019-04-16 13:25:11', 'img/OnePiece.jpg'),
	(4, 'Vagabond', 327, 'hiatus', '2019-04-16 13:26:22', 'img/Vagabond.jpg'),
	(5, 'Boku no hero academia', 224, 'publishing', '2019-04-16 13:28:56', 'img/BokuNoHero.jpg');
/*!40000 ALTER TABLE `COMICS` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.GENRES
CREATE TABLE IF NOT EXISTS `GENRES` (
  `genreId` int(11) NOT NULL AUTO_INCREMENT,
  `comicId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`genreId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `GENRES_ibfk_1` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.GENRES: ~11 rows (aproximadamente)
DELETE FROM `GENRES`;
/*!40000 ALTER TABLE `GENRES` DISABLE KEYS */;
INSERT INTO `GENRES` (`genreId`, `comicId`, `name`) VALUES
	(1, 1, 'adventures'),
	(2, 2, 'adventures'),
	(3, 3, 'adventures'),
	(4, 4, 'adventures'),
	(5, 5, 'adventures'),
	(7, 1, 'fighting'),
	(8, 2, 'ninjas'),
	(10, 4, 'samurais'),
	(11, 3, 'pirates'),
	(12, 5, 'superheros');
/*!40000 ALTER TABLE `GENRES` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.LISTS
CREATE TABLE IF NOT EXISTS `LISTS` (
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

-- Volcando datos para la tabla Splunk.LISTS: ~13 rows (aproximadamente)
DELETE FROM `LISTS`;
/*!40000 ALTER TABLE `LISTS` DISABLE KEYS */;
INSERT INTO `LISTS` (`listId`, `userId`, `comicId`, `comicStatus`, `score`, `progress`, `addedDate`) VALUES
	(1, 1, 1, 'P', 9, 87, '2019-04-16 13:33:49'),
	(9, 2, 1, NULL, 50, NULL, '2019-04-17 01:08:08'),
	(10, 2, 2, NULL, 40, NULL, '2019-04-17 01:08:19'),
	(11, 2, 3, NULL, 90, NULL, '2019-04-17 01:08:25'),
	(12, 2, 4, NULL, 100, NULL, '2019-04-17 01:08:32'),
	(13, 2, 5, NULL, 20, NULL, '2019-04-17 01:08:39'),
	(14, 3, 1, NULL, 10, NULL, '2019-04-17 01:08:48'),
	(15, 3, 2, NULL, 35, NULL, '2019-04-17 01:08:59'),
	(16, 3, 3, NULL, 75, NULL, '2019-04-17 01:09:08'),
	(17, 3, 4, NULL, 80, NULL, '2019-04-17 01:09:16'),
	(19, 3, 5, NULL, 98, NULL, '2019-04-17 01:11:52'),
	(20, 1, 3, 'P', 50, 40, '2019-04-17 13:33:27');
/*!40000 ALTER TABLE `LISTS` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.REVIEWS
CREATE TABLE IF NOT EXISTS `REVIEWS` (
  `reviewId` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `comicId` int(11) NOT NULL,
  `writtenDate` datetime NOT NULL,
  `reviewText` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  KEY `userId` (`userId`),
  KEY `comicId` (`comicId`),
  CONSTRAINT `REVIEWS_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `USERS` (`userId`),
  CONSTRAINT `REVIEWS_ibfk_2` FOREIGN KEY (`comicId`) REFERENCES `COMICS` (`comicId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.REVIEWS: ~8 rows (aproximadamente)
DELETE FROM `REVIEWS`;
/*!40000 ALTER TABLE `REVIEWS` DISABLE KEYS */;
INSERT INTO `REVIEWS` (`reviewId`, `userId`, `comicId`, `writtenDate`, `reviewText`) VALUES
	(1, 1, 1, '2019-04-16 22:34:09', 'Nice'),
	(2, 1, 2, '2019-04-16 22:34:18', 'Nice!'),
	(3, 1, 3, '2019-04-16 22:34:25', 'Nice!!'),
	(4, 1, 4, '2019-04-16 22:34:30', 'Nice!!!'),
	(5, 1, 5, '2019-04-16 22:34:35', 'Nice!!!!'),
	(6, 2, 1, '2019-04-16 22:34:46', 'Wow'),
	(7, 2, 2, '2019-04-16 22:34:51', 'Wow!'),
	(8, 2, 3, '2019-04-16 22:35:06', 'Wow!!'),
	(9, 2, 4, '2019-04-16 22:35:10', 'Wow!!!'),
	(10, 2, 5, '2019-04-16 22:35:15', 'Wow!!!!');
/*!40000 ALTER TABLE `REVIEWS` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.ROL
CREATE TABLE IF NOT EXISTS `ROL` (
  `rolId` int(11) NOT NULL AUTO_INCREMENT,
  `rol` enum('A','U') DEFAULT NULL,
  PRIMARY KEY (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla Splunk.ROL: ~2 rows (aproximadamente)
DELETE FROM `ROL`;
/*!40000 ALTER TABLE `ROL` DISABLE KEYS */;
INSERT INTO `ROL` (`rolId`, `rol`) VALUES
	(1, 'U'),
	(2, 'A');
/*!40000 ALTER TABLE `ROL` ENABLE KEYS */;

-- Volcando estructura para tabla Splunk.USERS
CREATE TABLE IF NOT EXISTS `USERS` (
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

-- Volcando datos para la tabla Splunk.USERS: ~7 rows (aproximadamente)
DELETE FROM `USERS`;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` (`userId`, `nickname`, `pass`, `private`, `expirationDate`, `banned`, `rol`, `email`) VALUES
	(1, 'Johnny', 'Johnny', b'0', '2019-04-16 13:31:16', b'0', 1, NULL),
	(2, 'Salty', 'Salty', b'0', '2019-04-16 13:31:54', b'0', 1, NULL),
	(3, 'Blue', 'Blue', b'1', '2019-04-16 13:32:10', b'0', 1, NULL),
	(4, 'admin', 'admin', b'1', '2019-05-05 11:05:16', b'0', 2, NULL),
	(5, 'usuario', 'Usuario1', b'0', '2019-05-06 01:08:36', b'0', 1, NULL),
	(6, 'usuario2', 'Usuario2', b'1', '2019-05-06 01:09:01', b'0', 1, NULL),
	(7, 'usuario3', 'Usuario3', b'0', '2019-05-06 01:16:32', b'0', 1, 'usuario3@gmail.com');
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
