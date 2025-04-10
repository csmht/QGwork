/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.22 : Database - user
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `user`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `main` int NOT NULL AUTO_INCREMENT,
  `course` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xf` int NOT NULL,
  `pd` tinyint(1) NOT NULL DEFAULT '0',
  `max` int NOT NULL DEFAULT '4',
  PRIMARY KEY (`main`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`main`,`course`,`xf`,`pd`,`max`) values (1,'高等数学',3,1,4),(2,'大学物理',2,0,4),(3,'线性代数',2,0,4),(4,'离散数学',2,0,4),(5,'c程序',2,0,4),(6,'元素反应论',1,0,4);

/*Table structure for table `empty` */

DROP TABLE IF EXISTS `empty`;

CREATE TABLE `empty` (
  `empty` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`empty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `empty` */

/*Table structure for table `less` */

DROP TABLE IF EXISTS `less`;

CREATE TABLE `less` (
  `main` int NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL COMMENT '学号',
  `course` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程',
  PRIMARY KEY (`main`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `less` */

insert  into `less`(`main`,`id`,`course`) values (1,'0','高等数学'),(7,'111','元素反应论'),(8,'112','离散数学'),(9,'0','离散数学');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `main` int NOT NULL AUTO_INCREMENT,
  `id` varchar(10) NOT NULL,
  `num` varchar(11) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`main`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`main`,`id`,`num`,`name`) values (1,'0','0','测试'),(9,'111','13888888','刻晴'),(10,'112','1355555','张三');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `main` int NOT NULL AUTO_INCREMENT,
  `id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `mima` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`main`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`main`,`id`,`mima`) values (1,'0','0'),(9,'111','666'),(10,'112','888');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
