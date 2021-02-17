-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: itwillbs14
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `c_num` int(11) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `p_num` int(11) DEFAULT NULL,
  `p_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_num`),
  KEY `c_id_fk_idx` (`id`),
  KEY `c_pnum_fk_idx` (`p_num`),
  CONSTRAINT `c_id_fk` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `c_pnum_fk` FOREIGN KEY (`p_num`) REFERENCES `product` (`p_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,'admin',17,1),(2,'admin2',19,1),(3,'admin2',12,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `id` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `email` varchar(30) DEFAULT NULL,
  `postcode` int(11) DEFAULT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `grade` int(11) DEFAULT '0',
  `totalprice` int(11) DEFAULT '0',
  `point` int(11) DEFAULT '0',
  `action` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('admin','1234','sdeo@naver.com',46759,'부산광역시 강서구 신호산단3로 73(신호동, 삼한골든뷰아파트)','1005호',1,412,412,0),('admin2','1234','teps@daum.net',47246,'부산광역시 부산진구 동천로 109(부전동)','306호',1,413,413,0),('test','test','ssep@nate.com',49420,'부산광역시 사하구 신산북로 11(신평동)','2202호',0,123,123,0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `p_order`
--

DROP TABLE IF EXISTS `p_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `p_order` (
  `b_num` int(11) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `o_name` varchar(45) DEFAULT NULL,
  `o_phone` varchar(45) DEFAULT NULL,
  `o_email` varchar(45) DEFAULT NULL,
  `b_date` date DEFAULT NULL,
  `p_num` int(11) DEFAULT NULL,
  `b_count` int(11) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `d_cost` int(11) DEFAULT NULL,
  `d_result` int(11) DEFAULT NULL,
  `d_name` varchar(45) DEFAULT NULL,
  `d_phone` varchar(45) DEFAULT NULL,
  `d_postcode` int(11) DEFAULT NULL,
  `d_address` varchar(70) DEFAULT NULL,
  `d_address2` varchar(70) DEFAULT NULL,
  `d_message` varchar(100) DEFAULT NULL,
  `payment` int(11) DEFAULT NULL,
  KEY `o_id_fk_idx` (`id`),
  KEY `o_pnum_fk_idx` (`p_num`),
  CONSTRAINT `o_id_fk` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `o_pnum_fk` FOREIGN KEY (`p_num`) REFERENCES `product` (`p_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `p_order`
--

LOCK TABLES `p_order` WRITE;
/*!40000 ALTER TABLE `p_order` DISABLE KEYS */;
INSERT INTO `p_order` VALUES (1,'admin','김영희','010-2222-2222','b@b.com','2021-01-27',14,2,888,2500,0,'김영희','010-2222-2222',NULL,'부산광역시 부산진구 동천로 109','삼한골든게이트빌딩 7층','문 앞에 나둬주세요',1),(2,'admin2','김철수','010-1111-1111','a@a.com','2021-01-27',11,1,567,2500,2,'김철수','010-1111-1111',NULL,'부산광역시 부산진구 동천로 109','삼한골든게이트빌딩 7층','부재시 경비실에 맡겨주세요',1),(3,'test','1','1','1','2021-01-27',11,1,1,1,1,'1','1',NULL,NULL,NULL,NULL,1),(4,'admin2','유저1','010-134-1354','2@2','2021-02-09',11,3,5841,58410,0,'유저1','010-1354-123',12345,'서울','서울','경비실에 맡겨주세요',1),(5,'admin2','유저12','1234','2@2','2021-02-10',11,1,1947,19470,0,'유저12','1234',3254,'서울','서울','',1),(6,'admin2','유저12','1234','2@2','2021-02-10',13,1,5000,50000,3,'유저12','1234',3254,'서울','서울','',1);
/*!40000 ALTER TABLE `p_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `b_num` int(11) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `b_date` date DEFAULT NULL,
  `b_account` varchar(45) DEFAULT NULL,
  `b_bank` int(11) DEFAULT NULL,
  PRIMARY KEY (`b_num`),
  KEY `p_id_fk_idx` (`id`),
  CONSTRAINT `p_id_fk` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,'admin','2021-01-26','1234',1),(2,'admin2','2021-01-27','4241',2),(3,'test','2021-01-27','1',1);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `p_num` int(11) NOT NULL AUTO_INCREMENT,
  `category` int(11) DEFAULT NULL,
  `p_name` varchar(100) DEFAULT NULL,
  `p_count` int(11) DEFAULT '0',
  `p_price` int(11) DEFAULT '0',
  `p_saleprice` int(11) DEFAULT '0',
  `img_main` varchar(500) DEFAULT NULL,
  `img_content` varchar(500) DEFAULT NULL,
  `price_count` int(11) DEFAULT NULL,
  `readcount` int(11) DEFAULT '0',
  `action` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`p_num`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (11,1,'네추럴라이즈 구연산 칼슘 마그네슘 비타민 D 아연 1500mg x 90정',96,22900,19470,'127.PNG','37.jpg,null,null,null',1947000,4,0),(12,1,'그린스토어 엽산600 600mg x 90정',99,22900,19470,'320.PNG','24.jpg,15.jpg,null,null',1947000,3,0),(13,1,'메가네이처 스트레스클린 453mg x 90캡슐',99,57000,50000,'142.PNG','326.PNG,47.jpg,null,null',5000000,3,0),(14,2,'그린스토어 수면엔 3g x 14포',100,28000,26500,'126.PNG','43.jpg,null,null,null',2650000,2,0),(15,1,'더 리얼 밀크씨슬 리버케어 1000mg x 60캡슐',100,17900,16110,'140.PNG','46.jpg,null,null,null',1611000,2,0),(16,1,'유유제약 인사메디 포르테 1400mg x 60정',100,10900,9270,'132.PNG','219.PNG,55.jpg,null,null',927000,5,0),(17,1,'제일 눈에 좋은 루테인 지아잔틴 500mg x 48캡슐',100,14190,13490,'130.png','218.png,411.PNG,null,null',1349000,3,0),(19,1,'노블 아연 50(+구리) 715mg x 100정',10,20900,19800,'133.PNG','25.jpg,null,null,null',120,13,0),(20,1,'test2',1,1,1,'pic_medicine8.png','medications-342462_1920_1920x1280.jpg,KakaoTalk_20210204_214521640_01_1920x12752.jpg,null,null',1,43,1),(21,1,'네추럴팜 베타루테인 컴플렉스 루테인 630mg x 90캡슐',123,12300,10800,'134.PNG','412.PNG,56.jpg,null,null',15129,2,0),(22,1,'네추럴라이즈 프리미엄 비타민 B12 485mg x 90정',33,30500,29800,'136.PNG','0286ff8a9ad164b5288f401ce4961205144cdda17ac6176e2a9f1c455cb72.jpg,null,null,null',983400,0,0),(23,1,'네추럴라이즈 원어데이 멀티비타민_멀티미네랄 맥스 1445mg x 90정',324,34590,23480,'323.PNG','27.jpg,null,null,null',7607520,0,0),(24,1,'네추럴라이즈 밀크씨슬 헤파케어 1000mg x 90캡슐',33,24500,21000,'137.PNG','38.jpg,null,null,null',693000,0,0),(25,1,'네추럴라이즈 더 퍼펙트 스킨 히알루론산 850mg x 60캡슐',44,23000,21000,'138.PNG','324.PNG,57.jpg,null,null',924000,0,0),(26,1,'네추럴라이즈 버퍼드 비타민 C 1000 플러스 1600mg x 90정',34,32000,21400,'220.PNG','sd.jpg,null,null,null',727600,1,0),(27,1,'네추럴 팩터스 멀티골드 원스 멀티비타민&미네랄 1275mg x 60정',33,23000,14320,'139.PNG','27caa88bcecfd72bae087c31367d1fa79d3b49096c3c3abed2f9e5d312dc.jpg,null,null,null',472560,0,0),(28,1,'그린스토어 스트레스 릴렉스 테아닌250 600mg x 60정',44,23000,21400,'16.jpg','325.PNG,413.PNG,null,null',941600,0,0),(29,1,'센트룸 포맨 1387mg x 70정',50,23000,21500,'df.PNG','26dfa5e11f32151ba32300746fa42145e32371820ac170be68433ff324d5.jpg,null,null,null',1075000,0,0),(30,1,'그린스토어 마그네슘 350 950mg x 90정',40,21980,17340,'1310.PNG','fa71cae66a396698b03de9c369c9944655d23ef95a6a2c0dc4b81cd1e9e3.jpg,null,null,null',693600,0,0),(31,1,'조아큐텐홍 1200mg x 60캡슐',40,32100,24500,'143.PNG','fa71cae66a396698b03de9c369c9944655d23ef95a6a2c0dc4b81cd1e9e31.jpg,null,null,null',980000,0,0),(32,1,'허벌랜드 핑크 블라썸 구미 2.8g x 60구미',500,23000,21500,'61.PNG','685dfb2d40662926ceeb6abead85fc217d5b33637322d807c23a5d49a2e9.jpg,null,null,null',10750000,0,0),(33,1,'허벌랜드 비타민C 구미 2.2g x 60구미',40,23000,21500,'51.PNG','e87ea5b899c8d2af8b04050cd24eb03d07841ffd4bd33df26a8b558ee3b5.jpg,null,null,null',860000,0,0),(34,1,'허벌랜드 멀티비타민 구미 2.2g x 60구미',40,23000,21500,'414.PNG','45068dd81e4e7dc3ac8ba17b6156fccd8388010fc11d114090063cd8649c.jpg,null,null,null',860000,0,0),(35,1,'더 리얼 칼슘 마그네슘 아연 앤 비타민D 1305mg x 100정',145,35000,32000,'sdf.PNG','f667389b4f19057c38adca5d23875cfb9c4d206426b9d83b6e3d9999cb3d.jpg,null,null,null',4640000,0,0),(36,1,'어바틀오브네이처 더 시크 비오틴 500mg x 60정',35,32100,28600,'81.PNG','649547dd5e73c861d911dad13f5c6df0fce17d490aef17feb6a8baf5aafd.jpg,null,null,null',1001000,0,0),(37,1,'허벌랜드 키즈 멀티비타민미네랄 구미 2.2g x 60개',300,23400,18290,'221.PNG','null,null,null,null',5487000,1,0),(38,1,'어바틀오브네이처 맥소 쏘팔메토 1000mg x 90캡슐',30,23400,18790,'1112.PNG','c5f6b41f9a984d65efdc7a02a7109383329134dcddd9d0e974f85c1ba571.jpg,null,null,null',563700,0,0),(39,1,'네추럴 라이즈 파워 마그네슘 1350mg x 100정',200,23000,20100,'sd.PNG','860602423775286-75900033-8305-4dc6-93b5-470069bc19e3.jpg,null,null,null',4020000,0,0),(40,1,'네추럴라이즈 프리미엄 나토키나제 700mg x 60정',460,20000,17500,'1210.PNG','c63118a706a0e99314b5b1066c98f52147a5dd30d012b622f230336fd67a.jpg,null,null,null',8050000,1,0),(41,1,'한미 프리미엄 비타민D 2000IU x 90캡슐',44,21000,18700,'101.PNG','5295920e871c8909bab912377f701f33ecca66a6d854958855b675cd1727.png,null,null,null',822800,0,0),(42,1,'네추럴 팩터스 비오틴 580mg x 60캡슐',400,34500,27890,'71.PNG','4e5fd7eaf8c5673ab34ea1cb36f9f2e2739ebc2a8500d3311e7399bb3227.jpg,null,null,null',11156000,0,0),(43,1,'마이니 센시라민큐 1100mg x 60정',200,23000,21000,'91.PNG','4afec9c8f5a1f0112ff88cd99bc48e90669848142aacaf7d25fb19591b44.jpg,null,null,null',4200000,0,0),(44,1,'프리티 비오틴 500mg x 100정',400,27000,24500,'327.PNG','360608103d476f9eeef14992b0c2861e23924214a579e846ef6aedb8b8fe.jpg,null,null,null',9800000,0,0);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productqna`
--

DROP TABLE IF EXISTS `productqna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productqna` (
  `q_num` int(11) NOT NULL,
  `id` varchar(20) DEFAULT NULL,
  `p_num` int(11) DEFAULT NULL,
  `subject` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `re_result` int(11) DEFAULT NULL,
  `reply` varchar(45) DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `re_reg_date` date DEFAULT NULL,
  `re_ref` int(11) DEFAULT NULL,
  `re_lev` int(11) DEFAULT NULL,
  `re_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`q_num`),
  KEY `pq_id_fk_idx` (`id`),
  KEY `pq_pnum_fk_idx` (`p_num`),
  CONSTRAINT `pq_id_fk` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pq_pnum_fk` FOREIGN KEY (`p_num`) REFERENCES `product` (`p_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productqna`
--

LOCK TABLES `productqna` WRITE;
/*!40000 ALTER TABLE `productqna` DISABLE KEYS */;
INSERT INTO `productqna` VALUES (1,'admin2',11,'궁금','문의합니다',0,NULL,'2021-02-04',NULL,1,0,NULL),(2,'test',17,'문의','문의합니다',0,NULL,'2021-02-04',NULL,2,0,NULL),(3,'admin2',14,'궁금해요','문의합니다',0,NULL,'2021-02-04',NULL,3,0,NULL),(4,'test',11,'문의합니다','문의합니다',0,NULL,'2021-02-04',NULL,4,0,NULL),(5,'test',11,'질문','문의합니다',0,NULL,'2021-02-04',NULL,5,0,NULL),(6,'test',15,'문의해요','문의합니다',0,NULL,'2021-02-04',NULL,6,0,NULL),(8,'test',11,'문의','문의합니다',0,NULL,'2021-02-04',NULL,8,0,0);
/*!40000 ALTER TABLE `productqna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `r_num` int(11) NOT NULL,
  `p_num` int(11) DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `r_content` varchar(100) DEFAULT NULL,
  `r_star` double DEFAULT NULL,
  `r_date` date DEFAULT NULL,
  PRIMARY KEY (`r_num`),
  KEY `id_idx` (`id`),
  KEY `re_pnum_fk_idx` (`p_num`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `member` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `re_pnum_fk` FOREIGN KEY (`p_num`) REFERENCES `product` (`p_num`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,11,'test','리뷰 테스트',5,'2021-02-09'),(2,11,'admin2','좋아요',3,'2021-02-09'),(3,13,'test','만족합니다',3,'2021-02-10'),(4,13,'test','좋아요',5,'2021-02-10'),(5,13,'admin2','좋네요',5,'2021-02-10'),(6,13,'admin','리뷰 테스트!!',2,'2021-02-10'),(7,13,'admin2','와 좋아요',5,'2021-02-11');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-16 12:40:35
