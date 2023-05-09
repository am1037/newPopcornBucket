-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: popcorn
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `actorId` int NOT NULL,
  `actorName` varchar(100) DEFAULT NULL,
  `actorFilmo` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (111,'bruce willis','sixth sense'),(222,'stephan chow','kungfu hustle'),(333,'mifune toshiro','seven samurai'),(0,'bruce willis',NULL),(444,'Hong',NULL);
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actorlike`
--

DROP TABLE IF EXISTS `actorlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actorlike` (
  `memberId` varchar(100) DEFAULT NULL,
  `actorid` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actorlike`
--

LOCK TABLES `actorlike` WRITE;
/*!40000 ALTER TABLE `actorlike` DISABLE KEYS */;
/*!40000 ALTER TABLE `actorlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_comment`
--

DROP TABLE IF EXISTS `b_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `b_comment` (
  `b_comment_id` int NOT NULL,
  `b_comment_content` varchar(100) DEFAULT NULL,
  `b_comment_writer_id` varchar(100) DEFAULT NULL,
  `b_comment_create` date DEFAULT NULL,
  `b_comment_update` date DEFAULT NULL,
  `b_comment_delete` date DEFAULT NULL,
  `b_comment_is_deleted` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_comment`
--

LOCK TABLES `b_comment` WRITE;
/*!40000 ALTER TABLE `b_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `b_reply`
--

DROP TABLE IF EXISTS `b_reply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `b_reply` (
  `b_reply_id` int NOT NULL,
  `bbs_id` int DEFAULT NULL,
  `b_reply_content` varchar(10) DEFAULT NULL,
  `b_reply_writer_id` varchar(10) DEFAULT NULL,
  `b_reply_create` date DEFAULT NULL,
  `b_reply_update` date DEFAULT NULL,
  `b_reply_delete` date DEFAULT NULL,
  `b_reply_is_deleted` date DEFAULT NULL,
  `b_reply_order_num` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `b_reply`
--

LOCK TABLES `b_reply` WRITE;
/*!40000 ALTER TABLE `b_reply` DISABLE KEYS */;
/*!40000 ALTER TABLE `b_reply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bbs`
--

DROP TABLE IF EXISTS `bbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bbs` (
  `bbs_id` int NOT NULL,
  `bbs_title` varchar(100) DEFAULT NULL,
  `bbs_content` varchar(100) DEFAULT NULL,
  `bbs_writer_id` varchar(100) DEFAULT NULL,
  `bbs_create` date DEFAULT NULL,
  `bbs_update` date DEFAULT NULL,
  `bbs_delete` date DEFAULT NULL,
  `bbs_is_deleted` varchar(100) DEFAULT NULL,
  `bbs_view_cnt` int DEFAULT NULL,
  `bbs_cate_num` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbs`
--

LOCK TABLES `bbs` WRITE;
/*!40000 ALTER TABLE `bbs` DISABLE KEYS */;
INSERT INTO `bbs` VALUES (1,'hi','bye','john',NULL,NULL,NULL,NULL,NULL,NULL),(2,'yes','no','jane',NULL,NULL,NULL,NULL,NULL,NULL),(3,'hello',NULL,NULL,NULL,NULL,NULL,NULL,0,0);
/*!40000 ALTER TABLE `bbs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` varchar(100) NOT NULL,
  `member_pw` varchar(100) DEFAULT NULL,
  `member_name` varchar(100) DEFAULT NULL,
  `member_knickname` varchar(100) DEFAULT NULL,
  `member_tel` varchar(100) DEFAULT NULL,
  `member_email` varchar(100) DEFAULT NULL,
  `member_level` int DEFAULT NULL,
  `member_fav` int DEFAULT NULL,
  `member_view` int DEFAULT NULL,
  `member_img` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movieid` int NOT NULL,
  `movieTitle` varchar(100) DEFAULT NULL,
  `movieGenre` varchar(100) DEFAULT NULL,
  `movieGrade` int DEFAULT NULL,
  `movieOpen` varchar(100) DEFAULT NULL,
  `movieLast` varchar(100) DEFAULT NULL,
  `movieStory` varchar(100) DEFAULT NULL,
  `movieTime` int DEFAULT NULL,
  `movieimg` varchar(100) DEFAULT NULL,
  `movieActor` varchar(100) DEFAULT NULL,
  `movieDirector` varchar(100) DEFAULT NULL,
  `movieLike` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'greenknight','comedy',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_screen`
--

DROP TABLE IF EXISTS `movie_screen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_screen` (
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `theater` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `screen` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `time` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `theater_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `movie_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_screen`
--

LOCK TABLES `movie_screen` WRITE;
/*!40000 ALTER TABLE `movie_screen` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_screen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviecalender`
--

DROP TABLE IF EXISTS `moviecalender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviecalender` (
  `calendarlog_no` int NOT NULL,
  `mydate` date DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `member_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviecalender`
--

LOCK TABLES `moviecalender` WRITE;
/*!40000 ALTER TABLE `moviecalender` DISABLE KEYS */;
/*!40000 ALTER TABLE `moviecalender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moviejjim`
--

DROP TABLE IF EXISTS `moviejjim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moviejjim` (
  `movie_id` int DEFAULT NULL,
  `member_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moviejjim`
--

LOCK TABLES `moviejjim` WRITE;
/*!40000 ALTER TABLE `moviejjim` DISABLE KEYS */;
INSERT INTO `moviejjim` VALUES (3,NULL);
/*!40000 ALTER TABLE `moviejjim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movierecommend`
--

DROP TABLE IF EXISTS `movierecommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movierecommend` (
  `userId` varchar(100) DEFAULT NULL,
  `movieSimilarity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movierecommend`
--

LOCK TABLES `movierecommend` WRITE;
/*!40000 ALTER TABLE `movierecommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `movierecommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mycalendar`
--

DROP TABLE IF EXISTS `mycalendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mycalendar` (
  `calendarlog_no` int NOT NULL,
  `mydate` date DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `member_id` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mycalendar`
--

LOCK TABLES `mycalendar` WRITE;
/*!40000 ALTER TABLE `mycalendar` DISABLE KEYS */;
/*!40000 ALTER TABLE `mycalendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rankactor`
--

DROP TABLE IF EXISTS `rankactor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rankactor` (
  `actorId` int NOT NULL,
  `actorName` varchar(10) DEFAULT NULL,
  `actorLike` int DEFAULT NULL,
  `actorHits` int DEFAULT NULL,
  `actorPopularity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rankactor`
--

LOCK TABLES `rankactor` WRITE;
/*!40000 ALTER TABLE `rankactor` DISABLE KEYS */;
INSERT INTO `rankactor` VALUES (3,NULL,NULL,NULL,NULL),(3121,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `rankactor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rankmovie`
--

DROP TABLE IF EXISTS `rankmovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rankmovie` (
  `movieId` int NOT NULL,
  `movieTitle` varchar(100) DEFAULT NULL,
  `movieLike` int DEFAULT NULL,
  `movieHits` int DEFAULT NULL,
  `moviePopularity` int DEFAULT NULL,
  `movieReviews` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rankmovie`
--

LOCK TABLES `rankmovie` WRITE;
/*!40000 ALTER TABLE `rankmovie` DISABLE KEYS */;
/*!40000 ALTER TABLE `rankmovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rankuser`
--

DROP TABLE IF EXISTS `rankuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rankuser` (
  `memberId` varchar(100) NOT NULL,
  `memberName` varchar(100) DEFAULT NULL,
  `memberLike` int DEFAULT NULL,
  `memberHits` int DEFAULT NULL,
  `memberPopularity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rankuser`
--

LOCK TABLES `rankuser` WRITE;
/*!40000 ALTER TABLE `rankuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `rankuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `theater`
--

DROP TABLE IF EXISTS `theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `theater` (
  `theater_company` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `theater_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `theater_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `theater_location` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`theater_company`,`theater_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `theater`
--

LOCK TABLES `theater` WRITE;
/*!40000 ALTER TABLE `theater` DISABLE KEYS */;
/*!40000 ALTER TABLE `theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userjjim`
--

DROP TABLE IF EXISTS `userjjim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userjjim` (
  `userId` varchar(100) DEFAULT NULL,
  `otherUserId` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userjjim`
--

LOCK TABLES `userjjim` WRITE;
/*!40000 ALTER TABLE `userjjim` DISABLE KEYS */;
/*!40000 ALTER TABLE `userjjim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usersimilarity`
--

DROP TABLE IF EXISTS `usersimilarity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usersimilarity` (
  `userId` varchar(100) DEFAULT NULL,
  `memberId` varchar(100) DEFAULT NULL,
  `similarity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usersimilarity`
--

LOCK TABLES `usersimilarity` WRITE;
/*!40000 ALTER TABLE `usersimilarity` DISABLE KEYS */;
/*!40000 ALTER TABLE `usersimilarity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'popcorn'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-09 21:30:50
