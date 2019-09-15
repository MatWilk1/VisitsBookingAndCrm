DROP DATABASE IF EXISTS `visits-booking-and-crm`;

CREATE DATABASE `visits-booking-and-crm`;

use `visits-booking-and-crm`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `visit`;

CREATE TABLE `visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL,
  `customer_id` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_CUSTOMER_idx` (`customer_id`),
  
  CONSTRAINT `FK_CUSTOMER` 
  FOREIGN KEY (`customer_id`) 
  REFERENCES `customer` (`id`) 
  
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `users` 
VALUES 
('admin1','{bcrypt}$2a$10$K2BuwRIMoA6jyxmb2u.kqejEXilupqcMg1QdR/c6VOe/pD67KdXry',1),
('admin2','{bcrypt}$2a$10$qclEFjsaQGT2qkQVW6VaGeOPhHnBVEG.zqgs88zxvIiOPkMa9DNVu',1);


DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `authorities` 
VALUES 
('admin1','ROLE_ADMIN'),
('admin2','ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS = 1;
