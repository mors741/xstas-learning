-- --------------------------------------------------------
-- Хост:                         
-- Версия сервера:               5.7.14 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных xstas
CREATE DATABASE IF NOT EXISTS `xstas` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `xstas`;

-- Дамп структуры для таблица xstas.courses
CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `course_name` varchar(100) NOT NULL,
  `course_desc` varchar(5000) DEFAULT NULL,
  `cost` mediumint(9) unsigned DEFAULT NULL,
  `promotion` varchar(100) DEFAULT NULL,
  `trailer` varchar(100) DEFAULT NULL,
  `teacher` int(11) unsigned NOT NULL,
  `state` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `course_name` (`course_name`),
  KEY `FK_courses_users` (`teacher`),
  KEY `FK_courses_states` (`state`),
  CONSTRAINT `FK_courses_states` FOREIGN KEY (`state`) REFERENCES `states` (`state_id`),
  CONSTRAINT `FK_courses_users` FOREIGN KEY (`teacher`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.courses: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.course_subscribers
CREATE TABLE IF NOT EXISTS `course_subscribers` (
  `course_subscriber_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student` int(10) unsigned NOT NULL,
  `course` int(10) unsigned NOT NULL,
  `course_rating` smallint(5) unsigned DEFAULT NULL,
  PRIMARY KEY (`course_subscriber_id`),
  UNIQUE KEY `student_course` (`student`,`course`),
  KEY `FK_course_subscribers_courses` (`course`),
  CONSTRAINT `FK_course_subscribers_courses` FOREIGN KEY (`course`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FK_course_subscribers_users` FOREIGN KEY (`student`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.course_subscribers: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `course_subscribers` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_subscribers` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.formats
CREATE TABLE IF NOT EXISTS `formats` (
  `format_id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `format_name` varchar(20) NOT NULL,
  PRIMARY KEY (`format_id`),
  UNIQUE KEY `format_name` (`format_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.formats: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `formats` DISABLE KEYS */;
INSERT INTO `formats` (`format_id`, `format_name`) VALUES
	(3, 'playbuzz'),
	(2, 'text'),
	(1, 'video');
/*!40000 ALTER TABLE `formats` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.moderations
CREATE TABLE IF NOT EXISTS `moderations` (
  `moderation_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `moderator` int(11) unsigned NOT NULL,
  `course` int(11) unsigned NOT NULL,
  `comment` varchar(300) DEFAULT NULL,
  `comission` tinyint(3) unsigned DEFAULT NULL,
  PRIMARY KEY (`moderation_id`),
  UNIQUE KEY `moderator` (`moderator`),
  KEY `FK_moderations_courses` (`course`),
  CONSTRAINT `FK_moderations_courses` FOREIGN KEY (`course`) REFERENCES `courses` (`course_id`),
  CONSTRAINT `FK_moderations_users` FOREIGN KEY (`moderator`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.moderations: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `moderations` DISABLE KEYS */;
/*!40000 ALTER TABLE `moderations` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.modules
CREATE TABLE IF NOT EXISTS `modules` (
  `module_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) NOT NULL,
  `module_desc` varchar(1000) DEFAULT NULL,
  `module_order` tinyint(4) unsigned NOT NULL,
  `course` int(11) unsigned NOT NULL,
  PRIMARY KEY (`module_id`),
  KEY `FK_modules_courses` (`course`),
  CONSTRAINT `FK_modules_courses` FOREIGN KEY (`course`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.modules: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;
/*!40000 ALTER TABLE `modules` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `role_id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.roles: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`role_id`, `role_name`) VALUES
  (0, 'Преподаватель'),
  (1, 'Студент'),
  (2, 'Модератор');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.states
CREATE TABLE IF NOT EXISTS `states` (
  `state_id` tinyint(4) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(20) NOT NULL,
  PRIMARY KEY (`state_id`),
  UNIQUE KEY `state_name` (`state_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.states: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` (`state_id`, `state_name`) VALUES
	(4, 'close'),
	(2, 'moderated'),
	(1, 'not_published'),
	(3, 'open');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.teasers
CREATE TABLE IF NOT EXISTS `teasers` (
  `teaser_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `teaser_name` varchar(100) NOT NULL,
  `teaser_desc` varchar(1000) DEFAULT NULL,
  `video` varchar(100) NOT NULL,
  `creator` int(11) unsigned NOT NULL,
  PRIMARY KEY (`teaser_id`),
  UNIQUE KEY `teaser_name` (`teaser_name`),
  KEY `FK_teasers_users` (`creator`),
  CONSTRAINT `FK_teasers_users` FOREIGN KEY (`creator`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.teasers: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `teasers` DISABLE KEYS */;
/*!40000 ALTER TABLE `teasers` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.teaser_subscribers
CREATE TABLE IF NOT EXISTS `teaser_subscribers` (
  `teaser_subscriber_id` int(11) unsigned NOT NULL,
  `student` int(11) unsigned NOT NULL,
  `teaser` int(11) unsigned NOT NULL,
  `teaser_rating` smallint(6) unsigned DEFAULT NULL,
  PRIMARY KEY (`teaser_subscriber_id`),
  UNIQUE KEY `student_teaser` (`student`,`teaser`),
  KEY `FK_teaser_subscribers_teasers` (`teaser`),
  CONSTRAINT `FK_teaser_subscribers_teasers` FOREIGN KEY (`teaser`) REFERENCES `teasers` (`teaser_id`),
  CONSTRAINT `FK_teaser_subscribers_users` FOREIGN KEY (`student`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.teaser_subscribers: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `teaser_subscribers` DISABLE KEYS */;
/*!40000 ALTER TABLE `teaser_subscribers` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.units
CREATE TABLE IF NOT EXISTS `units` (
  `unit_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(50) NOT NULL,
  `unit_order` tinyint(4) unsigned NOT NULL,
  `content` varchar(10000) NOT NULL,
  `format` tinyint(4) unsigned NOT NULL,
  `module` int(11) unsigned NOT NULL,
  PRIMARY KEY (`unit_id`),
  KEY `FK_units_formats` (`format`),
  KEY `FK_units_modules` (`module`),
  CONSTRAINT `FK_units_formats` FOREIGN KEY (`format`) REFERENCES `formats` (`format_id`),
  CONSTRAINT `FK_units_modules` FOREIGN KEY (`module`) REFERENCES `modules` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.units: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
/*!40000 ALTER TABLE `units` ENABLE KEYS */;

-- Дамп структуры для таблица xstas.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `patronymic` varchar(50) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `password` varchar(100) NOT NULL,
  `role` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`),
  KEY `FK_users_roles` (`role`),
  CONSTRAINT `FK_users_roles` FOREIGN KEY (`role`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы xstas.users: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
