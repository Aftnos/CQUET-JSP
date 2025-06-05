-- MySQL dump 10.13  Distrib 8.0.35, for macos13 (arm64)
--
-- Host: localhost    Database: emarket_mall
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '部门名称',
  `phoneNum` varchar(255) DEFAULT NULL COMMENT '联系方式',
  `leader` varchar(255) NOT NULL COMMENT '部门负责人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_cart`
--

DROP TABLE IF EXISTS `order_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_cart` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `productId` varchar(255) NOT NULL COMMENT '产品ID',
  `amount` int NOT NULL COMMENT '产品数量',
  `price` decimal(24,6) NOT NULL COMMENT '商品价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_cart`
--

LOCK TABLES `order_cart` WRITE;
/*!40000 ALTER TABLE `order_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '订单详情ID',
  `orderId` int NOT NULL COMMENT '订单ID',
  `productId` int NOT NULL COMMENT '产品ID',
  `productName` varchar(255) NOT NULL COMMENT '产品名称',
  `amount` int NOT NULL COMMENT '产品数量',
  `productPrice` decimal(24,6) NOT NULL COMMENT '产品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `orderNum` varchar(255) NOT NULL COMMENT '订单编号',
  `userId` int NOT NULL COMMENT '顾客（下单人）ID',
  `shippingUser` varchar(255) NOT NULL COMMENT '收货人姓名',
  `address` varchar(255) NOT NULL COMMENT '收获地址',
  `paymentMethod` int DEFAULT NULL COMMENT '支付方式：1现金，2余额，3网银，4支付宝，5微',
  `orderMoney` decimal(24,6) NOT NULL COMMENT '订单价格',
  `shippingMoney` decimal(24,6) DEFAULT NULL COMMENT '快递金额',
  `districtMoney` decimal(24,6) DEFAULT '0.000000' COMMENT '优惠金额',
  `paymentMoney` decimal(24,6) NOT NULL COMMENT '支付金额',
  `payTime` datetime NOT NULL COMMENT '支付时间',
  `receiveTime` datetime DEFAULT NULL COMMENT '收获时间',
  `orderStatus` int DEFAULT NULL COMMENT '订单状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `categoryName` varchar(255) NOT NULL COMMENT '分类名称',
  `categoryCode` varchar(255) NOT NULL COMMENT '分类编码',
  `parentId` int DEFAULT NULL COMMENT '父ID',
  `categoryLevel` int DEFAULT NULL COMMENT '分类等级',
  `categoryStatus` int DEFAULT NULL COMMENT '分类状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_info`
--

DROP TABLE IF EXISTS `product_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_info` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `productCore` varchar(255) NOT NULL COMMENT '商品编码',
  `productName` varchar(255) NOT NULL COMMENT '商品名称',
  `oneCategoryId` int NOT NULL COMMENT '一级分类ID',
  `twoCategoryId` int DEFAULT NULL COMMENT '二级分类ID',
  `price` decimal(24,6) NOT NULL COMMENT '商品价格',
  `publishStatus` int DEFAULT NULL COMMENT '上下架状态',
  `productionDate` datetime NOT NULL COMMENT '生成日期',
  `shelfLife` int DEFAULT NULL COMMENT '商品有效期',
  `description` varchar(900) DEFAULT NULL COMMENT '商品描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_info`
--

LOCK TABLES `product_info` WRITE;
/*!40000 ALTER TABLE `product_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_pic`
--

DROP TABLE IF EXISTS `product_pic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_pic` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `productId` int NOT NULL COMMENT '商品ID',
  `picDesc` varchar(255) DEFAULT NULL COMMENT '图片描述',
  `picUrl` varchar(255) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_pic`
--

LOCK TABLES `product_pic` WRITE;
/*!40000 ALTER TABLE `product_pic` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_pic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_info`
--

DROP TABLE IF EXISTS `staff_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_info` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `loginName` varchar(255) NOT NULL COMMENT '登录名',
  `name` varchar(255) NOT NULL COMMENT '员工姓名',
  `staffType` varchar(255) NOT NULL COMMENT '员工类型',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phoneNum` varchar(255) NOT NULL COMMENT '电话',
  `gender` varchar(255) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `status` int NOT NULL COMMENT '状态;帐号状态（0正常 1停用）',
  `deptId` int NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商家员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_info`
--

LOCK TABLES `staff_info` WRITE;
/*!40000 ALTER TABLE `staff_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '用户名',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `loginName` varchar(255) NOT NULL COMMENT '登录名',
  `avatar` varchar(900) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮件',
  `phone` varchar(255) NOT NULL COMMENT '电话',
  `password` varchar(255) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (0,'root','2025-06-03 00:00:00',NULL,NULL,NULL,1,'zhangsan','march','san',NULL,NULL,'13312345678','b38dc7b573909f4c8b428b0066dff385');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse_product`
--

DROP TABLE IF EXISTS `warehouse_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warehouse_product` (
  `delFlag` int DEFAULT NULL COMMENT '删除标记',
  `createdBy` varchar(32) DEFAULT NULL COMMENT '创建人',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updatedBy` varchar(32) DEFAULT NULL COMMENT '更新人',
  `updatedTime` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `productId` int NOT NULL COMMENT '产品ID',
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `currentAmount` int NOT NULL COMMENT '当前商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品库存';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse_product`
--

LOCK TABLES `warehouse_product` WRITE;
/*!40000 ALTER TABLE `warehouse_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'emarket_mall'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-05  9:55:12
