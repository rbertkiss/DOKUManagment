-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2021. Aug 12. 21:20
-- Kiszolgáló verziója: 10.4.20-MariaDB
-- PHP verzió: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `persondb`
--
CREATE DATABASE IF NOT EXISTS `persondb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `persondb`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hibernate_sequence`
--
-- Létrehozva: 2021. Aug 11. 18:08
-- Utolsó frissítés: 2021. Aug 12. 17:33
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `hibernate_sequence`:
--

--
-- A tábla adatainak kiíratása `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(21);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `language`
--
-- Létrehozva: 2021. Aug 12. 08:31
-- Utolsó frissítés: 2021. Aug 12. 17:33
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ids` int(11) NOT NULL,
  `nyelv` varchar(125) DEFAULT NULL,
  `nyelviszint` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `personid` (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `language`:
--   `ids`
--       `person` -> `id`
--

--
-- A tábla adatainak kiíratása `language`
--

INSERT INTO `language` (`id`, `ids`, `nyelv`, `nyelviszint`) VALUES
(2, 2, 'Német', 'A2'),
(4, 3, 'Arab', 'B1'),
(5, 2, 'Angol', 'C2'),
(7, 6, 'Kínai', 'A2'),
(14, 6, 'Angol', 'C2'),
(16, 15, 'Olasz', 'B1'),
(20, 1, 'Német', 'B2');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `person`
--
-- Létrehozva: 2021. Aug 11. 23:08
-- Utolsó frissítés: 2021. Aug 12. 16:00
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vezeteknev` varchar(255) DEFAULT NULL,
  `keresztnev` varchar(255) DEFAULT NULL,
  `szuletesidatum` varchar(11) DEFAULT NULL,
  `szuletesihely` varchar(128) DEFAULT NULL,
  `neme` varchar(10) DEFAULT NULL,
  `emailcim` varchar(128) DEFAULT NULL,
  `telefonszam` varchar(32) DEFAULT NULL,
  `webhely` varchar(256) DEFAULT NULL,
  `iranyitoszam` varchar(4) DEFAULT NULL,
  `telepules` varchar(128) DEFAULT NULL,
  `cim` varchar(256) DEFAULT NULL,
  `motivacioslevel` text DEFAULT NULL,
  `rogzitesdatuma` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `person`:
--

--
-- A tábla adatainak kiíratása `person`
--

INSERT INTO `person` (`id`, `vezeteknev`, `keresztnev`, `szuletesidatum`, `szuletesihely`, `neme`, `emailcim`, `telefonszam`, `webhely`, `iranyitoszam`, `telepules`, `cim`, `motivacioslevel`, `rogzitesdatuma`) VALUES
(1, 'Demó', 'József', '1995.05.13', 'Kisvárda', 'Férfi', 'demo.jozsef@gmail.com', '+36204586693', '', '4600', 'Kisvárda', 'Flórián tér 20/A', '', '2021-08-12'),
(2, 'Gipsz ', 'Jakab', '2000.12.14', 'Nyíregyháza', 'Férfi', 'gipszes@gmail.com', '+36453154789', '', '4600', 'Kisvárda', 'Athanáz út 123.', '', '2021-08-12'),
(3, 'Ádám', 'Emőke', '1994.05.26', 'Nyíregyháza', 'Nő', 'ada.emo@freemail.hu', '+36302581479', 'adam-emoke.com', '4400', 'Nyíregyháza', 'Szent István út 136.', '', '2021-08-12'),
(6, 'Háda', 'Csilla', '1969.07.27', 'Kisvárda', 'Nő', 'hcs21@yahoo.com', '+36209806421', '', '1818', 'Budapest', 'Almafa út 125/K', '', '2021-08-12'),
(15, 'Kovács', 'István', '1975.09.15', 'Győr', 'Férfi', 'koistvan@gmail.com', '+36702758974', '', '', 'Győr', 'Csillag utca 100', '', '2021-08-12');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `school`
--
-- Létrehozva: 2021. Aug 12. 13:49
-- Utolsó frissítés: 2021. Aug 12. 15:38
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE IF NOT EXISTS `school` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ids` int(11) NOT NULL,
  `iskolaneve` varchar(200) DEFAULT NULL,
  `kepzetseg` varchar(200) DEFAULT NULL,
  `szakirany` varchar(200) DEFAULT NULL,
  `kezdeseve` varchar(4) DEFAULT NULL,
  `vegzeseve` varchar(4) DEFAULT NULL,
  `rogzitesdatuma` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ids` (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `school`:
--   `ids`
--       `person` -> `id`
--

--
-- A tábla adatainak kiíratása `school`
--

INSERT INTO `school` (`id`, `ids`, `iskolaneve`, `kepzetseg`, `szakirany`, `kezdeseve`, `vegzeseve`, `rogzitesdatuma`) VALUES
(12, 6, 'Szent László Katolikus Középiskola, Kisvárda', 'Középiskola', 'Informaika', '2014', '2018', '2021-08-12'),
(19, 15, 'Győri Általános Iskola', 'Általános Iskola', '', '1982', '1990', '2021-08-12');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `skills`
--
-- Létrehozva: 2021. Aug 12. 13:50
-- Utolsó frissítés: 2021. Aug 12. 15:37
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ids` int(11) NOT NULL,
  `megnevezes` varchar(200) DEFAULT NULL,
  `tudasszint` varchar(200) DEFAULT NULL,
  `rogzitesdatuma` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ids` (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `skills`:
--   `ids`
--       `person` -> `id`
--

--
-- A tábla adatainak kiíratása `skills`
--

INSERT INTO `skills` (`id`, `ids`, `megnevezes`, `tudasszint`, `rogzitesdatuma`) VALUES
(8, 3, 'Számítógép szerelés', 'Mester', '2021-08-12'),
(10, 6, 'C# Programozás', 'Középhaladó', '2021-08-12'),
(17, 15, 'C# programozás', 'Középhaladó', '2021-08-12'),
(18, 15, 'Hálózati konfiguráció', 'Kezdő', '2021-08-12');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `working`
--
-- Létrehozva: 2021. Aug 12. 13:50
-- Utolsó frissítés: 2021. Aug 12. 15:30
--

DROP TABLE IF EXISTS `working`;
CREATE TABLE IF NOT EXISTS `working` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ids` int(11) NOT NULL,
  `munkahelyneve` varchar(200) DEFAULT NULL,
  `beosztas` varchar(200) DEFAULT NULL,
  `osszefoglalas` text DEFAULT NULL,
  `kezdeseve` varchar(4) DEFAULT NULL,
  `zaraseve` varchar(4) DEFAULT NULL,
  `rogzitesdatuma` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ids` (`ids`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- TÁBLA KAPCSOLATAI `working`:
--   `ids`
--       `person` -> `id`
--

--
-- A tábla adatainak kiíratása `working`
--

INSERT INTO `working` (`id`, `ids`, `munkahelyneve`, `beosztas`, `osszefoglalas`, `kezdeseve`, `zaraseve`, `rogzitesdatuma`) VALUES
(13, 6, 'SZSZBMK Informatikai Főosztály', 'Informatikus', 'XXXX', '2021', '2021', '2021-08-12');

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `language`
--
ALTER TABLE `language`
  ADD CONSTRAINT `language_ibfk_1` FOREIGN KEY (`ids`) REFERENCES `person` (`id`);

--
-- Megkötések a táblához `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `school_ibfk_1` FOREIGN KEY (`ids`) REFERENCES `person` (`id`);

--
-- Megkötések a táblához `skills`
--
ALTER TABLE `skills`
  ADD CONSTRAINT `skills_ibfk_1` FOREIGN KEY (`ids`) REFERENCES `person` (`id`);

--
-- Megkötések a táblához `working`
--
ALTER TABLE `working`
  ADD CONSTRAINT `working_ibfk_1` FOREIGN KEY (`ids`) REFERENCES `person` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
