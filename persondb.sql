-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2022. Ápr 10. 11:30
-- Kiszolgáló verziója: 10.4.22-MariaDB
-- PHP verzió: 8.1.2

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `persondb`
--
CREATE
DATABASE IF NOT EXISTS `persondb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE
`persondb`;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence`
(
    `next_val` bigint
(
    20
) DEFAULT NULL
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES (0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `language`
--

DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `ids` int
(
    11
) NOT NULL,
    `nyelv` varchar
(
    125
) DEFAULT NULL,
    `nyelviszint` varchar
(
    2
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
),
    KEY `ids`
(
    `ids`
) USING BTREE
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `vezeteknev` varchar
(
    255
) DEFAULT NULL,
    `keresztnev` varchar
(
    255
) DEFAULT NULL,
    `szuletesidatum` varchar
(
    11
) DEFAULT NULL,
    `szuletesihely` varchar
(
    128
) DEFAULT NULL,
    `neme` varchar
(
    10
) DEFAULT NULL,
    `emailcim` varchar
(
    128
) DEFAULT NULL,
    `telefonszam` varchar
(
    32
) DEFAULT NULL,
    `webhely` varchar
(
    256
) DEFAULT NULL,
    `iranyitoszam` varchar
(
    4
) DEFAULT NULL,
    `telepules` varchar
(
    128
) DEFAULT NULL,
    `cim` varchar
(
    256
) DEFAULT NULL,
    `motivacioslevel` text DEFAULT NULL,
    `rogzitesdatuma` varchar
(
    120
) DEFAULT NULL,
    PRIMARY KEY
(
    `id`
)
    ) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE IF NOT EXISTS `school`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `ids` int
(
    11
) NOT NULL,
    `iskolaneve` varchar
(
    200
) DEFAULT NULL,
    `kepzetseg` varchar
(
    200
) DEFAULT NULL,
    `szakirany` varchar
(
    200
) DEFAULT NULL,
    `kezdeseve` varchar
(
    4
) DEFAULT NULL,
    `vegzeseve` varchar
(
    4
) DEFAULT NULL,
    `rogzitesdatuma` varchar
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
),
    KEY `ids`
(
    `ids`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `skills`
--

DROP TABLE IF EXISTS `skills`;
CREATE TABLE IF NOT EXISTS `skills`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `ids` int
(
    11
) NOT NULL,
    `megnevezes` varchar
(
    200
) DEFAULT NULL,
    `tudasszint` varchar
(
    200
) DEFAULT NULL,
    `rogzitesdatuma` varchar
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
),
    KEY `ids`
(
    `ids`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `working`
--

DROP TABLE IF EXISTS `working`;
CREATE TABLE IF NOT EXISTS `working`
(
    `id` int
(
    11
) NOT NULL AUTO_INCREMENT,
    `ids` int
(
    11
) NOT NULL,
    `munkahelyneve` varchar
(
    200
) DEFAULT NULL,
    `beosztas` varchar
(
    200
) DEFAULT NULL,
    `osszefoglalas` text DEFAULT NULL,
    `kezdeseve` varchar
(
    4
) DEFAULT NULL,
    `zaraseve` varchar
(
    4
) DEFAULT NULL,
    `rogzitesdatuma` varchar
(
    20
) NOT NULL,
    PRIMARY KEY
(
    `id`
),
    KEY `ids`
(
    `ids`
)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
