-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.25-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for testglobant
CREATE DATABASE IF NOT EXISTS `testglobant` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testglobant`;


-- Dumping structure for table testglobant.tokenmanagement
CREATE TABLE IF NOT EXISTS `tokenmanagement` (
  `Token_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) DEFAULT NULL,
  `ExpTime` bigint(20) DEFAULT NULL,
  `StartTime` bigint(20) DEFAULT NULL,
  `TokenGenrated` varchar(50) DEFAULT NULL COMMENT 'based on this we can i dentify the user',
  `Status` varchar(10) DEFAULT NULL COMMENT 'Active or deactive .. If user wants to end session ',
  PRIMARY KEY (`Token_Id`),
  KEY `FK_tokenmanagement_users` (`User_Id`),
  CONSTRAINT `FK_tokenmanagement_users` FOREIGN KEY (`User_Id`) REFERENCES `users` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table testglobant.tokenmanagement: ~13 rows (approximately)
/*!40000 ALTER TABLE `tokenmanagement` DISABLE KEYS */;
INSERT IGNORE INTO `tokenmanagement` (`Token_Id`, `User_Id`, `ExpTime`, `StartTime`, `TokenGenrated`, `Status`) VALUES
	(1, 1, 1, 1, '1', '0'),
	(2, 1, 1457992350671, 1457992348871, 'P5YXGZWE91YODJAJ4', '0'),
	(3, 1, 1457992840818, 1457992839018, 'EECWIJVFW4RQDIBMK', '0'),
	(4, 1, 1457994704662, 1457992904662, '6RTMKG1JN54MSPC40', '0'),
	(5, 1, 1457995624230, 1457993824230, '1JNA5L0MRPEOAL3RU', '0'),
	(6, 1, 1457995893696, 1457994093696, 'PZSSK2FTVLABEJYC4', '1'),
	(7, 2, 1457995919359, 1457994119359, 'CB55GXR7Z7L5DVPBJ', '0'),
	(8, 2, 1458006229728, 1458004429728, 'VC82QCILJHINGVZJO', '0'),
	(9, 2, 1458006316952, 1458004516952, '786LVVGGE48NK9DT9', '1'),
	(10, 12, 1458006335503, 1458004535503, '90KBU1TOBNU0LWY59', '0'),
	(11, 14, 1458006480971, 1458004680971, 'O0PJ5OVGYVUKVAWUO', '1'),
	(12, 12, 1458006494686, 1458004694686, 'UJHESOGQ3H399RSLT', '0'),
	(13, 12, 1458024084068, 1458022284068, 'Q8VK2C9FUUJ9O65W3', '1');
/*!40000 ALTER TABLE `tokenmanagement` ENABLE KEYS */;


-- Dumping structure for table testglobant.users
CREATE TABLE IF NOT EXISTS `users` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `Enable` tinyint(4) NOT NULL DEFAULT '1',
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Gender` varchar(50) DEFAULT NULL,
  `Profession` varchar(50) DEFAULT NULL,
  `GivenName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- Dumping data for table testglobant.users: ~15 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`UserId`, `UserName`, `Password`, `Enable`, `City`, `State`, `Gender`, `Profession`, `GivenName`) VALUES
	(1, 'nikunj1', '123123', 1, 'plano', 'Ca', 'Male', 'Engg', 'Nikunj Mange'),
	(2, 'nikunj2', '123123', 1, 'Dallas', 'Tn', 'Female', 'Doctor', 'Raj bhanushali'),
	(3, 'nikunj9', '123123', 1, 'Arlington', 'Tx', 'Male', 'Teacher', 'Jay shah'),
	(4, 'nikunj3', '123123', 1, 'Dallas', 'Tn', 'Female', 'Engg', 'nikunj3'),
	(5, 'nikunj7', '123123', 1, 'Dallas', 'Tx', 'Female', 'Doctor', 'Raj bhanushali'),
	(6, 'nikunj6', '123123', 1, 'Dallas', 'Tn', 'Male', 'Engg', 'Raj bhanushali'),
	(7, 'nikunj8', '123123', 1, 'Dallas', 'Tn', 'Female', 'Engg', 'Raj bhanushali'),
	(8, 'nikunj10', '123123', 1, 'Arlington', 'Tx', 'Male', 'Doctor', 'Jay shah'),
	(9, 'nikunj11', '123123', 1, 'Arlington', 'Tx', 'Female', 'Teacher', 'Jay shah'),
	(10, 'nikunj12', '123123', 1, 'Arlington', 'Tx', 'Male', 'Engg', 'Jay shah'),
	(11, 'nikunj13', '123123', 1, 'Arlington', 'Tx', 'Female', 'Teacher', 'Jay shah'),
	(12, 'nikunj14', '123123', 1, 'Arlington', 'Tx', 'Male', 'Engg', 'Jay shah'),
	(13, 'nikunj5', '123123', 1, 'Dallas', 'Tn', 'Male', 'Doctor', 'Raj bhanushali13'),
	(14, 'nikunj15', '123123', 1, 'Dallas', 'Tn', 'Male', 'Teacher', 'Raj bhanushali21'),
	(15, 'nikunj4', '123123', 1, 'Dallas', 'Tn', 'Male', 'Teacher', 'Raj bhanushali');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
