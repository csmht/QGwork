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
  `id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名字',
  `pd` tinyint(1) NOT NULL DEFAULT '0' COMMENT '课程状态，0是可选',
  `xf` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`pd`,`xf`) values ('hhh',0,5),('课程1',1,10),('课程2',1,0),('课程3',0,0),('课程4',0,0),('课程5',0,0),('课程6',0,0);

/*Table structure for table `less` */

DROP TABLE IF EXISTS `less`;

CREATE TABLE `less` (
  `student` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  KEY `student` (`student`),
  KEY `course` (`course`),
  CONSTRAINT `course` FOREIGN KEY (`course`) REFERENCES `course` (`id`),
  CONSTRAINT `student` FOREIGN KEY (`student`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `less` */

insert  into `less`(`student`,`course`) values ('student1','课程1'),('csmht','hhh'),('csmht','课程4'),('csmht','课程1'),('op','hhh'),('op','课程3');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` varchar(10) NOT NULL COMMENT '学号',
  `name` varchar(10) DEFAULT NULL COMMENT '名字',
  `num` varchar(12) DEFAULT NULL COMMENT '手机号',
  `xf` int DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`num`,`xf`) values ('csmht',NULL,NULL,NULL),('op',NULL,NULL,NULL),('stdent1',NULL,NULL,NULL),('student1','123','1235468',NULL),('student2','12333',NULL,NULL),('student3','444444',NULL,NULL),('student9',NULL,NULL,NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id666` int DEFAULT NULL COMMENT 'id',
  `mima` int NOT NULL DEFAULT '123456' COMMENT '密码',
  `id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  PRIMARY KEY (`id`),
  FULLTEXT KEY `student-id` (`id`),
  CONSTRAINT `hahaha` FOREIGN KEY (`id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id666`,`mima`,`id`) values (NULL,1,'op'),(NULL,12345,'stdent1'),(NULL,123456,'student1'),(NULL,123456,'student2'),(NULL,123456,'student3'),(NULL,666666,'student9');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
