DROP DATABASE IF EXISTS `otpdatabase`;
CREATE DATABASE `otpdatabase`;
USE `otpdatabase`;

CREATE TABLE IF NOT EXISTS `otp` (
  `id` int(11) NOT NULL,
  `otp` varchar(10) NOT NULL,
  `time` timestamp NOT NULL,
  `user` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP EVENT IF EXISTS `delete_otps`;
CREATE EVENT `delete_otps` 
ON schedule EVERY 10 SECOND ENABLE

DO
	DELETE FROM `otpdatabase`.`otp` WHERE `time` < current_timestamp() - INTERVAL 10 second;