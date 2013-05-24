-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 23, 2013 at 12:30 PM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `event_features`
--

CREATE TABLE IF NOT EXISTS `event_features` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `event_id` int(10) unsigned NOT NULL,
  `feature` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Dumping data for table `event_features`
--

INSERT INTO `event_features` (`id`, `event_id`, `feature`) VALUES
(1, 24, 2),
(2, 29, 2),
(3, 30, 2),
(4, 31, 1),
(5, 32, 1),
(6, 32, 1),
(7, 33, 1),
(8, 33, 2),
(9, 34, 2),
(10, 35, 1),
(11, 35, 2),
(12, 35, 3),
(13, 36, 0),
(14, 36, 2),
(15, 36, 0),
(16, 37, 1),
(17, 37, 0),
(18, 37, 0),
(19, 38, 0),
(20, 38, 0),
(21, 38, 3),
(22, 39, 0),
(23, 39, 0),
(24, 39, 3),
(25, 40, 1),
(26, 40, 0),
(27, 40, 0),
(28, 46, 0),
(29, 46, 2),
(30, 46, 3),
(31, 47, 2),
(32, 48, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `event_features`
--
ALTER TABLE `event_features`
  ADD CONSTRAINT `event_features_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
