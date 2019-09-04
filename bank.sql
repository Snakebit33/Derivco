SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `bank`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE IF NOT EXISTS `accounts` (
  `nr` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `balance` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`nr`),
  UNIQUE KEY `nr` (`nr`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=92567262 ;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`nr`, `username`, `balance`) VALUES
(40880661, 'Tom', 23.54),
(47131599, 'Sara', 0),
(78940005, 'Tom', 1560),
(79167592, 'Jake', 200),
(86652967, 'Tom', 0),
(92567261, 'Jake', 3020);

-- --------------------------------------------------------

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `ix_auth_username` (`username`,`authority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('Jake', 'ROLE_USER'),
('Maria', 'ROLE_ADMIN'),
('Mike', 'ROLE_NEWUSER'),
('Sara', 'ROLE_NEWUSER'),
('Tom', 'ROLE_NEWUSER');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('Jake', 'e2a79383c1347633461eb9fb56cf4972a0155919', 1),
('Maria', '3e182b1ea9376483a38614d916a0b666ef531b6d', 1),
('Mike', 'd6ac022931a66a2bcc244db91818ebec76ce5e18', 1),
('Sara', '5d569dfc13001c8b30aa11eeb2a59d22071f3d80', 1),
('Tom', '26d58cf3df0903a2298788b72fced5bca9ea7144', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
