delimiter $$

CREATE DATABASE `clubuml2` /*!40100 DEFAULT CHARACTER SET utf8 */$$

delimiter $$

CREATE TABLE `project` (
  `project_Id` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `achived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`project_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `user` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `project_Id` int(11) DEFAULT NULL,
  `securityQuestion` varchar(45) DEFAULT NULL,
  `securityAnswer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`User_Id`),
  KEY `fk_Project_User_idx` (`project_Id`),
  CONSTRAINT `fk_Project_User` FOREIGN KEY (`project_Id`) REFERENCES `project` (`project_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `diagram` (
  `diagram_Id` int(11) NOT NULL AUTO_INCREMENT,
  `diagramName` varchar(45) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `inEdition` tinyint(1) DEFAULT NULL,
  `owner_Id` int(11) NOT NULL,
  `filePath` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`diagram_Id`),
  KEY `fk_Diagram_User_idx` (`owner_Id`),
  CONSTRAINT `fk_Diagram_User` FOREIGN KEY (`owner_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `comment` (
  `comment_Id` int(11) NOT NULL AUTO_INCREMENT,
  `user_Id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `writtenTime` datetime DEFAULT NULL,
  `diagram_Id` int(11) NOT NULL,
  PRIMARY KEY (`comment_Id`),
  KEY `fk_Comment_Diagram_idx` (`diagram_Id`),
  KEY `fk_Comment_User_idx` (`user_Id`),
  CONSTRAINT `fk_Comment_User` FOREIGN KEY (`user_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Diagram` FOREIGN KEY (`diagram_Id`) REFERENCES `diagram` (`diagram_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `report` (
  `report_Id` int(11) NOT NULL,
  `diagram_A` int(11) NOT NULL,
  `diagram_B` int(11) NOT NULL,
  `comparedTime` timestamp NULL DEFAULT NULL,
  `reportFilePath` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`report_Id`),
  KEY `fk_Report_Diagram_idx` (`diagram_A`),
  KEY `fk_Report__idx` (`diagram_B`),
  CONSTRAINT `fk_Report_Diagram_A` FOREIGN KEY (`diagram_A`) REFERENCES `diagram` (`diagram_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Report_Diagram_B` FOREIGN KEY (`diagram_B`) REFERENCES `diagram` (`diagram_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$

delimiter $$

CREATE TABLE `editinghistory` (
  `diagram_Id` int(11) NOT NULL,
  `EditingTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `editingHistory_Id` int(11) NOT NULL,
  `user_Id` int(11) NOT NULL,
  PRIMARY KEY (`editingHistory_Id`),
  KEY `fk_EditingHistory_Diagram_idx` (`diagram_Id`),
  KEY `fk_EditingHistory_User_idx` (`user_Id`),
  CONSTRAINT `fk_EditingHistory_User` FOREIGN KEY (`user_Id`) REFERENCES `user` (`User_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EditingHistory_Diagram` FOREIGN KEY (`diagram_Id`) REFERENCES `diagram` (`diagram_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8$$


