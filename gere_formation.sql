-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Mer 27 Septembre 2017 à 11:08
-- Version du serveur :  5.7.19-0ubuntu0.17.04.1
-- Version de PHP :  7.0.22-0ubuntu0.17.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `gere_formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `ecf`
--

CREATE TABLE `ecf` (
  `id` int(11) NOT NULL,
  `idstagiaire` int(11) NOT NULL,
  `idmodule` int(11) NOT NULL,
  `validate` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `ecf`
--

INSERT INTO `ecf` (`id`, `idstagiaire`, `idmodule`, `validate`) VALUES
(1, 2, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `formation`
--

CREATE TABLE `formation` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `duration` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `lieu` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `formation`
--

INSERT INTO `formation` (`id`, `name`, `duration`, `date_debut`, `lieu`) VALUES
(1, 'développeur ', 8, '2017-09-14', 'Morlaix');

-- --------------------------------------------------------

--
-- Structure de la table `gestion_formation`
--

CREATE TABLE `gestion_formation` (
  `idformation` int(11) NOT NULL,
  `idmodule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `gestion_formation`
--

INSERT INTO `gestion_formation` (`idformation`, `idmodule`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `gestion_sequence`
--

CREATE TABLE `gestion_sequence` (
  `idmodule` int(11) NOT NULL,
  `idsequence` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `gestion_sequence`
--

INSERT INTO `gestion_sequence` (`idmodule`, `idsequence`) VALUES
(1, 2),
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `module`
--

CREATE TABLE `module` (
  `id` int(11) NOT NULL,
  `name` varchar(256) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `module`
--

INSERT INTO `module` (`id`, `name`) VALUES
(1, 'WEB');

-- --------------------------------------------------------

--
-- Structure de la table `sequence`
--

CREATE TABLE `sequence` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `sequence`
--

INSERT INTO `sequence` (`id`, `name`) VALUES
(1, 'html'),
(2, 'css');

-- --------------------------------------------------------

--
-- Structure de la table `stagiaire`
--

CREATE TABLE `stagiaire` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `firstname` varchar(256) DEFAULT NULL,
  `adresse` varchar(256) DEFAULT NULL,
  `code_postal` varchar(256) DEFAULT NULL,
  `ville` varchar(256) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `telephone` varchar(256) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `idformation` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `stagiaire`
--

INSERT INTO `stagiaire` (`id`, `name`, `firstname`, `adresse`, `code_postal`, `ville`, `email`, `telephone`, `date_naissance`, `idformation`) VALUES
(2, 'joudar', 'hasnae', '18  alle jfjjfjf', '22190', 'plerin', 'hahahah@gmail.com', '06 06 06 06 06 ', '2017-09-21', 1),
(3, 'biddal', 'JC', '12 FHHFLHFHFLHF', '220000', 'SAINT BRIEUC', 'VGKJGKJF@GMAIL.COM', '01 02 01 01 02', '2017-09-08', 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idstagiaire` (`idstagiaire`),
  ADD KEY `idmodule` (`idmodule`);

--
-- Index pour la table `formation`
--
ALTER TABLE `formation`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `gestion_formation`
--
ALTER TABLE `gestion_formation`
  ADD KEY `idformation` (`idformation`),
  ADD KEY `idmodule` (`idmodule`);

--
-- Index pour la table `gestion_sequence`
--
ALTER TABLE `gestion_sequence`
  ADD KEY `idmodule` (`idmodule`),
  ADD KEY `idsequence` (`idsequence`);

--
-- Index pour la table `module`
--
ALTER TABLE `module`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `sequence`
--
ALTER TABLE `sequence`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idformation` (`idformation`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `ecf`
--
ALTER TABLE `ecf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `formation`
--
ALTER TABLE `formation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `module`
--
ALTER TABLE `module`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `sequence`
--
ALTER TABLE `sequence`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `ecf`
--
ALTER TABLE `ecf`
  ADD CONSTRAINT `ecf_ibfk_1` FOREIGN KEY (`idmodule`) REFERENCES `module` (`id`),
  ADD CONSTRAINT `ecf_ibfk_2` FOREIGN KEY (`idstagiaire`) REFERENCES `stagiaire` (`id`);

--
-- Contraintes pour la table `gestion_formation`
--
ALTER TABLE `gestion_formation`
  ADD CONSTRAINT `gestion_formation_ibfk_1` FOREIGN KEY (`idformation`) REFERENCES `formation` (`id`),
  ADD CONSTRAINT `gestion_formation_ibfk_2` FOREIGN KEY (`idmodule`) REFERENCES `module` (`id`);

--
-- Contraintes pour la table `gestion_sequence`
--
ALTER TABLE `gestion_sequence`
  ADD CONSTRAINT `gestion_sequence_ibfk_1` FOREIGN KEY (`idsequence`) REFERENCES `sequence` (`id`),
  ADD CONSTRAINT `gestion_sequence_ibfk_2` FOREIGN KEY (`idmodule`) REFERENCES `module` (`id`);

--
-- Contraintes pour la table `stagiaire`
--
ALTER TABLE `stagiaire`
  ADD CONSTRAINT `stagiaire_ibfk_1` FOREIGN KEY (`idformation`) REFERENCES `formation` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
