-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2025 at 12:35 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `idm3project2024`
--
CREATE DATABASE IF NOT EXISTS `idm3project2024` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `idm3project2024`;

-- --------------------------------------------------------

--
-- Table structure for table `employer`
--

CREATE TABLE IF NOT EXISTS `employer` (
  `EmployerID` bigint(20) NOT NULL AUTO_INCREMENT,
  `additionalDoc` varchar(255) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL,
  `CreationDate` datetime DEFAULT NULL,
  `EmployerDescription` varchar(2000) DEFAULT NULL,
  `EmployerDescriptionSummary` varchar(255) DEFAULT NULL,
  `EmployerHeroImage` varchar(255) DEFAULT NULL,
  `EmployerName` varchar(255) NOT NULL,
  `notes` varchar(2000) DEFAULT NULL,
  `saved` bit(1) DEFAULT NULL,
  PRIMARY KEY (`EmployerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `ProjectID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProjectName` varchar(255) DEFAULT NULL,
  `ProjectDescription` varchar(500) NOT NULL,
  `UserID` bigint(20) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `projectDescriptionSummary` varchar(255) DEFAULT NULL,
  `ProjectHeroImage` varchar(255) DEFAULT NULL,
  `CreationDate` datetime NOT NULL DEFAULT current_timestamp(),
  `additionalDoc` varchar(255) DEFAULT NULL,
  `showcase_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ProjectID`),
  KEY `UserID` (`UserID`),
  KEY `FK4ow2o8mfch91jnyu44scyuj8h` (`showcase_id`),
  KEY `FKo06v2e9kuapcugnyhttqa1vpt` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`ProjectID`, `ProjectName`, `ProjectDescription`, `UserID`, `category`, `projectDescriptionSummary`, `ProjectHeroImage`, `CreationDate`, `additionalDoc`, `showcase_id`, `user_id`) VALUES
(1, 'sea life', 'The oceans are home to some of the most magnificent creatures both great and small, wild and wonderful. Yet human interference is damaging marine ecosystems around the world causing a vast decline in marine life and the health of the oceans. But there are a few inspiring individuals out there who have dedicated their lives to ocean conservation and helping the creatures of the great wild blue. ', 1, 'Digital Media', 'a look at sea life in winter', 'p1sea.jpg', '2023-09-22', 'x.doc', NULL, NULL),
(2, 'winter view', 'If you think wintertime is an excuse to put your camera away and stay warm indoors, then you need to read this article! Winter photography can be one of the most rewarding challenges in photography, whether you are interested in landscapes, cityscapes, seascapes, wildlife, or even portraits!\r\n\r\nIn this article, we will cover a variety of tips, ideas, and examples; everything from the technical to the creative! You might be surprised, too–some of these tips go against the conventional wisdom you’', 2, 'Digital Media', 'winter in the lock down', 'p2snow.jpg', '2023-09-23', 'y.doc', NULL, NULL),
(3, '3D cartoons', '3D character modeling is a graphic design technique that creates a three-dimensional digital representation of a surface or an object. Artists use specific software, start with a simple shape, and slowly enrich it with more details', 1, 'IT', 'a collection of 3D Disney characters', 'p3disney.jpg', '2023-09-23', 'x.doc', NULL, NULL),
(7, 'lizs best project', 'great project', 4, 'ART', 'more details', 'small_cute_cat.png', '2024-11-03', 'no', NULL, NULL),
(24, 'Man-in-the-Moon', 'What is organic chemistry? Organic chemistry is the field of chemistry over the study of organic substances and compounds – that is, those that contain carbon in their molecular structure, combined with other elements such as hydrogen, nitrogen, oxygen, and sulfur.', 1, 'organic chemistry', 'The Effect of Gamma Rays on Man-in-the-Moon Marigolds', 'p6artshowcase.jpg', '2023-09-23', 'p.doc', NULL, NULL),
(26, 'Autonomous Line-Following Robot', 'A programmable robot built using sensors and microcontrollers.', 1, 'Engineering', 'Sensor-based robotics project.', 'eng_robot.jpg', '2025-11-18', NULL, NULL, NULL),
(27, '3D Printed Prosthetic Hand', 'A low-cost prosthetic prototype designed using CAD tools.', 1, 'Engineering', 'Affordable prosthetic design.', 'prosthetic.jpg', '2025-11-18', NULL, NULL, NULL),
(28, 'Bridge Stress Test Simulation', 'A structural engineering simulation analyzing load capacity.', 1, 'Engineering', 'Load stress simulation.', 'bridge.jpg', '2025-11-18', NULL, NULL, NULL),
(29, 'Startup Market Analysis Report', 'A full market analysis for a hypothetical SaaS startup.', 1, 'Business', 'Market research project.', 'market.jpg', '2025-11-18', NULL, NULL, NULL),
(30, 'Brand Identity Redesign', 'A corporate rebranding strategy with logo and pitch deck.', 1, 'Business', 'Branding strategy.', 'brand.jpg', '2025-11-18', NULL, NULL, NULL),
(31, 'Financial Forecasting Dashboard', 'An Excel-based forecasting model for business planning.', 1, 'Business', 'Financial modelling.', 'finance.jpg', '2025-11-18', NULL, NULL, NULL),
(32, 'Character Concept Art Collection', 'A set of digital character concept designs.', 1, 'Art & Design', 'Concept art showcase.', 'conceptart.jpg', '2025-11-18', NULL, NULL, NULL),
(33, '3D Environment Scene', 'A stylised 3D environment created in Blender.', 1, 'Art & Design', '3D environment.', 'blender.jpg', '2025-11-18', NULL, NULL, NULL),
(34, 'Animated Short Clip', 'A 15-second animation demonstrating motion principles.', 1, 'Art & Design', 'Motion animation.', 'animation.jpg', '2025-11-18', NULL, NULL, NULL),
(35, 'Mobile Fitness Tracking App', 'An Android fitness-tracking app with step counter.', 1, 'Computing', 'Mobile app development.', 'fitness.jpg', '2025-11-18', NULL, NULL, NULL),
(36, 'Cybersecurity Password Auditor', 'A Java tool to evaluate password strength and security.', 1, 'Computing', 'Security auditing tool.', 'security.jpg', '2025-11-18', NULL, NULL, NULL),
(37, 'AI Image Classifier', 'A machine learning model that classifies images.', 1, 'Computing', 'AI classification model.', 'ai.jpg', '2025-11-18', NULL, NULL, NULL),
(38, 'Water Quality Testing Report', 'A study on local water quality and contaminant levels.', 1, 'Science', 'Environmental testing.', 'water.jpg', '2025-11-18', NULL, NULL, NULL),
(39, 'Plant Growth Experiment', 'A controlled experiment analysing plant growth factors.', 1, 'Science', 'Biology study.', 'plants.jpg', '2025-11-18', NULL, NULL, NULL),
(40, 'Chemical Reaction Rate Analysis', 'A lab experiment measuring reaction kinetics.', 1, 'Science', 'Chemistry experiment.', 'chemistry.jpg', '2025-11-18', NULL, NULL, NULL),
(41, 'Test Project', 'dhgsjsahj', NULL, 'Digital Media', 'djsdk', 'thumb_dc12ebcc-f00a-4538-856a-e6b0786d5ce3.PNG', '2025-11-30', NULL, NULL, 46),
(42, 'Another Test', 'jksfd', NULL, 'Digital Media', 'haw haw luke', 'thumb_e991cdec-d8cb-4160-bbcd-16733557c0de.PNG', '2025-12-01', NULL, 32, 46),
(43, 'Test Project', 'hjshdassda', NULL, 'jksakjsd', 'j,xsd', 'thumb_7b28d092-cbbc-4520-aa01-c06ded3371c6.png', '2025-12-04', NULL, 4, 46);

-- --------------------------------------------------------

--
-- Table structure for table `project_note`
--

CREATE TABLE IF NOT EXISTS `project_note` (
  `NoteID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedAt` datetime(6) DEFAULT NULL,
  `NoteText` varchar(2000) DEFAULT NULL,
  `Saved` bit(1) DEFAULT NULL,
  `UserID` bigint(20) NOT NULL,
  `ProjectID` bigint(20) NOT NULL,
  PRIMARY KEY (`NoteID`),
  KEY `FKt6qexvv3mqyot7gpse2hxrbkv` (`ProjectID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `project_note`
--

INSERT INTO `project_note` (`NoteID`, `CreatedAt`, `NoteText`, `Saved`, `UserID`, `ProjectID`) VALUES
(1, '2025-11-30 21:35:28.000000', NULL, b'0', 68, 41),
(2, '2025-11-30 21:35:34.000000', 'sdssaasjajks', b'0', 68, 41),
(3, '2025-12-01 14:33:10.000000', NULL, b'0', 68, 41),
(4, '2025-12-01 14:33:16.000000', NULL, b'0', 68, 41),
(5, '2025-12-01 14:33:32.000000', 'Luke has stuffing', b'0', 68, 41),
(6, '2025-12-01 21:22:50.000000', 'Luke has stuffing', b'0', 68, 41),
(7, '2025-12-01 21:25:52.000000', NULL, b'0', 68, 24),
(8, '2025-12-01 21:26:00.000000', 'hello note', b'0', 68, 24),
(9, '2025-12-01 21:27:06.000000', NULL, b'0', 68, 42),
(10, '2025-12-01 21:27:13.000000', 'Note note note', b'0', 68, 42),
(11, '2025-12-01 21:34:53.000000', NULL, b'0', 68, 41),
(12, '2025-12-01 21:35:00.000000', 'Note', b'0', 68, 41),
(13, '2025-12-01 21:35:11.000000', 'Note', b'0', 68, 41),
(14, '2025-12-01 21:35:22.000000', NULL, b'1', 68, 40),
(15, '2025-12-01 21:35:24.000000', NULL, b'1', 68, 1),
(16, '2025-12-01 21:35:26.000000', NULL, b'0', 68, 2),
(17, '2025-12-01 21:35:35.000000', NULL, b'0', 68, 2),
(18, '2025-12-01 21:35:40.000000', NULL, b'1', 68, 1),
(19, '2025-12-01 21:35:43.000000', NULL, b'0', 68, 2),
(20, '2025-12-02 09:51:25.000000', NULL, b'1', 68, 40),
(21, '2025-12-02 11:49:41.000000', NULL, b'1', 68, 41),
(22, '2025-12-04 22:00:03.000000', NULL, b'1', 68, 7),
(23, '2025-12-04 22:00:11.000000', NULL, b'1', 68, 7),
(24, '2025-12-05 20:02:14.000000', 'fhkghgkhg', b'1', 68, 7);

-- --------------------------------------------------------

--
-- Table structure for table `showcase`
--

CREATE TABLE IF NOT EXISTS `showcase` (
  `ShowcaseId` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(500) NOT NULL,
  `Image` varchar(255) DEFAULT NULL,
  `Status` varchar(50) NOT NULL,
  `ThumbnailImage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ShowcaseId`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `showcase`
--

INSERT INTO `showcase` (`ShowcaseId`, `Name`, `Description`, `Image`, `Status`, `ThumbnailImage`) VALUES
(1, 'Art Showcase', 'Yes art this is good', '/uploads/3553ad7e-3a85-4e58-b11b-699acd3211df_Screenshot 2024-04-17 204120.png', 'PENDING', '/uploads/thumbs/3553ad7e-3a85-4e58-b11b-699acd3211df_Screenshot 2024-04-17 204120.png'),
(2, 'TEST', 'dfghj', NULL, 'LIVE', NULL),
(3, 'Sea times', 'a collection of immersive digital pieces to celebrate the beauty of the sea ', NULL, 'LIVE', NULL),
(4, 'Politics showcase', 'a collection of 3rd year work.', '/uploads/8e098fa0-258e-4888-a6bb-a530de77382e_CrashCost.png', 'LIVE', NULL),
(32, 'Doomsday', 'Avengers Doomsday', '/uploads/28c333d7-29b5-4f72-87f5-8a8c1eeea0c7_doomsday1.jpeg', 'LIVE', '/uploads/thumbs/28c333d7-29b5-4f72-87f5-8a8c1eeea0c7_doomsday1.jpeg'),
(33, 'Create Showcase Example', 'This is an example showcase', '/uploads/9fbe0d15-5c9c-404e-b45d-c4d0b76335d2_arkham-knight-4k.jpg', 'LIVE', '/uploads/thumbs/9fbe0d15-5c9c-404e-b45d-c4d0b76335d2_arkham-knight-4k.jpg'),
(34, 'Showcase', 'dskjfdsdff', '/uploads/c4ea282e-b3ce-4ce6-9117-d5265363a8e0_air.png', 'LIVE', '/uploads/thumbs/c4ea282e-b3ce-4ce6-9117-d5265363a8e0_air.png');

-- --------------------------------------------------------

--
-- Table structure for table `showcaseproject`
--

CREATE TABLE IF NOT EXISTS `showcaseproject` (
  `ShowcaseProjectID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProjectId` bigint(20) DEFAULT NULL,
  `ShowcaseID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ShowcaseProjectID`),
  KEY `ShowcaseID` (`ShowcaseID`),
  KEY `ProjectID` (`ProjectId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `showcaseproject`
--

INSERT INTO `showcaseproject` (`ShowcaseProjectID`, `ProjectId`, `ShowcaseID`) VALUES
(1, 1, 3),
(2, 3, 4),
(3, 2, 4),
(4, 3, 4),
(5, 3, 2),
(7, 2, 3),
(25, 26, 1),
(26, 27, 1),
(28, 29, 2),
(30, 31, 2),
(31, 32, 3),
(32, 33, 3),
(33, 34, 3),
(34, 35, 4),
(35, 36, 4),
(36, 37, 4);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userid` bigint(20) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) NOT NULL,
  `surname` varchar(225) DEFAULT NULL,
  `EmailAddress` varchar(100) NOT NULL,
  `UserType` varchar(50) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `password` varchar(225) DEFAULT NULL,
  `organization` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `FirstName`, `surname`, `EmailAddress`, `UserType`, `UserName`, `password`, `organization`) VALUES
(1, 'TRYANxxx', 'Ryanxxxxx', 'TRyan@gmail.comx', 'STUDENT', 'TRYAN', 'pass123', 'litx'),
(2, 'Mary', 'Smith', 'MS@homail.com', 'STUDENT', 'MSith', '123pass', ''),
(3, 'Peter', 'Carr', 'PC@somebusiness.ie', 'VIEWER', 'PCbus', 'PC123', 'buzz tech - 3d modelling'),
(4, 'Elizabeth', 'Bourke', 'Elizabeth.Bourke@TUS.ie', 'Admin', 'EBourke', 'Bourke123', ''),
(6, 'Tom', 'Smith', 'xx@jj.com', 'ADMIN', 'tsmith', 'pass123', ''),
(28, 'Elizabeth', 'Bourke', 'Elizabeth_Bourke@hotmail.com', '', 'EB', '123', 'lit'),
(29, 'Elizabeth2', 'Bourke', 'Elizabeth_Bourke2@hotmail.com', '', '22', '22', '22'),
(30, 'l', 'l', 'Elizabeth_Bourkejj@hotmail.com', '', 'l', 'l', 'l'),
(31, 'Elizabeth', 'Bourke', 'Elizabeth33_Bourke@hotmail.com', '', '33', '33', '33'),
(32, 'Elizabeth', 'Bourke', 'Elizabeth_Bourke66@hotmail.com', 'STUDENT', '66', '66', '66'),
(33, 'Elizabeth', 'Bourke', 'Elizabethmmm_Bourke@hotmail.com', 'STUDENT', 'mm', 'mm', 'mm'),
(34, 'Elizabeth', 'Bourke', 'Elizabethttt_Bourke@hotmail.com', 'STUDENT', 'EB444', '123', 'lit'),
(35, 'Shinji', 'Ikari', 'shinji@nerv.com', 'Student', 'eva01', 'nerv123', 'Nerv'),
(36, 'Tony', 'Stark', 'tony.stark@avengershq.com', 'Admin', 'ironAdmin', 'admin123', 'Avengers HQ'),
(37, 'Bruce', 'Wayne', 'bruce.wayne@wayneenterprises.com', 'Admin', 'batAdmin', 'admin123', 'Wayne Enterprises'),
(38, 'Leia', 'Organa', 'leia.organa@rebellion.org', 'Admin', 'princessAdmin', 'admin123', 'Rebel Alliance'),
(39, 'Geralt', 'Of Rivia', 'geralt@kaermorhen.net', 'Admin', 'witcherAdmin', 'admin123', 'Kaer Morhen'),
(40, 'Misato', 'Katsuragi', 'misato@nerv.jp', 'Admin', 'nervAdmin', 'admin123', 'NERV HQ'),
(41, 'Walter', 'White', 'walter.white@abqchem.org', 'Admin', 'heisenAdmin', 'admin123', 'ABQ Chemistry'),
(42, 'Gandalf', 'TheGrey', 'gandalf@middleearth.me', 'Admin', 'wizardAdmin', 'admin123', 'White Council'),
(43, 'Solid', 'Snake', 'snake@foxhound.ops', 'Admin', 'foxhoundAdmin', 'admin123', 'FOXHOUND'),
(44, 'Johnny', 'Silverhand', 'johnny@samurai.band', 'Admin', 'samuraiAdmin', 'admin123', 'Samurai'),
(45, 'Levi', 'Ackerman', 'levi.ackerman@surveycorp.org', 'Admin', 'captainAdmin', 'admin123', 'Survey Corps'),
(46, 'Shinji', 'Ikari', 'shinji.ikari@nerv-school.jp', 'Student', 'evaShinji', 'user123', 'Tokyo-3 College'),
(47, 'Asuka', 'Soryu', 'asuka.soryu@nerv-school.jp', 'Student', 'asukaS', 'user123', 'Tokyo-3 College'),
(48, 'Rei', 'Ayanami', 'rei.ayanami@nerv-school.jp', 'Student', 'reiA', 'user123', 'Tokyo-3 College'),
(49, 'Izuku', 'Midoriya', 'deku@ua.edu', 'Student', 'uaDeku', 'user123', 'U.A. High'),
(50, 'Katsuki', 'Bakugo', 'bakugo@ua.edu', 'Student', 'boomBoy', 'user123', 'U.A. High'),
(51, 'Ochaco', 'Uraraka', 'uraraka@ua.edu', 'Student', 'floatGirl', 'user123', 'U.A. High'),
(52, 'Eren', 'Yeager', 'eren@paradise.edu', 'Student', 'erenY', 'user123', 'Paradis Academy'),
(53, 'Mikasa', 'Ackerman', 'mikasa@paradise.edu', 'Student', 'mikasaA', 'user123', 'Paradis Academy'),
(54, 'Naruto', 'Uzumaki', 'naruto@leaf.edu', 'Student', 'narutoU', 'user123', 'Leaf Village Academy'),
(55, 'Sakura', 'Haruno', 'sakura@leaf.edu', 'Student', 'sakuraH', 'user123', 'Leaf Village Academy'),
(56, 'Cloud', 'Strife', 'cloud@midgar.edu', 'Student', 'cloudS', 'user123', 'Midgar Institute'),
(57, 'Tifa', 'Lockhart', 'tifa@midgar.edu', 'Student', 'tifaL', 'user123', 'Midgar Institute'),
(58, 'Tanjiro', 'Kamado', 'tanjiro@slayer.edu', 'Student', 'tanjiK', 'user123', 'Demon Slayer School'),
(59, 'Nezuko', 'Kamado', 'nezuko@slayer.edu', 'Student', 'nezukoK', 'user123', 'Demon Slayer School'),
(60, 'Hornet', 'Protector', 'hornet@hallownest.edu', 'Student', 'hornetHK', 'user123', 'Hallownest Academy'),
(61, 'Quirrel', 'Wanderer', 'quirrel@hallownest.edu', 'Student', 'quirrelW', 'user123', 'Hallownest Academy'),
(62, 'Link', 'Hyrule', 'link@hyrule.ac', 'Student', 'linkStudent', 'user123', 'Hyrule Academy'),
(63, 'Zelda', 'Hyrule', 'zelda@hyrule.ac', 'Student', 'zeldaH', 'user123', 'Hyrule Academy'),
(64, 'Ciri', 'Sparrow', 'ciri@kaer.morhen', 'Student', 'ciriKM', 'user123', 'Kaer Morhen School'),
(65, 'Gendo', 'Ikari', 'gendo.ikari@nerv.jp', 'Employer', 'nervDirector', 'user123', 'NERV'),
(66, 'Ritsuko', 'Akagi', 'ritsuko.akagi@nerv.jp', 'Employer', 'nervScientist', 'user123', 'NERV'),
(67, 'Lando', 'Calrissian', 'lando@cloudcity.org', 'Employer', 'landoBoss', 'user123', 'Cloud City'),
(68, 'Lando', 'Calrissian', 'mando@guild.org', 'Employer', 'mandoHunter', 'user123', 'Cloud City'),
(69, 'Erwin', 'Smith', 'erwin@surveycorp.com', 'Employer', 'erwinCommander', 'user123', 'Survey Corps HQ'),
(70, 'Levi', 'Ackerman', 'levi@surveycorp.com', 'Employer', 'leviCaptain', 'user123', 'Survey Corps HQ'),
(71, 'Judy', 'Alvarez', 'judy@lizzie.net', 'Employer', 'judyTech', 'user123', 'Lizzie’s Tech'),
(72, 'Panam', 'Palmer', 'panam@aldecaldos.org', 'Employer', 'nomadLeader', 'user123', 'Aldecaldos'),
(73, 'Master', 'Chief', 'chief@unsc.mil', 'Employer', 'chiefUNSC', 'user123', 'UNSC'),
(74, 'Cortana', 'AI', 'cortana@unsc.mil', 'Employer', 'cortanaUNSC', 'user123', 'UNSC'),
(75, 'Rauru', 'Sage', 'rauru@hyrule.gov', 'Employer', 'rauruSage', 'user123', 'Hyrule Royal Office'),
(76, 'Mipha', 'Zora', 'mipha@zora.gov', 'Employer', 'miphaZ', 'user123', 'Zora Kingdom'),
(77, 'Geralt', 'Rivia', 'geralt@witcher.guild', 'Employer', 'geraltW', 'user123', 'Witcher Guild'),
(78, 'Yennefer', 'Vengerberg', 'yen@lodge.mage', 'Employer', 'yenMage', 'user123', 'Lodge of Sorceresses'),
(79, 'Caitlyn', 'Kiramman', 'caitlyn@piltover.gov', 'Employer', 'caitlynEnforcer', 'user123', 'Piltover Council'),
(80, 'Jayce', 'Talis', 'jayce@piltover.gov', 'Employer', 'jayceInnovator', 'user123', 'Piltover Council'),
(81, 'Garrus', 'Vakarian', 'garrus@csec.space', 'Employer', 'garrusCSEC', 'user123', 'C-SEC'),
(82, 'Liara', 'Tsoni', 'liara@shadowbroker.net', 'Employer', 'liaraShadow', 'user123', 'Shadow Broker Network'),
(83, 'Cassandra', 'Pentaghast', 'cassandra@chantry.org', 'Employer', 'seekersLead', 'user123', 'Seekers of Truth'),
(84, 'Varric', 'Tethras', 'varric@merchantsguild.org', 'Employer', 'varricGuild', 'user123', 'Merchants Guild');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FK4ow2o8mfch91jnyu44scyuj8h` FOREIGN KEY (`showcase_id`) REFERENCES `showcase` (`ShowcaseId`),
  ADD CONSTRAINT `FK84avpir3s8alvr5yud0yh8d7b` FOREIGN KEY (`UserID`) REFERENCES `user` (`userid`),
  ADD CONSTRAINT `FKo06v2e9kuapcugnyhttqa1vpt` FOREIGN KEY (`user_id`) REFERENCES `user` (`userid`);

--
-- Constraints for table `project_note`
--
ALTER TABLE `project_note`
  ADD CONSTRAINT `FKt6qexvv3mqyot7gpse2hxrbkv` FOREIGN KEY (`ProjectID`) REFERENCES `project` (`ProjectID`);

--
-- Constraints for table `showcaseproject`
--
ALTER TABLE `showcaseproject`
  ADD CONSTRAINT `FK6ge3kqk7na2e01frc8rj08y9w` FOREIGN KEY (`ShowcaseID`) REFERENCES `showcase` (`ShowcaseId`),
  ADD CONSTRAINT `FKgt6y2lqndsh5b1gs0nxci6wk5` FOREIGN KEY (`ProjectId`) REFERENCES `project` (`ProjectID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
