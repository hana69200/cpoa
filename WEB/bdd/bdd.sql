-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  Dim 29 déc. 2019 à 01:39
-- Version du serveur :  10.3.17-MariaDB
-- Version de PHP :  7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `p1804713`
--

-- --------------------------------------------------------

--
-- Structure de la table `Atome`
--

CREATE TABLE `Atome` (
  `Symbole` varchar(3) NOT NULL,
  `Nom` varchar(40) NOT NULL,
  `Mr` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Atome`
--

INSERT INTO `Atome` (`Symbole`, `Nom`, `Mr`) VALUES
('C', 'CARBONE', 12.011),
('Cl', 'CHLORE', 35.453),
('Fe', 'FER', 55.845),
('H', 'HYDROGENE', 1.0079),
('Na', 'SODIUM', 22.99),
('O', 'OXYGENE', 15.999);

-- --------------------------------------------------------

--
-- Structure de la table `Categorie`
--

CREATE TABLE `Categorie` (
  `catId` int(11) NOT NULL,
  `nomCat` varchar(255) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Categorie`
--

INSERT INTO `Categorie` (`catId`, `nomCat`) VALUES
(1, 'Animaux'),
(2, 'Repas'),
(3, 'Monuments');

-- --------------------------------------------------------

--
-- Structure de la table `Photo`
--

CREATE TABLE `Photo` (
  `photoId` int(11) NOT NULL,
  `nomFich` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `catId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Photo`
--

INSERT INTO `Photo` (`photoId`, `nomFich`, `description`, `catId`) VALUES
(1, 'DSC00011.jpg', 'Une perruche en cage', 1),
(2, 'DSC01212.jpg', 'Un chien en laisse', 1),
(3, 'DSC01422.jpg', 'Un canard dans l\'eau', 1),
(4, 'DSC01446.jpg', 'Une chèvre dans un pré', 1),
(5, 'DSC01040.jpg', 'Un plateau télé', 2),
(6, 'DSC01280.jpg', 'Quelque chose de sculpté', 3),
(7, 'DSC01436.jpg', 'Un monument lointain', 3),
(8, 'DSC01464.jpg', 'Un monument très très loin', 3),
(9, 'DSC02764.jpg', 'Un monument vu d\'en bas', 3);

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `userID` varchar(30) CHARACTER SET utf8 NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`userID`, `username`, `password`) VALUES
('0', 'kiki', '$2y$10$2oFkbWdvVY8NqBCf9uDBv.waKA2bOsa/0f..DsJTHCZdHq/w.CWX.');

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `login` varchar(50) CHARACTER SET utf8 NOT NULL,
  `mot` varchar(50) CHARACTER SET utf8 NOT NULL,
  `nbRepet` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`login`, `mot`, `nbRepet`) VALUES
('toto', 'hello', 7),
('titi', 'bonjour', 5);

-- --------------------------------------------------------

--
-- Structure de la table `VIP`
--

CREATE TABLE `VIP` (
  `VIPID` int(30) NOT NULL,
  `photoId` int(30) NOT NULL,
  `nomFich` varchar(30) NOT NULL,
  `VIPRENOM` varchar(30) NOT NULL,
  `VIPNOM` varchar(30) NOT NULL,
  `VIPAD` varchar(30) NOT NULL,
  `VIPNAT` varchar(30) NOT NULL,
  `CLASSATP` varchar(30) NOT NULL,
  `NBTOURNOIS` int(30) NOT NULL,
  `PRISECHARGE` int(30) NOT NULL,
  `COMPAGNON` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `VIP`
--

INSERT INTO `VIP` (`VIPID`, `photoId`, `nomFich`, `VIPRENOM`, `VIPNOM`, `VIPAD`, `VIPNAT`, `CLASSATP`, `NBTOURNOIS`, `PRISECHARGE`, `COMPAGNON`) VALUES
(1, 1, 'djokovic.png', 'Novak', 'Djokovic', '32', 'Serbie', '2', 16, 15000, 'Jelena Ristic');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Atome`
--
ALTER TABLE `Atome`
  ADD PRIMARY KEY (`Symbole`);

--
-- Index pour la table `Categorie`
--
ALTER TABLE `Categorie`
  ADD PRIMARY KEY (`catId`);

--
-- Index pour la table `Photo`
--
ALTER TABLE `Photo`
  ADD PRIMARY KEY (`photoId`),
  ADD KEY `fk_categorie` (`catId`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`userID`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Photo`
--
ALTER TABLE `Photo`
  ADD CONSTRAINT `fk_categorie` FOREIGN KEY (`catId`) REFERENCES `Categorie` (`catId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
