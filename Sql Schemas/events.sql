-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 23, 2013 at 12:29 PM
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
-- Table structure for table `events`
--

CREATE TABLE IF NOT EXISTS `events` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `society_id` int(10) unsigned NOT NULL,
  `name` text NOT NULL,
  `when` date NOT NULL,
  `where` text NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `society_id` (`society_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`id`, `society_id`, `name`, `when`, `where`, `type`) VALUES
(1, 1, 'Public Speaking Showdown', '2016-02-20', 'Level 5, Sherfield Building', 'Public'),
(2, 1, 'Juggling Classes', '2013-02-28', 'Junior Common Room (JCR)', 'Public'),
(3, 1, 'Rotary Club Session', '2013-03-06', 'Lecture Theatre 311, Huxley Building', 'Private'),
(5, 1, 'sdfa', '2013-02-18', 'asdf', 'Public'),
(7, 1, 'sdgdfgas', '2015-03-16', 'asfasdghggg', 'Private'),
(8, 1, 'Hello Test', '2015-05-19', 'Sherfield', 'Public'),
(9, 1, 'dddd', '2013-02-18', 'asdf', 'Public'),
(10, 1, 'Test', '2013-02-20', 'Huxley', 'Private'),
(11, 1, 'Test1', '2013-04-20', 'Huxley', 'Private'),
(12, 1, 'new test', '2013-03-10', 'new test', 'Private'),
(13, 1, 'a', '2013-03-19', 's', 'Public'),
(14, 2, 'First associated event!', '2013-03-21', 'Huxley 344', 'Private'),
(15, 3, 'Debate', '2013-03-21', 'Huxley 311', 'Public'),
(16, 3, 'test', '2013-03-21', 'Testing', 'Public'),
(17, 3, 'a', '2013-04-21', 'a', 'Public'),
(18, 3, 'a', '2013-04-21', 'sdfasd', 'Public'),
(19, 3, 'test2', '2013-03-21', 'test2', 'Private'),
(20, 2, 'Juggle mania', '2013-03-21', 'Huxley', 'Public'),
(21, 2, 'sdf', '2013-03-21', 'asdf', 'Public'),
(22, 2, 'adf', '2013-03-21', 'sdf', 'Public'),
(23, 3, 'Speech', '2013-03-21', 'hUXLEY', 'Private'),
(24, 1, 'ef', '2013-05-21', 'ef', 'Public'),
(29, 1, 'dd', '2013-05-21', 'ss', 'Public'),
(30, 1, 'dd', '2013-05-21', 'ss', 'Public'),
(31, 1, 'ff', '2013-05-21', 'ffs', 'Public'),
(32, 1, 'asdfsdf', '2013-05-21', 'sdafds', 'Public'),
(33, 1, 'pswork', '2013-05-21', 'pswork', 'Public'),
(34, 1, '22', '2013-05-21', '22', 'Public'),
(35, 1, 'for loop', '2013-05-21', 'for loop', 'Public'),
(36, 1, 'just 2', '2013-05-21', 'just 2', 'Public'),
(37, 1, 'just 1', '2013-05-21', 'just 1', 'Public'),
(38, 1, 'sdfdsa', '2013-05-21', 'sdfa', 'Public'),
(39, 1, 'sdfdsa', '2013-05-21', 'sdfa', 'Public'),
(40, 1, 'fgdsfg', '2013-05-21', 'dsfds', 'Public'),
(46, 1, 'ffffff', '2013-05-21', 'dfds', 'Public'),
(47, 1, 'please', '2013-05-21', 'please', 'Public'),
(48, 1, 'check again', '2013-05-22', 'check again', 'Public');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `events_ibfk_1` FOREIGN KEY (`society_id`) REFERENCES `societies` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
