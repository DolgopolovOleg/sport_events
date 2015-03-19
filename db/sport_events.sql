/*
SQLyog Ultimate v9.50 
MySQL - 5.5.23 : Database - sport_events
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sport_events` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sport_events`;

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `text` text,
  `created` datetime DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `from_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `Users_id` (`user_id`),
  CONSTRAINT `Users_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`_id`,`user_id`,`text`,`created`,`from`,`from_id`) values (1,1,'sdfgsdfg','2014-12-14 23:46:36','PLACE',4),(2,2,'sdfgsdfg','2014-12-13 23:46:33','PLACE',4);

/*Table structure for table `equipment` */

DROP TABLE IF EXISTS `equipment`;

CREATE TABLE `equipment` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `icon` char(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `equipment` */

insert  into `equipment`(`_id`,`username`,`icon`) values (1,'Турник','icon1'),(2,'Брусья','icon2'),(3,'Футбольное поле','icon3'),(4,'Баскетбольное поле','icon4'),(5,'Тенисный стол','icon5');

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `sport` int(11) DEFAULT NULL,
  `description` text,
  `place_id` int(11) DEFAULT NULL,
  `date_start` datetime DEFAULT NULL,
  `date_finish` datetime DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `Sport_rel` (`sport`),
  KEY `Place_rel` (`place_id`),
  CONSTRAINT `Places_id` FOREIGN KEY (`place_id`) REFERENCES `place` (`_id`),
  CONSTRAINT `Sport_rel` FOREIGN KEY (`sport`) REFERENCES `sport` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `event` */

insert  into `event`(`_id`,`username`,`sport`,`description`,`place_id`,`date_start`,`date_finish`) values (1,'first',1,'Some description to event',4,'2014-12-16 22:13:27','2014-12-16 22:13:29');

/*Table structure for table `participant` */

DROP TABLE IF EXISTS `participant`;

CREATE TABLE `participant` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `role` enum('ORGANIZER','PARTICIPANT') DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `Eventid_rel` (`event_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `event_id` FOREIGN KEY (`event_id`) REFERENCES `event` (`_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `participant` */

insert  into `participant`(`_id`,`user_id`,`event_id`,`role`) values (2,1,1,'ORGANIZER'),(3,2,1,'PARTICIPANT');

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `description` text,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`),
  KEY `creator_id` (`creator`),
  CONSTRAINT `creator_id` FOREIGN KEY (`creator`) REFERENCES `user` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `place` */

insert  into `place`(`_id`,`username`,`longitude`,`latitude`,`description`,`creator`) values (4,'first',111,111,'Some description to place',2),(5,'second',222,222,'asdasdasd',1);

/*Table structure for table `place_equipment` */

DROP TABLE IF EXISTS `place_equipment`;

CREATE TABLE `place_equipment` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `pl_id` int(11) NOT NULL,
  `eq_id` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `Equipment_id` (`eq_id`),
  KEY `place_id` (`pl_id`),
  CONSTRAINT `equipment_id` FOREIGN KEY (`eq_id`) REFERENCES `equipment` (`_id`),
  CONSTRAINT `place_id` FOREIGN KEY (`pl_id`) REFERENCES `place` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `place_equipment` */

insert  into `place_equipment`(`_id`,`pl_id`,`eq_id`,`count`) values (1,4,1,2),(2,4,2,1),(3,4,3,1);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` enum('ADMIN','USER') DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`_id`,`title`) values (1,'ADMIN'),(2,'USER');

/*Table structure for table `sport` */

DROP TABLE IF EXISTS `sport`;

CREATE TABLE `sport` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sport` */

insert  into `sport`(`_id`,`username`,`icon`) values (1,'Футбол','icon'),(2,'Баскетбол','icon');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `sname` char(255) DEFAULT NULL,
  `nickname` char(255) DEFAULT NULL,
  `phone` char(31) DEFAULT NULL,
  `email` char(255) DEFAULT NULL,
  `username` char(255) DEFAULT NULL,
  `password` char(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`_id`,`username`,`sname`,`nickname`,`phone`,`email`,`username`,`password`,`enabled`) values (1,'Oleg','Dolgopolov','Allega','123123','allegameex@yandex.ru','oleg','202cb962ac59075b964b07152d234b70',1),(2,'Second','Second sname','snickname','123123','email','second','202cb962ac59075b964b07152d234b70',1);

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values (1,1),(1,2),(2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
