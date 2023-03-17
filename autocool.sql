-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 17 mars 2023 à 07:09
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `autocool`
--

-- --------------------------------------------------------

--
-- Structure de la table `abonne`
--

DROP TABLE IF EXISTS `abonne`;
CREATE TABLE IF NOT EXISTS `abonne` (
  `NUMABONNE` bigint(4) NOT NULL,
  `CODEFORMULE` varchar(32) NOT NULL,
  `NOM` varchar(32) DEFAULT NULL,
  `PRENOM` varchar(32) DEFAULT NULL,
  `DATENAISSANCE` date DEFAULT NULL,
  `RUE` varchar(32) DEFAULT NULL,
  `VILLE` varchar(32) DEFAULT NULL,
  `CODEPOSTAL` varchar(32) DEFAULT NULL,
  `TEL` varchar(32) DEFAULT NULL,
  `TELMOBILE` varchar(32) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PAIEMENTADHESION` tinyint(1) DEFAULT NULL,
  `PAIEMENTCAUTION` tinyint(1) DEFAULT NULL,
  `RIBFOURNI` tinyint(1) DEFAULT NULL,
  `IDENTIFIANT` varchar(32) DEFAULT NULL,
  `MDP` varchar(150) DEFAULT NULL,
  `NUMPERMIS` varchar(32) DEFAULT NULL,
  `LIEUPERMIS` varchar(32) DEFAULT NULL,
  `DATEPERMIS` date DEFAULT NULL,
  PRIMARY KEY (`NUMABONNE`),
  KEY `I_FK_ABONNE_FORMULE` (`CODEFORMULE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `categorie_vehicule`
--

DROP TABLE IF EXISTS `categorie_vehicule`;
CREATE TABLE IF NOT EXISTS `categorie_vehicule` (
  `CODECATEG` varchar(32) NOT NULL,
  `LIBELLECATEG` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CODECATEG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `formule`
--

DROP TABLE IF EXISTS `formule`;
CREATE TABLE IF NOT EXISTS `formule` (
  `CODEFORMULE` varchar(32) NOT NULL,
  `LIBELLEFORMULE` varchar(32) DEFAULT NULL,
  `FRAISADHESION` decimal(10,2) DEFAULT NULL,
  `TARIFMENSUEL` decimal(10,2) DEFAULT NULL,
  `PARTSOCIALE` decimal(10,2) DEFAULT NULL,
  `DEPOTGARANTIE` decimal(10,2) DEFAULT NULL,
  `CAUTION` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`CODEFORMULE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

DROP TABLE IF EXISTS `station`;
CREATE TABLE IF NOT EXISTS `station` (
  `CODEVILLE` bigint(4) NOT NULL,
  `CODELIEU` bigint(4) NOT NULL,
  `NOMLIEU` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CODEVILLE`,`CODELIEU`),
  KEY `I_FK_STATION_VILLE` (`CODEVILLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tarif1`
--

DROP TABLE IF EXISTS `tarif1`;
CREATE TABLE IF NOT EXISTS `tarif1` (
  `CODEFORMULE` varchar(32) NOT NULL,
  `CODETRANCHEH` varchar(32) NOT NULL,
  `CODECATEG` varchar(32) NOT NULL,
  `TARIFH` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CODEFORMULE`,`CODETRANCHEH`,`CODECATEG`),
  KEY `I_FK_TARIF1_FORMULE` (`CODEFORMULE`),
  KEY `I_FK_TARIF1_TRANCHE_HORAIRE` (`CODETRANCHEH`),
  KEY `I_FK_TARIF1_CATEGORIE_VEHICULE` (`CODECATEG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tarif2`
--

DROP TABLE IF EXISTS `tarif2`;
CREATE TABLE IF NOT EXISTS `tarif2` (
  `CODEFORMULE` varchar(32) NOT NULL,
  `CODETRANCHEKM` varchar(32) NOT NULL,
  `CODECATEG` varchar(32) NOT NULL,
  `TARIFKM` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CODEFORMULE`,`CODETRANCHEKM`,`CODECATEG`),
  KEY `I_FK_TARIF2_FORMULE` (`CODEFORMULE`),
  KEY `I_FK_TARIF2_TRANCHE_KM` (`CODETRANCHEKM`),
  KEY `I_FK_TARIF2_CATEGORIE_VEHICULE` (`CODECATEG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tranche_horaire`
--

DROP TABLE IF EXISTS `tranche_horaire`;
CREATE TABLE IF NOT EXISTS `tranche_horaire` (
  `CODETRANCHEH` varchar(32) NOT NULL,
  `DUREE` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`CODETRANCHEH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `tranche_km`
--

DROP TABLE IF EXISTS `tranche_km`;
CREATE TABLE IF NOT EXISTS `tranche_km` (
  `CODETRANCHEKM` varchar(32) NOT NULL,
  `MINKM` bigint(4) DEFAULT NULL,
  `MAXKM` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`CODETRANCHEKM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_vehicule`
--

DROP TABLE IF EXISTS `type_vehicule`;
CREATE TABLE IF NOT EXISTS `type_vehicule` (
  `CODETYPE` varchar(32) NOT NULL,
  `CODECATEG` varchar(32) NOT NULL,
  `LIBELLETYPE` varchar(32) DEFAULT NULL,
  `NBPLACES` bigint(4) DEFAULT NULL,
  `AUTOMATIQUE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`CODETYPE`),
  KEY `I_FK_TYPE_VEHICULE_CATEGORIE_VEHICULE` (`CODECATEG`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `NUMVEHICULE` varchar(32) NOT NULL,
  `CODELIEU` bigint(4) NOT NULL,
  `CODETYPE` varchar(32) NOT NULL,
  `CODEVILLE` bigint(4) NOT NULL,
  `KILOMETRAGE` bigint(4) DEFAULT NULL,
  `NIVEAUESSENCE` bigint(4) DEFAULT NULL,
  PRIMARY KEY (`NUMVEHICULE`),
  KEY `I_FK_VEHICULE_STATION` (`CODEVILLE`,`CODELIEU`),
  KEY `I_FK_VEHICULE_TYPE_VEHICULE` (`CODETYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `ville`
--

DROP TABLE IF EXISTS `ville`;
CREATE TABLE IF NOT EXISTS `ville` (
  `CODEVILLE` bigint(4) NOT NULL,
  `NOMVILLE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CODEVILLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `abonne`
--
ALTER TABLE `abonne`
  ADD CONSTRAINT `abonne_ibfk_1` FOREIGN KEY (`CODEFORMULE`) REFERENCES `formule` (`CODEFORMULE`);

--
-- Contraintes pour la table `station`
--
ALTER TABLE `station`
  ADD CONSTRAINT `station_ibfk_1` FOREIGN KEY (`CODEVILLE`) REFERENCES `ville` (`CODEVILLE`);

--
-- Contraintes pour la table `tarif1`
--
ALTER TABLE `tarif1`
  ADD CONSTRAINT `tarif1_ibfk_1` FOREIGN KEY (`CODEFORMULE`) REFERENCES `formule` (`CODEFORMULE`),
  ADD CONSTRAINT `tarif1_ibfk_2` FOREIGN KEY (`CODETRANCHEH`) REFERENCES `tranche_horaire` (`CODETRANCHEH`),
  ADD CONSTRAINT `tarif1_ibfk_3` FOREIGN KEY (`CODECATEG`) REFERENCES `categorie_vehicule` (`CODECATEG`);

--
-- Contraintes pour la table `tarif2`
--
ALTER TABLE `tarif2`
  ADD CONSTRAINT `tarif2_ibfk_1` FOREIGN KEY (`CODEFORMULE`) REFERENCES `formule` (`CODEFORMULE`),
  ADD CONSTRAINT `tarif2_ibfk_2` FOREIGN KEY (`CODETRANCHEKM`) REFERENCES `tranche_km` (`CODETRANCHEKM`),
  ADD CONSTRAINT `tarif2_ibfk_3` FOREIGN KEY (`CODECATEG`) REFERENCES `categorie_vehicule` (`CODECATEG`);

--
-- Contraintes pour la table `type_vehicule`
--
ALTER TABLE `type_vehicule`
  ADD CONSTRAINT `type_vehicule_ibfk_1` FOREIGN KEY (`CODECATEG`) REFERENCES `categorie_vehicule` (`CODECATEG`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`CODEVILLE`,`CODELIEU`) REFERENCES `station` (`CODEVILLE`, `CODELIEU`),
  ADD CONSTRAINT `vehicule_ibfk_2` FOREIGN KEY (`CODETYPE`) REFERENCES `type_vehicule` (`CODETYPE`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
