
--
-- Database: `toutdouxlist`
--

DROP TABLE IF EXISTS `liste`;
CREATE TABLE IF NOT EXISTS `liste` (
  `id_liste` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `ref_utilisateur` int NOT NULL,
  PRIMARY KEY (`id_liste`),
  KEY `fk_liste_utilisateur` (`ref_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `participe`;
CREATE TABLE IF NOT EXISTS `participe` (
  `ref_utilisateur` int NOT NULL,
  `ref_liste` int NOT NULL,
  PRIMARY KEY (`ref_utilisateur`,`ref_liste`),
  KEY `fk_participe_liste` (`ref_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `id_tache` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `description` varchar(50) NOT NULL,
  `est_realise` tinyint(1) NOT NULL DEFAULT '0',
  `ref_liste` int DEFAULT NULL,
  `ref_type` int DEFAULT NULL,
  PRIMARY KEY (`id_tache`),
  KEY `fk_tache_liste` (`ref_liste`),
  KEY `fk_tache_type` (`ref_type`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `id_type` int NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `code_couleur` varchar(50) NOT NULL,
  `ref_utilisateur` int NOT NULL,
  PRIMARY KEY (`id_type`),
  KEY `fk_type_utilisateur` (`ref_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mdp` varchar(50) NOT NULL,
  `valid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;



INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `email`, `mdp`, `valid`) VALUES
(1, 'hh', 'hh', 'saze.hugt@gmail.com', 'aa', 1);
INSERT INTO `type` (`id_type`, `libelle`, `code_couleur`, `ref_utilisateur`) VALUES
(1, 'Default', 'fff', 1);


ALTER TABLE `liste`
  ADD CONSTRAINT `fk_liste_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);


ALTER TABLE `participe`
  ADD CONSTRAINT `fk_participe_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`),
  ADD CONSTRAINT `fk_participe_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);


ALTER TABLE `tache`
  ADD CONSTRAINT `fk_ref_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ref_liste_new` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_tache_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`),
  ADD CONSTRAINT `fk_tache_type` FOREIGN KEY (`ref_type`) REFERENCES `type` (`id_type`),
  ADD CONSTRAINT `id_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`) ON DELETE CASCADE;


ALTER TABLE `type`
  ADD CONSTRAINT `fk_type_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);

  
COMMIT;
