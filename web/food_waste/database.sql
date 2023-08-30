/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.9 : Database - foodwaste_management
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`foodwaste_management` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `foodwaste_management`;

/*Table structure for table `charity` */

DROP TABLE IF EXISTS `charity`;

CREATE TABLE `charity` (
  `charity_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `charity_name` varchar(55) DEFAULT NULL,
  `latitude` varchar(55) DEFAULT NULL,
  `longitude` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`charity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `charity` */

insert  into `charity`(`charity_id`,`login_id`,`charity_name`,`latitude`,`longitude`,`phone`,`email`) values (1,4,'thanal','9.986088818486847','76.29931826464845','7594869862','thanal@gmail.com'),(2,5,'kaniv','9.974345325387674','76.2839126586914','9087654312','kaniv@gmail.com');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `sender_type` varchar(55) DEFAULT NULL,
  `feedback_description` varchar(55) DEFAULT NULL,
  `date` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`feedback_id`,`sender_id`,`sender_type`,`feedback_description`,`date`) values (1,2,'charity','thanks ','2020-03-30');

/*Table structure for table `food_availability` */

DROP TABLE IF EXISTS `food_availability`;

CREATE TABLE `food_availability` (
  `availability_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) DEFAULT NULL,
  `provider_type` varchar(55) DEFAULT NULL,
  `latitude` varchar(55) DEFAULT NULL,
  `longitude` varchar(55) DEFAULT NULL,
  `date_time` varchar(55) DEFAULT NULL,
  `status` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`availability_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `food_availability` */

insert  into `food_availability`(`availability_id`,`provider_id`,`provider_type`,`latitude`,`longitude`,`date_time`,`status`) values (1,0,'Guest','9.9786543','76.7804','2020-03-30 12:27:21','available'),(2,1,'hotel','9.98649198673019','76.29176516406251','2020-03-30','available'),(3,0,'Guest','9.9786543','76.7804','2020-03-30 12:58:49','available');

/*Table structure for table `food_distribution` */

DROP TABLE IF EXISTS `food_distribution`;

CREATE TABLE `food_distribution` (
  `distribution_id` int(11) NOT NULL AUTO_INCREMENT,
  `volunteer_id` int(11) DEFAULT NULL,
  `pick_up_id` int(11) DEFAULT NULL,
  `pick_up_type` varchar(55) DEFAULT NULL,
  `pick_up_date_time` varchar(55) DEFAULT NULL,
  `charity_id` int(11) DEFAULT NULL,
  `distributed_date_time` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`distribution_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `food_distribution` */

insert  into `food_distribution`(`distribution_id`,`volunteer_id`,`pick_up_id`,`pick_up_type`,`pick_up_date_time`,`charity_id`,`distributed_date_time`) values (1,1,0,'Guest','2020-03-30 12:37:37',1,'2020-03-30 12:37:37'),(2,1,0,'Guest','2020-03-30 12:46:40',2,'2020-03-30 12:46:40');

/*Table structure for table `food_request` */

DROP TABLE IF EXISTS `food_request`;

CREATE TABLE `food_request` (
  `request_id` int(11) NOT NULL AUTO_INCREMENT,
  `charity_id` int(11) DEFAULT NULL,
  `date_time` varchar(55) DEFAULT NULL,
  `quantity_required` varchar(55) DEFAULT NULL,
  `request_status` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `food_request` */

insert  into `food_request`(`request_id`,`charity_id`,`date_time`,`quantity_required`,`request_status`) values (1,1,'2020-03-30 12:26:48','100','request'),(3,2,'2020-03-30 12:35:17','150','handover');

/*Table structure for table `hotels` */

DROP TABLE IF EXISTS `hotels`;

CREATE TABLE `hotels` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `hotel_name` varchar(55) DEFAULT NULL,
  `latitude` varchar(55) DEFAULT NULL,
  `longitude` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `hotels` */

insert  into `hotels`(`hotel_id`,`login_id`,`hotel_name`,`latitude`,`longitude`,`phone`,`email`) values (1,3,'hotel','9.980236688935248','76.29871744982911','1234567890','hotel@g.com');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,
  `usertype` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values (1,'admin','admin','admin'),(2,'vv','vv','volunteer'),(3,'hotel','hotel','hotel'),(4,'thanal','thanal','charity'),(5,'kaniv','kaniv','charity');

/*Table structure for table `refrigerators` */

DROP TABLE IF EXISTS `refrigerators`;

CREATE TABLE `refrigerators` (
  `ref_id` int(11) NOT NULL AUTO_INCREMENT,
  `latitude` varchar(55) DEFAULT NULL,
  `longitude` varchar(55) DEFAULT NULL,
  `food_status` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`ref_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `refrigerators` */

insert  into `refrigerators`(`ref_id`,`latitude`,`longitude`,`food_status`) values (1,'9.970456766128144','76.29910469055176','Handover'),(2,'9.986576516958147','76.28095049731446','available');

/*Table structure for table `volunteers` */

DROP TABLE IF EXISTS `volunteers`;

CREATE TABLE `volunteers` (
  `volunteer_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(55) DEFAULT NULL,
  `last_name` varchar(55) DEFAULT NULL,
  `gender` varchar(55) DEFAULT NULL,
  `age` varchar(55) DEFAULT NULL,
  `phone` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`volunteer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `volunteers` */

insert  into `volunteers`(`volunteer_id`,`login_id`,`first_name`,`last_name`,`gender`,`age`,`phone`,`email`) values (1,2,'vv','vv','Male','30','9988776655','vv@g.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
