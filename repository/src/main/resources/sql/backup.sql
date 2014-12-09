-- MySQL dump 10.13  Distrib 5.6.16, for Win64 (x86_64)
--
-- Host: localhost    Database: refund_record
-- ------------------------------------------------------
-- Server version	5.6.16-log

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
-- Table structure for table `amazon_category`
--

DROP TABLE IF EXISTS `amazon_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazon_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `search_url` varchar(255) DEFAULT '' COMMENT '页面搜索出来的初始url',
  `goods_type` enum('shoes','watch') DEFAULT NULL COMMENT '商品类型',
  `sex` enum('male','female') DEFAULT NULL COMMENT '男用还是女用',
  `display` enum('yes','no') DEFAULT 'yes' COMMENT '是否显示并爬网页',
  `search_status` enum('searching','complete') DEFAULT NULL,
  `last_http_code` int(11) DEFAULT NULL,
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazon_category`
--

LOCK TABLES `amazon_category` WRITE;
/*!40000 ALTER TABLE `amazon_category` DISABLE KEYS */;
INSERT INTO `amazon_category` VALUES (1,'男士手表','http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion-mens-watches&field-keywords=MARC+BY+MARC+JACOBS','watch','male','yes',NULL,200,'2014-10-01 01:30:54','2014-11-25 22:10:56'),(2,'女士手表','http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion-womens-watches&field-keywords=MARC+BY+MARC+JACOBS','watch','female','yes',NULL,200,'2014-10-01 12:40:38','2014-11-22 16:28:41'),(3,'女士鞋子（shoes）','http://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Dfashion-womens-shoes&field-keywords=Dansko','shoes','female','yes',NULL,200,'2014-11-09 10:05:26','2014-11-09 13:33:51'),(4,'女士鞋子（cole haan）','http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion-womens-shoes&field-keywords=cole+haan&rh=i%3Afashion-womens-shoes%2Ck%3Acole+haan','shoes','female','yes',NULL,200,'2014-11-09 10:06:49','2014-11-09 13:34:02');
/*!40000 ALTER TABLE `amazon_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amazon_item`
--

DROP TABLE IF EXISTS `amazon_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazon_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `asin` varchar(255) NOT NULL COMMENT 'amazon的唯一id',
  `item_url` varchar(255) NOT NULL COMMENT '商品的url',
  `last_http_code` int(11) NOT NULL DEFAULT '0' COMMENT '上一次查询返回的响应代码,0是没有得到返回',
  `whole_content` longtext COMMENT 'http请求返回的文本内容',
  `belongto_category_id` int(11) DEFAULT NULL COMMENT '属于哪一类',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `asin_index` (`asin`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazon_item`
--

LOCK TABLES `amazon_item` WRITE;
/*!40000 ALTER TABLE `amazon_item` DISABLE KEYS */;
INSERT INTO `amazon_item` VALUES (1,'Marc by Marc Jacobs Henry Skeleton Gold Leather Watch - MBM1246','B009V0KUT6','http://www.amazon.com/dp/B009V0KUT6',200,'stock:Only 5 left in stock.,price:$136.95,seller:Savvy Watch,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Gold Ion-plated Stainless Steel,Band Material:leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/414qZH0icCL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51GqEffR1oL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41CCh-SyhGL.jpg}',1,'2014-11-22 16:41:07','2014-11-25 22:11:02'),(2,'Marc by Marc Jacobs Women\'s Blade Etched Logo Watch, Silver/Black, One Size','B006IUDZ8K','http://www.amazon.com/dp/B006IUDZ8K',200,'stock:In Stock.,price:$118.05,seller:Time And Beyond,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41yGrruxHTL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/210c8u-NM9L.jpg}',1,'2014-11-22 16:41:09','2014-11-25 22:11:04'),(3,'Marc by Marc Jacobs Men\'s MBM1316 Brown Leather Strap Watch','B00HGDLKIU','http://www.amazon.com/dp/B00HGDLKIU',200,'stock:In Stock.,price:$106.48,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Gold-tone Stainless Steel,Band Material:Pig skin leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41y8SF5kDwL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41YEfesrEGL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51-PdyGbUSL.jpg}',1,'2014-11-22 16:41:11','2014-11-25 22:11:00'),(4,'Marc by Marc Jacobs Larry Chronograph Black Dial Black Ion-plated Mens Watch MBM5032.','B009RSZHX6','http://www.amazon.com/dp/B009RSZHX6',200,'stock:Only 1 left in stock.,price:$222.20,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Black Ion-plated Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51R9EJflVsL.jpg}',1,'2014-11-22 16:41:13','2014-11-25 22:11:06'),(5,'Marc by Marc Jacobs MBM3255 Henry Black Skeleton Watch','B00BFNAVPK','http://www.amazon.com/dp/B00BFNAVPK',200,'stock:Only 5 left in stock.,price:$161.91,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41efB5zk9dL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/318rM1TADBL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41VPkV2nX%2BL.jpg}',1,'2014-11-22 16:41:15','2014-11-25 22:11:10'),(6,'MARC JACOBS Watches CLASSIC MEN COLLECTION: FERGUS MBM5076','B00KCCWGBC','http://www.amazon.com/dp/B00KCCWGBC',200,'stock:Only 4 left in stock.,price:$152.99,seller:Savvy Watch,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Pig skin leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41Mz2J-%2B1tL.jpg}',1,'2014-11-22 16:41:18','2014-11-25 22:11:08'),(7,'Marc By Marc Jacobs MBM5066 Brown Leather Blue Dial Men\'s Watch','B00NJ3CV98','http://www.amazon.com/dp/B00NJ3CV98',200,'stock:Only 2 left in stock.,price:$144.50,seller:StoreVip,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Calfskin},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51K0PKD2RNL.jpg}',1,'2014-11-22 16:41:20','2014-11-25 22:11:12'),(8,'Marc by Marc Jacobs Jimmy Blue Dial Stainless Steel Mens Watch MBM5058','B00HGDLMXS','http://www.amazon.com/dp/B00HGDLMXS',200,'stock:In Stock.,price:$127.00,seller:BLASANI,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/416tNdBbAOL.jpg}',1,'2014-11-22 16:41:22','2014-11-25 22:11:26'),(9,'Marc by Marc Jacobs Blade Leather Strap Women\'s Watch - MBM1218','B007CONOHS','http://www.amazon.com/dp/B007CONOHS',200,'stock:In Stock.,price:$129.99,seller:Advance Buy,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41vB09-Q42L.jpg}',1,'2014-11-22 16:41:24','2014-11-25 22:11:14'),(10,'Marc by Marc Jacobs Rock Chronograph Mother of Pearl Dial Rose Gold-tone Unisex Watch MBM3251','B00DX7SOMS','http://www.amazon.com/dp/B00DX7SOMS',200,'stock:Only 1 left in stock.,price:$200.62,seller:Jomashop,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Rose Gold-tone Stainless Steel,Band Material:Stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41dcmdq-meL.jpg}',1,'2014-11-22 16:41:26','2014-11-22 16:41:26'),(11,'Marc by Marc Jacobs MBM3205 Mens Silver Henry Skeleton Watch','B00BBWW44Q','http://www.amazon.com/dp/B00BBWW44Q',200,'stock:Only 4 left in stock.,price:$169.43,seller:Matilda F & C,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41K5pkVfaNL.jpg}',1,'2014-11-22 16:41:28','2014-11-25 22:11:28'),(12,'Marc by Marc Jacob Larry Black Dial Black Leather Mens Watch MBM5033','B00BJI4PTO','http://www.amazon.com/dp/B00BJI4PTO',200,'stock:Only 5 left in stock.,price:$164.48,seller:BLASANI,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51UWndI2BGL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41vPqF6WOQL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31eiFmpCIGL.jpg}',1,'2014-11-22 16:41:30','2014-11-25 22:11:44'),(13,'Marc Jacobs Henry White Dial Rose Gold-tone Stainless Steel Mens Watch MBM1249','B00BUUTBHM','http://www.amazon.com/dp/B00BUUTBHM',200,'stock:Only 2 left in stock.,price:$117.00,seller:Authentic Watch Store,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Rose Gold-tone Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/31z9EXLCs8L.jpg}',1,'2014-11-22 16:41:32','2014-11-25 22:11:23'),(14,'Marc by Marc Jacobs Chronograph Silver Dial Two-tone Ladies Watch MBM3177','B0085G4A7A','http://www.amazon.com/dp/B0085G4A7A',200,'stock:Only 3 left in stock.,price:$215.11,seller:GEM Products,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Gold-plated stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41bBGUIZ6NL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41E9RRrT6YL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41-48-U6%2BuL.jpg}',1,'2014-11-22 16:41:34','2014-11-25 22:11:32'),(15,'Marc by Marc Jacobs Larry Chronograph Olive Leather Mens Watch MBM5034','B009RSZJBG','http://www.amazon.com/dp/B009RSZJBG',200,'stock:Only 2 left in stock.,price:$199.99,seller:BLASANI,imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/411gNpIt5rL.jpg}',1,'2014-11-22 16:41:36','2014-11-25 22:11:33'),(16,'Marc by Marc Jacobs Black Dial Stainless Steel Mens Watch MBM5059','B00HGDLMOW','http://www.amazon.com/dp/B00HGDLMOW',200,'stock:Only 1 left in stock.,price:$145.00,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41WxMOyKy-L.jpg}',1,'2014-11-22 16:41:38','2014-11-25 22:11:30'),(17,'Marc by Marc Jacobs Women\'s Fergus Watch, Gold/Green Jean/Tan, One Size','B00KCCWHUM','http://www.amazon.com/dp/B00KCCWHUM',200,'stock:Only 2 left in stock.,price:$148.69,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41S66MY0TSL.jpg}',1,'2014-11-22 16:41:39','2014-11-25 22:11:19'),(18,'Marc by Marc Jacobs MBM5061 Grey Strap Leather Men\'s Watch','B00HGDLND2','http://www.amazon.com/dp/B00HGDLND2',200,'stock:Only 4 left in stock.,price:$151.81,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Calfskin},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/516-7zqsG6L.jpg}',1,'2014-11-22 16:41:42','2014-11-25 22:11:49'),(19,'Marc by Marc Jacobs Danny Black Dial Brown Leather Mens Watch MBM5039','B00HGDLNYQ','http://www.amazon.com/dp/B00HGDLNYQ',200,'stock:Only 4 left in stock.,price:$133.75,seller:Jomashop,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41msKB9bocL.jpg}',1,'2014-11-22 16:41:43','2014-11-25 22:11:36'),(20,'Marc by Marc Jacobs MBM5065 Chronograph Black Bracelet Men\'s Watch','B00NJ39IHQ','http://www.amazon.com/dp/B00NJ39IHQ',200,'stock:Only 3 left in stock.,price:$211.28,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless steel-plated},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51Hhc-L5eSL.jpg}',1,'2014-11-22 16:41:45','2014-11-25 22:11:21'),(21,'Marc by Marc Jacobs MBM2598 Mens Black Rock Chronograph Watch','B00E267M4U','http://www.amazon.com/dp/B00E267M4U',200,'stock:Only 1 left in stock.,price:$259.00,seller:K.G. Company,material:{,Case Material:Steel and 18k gold,Band Material:Silicone},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41GDkSJ%2Bg%2BL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41GDkSJ%2Bg%2BL.jpg}',1,'2014-11-22 16:41:47','2014-11-25 22:11:48'),(22,'Marc by Marc Jacobs Men\'s Fergus 42MM Watch, Brown/Mineral Blue, One Size','B00KCCWH84','http://www.amazon.com/dp/B00KCCWH84',200,'stock:Only 1 left in stock.,price:$144.50,seller:StoreVip,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Pig skin leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41IOAoUqYSL.jpg}',1,'2014-11-22 16:41:49','2014-11-25 22:11:42'),(23,'Marc by Marc Jacobs Men\'s Jimmy Watch, Black/Black, One Size','B00HGDLOLS','http://www.amazon.com/dp/B00HGDLOLS',200,'stock:Only 2 left in stock.,price:$153.50,seller:StoreVip,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41ZNZYEu5iL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41m6A-JxNML.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41rBGiUW6GL.jpg}',1,'2014-11-22 16:41:51','2014-11-22 16:41:51'),(24,'Marc by Marc Jacobs MBM5525 Mens Black White Rock Watch','B00I90GBWE','http://www.amazon.com/dp/B00I90GBWE',200,'stock:Only 4 left in stock.,price:$174.93,seller:BLASANI,material:{Dial window material type:Mineral,Case Material:Plastic,Band Material:Rubber},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51PkuGiJPrL.jpg}',1,'2014-11-22 16:41:52','2014-11-25 22:11:52'),(25,'Marc by Marc Jacobs Rock Stainless Steel &amp; Blue Watch, 46mm','B00IHNNVT4','http://www.amazon.com/dp/B00IHNNVT4',200,'stock:Only 2 left in stock.,price:$193.51,seller:BLASANI,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51hlyoueg6L.jpg}',1,'2014-11-22 16:41:54','2014-11-22 16:41:54'),(26,'Marc By Marc Jacobs Rock Chronograph Grey Dial Stainless Steel Mens Watch MBM5028','B00A3PGYES','http://www.amazon.com/dp/B00A3PGYES',200,'stock:Only 2 left in stock.,price:$178.99,seller:DealMeDeal,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/5161l7yzjRL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41MY8m9p4DL.jpg}',1,'2014-11-22 16:41:56','2014-11-25 22:11:46'),(27,'Marc by Marc Jacobs MBM3188 Mens Gold Rock Chronograph Watch','B00A4C3T5W','http://www.amazon.com/dp/B00A4C3T5W',200,'stock:Only 4 left in stock.,price:$229.99,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41rsRXY0oDL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41rsRXY0oDL.jpg}',1,'2014-11-22 16:41:58','2014-11-22 16:41:58'),(28,'Henry Women\'s Watch','B00BFNARKE','http://www.amazon.com/dp/B00BFNARKE',200,'stock:In Stock.,price:$163.00,seller:Advance Buy,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Metal},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41G-KVxCBiL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/416hooeosqL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41B5FlqGu9L.jpg}',1,'2014-11-22 16:41:59','2014-11-22 16:41:59'),(29,'Marc by Marc Jacobs Rock White Unisex Chrono Watch MBM2545','B005PL59AK','http://www.amazon.com/dp/B005PL59AK',200,'stock:Only 1 left in stock.,price:$280.10,seller:Watch Cove,material:{Dial window material type:Mineral,Case Material:Silver Tone Stainless Steel,Band Material:Resin},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41evYy12bBL.jpg}',1,'2014-11-22 16:42:02','2014-11-22 16:42:02'),(30,'Marc By Marc Jacobs Men\'s Jimmy MBM5060 Red Leather Quartz Watch with Black Dial','B00HGDLMCO','http://www.amazon.com/dp/B00HGDLMCO',200,'stock:Only 2 left in stock.,price:$154.42,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Calfskin},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41BEyvI7nWL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41EWAfodyoL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41Ek7Ws3QOL.jpg}',1,'2014-11-22 16:42:03','2014-11-22 16:42:03'),(31,'Marc by Marc Jacobs Men\'s Danny Watch, Stainless Steel/Black, One Size','B00HGDLO9K','http://www.amazon.com/dp/B00HGDLO9K',200,'stock:Only 1 left in stock.,price:$194.80,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/417%2BKwSow1L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41X2mqyUoVL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41ax%2BsDqEsL.jpg}',1,'2014-11-22 16:42:06','2014-11-22 16:42:06'),(32,'Marc by Marc Jacobs Men\'s Jimmy Watch, Stainless Steel/Navy, One Size','B00HGDLOW2','http://www.amazon.com/dp/B00HGDLOW2',200,'stock:Only 2 left in stock.,price:$132.50,seller:StoreVip,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41VJjR4ivML.jpg}',1,'2014-11-22 16:42:07','2014-11-25 22:11:17'),(33,'Marc by Marc Jacobs Men\'s Fergus 42MM Watch, Stainless Steel/Black, One Size','B00KCCWFRC','http://www.amazon.com/dp/B00KCCWFRC',200,'stock:Only 3 left in stock.,price:$163.73,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41NAekgqC8L.jpg}',1,'2014-11-22 16:42:09','2014-11-22 16:42:09'),(34,'Marc by Marc Jacobs MBM3244 Mens Rose Gold IP Baker Watch','B00ED3ZBUY','http://www.amazon.com/dp/B00ED3ZBUY',200,'stock:In Stock.,price:$181.37,seller:Watch Grabber,material:{,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41T6lgpD3ZL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41T6lgpD3ZL.jpg}',1,'2014-11-22 16:42:10','2014-11-22 16:42:10'),(35,'Marc by Marc Jacobs MBM3207 Mens Rose Gold Henry Skeleton Watch','B00BBWW4MS','http://www.amazon.com/dp/B00BBWW4MS',200,'stock:Only 2 left in stock.,price:$191.99,seller:Matilda F & C,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51BtbXIgLYL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31ffPuF8APL.jpg}',1,'2014-11-22 16:42:12','2014-11-22 16:42:12'),(36,'Marc By Marc Jacobs MBM9026 Pair of two Pelly Perry Watch Set','B00O8EDQ6O','http://www.amazon.com/dp/B00O8EDQ6O',200,'stock:Only 5 left in stock.,price:$275.00,material:{Dial window material type:Mineral,Band Material:Rubber},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51XQDCZzYbL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51MaYYa%2BRtL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51Q6cdGF0QL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41FRfZtgDQL.jpg}',1,'2014-11-22 16:42:14','2014-11-22 16:42:14'),(37,'Marc By Marc Jacobs MBM9044 Baker Pair of two Watch Set (MBM3247 &amp; MBM1318)','B00P9RQM8S','http://www.amazon.com/dp/B00P9RQM8S',200,'stock:Only 5 left in stock.,price:$325.00,material:{Dial window material type:Mineral,Band Material:Stainless Steel & Rubber},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41TGhVPDM1L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41NzjEbVV8L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31fwieoaiBL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41Q1c7jxhxL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/411WFkSGeRL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41q%2By4h06HL.jpg}',1,'2014-11-22 16:42:15','2014-11-22 16:42:15'),(38,'Marc by Marc Jacobs Men\'s Larry Band Watch, Gunmetal/Black, One Size','B00IH5KJOW','http://www.amazon.com/dp/B00IH5KJOW',200,'stock:Only 1 left in stock.,price:$274.95,seller:What\'s Hot,material:{Dial window material type:Mineral,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/418eeeDuXvL.jpg}',1,'2014-11-22 16:42:17','2014-11-22 16:42:17'),(39,'Marc Jacobs Stainless Steel Chronograph Mens Watch - MBM5027','B009AZ9HSM','http://www.amazon.com/dp/B009AZ9HSM',200,'stock:Available from <a href=\'/gp/offer-listing/B009AZ9HSM/ref=dp_olp_0?ie=UTF8&amp;condition=all\'>these sellers</a>.,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51lXBn-NHIL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41IeE5CXFEL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41RfajzDR5L.jpg}',1,'2014-11-22 16:42:19','2014-11-22 16:42:19'),(40,'Marc by Marc Jacobs Men\'s Larry Chronograph Brushes Watch, Silver, One Size','B0094A4LWK','http://www.amazon.com/dp/B0094A4LWK',200,'stock:Only 1 left in stock.,price:$239.00,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41MhbS0iJyL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/5181nJG04eL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31N2t7Wo0XL.jpg}',1,'2014-11-22 16:42:20','2014-11-22 16:42:20'),(41,'Marc by Marc Jacobs Men\'s MBM3157 Grey Dial Two-tone Chronograph Watch','B0085G4OK8','http://www.amazon.com/dp/B0085G4OK8',200,'stock:Only 4 left in stock.,price:$254.99,seller:DealMeDeal,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:TT Rose Gold Plated SS},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41eVp-RvX2L.jpg}',1,'2014-11-22 16:42:22','2014-11-22 16:42:22'),(42,'Marc by Marc Jacobs Baby Dave White Dial Black Leather Unisex Watch MBM1263','B00CGAJYHM','http://www.amazon.com/dp/B00CGAJYHM',200,'stock:Only 1 left in stock.,price:$179.90,seller:BSD Jewelry And Watches,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/414kBjtPt%2BL.jpg}',1,'2014-11-22 16:42:24','2014-11-22 16:42:24'),(43,'Marc Jacobs Sloane Black MBM8594 Watch','B00DULX4SQ','http://www.amazon.com/dp/B00DULX4SQ',200,'stock:Only 1 left in stock.,price:$139.95,seller:dbbargains,material:{,Band Material:Polycar urethane rubber},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41oz7ZRV7pL.jpg}',1,'2014-11-22 16:42:26','2014-11-22 16:42:26'),(44,'Marc by Marc Jacobs Baby Dave Ivory Dial Brown Leather Unisex Watch MBM1261','B00AJGK5SC','http://www.amazon.com/dp/B00AJGK5SC',200,'stock:Only 4 left in stock.,price:$165.00,seller:GEM Products,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/31quDz4wN7L.jpg}',1,'2014-11-22 16:42:28','2014-11-22 16:42:28'),(45,'Marc by Marc Jacobs Danny Black Leather Mens Watch MBM5041','B00IHVGZG2','http://www.amazon.com/dp/B00IHVGZG2',200,'stock:Available from <a href=\'/gp/offer-listing/B00IHVGZG2/ref=dp_olp_0?ie=UTF8&amp;condition=all\'>these sellers</a>.,imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41xSP8xVVqL.jpg}',1,'2014-11-22 16:42:29','2014-11-22 16:42:29'),(46,'Marc by Marc Jacobs Men\'s Fergus Automatic Watch, Black/Tan, One Size','B00NJ3GGOY','http://www.amazon.com/dp/B00NJ3GGOY',200,'stock:In stock.,price:$415.65,material:{Dial window material type:Mineral Crystal,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41Qb3zxfl8L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51R5P1BfKvL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31ovYxdh%2BYL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31VCHAF7lfL.jpg}',1,'2014-11-22 16:42:31','2014-11-22 16:42:31'),(47,'Marc Jacobs Rock Black Watch MBM5057','B00IHNKPXE','http://www.amazon.com/dp/B00IHNKPXE',200,'stock:Only 2 left in stock.,price:$258.21,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51-PZouBnsL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41aZPNyMrML.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41g5bwVlPZL.jpg}',1,'2014-11-22 16:42:32','2014-11-22 16:42:32'),(48,'MARC BY MARC JACOBS MBM5040 Danny black leather watch','B00FAORY5A','http://www.amazon.com/dp/B00FAORY5A',200,'stock:Only 4 left in stock.,price:$148.50,seller:StoreVip,imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/417-Vz-cBEL.jpg}',1,'2014-11-22 16:42:35','2014-11-22 16:42:35'),(49,'Marc Jacobs MBM2583 Black/Shell','B007CONPB8','http://www.amazon.com/dp/B007CONPB8',200,'stock:In Stock.,price:$217.98,seller:Teelys,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Black Ion-plated Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51YDRuZAMfL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41%2BJzmnpO5L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41XiLyIJSSL.jpg}',1,'2014-11-22 16:42:39','2014-11-22 16:42:39'),(50,'Marc by Marc Jacobs MBM3155 Mens Silver Rock Chronograph Watch','B008N8X4SG','http://www.amazon.com/dp/B008N8X4SG',200,'stock:Only 3 left in stock.,price:$219.82,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41Y%2BRGp%2BxqL.jpg}',1,'2014-11-22 16:42:40','2014-11-22 16:42:40'),(51,'Marc by Marc Jacobs MBM5036 Mens Black Steel Danny Watch','B00I90GE44','http://www.amazon.com/dp/B00I90GE44',200,'stock:Only 2 left in stock.,price:$212.92,seller:Savvy Watch,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41cRu7SD%2BVL.jpg}',1,'2014-11-22 16:42:42','2014-11-22 16:42:42'),(52,'Marc by Marc Jacobs Mens Diver Silicone Black Red Jewelry Watch MBM2571','B0059776X8','http://www.amazon.com/dp/B0059776X8',200,'stock:Only 1 left in stock.,price:$177.99,seller:FashionsUnlimited,material:{Dial window material type:Mineral,Band Material:Silicone},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41LvcKfNpDL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41popprzrbL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41Ob2ipSqWL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41LvcKfNpDL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31MKh0dfshL.jpg}',1,'2014-11-22 16:42:44','2014-11-22 16:42:44'),(53,'Marc Jacobs Henry White Dial White Leather Unisex Watch MBM1247','B00CGOQYW6','http://www.amazon.com/dp/B00CGOQYW6',200,'stock:Only 4 left in stock.,price:$148.00,seller:Advance Buy,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41%2BEzQb0yFL.jpg}',1,'2014-11-22 16:42:46','2014-11-22 16:42:46'),(54,'Marc By Marc Jacobs Rock Chronograph White Dial Stainless Steel Mens Watch MBM50287','B0085G49OO','http://www.amazon.com/dp/B0085G49OO',200,'stock:Available from <a href=\'/gp/offer-listing/B0085G49OO/ref=dp_olp_0?ie=UTF8&amp;condition=all\'>these sellers</a>.,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Stainless steel-plated},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/515XrNWVFUL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41l9GVfcG3L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41PHYkzg2tL.jpg}',1,'2014-11-22 16:42:48','2014-11-22 16:42:48'),(55,'Marc by Marc Men\'s MBM2584 Black Stainless-Steel Quartz Watch with Black Dial','B007CONNBU','http://www.amazon.com/dp/B007CONNBU',200,'stock:Only 2 left in stock.,price:$175.50,seller:Authentic Watch Store,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41vjcacAq7L.jpg}',1,'2014-11-22 16:42:50','2014-11-22 16:42:50'),(56,'Marc By Marc Jacobs Rock Metal Chronograph Gold Ion-plated Mens Watch MBM3158','B0085G4K9I','http://www.amazon.com/dp/B0085G4K9I',200,'stock:Only 1 left in stock.,price:$350.00,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Gold-plated Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51avNh73u-L.jpg}',1,'2014-11-22 16:42:52','2014-11-22 16:42:52'),(57,'Marc By Marc Jacobs Mbm9030 Pelly Glitz Bezel Pair of Black &amp; White Silver Unisex Watch','B00M02C6R6','http://www.amazon.com/dp/B00M02C6R6',200,'stock:Only 1 left in stock.,price:$329.99,seller:Fine watch house,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Silicone / Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41mCJoqw1KL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41fnTW-TCfL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41yWkEJJDQL.jpg}',1,'2014-11-22 16:42:53','2014-11-22 16:42:53'),(58,'Marc by Marc Men\'s MBM5070 Danny Blue Dial Face Analog Watch','B00IPQWBQW','http://www.amazon.com/dp/B00IPQWBQW',200,'stock:Only 2 left in stock.,price:$176.50,seller:Cool Chrono,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51CrU22lZnL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/216JBKKAOLL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41uwCTHGDcL.jpg}',1,'2014-11-22 16:42:56','2014-11-22 16:42:56'),(59,'Marc by Marc Jacobs MBM4020 Henry Two Tone Leather Strap Watch','B00J8IJIZS','http://www.amazon.com/dp/B00J8IJIZS',200,'stock:In Stock.,price:$138.90,seller:StoreVip,material:{Dial window material type:Mineral,Case Material:Resin,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41AM%2BMy2oNL.jpg}',1,'2014-11-22 16:42:58','2014-11-22 16:42:58'),(60,'Marc by Marc Jacobs MBM3156 Mens Rose Gold Rock Chronograph Watch','B008N8X5TE','http://www.amazon.com/dp/B008N8X5TE',200,'stock:Only 3 left in stock.,price:$253.02,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51-RsYp-I4L.jpg}',1,'2014-11-22 16:42:59','2014-11-22 16:42:59'),(61,'Marc by Marc Jacobs Baby Dave Ivory Dial White Leather Unisex Watch MBM1260','B00AJGKG5O','http://www.amazon.com/dp/B00AJGKG5O',200,'stock:Only 2 left in stock.,price:$119.99,seller:BSD Jewelry And Watches,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41N5HNsjSVL.jpg}',1,'2014-11-22 16:43:01','2014-11-22 16:43:01'),(62,'Marc Jacobs MBM5029 Men\'s Rock Gunmetal Grey Stainless Steel Grey Dial Chronograph Watch','B0085G4LH4','http://www.amazon.com/dp/B0085G4LH4',200,'stock:Only 1 left in stock.,price:$289.99,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless steel-plated},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51iUbnSjRNL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41O8YevW69L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41Cog31gqOL.jpg}',1,'2014-11-22 16:43:03','2014-11-22 16:43:03'),(63,'Marc by Marc Jacobs MBM3255 Mens Black IP Henry Skeleton Watch','B00EDOAJ5U','http://www.amazon.com/dp/B00EDOAJ5U',200,'stock:Only 2 left in stock.,price:$194.99,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/418mXgyJfPL.jpg}',1,'2014-11-22 16:43:05','2014-11-22 16:43:05'),(64,'Marc by Marc Men\'s MBM3320 The Slim Red Dial Face Analog Watch','B00MBV91H4','http://www.amazon.com/dp/B00MBV91H4',200,'stock:Only 2 left in stock.,price:$126.95,seller:StoreVip,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Stainless steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41xZYcgRedL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/410C7O1UpZL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41sQQGBw1wL.jpg}',1,'2014-11-22 16:43:06','2014-11-22 16:43:06'),(65,'Marc by Marc Jacobs MBM2049 Black','B007X5ACPI','http://www.amazon.com/dp/B007X5ACPI',200,'stock:In stock.,price:$160.53,material:{Dial window material type:Mineral,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41f9oJNLVGL.jpg}',1,'2014-11-22 16:43:09','2014-11-22 16:43:09'),(66,'Marc Jacobs Henry Charcoal Dial Black Leather Unisex Watch MBM1216','B007CONSXS','http://www.amazon.com/dp/B007CONSXS',200,'stock:Available from <a href=\'/gp/offer-listing/B007CONSXS/ref=dp_olp_0?ie=UTF8&amp;condition=all\'>these sellers</a>.,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Gunmetal Ion-plated Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/411NccqCiWL.jpg}',1,'2014-11-22 16:43:10','2014-11-22 16:43:10'),(67,'Marc by Marc Jacobs MBM5055 Mens Blue Steel Rock Watch','B00I90GB56','http://www.amazon.com/dp/B00I90GB56',200,'stock:Only 5 left in stock.,price:$250.78,seller:Watch Grabber,material:{,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51QvJNr2aDL.jpg}',1,'2014-11-22 16:43:12','2014-11-22 16:43:12'),(68,'Marc by Marc Jacobs MBM1246 Mens Black Gold Henry Skeleton Watch','B00BCYMK38','http://www.amazon.com/dp/B00BCYMK38',200,'stock:Only 3 left in stock.,price:$174.77,material:{,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51LYmU7pUAL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31U82%2BasyBL.jpg}',1,'2014-11-22 16:43:14','2014-11-22 16:43:14'),(69,'Marc by Marc Jacobs Men\'s Rock Chronograph Watch, Gunmetal/Gunmetal, One Size','B00IHVH4IA','http://www.amazon.com/dp/B00IHVH4IA',200,'stock:Only 2 left in stock.,price:$229.01,seller:Watch Cove,material:{Dial window material type:Mineral,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/417Mq1jevUL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41%2BYl0ofv%2BL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41-aubb4oWL.jpg}',1,'2014-11-22 16:43:15','2014-11-22 16:43:15'),(70,'Marc by Marc Jacobs Baby Dave Champagne Dial Teal Leather Unisex Watch MBM1263','B00AJGK4U6','http://www.amazon.com/dp/B00AJGK4U6',200,'stock:Only 1 left in stock.,price:$165.00,material:{Dial window material type:Scratch Resistant Mineral,Case Material:Stainless Steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51wsOYn-ubL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51rxqdZTivL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51fBAaut3tL.jpg}',1,'2014-11-22 16:43:17','2014-11-22 16:43:17'),(71,'Marc by Marc Jacobs MBM1322 Mens Red Orange The Slim Watch','B00LH9G0DY','http://www.amazon.com/dp/B00LH9G0DY',200,'stock:Only 5 left in stock.,price:$147.65,seller:Watch Grabber,material:{Dial window material type:Mineral,Case Material:Stainless steel,Band Material:Leather},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41MdWyeE8-L.jpg}',1,'2014-11-22 16:43:18','2014-11-22 16:43:18'),(72,'Marc by Marc Jacobs Henry Silver Watch with Stretch Band MBM3236','B00CGAK20A','http://www.amazon.com/dp/B00CGAK20A',200,'stock:Only 1 left in stock.,price:$129.12,material:{Dial window material type:Mineral,Case Material:Silver Tone Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41zv3Mm1mCL.jpg}',1,'2014-11-22 16:43:20','2014-11-22 16:43:20'),(73,'Marc Jacobs Grey Silicone Steel Link Combo Bracelet Chronograph MBM2566','B0059DWM36','http://www.amazon.com/dp/B0059DWM36',200,'stock:Only 1 left in stock.,price:$161.12,material:{Dial window material type:Mineral,Band Material:Metal},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41rg-Su6qwL.jpg}',1,'2014-11-22 16:43:22','2014-11-22 16:43:22'),(74,'Mbm9042 his and hers stainless steel rose gold watch gift set','B00GWUOIZK','http://www.amazon.com/dp/B00GWUOIZK',200,'stock:Available from <a href=\'/gp/offer-listing/B00GWUOIZK/ref=dp_olp_0?ie=UTF8&amp;condition=all\'>these sellers</a>.,material:{,Band Material:Stainless steel rose gold},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/31FhiGrhyYL.jpg}',1,'2014-11-22 16:43:24','2014-11-22 16:43:24'),(75,'Marc by Marc Jacobs MBM8600 Women\'s Black &amp; Grey Dial Rubber &amp; Steel Bracelet Watch','B00D0MP0GY','http://www.amazon.com/dp/B00D0MP0GY',200,'stock:Only 2 left in stock.,price:$319.92,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Multi-material},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/51cQfq2uPdL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51n6%2BZA%2BQjL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/41QkVuef27L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51eJ3xJXu%2BL.jpg}',1,'2014-11-22 16:43:25','2014-11-22 16:43:25'),(76,'Marc Jacobs Royal Purple Silicone Strap, Dial &amp; Black Case Watch MBM5517','B0059DW0W4','http://www.amazon.com/dp/B0059DW0W4',200,'stock:Only 1 left in stock.,price:$195.00,seller:SurfNTime,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Silicone},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/411l15j4m7L.jpg}',1,'2014-11-22 16:43:27','2014-11-22 16:43:27'),(77,'Marc by Marc Jacobs Men\'s Larry Chronograph Watch, Black, One Size','B0094A4LR0','http://www.amazon.com/dp/B0094A4LR0',200,'stock:Only 1 left in stock.,price:$213.75,imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41D0%2Bmtp0-L.jpg,imgUrl:http://ecx.images-amazon.com/images/I/51D4tv88PtL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31kokcSn8aL.jpg,imgUrl:http://ecx.images-amazon.com/images/I/31a6nWp0EiL.jpg}',1,'2014-11-22 16:43:28','2014-11-22 16:43:28'),(78,'Marc by Marc Jacobs MBM2585 Rock Chronograph Watch','B007CONN38','http://www.amazon.com/dp/B007CONN38',200,'stock:Only 1 left in stock.,price:$239.95,material:{Dial window material type:Mineral,Case Material:Stainless Steel,Band Material:Stainless Steel},imgUrls:{,imgUrl:http://ecx.images-amazon.com/images/I/41dSYlEdaNL.jpg}',1,'2014-11-22 16:43:30','2014-11-22 16:43:30');
/*!40000 ALTER TABLE `amazon_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amazon_item_info`
--

DROP TABLE IF EXISTS `amazon_item_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazon_item_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `self_asin` varchar(255) DEFAULT NULL COMMENT '自身asin，颜色下的尺寸的asin',
  `parent_asin` varchar(255) DEFAULT NULL COMMENT '属于哪一个asin,不是页面进来的那个',
  `belong_asin` varchar(255) DEFAULT NULL COMMENT '对应于进入页面的，也就是item表的asin',
  `material` varchar(255) DEFAULT NULL COMMENT '材质，用json格式存储',
  `size` int(11) DEFAULT '0' COMMENT '尺寸',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片链接，多图时使用逗号隔开',
  `price` double DEFAULT '0' COMMENT '价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存数量',
  `seller` varchar(255) DEFAULT NULL COMMENT '卖家名字',
  `last_http_code` int(11) DEFAULT '0',
  `goods_type` enum('shoes','watch') DEFAULT NULL COMMENT '商品类型',
  `gmt_created` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazon_item_info`
--

LOCK TABLES `amazon_item_info` WRITE;
/*!40000 ALTER TABLE `amazon_item_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `amazon_item_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amazon_page`
--

DROP TABLE IF EXISTS `amazon_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazon_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `page_url` varchar(255) DEFAULT NULL COMMENT '翻页点击时候的url',
  `belongto_category_id` int(11) DEFAULT '0' COMMENT '属于哪一类',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazon_page`
--

LOCK TABLES `amazon_page` WRITE;
/*!40000 ALTER TABLE `amazon_page` DISABLE KEYS */;
INSERT INTO `amazon_page` VALUES (1,'http://www.amazon.com/s?ie=UTF8&page=1&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-22 16:41:03','2014-11-22 16:41:03'),(2,'http://www.amazon.com/s?ie=UTF8&page=2&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-22 16:41:03','2014-11-22 16:41:03'),(3,'http://www.amazon.com/s?ie=UTF8&page=1&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-25 22:09:45','2014-11-25 22:09:45'),(4,'http://www.amazon.com/s?ie=UTF8&page=2&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-25 22:09:45','2014-11-25 22:09:45'),(5,'http://www.amazon.com/s?ie=UTF8&page=1&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-25 22:10:56','2014-11-25 22:10:56'),(6,'http://www.amazon.com/s?ie=UTF8&page=2&rh=n%3A6358539011%2Ck%3AMARC%20BY%20MARC%20JACOBS',1,'2014-11-25 22:10:56','2014-11-25 22:10:56');
/*!40000 ALTER TABLE `amazon_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amazon_update_log`
--

DROP TABLE IF EXISTS `amazon_update_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amazon_update_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `update_category_id` int(11) NOT NULL,
  `update_goods_url` varchar(255) NOT NULL,
  `status` enum('completed','executing') DEFAULT 'executing' COMMENT '是否正在抓取数据',
  `update_time` datetime NOT NULL COMMENT '每次更新记录的时间',
  `gmt_created` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amazon_update_log`
--

LOCK TABLES `amazon_update_log` WRITE;
/*!40000 ALTER TABLE `amazon_update_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `amazon_update_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_kf_log`
--

DROP TABLE IF EXISTS `tab_kf_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_kf_log` (
  `user_id` bigint(20) NOT NULL,
  `work_status` smallint(10) NOT NULL DEFAULT '1',
  `create_date` datetime NOT NULL,
  `remarks` varchar(2000) DEFAULT NULL,
  `kflog_id` bigint(255) NOT NULL AUTO_INCREMENT,
  `del_status` smallint(6) NOT NULL,
  PRIMARY KEY (`kflog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_kf_log`
--

LOCK TABLES `tab_kf_log` WRITE;
/*!40000 ALTER TABLE `tab_kf_log` DISABLE KEYS */;
INSERT INTO `tab_kf_log` VALUES (1,2,'2014-04-25 00:00:00','上夜班上夜班上夜班上夜班上夜班上夜班上夜班上夜班上夜班上夜班上夜班上夜班上',1,0),(1,1,'2014-04-24 09:00:00','上白班上白班上白班上白班上白班上白班上白班上白班上白班上白班上白班上白班上白班',2,0),(1,1,'2014-04-24 09:00:00','999',3,0),(1,1,'2014-03-28 16:21:29','',5,1),(1,2,'2014-04-29 17:26:31','额顶顶顶顶顶',6,1),(1,2,'2014-04-29 17:33:14','2635687974',7,1),(1,1,'2014-04-30 09:20:23','反反复',8,1),(1,1,'2014-04-30 12:37:01','111111111111111',9,1),(1,1,'2014-10-03 05:33:33','8797978',10,0);
/*!40000 ALTER TABLE `tab_kf_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_permission`
--

DROP TABLE IF EXISTS `tab_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_permission` (
  `permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='ȨОҭ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_permission`
--

LOCK TABLES `tab_permission` WRITE;
/*!40000 ALTER TABLE `tab_permission` DISABLE KEYS */;
INSERT INTO `tab_permission` VALUES (1,'用户管理','YHGL'),(2,'退款记录管理','TKJLGL'),(3,'财付通订单管理','CFTGL'),(4,'修改用户信息','YHGLX'),(5,'删除用户','YHGLS'),(6,'添加用户','YHGLT'),(7,'退款记录管理审核','TKJLGLS');
/*!40000 ALTER TABLE `tab_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_role`
--

DROP TABLE IF EXISTS `tab_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user_id` bigint(20) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='އɫҭ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_role`
--

LOCK TABLES `tab_role` WRITE;
/*!40000 ALTER TABLE `tab_role` DISABLE KEYS */;
INSERT INTO `tab_role` VALUES (1,1,'超级管理员','administrator','2014-03-28 16:26:33'),(2,1,'一般用户','customerservice','2014-03-28 16:30:03');
/*!40000 ALTER TABLE `tab_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_role_permission`
--

DROP TABLE IF EXISTS `tab_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_role_permission` (
  `role_permission_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='އɫȨОژjҭ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_role_permission`
--

LOCK TABLES `tab_role_permission` WRITE;
/*!40000 ALTER TABLE `tab_role_permission` DISABLE KEYS */;
INSERT INTO `tab_role_permission` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,2),(5,2,3),(7,1,4),(8,1,5),(9,1,6),(10,1,7),(11,2,1);
/*!40000 ALTER TABLE `tab_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_user`
--

DROP TABLE IF EXISTS `tab_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `status` smallint(6) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='TAB_USER,ԃۧҭ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_user`
--

LOCK TABLES `tab_user` WRITE;
/*!40000 ALTER TABLE `tab_user` DISABLE KEYS */;
INSERT INTO `tab_user` VALUES (1,'管理员','e10adc3949ba59abbe56e057f20f883e','2014-03-28 16:21:29',0),(46,'测试2','e10adc3949ba59abbe56e057f20f883e','2014-06-16 16:06:44',0),(47,'测试4','e10adc3949ba59abbe56e057f20f883e','2014-06-16 16:06:44',0),(48,'测试1','e10adc3949ba59abbe56e057f20f883e','2014-07-28 11:29:47',1),(49,'test','e10adc3949ba59abbe56e057f20f883e','2014-09-14 15:58:20',1),(50,'蓝伟康','7fc3ee9aad33036cc501b2ca7c163fc1','2014-10-03 05:31:20',1);
/*!40000 ALTER TABLE `tab_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tab_user_role`
--

DROP TABLE IF EXISTS `tab_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tab_user_role` (
  `user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='ԃۧއɫژjҭ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tab_user_role`
--

LOCK TABLES `tab_user_role` WRITE;
/*!40000 ALTER TABLE `tab_user_role` DISABLE KEYS */;
INSERT INTO `tab_user_role` VALUES (1,1,1),(2,2,2),(3,2,1),(4,3,1),(5,2,2),(6,3,2),(7,4,2),(8,5,2),(9,6,2),(10,7,2),(11,8,2),(12,9,2),(13,10,2),(14,11,2),(15,12,2),(16,13,2),(17,14,2),(18,15,2),(19,16,2),(20,17,2),(21,19,2),(22,20,2),(23,22,2),(28,32,2),(29,32,2),(30,34,2),(31,34,2),(32,36,2),(33,36,2),(34,38,2),(35,38,2),(40,47,2),(41,48,2),(42,50,1),(45,46,2);
/*!40000 ALTER TABLE `tab_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-02  0:55:45
