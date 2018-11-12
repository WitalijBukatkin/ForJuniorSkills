-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: 127.0.0.1    Database: ForJuniorSkills
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Championship`
--

DROP TABLE IF EXISTS `Championship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Championship` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `sponsor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Championship_Sponsors_id_fk` (`sponsor`),
  CONSTRAINT `Championship_Sponsors_id_fk` FOREIGN KEY (`sponsor`) REFERENCES `Sponsors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Championship`
--

LOCK TABLES `Championship` WRITE;
/*!40000 ALTER TABLE `Championship` DISABLE KEYS */;
/*!40000 ALTER TABLE `Championship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Competence`
--

DROP TABLE IF EXISTS `Competence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Competence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Competence`
--

LOCK TABLES `Competence` WRITE;
/*!40000 ALTER TABLE `Competence` DISABLE KEYS */;
INSERT INTO `Competence` VALUES (1,'Test Competence'),(2,'Test Competence2'),(3,'Test Competence3'),(6,'Test');
/*!40000 ALTER TABLE `Competence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompetenceExperts`
--

DROP TABLE IF EXISTS `CompetenceExperts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CompetenceExperts` (
  `id` int(11) NOT NULL,
  `сompetence` int(11) NOT NULL,
  `expert` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `Competence` (`сompetence`),
  KEY `Expert` (`expert`),
  CONSTRAINT `CompetenceExperts_ibfk_1` FOREIGN KEY (`сompetence`) REFERENCES `Competence` (`id`),
  CONSTRAINT `CompetenceExperts_ibfk_2` FOREIGN KEY (`expert`) REFERENCES `Experts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompetenceExperts`
--

LOCK TABLES `CompetenceExperts` WRITE;
/*!40000 ALTER TABLE `CompetenceExperts` DISABLE KEYS */;
/*!40000 ALTER TABLE `CompetenceExperts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompetenceJuniors`
--

DROP TABLE IF EXISTS `CompetenceJuniors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CompetenceJuniors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `junior` int(11) NOT NULL,
  `competence` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `CompetenceJuniors_Competence_id_fk` (`competence`),
  KEY `CompetenceJuniors_Juniors_id_fk` (`junior`),
  CONSTRAINT `CompetenceJuniors_Competence_id_fk` FOREIGN KEY (`competence`) REFERENCES `Competence` (`id`),
  CONSTRAINT `CompetenceJuniors_Juniors_id_fk` FOREIGN KEY (`junior`) REFERENCES `Juniors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompetenceJuniors`
--

LOCK TABLES `CompetenceJuniors` WRITE;
/*!40000 ALTER TABLE `CompetenceJuniors` DISABLE KEYS */;
/*!40000 ALTER TABLE `CompetenceJuniors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CompetenceSponsors`
--

DROP TABLE IF EXISTS `CompetenceSponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CompetenceSponsors` (
  `id` int(11) NOT NULL,
  `sponsor` int(11) DEFAULT NULL,
  `competence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Competence` (`competence`),
  KEY `Sponsor` (`sponsor`),
  CONSTRAINT `CompetenceSponsors_ibfk_1` FOREIGN KEY (`competence`) REFERENCES `Competence` (`id`),
  CONSTRAINT `CompetenceSponsors_ibfk_2` FOREIGN KEY (`sponsor`) REFERENCES `Sponsors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CompetenceSponsors`
--

LOCK TABLES `CompetenceSponsors` WRITE;
/*!40000 ALTER TABLE `CompetenceSponsors` DISABLE KEYS */;
/*!40000 ALTER TABLE `CompetenceSponsors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Country`
--

DROP TABLE IF EXISTS `Country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Country`
--

LOCK TABLES `Country` WRITE;
/*!40000 ALTER TABLE `Country` DISABLE KEYS */;
INSERT INTO `Country` VALUES (1,'Russia'),(2,'USA'),(3,'Germany'),(4,'Italy'),(5,'France');
/*!40000 ALTER TABLE `Country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Experts`
--

DROP TABLE IF EXISTS `Experts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Experts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `User` (`user`),
  CONSTRAINT `Experts_ibfk_1` FOREIGN KEY (`user`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Experts`
--

LOCK TABLES `Experts` WRITE;
/*!40000 ALTER TABLE `Experts` DISABLE KEYS */;
INSERT INTO `Experts` VALUES (1,'Expert1',1);
/*!40000 ALTER TABLE `Experts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `JuniorSponsors`
--

DROP TABLE IF EXISTS `JuniorSponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `JuniorSponsors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `junior` int(11) DEFAULT NULL,
  `sponsor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Junior` (`junior`),
  KEY `Sponsor` (`sponsor`),
  CONSTRAINT `JuniorSponsors_ibfk_1` FOREIGN KEY (`junior`) REFERENCES `Juniors` (`id`),
  CONSTRAINT `JuniorSponsors_ibfk_2` FOREIGN KEY (`sponsor`) REFERENCES `Sponsors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `JuniorSponsors`
--

LOCK TABLES `JuniorSponsors` WRITE;
/*!40000 ALTER TABLE `JuniorSponsors` DISABLE KEYS */;
INSERT INTO `JuniorSponsors` VALUES (1,1,1),(2,1,2),(3,2,2),(4,2,2),(5,3,1);
/*!40000 ALTER TABLE `JuniorSponsors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Juniors`
--

DROP TABLE IF EXISTS `Juniors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Juniors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `sex` bit(1) NOT NULL,
  `birthday` datetime NOT NULL,
  `country` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `school` varchar(45) NOT NULL,
  `photo` longblob,
  `user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `User` (`user`),
  CONSTRAINT `Juniors_ibfk_1` FOREIGN KEY (`user`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Juniors`
--

LOCK TABLES `Juniors` WRITE;
/*!40000 ALTER TABLE `Juniors` DISABLE KEYS */;
INSERT INTO `Juniors` VALUES (1,'Виталий','Букаткин','','1999-01-07 00:00:00','Russia','vitaliy.bukatkin@gmail.com','604','�\��\�\0JFIF\0\0\0\0\0\0�\�\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342�\�\0C			\r\r2!!22222222222222222222222222222222222222222222222222��\0\"\0�\�\0\0\0\0\0\0\0\0\0\0\0	\n�\�\0�\0\0\0}\0!1AQa\"q2���#B��R\�\�$3br�	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz�����������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0\0\0\0\0\0	\n�\�\0�\0\0w\0!1AQaq\"2�B����	#3R\�br\�\n$4\�%\�\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz������������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0?\0\�\�^\�z\�\�\�e�\�\�\"�\�\�/\�G��\�\�?\�\\\�`\�tU`;���*:60T�\rFc\�dS���\�!�\�\���	��J~*�����=G��\�P2P�\�S\��\0\��\0�\��x\���\0\0Q�w��X\��\�t}\�?��\0\ZZ\0��5v\��ٷ\��\��U2�??e�P��n\�\�m>\�tj�C(�9\��Q\�\n�T���u��u2ƙ�,z�Q^\�Zi\�\�4��\0\�B7{�\�\�GT�Q��i��\�b�\�AN1�2���R\�\�	-�.\�\�\n=\�ֹ��j��\0Lh\�\�vq�Ʊ�r\�\ZM�y�z\�A#7&\�K�^���\��bs�\�<\�\�5\�A0�w&\�R�\�Ew�J�T\nL\�ź�6��\�J/�c\��U��&\���\�=�\0\n\�A\�Hr�*y�\�\�z�]0�H�!2v�֢ʍ\�s\�j\�n^&ʻ#	RFkj\�\�\�ֻCL\�\�8�n��f\�\�S=C�o֗C�kJ\�\�ԣ\�W$8�pG�֦\�\���\�M�&+M#\�ʿ�\�4�_o�\�E���a²\�C\�Қcv\�\�o�\�\�BF�=�JF�n>y	=r憄�;\�}�\�@I���\Z\�W�Z9��v\n�\0�(vѶ�I\�o\�Rn>�\�\�;m\�\�\�\������\0U8%?m(_�\0]0#e\�})�\nx(�(�ϥ8-</�8-+���?\�)Bԁ\np_O�&ӂ\����P��@?�:y�KW�T�\'�~qQ\�2[\�\�\�*�\�\'��5\�\�ֿ܊�Wyc\�$\�\�\�\�`\�N*\�nüY���\��\�\�E	x=2?\�+�\'\�4�z\�\��[-�m��\�\��c�\�\�J\�\�H0\Z(\'m\rI�`�\0\�F\�n\\zSqHC�\�\�J�W�\�4�\�\�z\\���\�e�*\�\��\0�w�?��o6�rW�O(\�C\�\�6kU�s�r0zTJ7.2�\�n\�ߊB?\�k�\�\�\�T�S�\�\�9�/��t\��Edi{�\"�ݽ�=�}��b\�}\�\�P!w1\��z~u(M��֚W�\0C�n?ȩ���M	Znژ�&\�`G���m�e)Oʴ\�\�f�5n�y\�X^Ō�gu9twe$_i�q}�\�4�\�m�e\�FͭZ3\�WV��o������\0Ǫ�$�ʻ矒hۏPsK`-<-G\�\�u\�\�MJ�[�Y����4��\�\�]ݏ\�G�ԋn�{|\��M !\�A�xq\�]sq\�|\�3\��\0\�Vn�^\�\�Wr������p�d\��\�\�\'\�:�f[w;W摳\��\�\��^�\�7oN�j�\�\�o$�~�\���pSc��h�#;�\���\���\nw\�_sI�\�\����i�~QMs\Z�\�0H`�u8\�V���Hm�\�\�\Z��vъ�$EX�U\�\�\�ӹ�C1F6\�\�\����LB})\�\�\�J\�Z\0\�\�\�\�A�D���Lv1\�C\��νYj�~lgּR0|��\�\�\�{W�-\�[C��[+�2�K�G��VS�oCX�,8���͑��\�f�\�\�k�\�\�\�$R��aު�\�*�@|�P\�4\�D\�SH���\��\�\�\��}I�\0��\0R�\�M\�A�����\�\����\Z\�f)\Z\�\����*?�A\�\��#�0\�*\��(�<\�i�č�\0RG\�(�FV�9�εy?st6�8 H:g\�\��/\�\���\��S���#*GN*\�\��|\�\�B�\�0\�>g(\�\��\�O\���\�R7�@R@X\r�:��\0Z�JV\�.��Wrn-ϔA\0��8o\��VrE6�u�yf��9R{U\�>y,/�Y@Eo\�\\\�\�\�HkR�\� �-\n\�%`�A\�\�(\�\�\�\�\�\�>R��{�\��\0>Իj���.�\�\�`۹E\�9S\���:\�\�R\� \�J�M��- !+\��\0\\W\r\�{\�ܖ�\n�\0\�O\�`�ӧ\�]�\�\�<q��\0���\�\�2\���\�R\�\�\�\�I\�\�Q-J�\0v��ksB\�o�=c݁R\"|���Ոa\�\��l�=Wl\�T\�2�o|\Z�hv\\)<W��2�\�\�Ce\�#b�mZ�aܣ\�S M�Q�\�\�\�uG\�+&\�\�V2n����\�Te�>�\Z\�\�0\�y\�U0\'\�n���R�2�g<km?�\���/ZԸ�\�\�8\�\�P5�ۺ.Gq\�W\�b\�V*x�v�j�!~��\��Zi�\�\�09\�:zW��:\�\�t�&3\�,Y\'+3*)\�@\0\�NG��.^\�\��7K^\�\�\�$\�\�@\�\�ȩ���\r%\���\ZI[�;~�?�\�KR㷥7�\��\�M\�S��\�  \�I��\�&\�\0���\�E��\��jw�Qӹ�\�\�v�XEK��.h����I;0Yw\�\��p\��\0Z�ak%��\�\�aT����\�\'��\�\�3\�]�?�?*w�?�?*��c�\�4y6\�vm�\�9\�\�9b�\�\�GQ\�~�,v�Q�X�\�q� \�\�\�Q\�gڷ(��Q�S��\�Qq\�@�p\�9�\�\�X����C\�v\�\�\��s޺�\�&F6�\� \�kݐ��ӁZ>Oڴ\�,�\�g\�4�\�\�\�=c\�@AK\�H\�\�SǮCD?zI@\�	��\�\�\�\�\�\'\�+�[٦y.H�_֪;�\�y��,\�\�Oݓ\�s\r�\�b�\�[#\�\�g9�\�\�\�\�\�vx\�\n�YE6YU#\�=1Y\�J[��T$٣��z\�\�\�\��\�J6\�8#�VO\�\�ԑ��!\�nnV`{��I�y\�Y�\�j�b\�Y�l�L\�c���&����\�M�����{�����c�C\�\�q\��,\�\�x\�\�Tǵ/�\��\0몲3摷n�*�\�\Z���\�a�CN�yՆ�\Z��ž̷�\�Jі\�F\�\�\���\�^E�,�q\�3\\<_\�\�5\��)\'�\"��:\�^�\�U=���ڄ�\�\�c<����K�H\�\���Y\Z�\�I��P\�\�~�(ݕ�\�׹\��*�U9�\r�2X¼~�^Xcv���c\�m\�#�yazg\�\\�h\�]\�@�h\��FFe8\"�˭o!/!ܲ,+�%>\��3ګ�\�^��\��\0ע\�)yT�:��\�\�\�\��\0^��\�\�v���tvD�X��\0U01>}\�MX��\�G\�3R,6��.N둡!2�ͫ\�\�I�4lT���q]�o\�o~g#\�M�D}Yx?J\�Zw�o\�/��\r\n2\�\�ן�D!o�c\�;\�\�\�V��\0?Rߟ��plm�\�\�\�\�G�㔫\�=�R=s\�T�\'�\�2/\�ElG\�ϦMg\�ۂ=\�[M�8_j\�}	��0gAP��\0=\�jKg�&�Im��i�7�:o_ʢ\��\0\���~n�\�\�Ko�x�i�	]�Y.�6�\�KDۚ�g\�\�F28�l\�_\�\\\�z�P���Hw:j�6\�\�\\V\�\�M��z\�l�1\���\�qh\�q}\nkx�\�\�\����ͅ�\�#R#�>Y�j\�%�%?)����\�ԕ���e@\�l�C�/a�\n����՛����G\'\�\�\�z�-B\�\�0\�s\�Ǧ\0?\�\��=�\"\�ʊ2X\��wAӿ�����|\�u\�\nsf1E\�.�R�-\�:M\�\�\�TE\�T�\�f\�M��\�Y�Eɡ|\�`�31�\0\��^WH\��\�\�\�?\�(˧��`�C�#�?\�5WeX��\�\���Ӹtj\�\��\0�\�\�\��\0\n\0�\"�����i�H\�\�7\0ē\�8�\0\�Q@]۳\�̲��#��b\�\�m\���y��\�i�\\=���\0\�՛3\Z��3�\�^\�=��^U8EW��0L\�7U8<u�D����\��\0צ/&�\"�\�8;\��\0\�\��\0^�Xm?缙�\0�_�zhM�Y\�\�\�&\�\�\�\��\�Ǹ\��VI��\�L��*\�\��<��\�\�\�\��C#(\�@	�#�5M]��\'�T\�M*=KO�\�_�\"\�\�5\�-紟\�\��\0�I\�Z\�i�\�\��\0\Z,6\�\�jmu��\�>\�Gr\�|�\�[?\��e\�\'�\�sA0Wc��s\��\�I�B;�\�\�T�\r\�Yj�����Nƥl;�\n!{sV�T$�˩�q\�I\�U��TUut^\�7���7J���\�Xn{Hbhf�\�5�@\"\�S&)\�ZM��PH�b�Q\�튮�q�n�<�\�\\\������3�5l.<Mj:�7v=q��+\�|�\\O\�}.5�FyQ\�<�\�Bw�\�z\�^�\�[_��_�\��\0\�N[�&��U(�v\0$\�\0:֣Z��7Q��?�\0WV\�4\��יӮc7ȧ��p}=)Z\�rF\\�\�+�r\��~\��\�\�\��>^\�\�\�cnZuϰo\�\�`�\0�\��\0|��P;��U\'�Z~T?\�\�\�\'�)<�{L?h�\�\Z�\r�pB.n\�c�4��\�\��U\�k\r�\�8Y&\�Rݿ\�\�$h����̌rIRj��7��s$�n|\0@¨\�\0\�Eh}�\��{�\0ㆊC,�zx���\���*��~B��}���tC�\��\�\��\0g\��ѕ�A\0qS^\��\�� >^ᴅ\�~\�8<��RW!�S,[ҋzK\'yc�\�\�\�stc�\��x�j}���[I�\�)\�\�9_\�q�jA\�*\�W\�b�E�3\�d[J�h\�4\�Z\�>[��Gc��\�>�����X\�w?\��\�pG򪎺)�\�G٫L\�\�\��\0�$\��\0�EIW<\�\�U�\�{;�\�|dz\�#\\G�\�1\�^\�\�o\���I�m��Ż\�H\�x#g�\��W�\�i��j�7ֲ[\�\�A��\���v)3\Zs\r�5\�,�SY\�r:/Z\�o��J��\�\r�\�4��<zZr\�\"�\'�\�Zhv�28�\�\��Z^�\�0ǵi\�۹\�P-�n\r���mg/\"\�\�@�4�\�Z�\�68e��µ�\�٢��\�\�\�#\���ƻ-\�\�\��f�\�\"�\�O^2zUh�\�\�O/���(I�F^��\�Q\�s\�V�\�H\�\�8\�-a�!�`��\�I\�kZ\�=\�f�#\�`n>������\�[d��n�M\�y�3\��\0x�\n�0�\�Fw\�3�{ּ�$�(��\�)�����\0ߵ�\�A+\�d}�\�}���[d\�O�\��:8&���RO�#��\�\�[Gv\n����\0\�V\�$���{�\�v�\�\�}\�c쑭�r�S*��\0e\�MI<{�\�p<����\�>��zzը\�#�.Ĝ�y$\���9\���H-�\���O-C1\�z��`~�h\�A	e�\0	�=zt$\�\���=�9��Q];\�\�\�\�\�\Z;\0�h\�ܚ)\�\rN�\"1\0\�G\�R,\�*��\0�8\�sR�m\��\�\��*-��\��\�@(\�֭*\npj,\�U.eK�A-��9w#���\�\�e\�m�e|�G\��~��h\�\�r�\�\�\�\�\"{�1l0\�p:�\�\�R\r5�]`\�\��\�z�j)\n\�a��O%=*J)\�\"\�Ҽ\�⾝�\0\�+\�_\�r�l}�\��פ\�7�t\�\�\r\�م\�#&\�\��K\��X�h\�EFM3\�~V\"�9\�Wn���\�|�\�t�F\�}j)&�.z�J�/�\�\�\�&�\�\�{\n9G\�\�>\���}i~֋ʧ>\��^\�Q\�A�\0=8\��ʇy�\�#f�\�;?�<�\���\0�wcJ\�x\�*;\�8��\�l+n�3\n���F\�w�}*\Z*\�\�n\�:�ҽ��\�X�\�\�33\�\�tʼ�H�}GR���e\�p��S_GXi\�\�\�\�m�\"@������\�\"�V�\'!G^*w�>\0QF\0�h�;R\��J\�\�S0ZC�Sŧ�\�Z8�{0\��\���@1\�M�\�Fڈ\�a�1�%��\�V��T\�\';�?a��v\�\nF8\��\0\�~9�=��\�+&0S�\rjb�����l�|�\�Nݣ\��\�.�\�iQ\�GO3-�\�\�WqF)��\�\�\�rrI\���1E/fW9Ҝ>�_\�\�\��t�k�\0�g\�x\�v,\�U�\��\0L\�\�G\�?\��\��\�ܞIv,\�U~\��\0LO\�HoO�\�?�\�\�>Iv-\�U>\�\�\���\0\�T�mo�\�\�|\�\��\�\\�����\0\��\0��>\��\0\�\�\�^\�=\�\�˱v��u�\0�v�\0��0\�l�\�\�\�\�У\�G�{9v<[\�7�Α�H\�-���\";g\�/\�O\�Ey�\�k\Z�+\�6\���I{)mY\�H|�\��=Ex&��\�at\�N�$S\�\"�%-��ijec\�dCS�\�JWw��=\�銅�\�LCґa\\vexЭX\0S��\�i\�P\�ʱ�Z�\�\�������tz��\�-m[\�Y+\�\�\�=\�O�\�I�Θ�,\�o�\�o\�oç�\0��j��뤰��\�\�\�ˆ��\�W�\�ٿ\�Dj\�\�sʜ۽�\Z+?�I�\0\�\��\0\�T�\�O�\0>����\��\�G��cF�\��ӓ�}O�\��\0֤:��\�\�\���\�\�\�\�Ң���7��\��\0�j?���\0�?���\0֣\�ø{)\�5h��\�i�\�\��\0\"\���֛�}?\�\'�\0Z���p\�S\�jQY�T���\�\��\0�K��7������\0֥\�a\�=��\ZTVw\��\�\�\�?\�\��\0Z�=�{�\�˰\�톭\�Z6�\�ز��W\�4yukb�\�1{B��J#�8�{&\�ʤ1՜Q�~͋����<���1Kٱ󲩇ڢh���)\n\n�I�Tf�}e�i\�\�_\�!��e�\�\�\0w\'Ҽ#ĺ\�:\�=\�1�v\�U_�nx\�\�O\�\�&�\��j\"\�i�@F\��.?\�^w\�f�П\�[S�ʮ\�U9�����}=\�S$\�\�֫�Yz\�\�b\�jho�\�T\�zO>���sCx�3U/�m\�J�_�O(��\\��\�\��+[��9fRU\�6*A\�N\reƵa�\�d�\0t�\0*�y���|kӚ\�8��:\�)\�a��ã{\��GӚ\���B\�2�\�B9�Q����(\�*p�\�qj��\�:u\�7���70�\�\�Cʟ\��\�,4^\�^\�\�>\�{R�k��u\�\�\��d�E�W�3$c\�ʽ\�?�\�\�+5A�\�\�f\Za��\�Rm{\n�3\�>\�y5��hؾ�{W�f�=)|�j\�ڴ�G�?`\�\�YDE\�N{Uͣ\�R\��{/jT{QV\��)�/hŢ�+�\�(��\0(��\0(��\0(��\0*����\�^\�\�\n3rq�\�\�kŖ:B��͔<��!\�nu끼�\�a#S�\�?Z\�4�\�d\�\�\�{��&���سz�\�5F\�M��\�\"���s\Z\�/��Z8\�X#;;�2\n�*{T�\�G�\�\r��\Z�ԝ\�\r0\�\��n3�DIY�)T\�\�\�re%bdi.�m����J\rS\�$\�jG\���[��|д\�J�\�r��դ\�5�=F��Ҭ�Ccv#>\�#\�x��բ\\2	쮡R>f>\�\��\�mL��\�h\���ᥐ*��\�\�a\�\���\�\��\�UM��jK\�#~G\�Z\�}jx\�{�\�\�\�\�\�\�\n�Aik\�]~�\�q\�\�Yq�U�|]�D\�]R\�z~��\0ƕ�\�\�W\�z_\�-~̀{ߴ 9+:�\�\�\�\��\�\�\�\Z6\�}����\��\�\��\�J+����\�\�\�6��\�\��{�\0���+��x�!Y`�d��Uс=�&�\�	(��@QE\0QE\0VMω4�Y\�Y\�\��\��\�\�H�A\�X֑�9l��\�w;�8���g\�\�˩YE\�\�b\�*\�=K\�77�y	\�\��iu���:\�a��f2��#\�\�|_�Z�\�\�B=+�\�|ys:�B|�<aO_Ƹi�\'n�\��\�\�\�w�U8Gb�-\�wR\�d�%���\�=s9f\�?�,\���\�S&ZV#�J�\�R�Ug5\r�!\�1_�YIww�\�B\�S�c%sX\�Ɠ\�\�P$\�\�\�}\�\�Jɦl�N��\0\�T�\�~uH�\�4Ze�\���K��A\�\�\�	���\�U$\�\�?�8ǩ��)♚PkC\"\\\���A�@��\�\�\�\0Ӂ��\�\"�V�S@�\�u�\�%jxj�M�+���WK\��\0\���n�W����}A�5^�\�%s\��\�O\��m��#|�\�\'�֊\�;ǁ�+�A\��Ҋ�A\����*9��\��W	\Z\�Ǡ�j\Z���f\�Wr�\�Q\�=��\�k\�|E\�\�Rg�\�6��0�\�q\���\�x�MgPf\�\�\"�=�\�\� e\�\�\���P[�KCTߝ�\�U��\�\�Qyj�K���\�\r_sE��Z�\�\�T\�Q4�\\�|\�i&�J�\�e�I$�RF\�H\�u�]\�6;[�B\�4\�\�2\�\�L-Nj�\��4�s�CQM\�K,�\��E/ڟ\��V\�HZ��rR\�\�O&�\�ݣ4\0�\�\�\�8\Z\0��\Z�u(42�p54\�4��5 55 42�Pj0i�\���2=V\�H�LE\�ފ��b>�\�\�l\�\�\��\0\Z\��\�\�$\�-_m�\r�B\�q\�\�>�\�x�\�\�>��������\�O\�^5\�v%�$\�I=k\nv��\�<��Or]��G��\�\�O\�]pG<�\�H\�\0m\�Ԓ5G���\�K\�.�i\�z&�\�U\�N�<�]ڳl����R�ɩl�&�[ҚZ�MEƐ\�۩�i��7T���i���(i��\"�i\\����斀\r8\Z`4�\0�\\\��\�%�������RPRf�J\r=MB\r=M0\'��V��B\'SEF\rb�\�5S-\� ���\�\���O\��\�\�U�[P7��\�\�u�\�`\��\�1Y�\�)G�)\ZU�\�\�iOJs?Z�S%��N\�\�oQ\�\'\�Aq��\�A~Ԯ$w�\�\�֒�w�\�XGj���ڡf��hqji4\�\�f�\Za4M5% &�iI�R\�\�4��&i)]Թ�\Zu �Lњ\0~isL�\n`;4�\�3HD�Ӂ���@\��\���`J\r=M@\rH���\�Ԁ\�\njEn�\�L\r\�4U\\rI���\�5DZ�\�Be��j����-\�U����3SwS�Vj�����f��\�Q�JM35�Bi���Ci��&�MM\�!4\�h&�M!��nh\��B\�\�QH\�\0\�Q�,\�K�`4\�\�1sN�\�@\nPi�Ӂ�C\�.i�\�恏�SQNSLD\�\���A�T�\�T@\�@ɷT�\�QZD�\rv�sE\�H���\�QJ\�!f�\�QEK)&��(�P��&�)0BL\�T�ni	��%-Rb\n(��\0��*�)A��@(4���(�E�\\њ(�C��E~i�\�ER\�\�QE\0�\�',1),(2,'Леха','Еремин','','1999-05-10 00:00:00','Russia','lexa@gmail.com','1105','�\��\�\0JFIF\0\0\0\0\0\0�\�\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342�\�\0C			\r\r2!!22222222222222222222222222222222222222222222222222��\0\"\0�\�\0\0\0\0\0\0\0\0\0\0\0	\n�\�\0�\0\0\0}\0!1AQa\"q2���#B��R\�\�$3br�	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz�����������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0\0\0\0\0\0	\n�\�\0�\0\0w\0!1AQaq\"2�B����	#3R\�br\�\n$4\�%\�\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz������������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0?\0\�\�^\�z\�\�\�e�\�\�\"�\�\�/\�G��\�\�?\�\\\�`\�tU`;���*:60T�\rFc\�dS���\�!�\�\���	��J~*�����=G��\�P2P�\�S\��\0\��\0�\��x\���\0\0Q�w��X\��\�t}\�?��\0\ZZ\0��5v\��ٷ\��\��U2�??e�P��n\�\�m>\�tj�C(�9\��Q\�\n�T���u��u2ƙ�,z�Q^\�Zi\�\�4��\0\�B7{�\�\�GT�Q��i��\�b�\�AN1�2���R\�\�	-�.\�\�\n=\�ֹ��j��\0Lh\�\�vq�Ʊ�r\�\ZM�y�z\�A#7&\�K�^���\��bs�\�<\�\�5\�A0�w&\�R�\�Ew�J�T\nL\�ź�6��\�J/�c\��U��&\���\�=�\0\n\�A\�Hr�*y�\�\�z�]0�H�!2v�֢ʍ\�s\�j\�n^&ʻ#	RFkj\�\�\�ֻCL\�\�8�n��f\�\�S=C�o֗C�kJ\�\�ԣ\�W$8�pG�֦\�\���\�M�&+M#\�ʿ�\�4�_o�\�E���a²\�C\�Қcv\�\�o�\�\�BF�=�JF�n>y	=r憄�;\�}�\�@I���\Z\�W�Z9��v\n�\0�(vѶ�I\�o\�Rn>�\�\�;m\�\�\�\������\0U8%?m(_�\0]0#e\�})�\nx(�(�ϥ8-</�8-+���?\�)Bԁ\np_O�&ӂ\����P��@?�:y�KW�T�\'�~qQ\�2[\�\�\�*�\�\'��5\�\�ֿ܊�Wyc\�$\�\�\�\�`\�N*\�nüY���\��\�\�E	x=2?\�+�\'\�4�z\�\��[-�m��\�\��c�\�\�J\�\�H0\Z(\'m\rI�`�\0\�F\�n\\zSqHC�\�\�J�W�\�4�\�\�z\\���\�e�*\�\��\0�w�?��o6�rW�O(\�C\�\�6kU�s�r0zTJ7.2�\�n\�ߊB?\�k�\�\�\�T�S�\�\�9�/��t\��Edi{�\"�ݽ�=�}��b\�}\�\�P!w1\��z~u(M��֚W�\0C�n?ȩ���M	Znژ�&\�`G���m�e)Oʴ\�\�f�5n�y\�X^Ō�gu9twe$_i�q}�\�4�\�m�e\�FͭZ3\�WV��o������\0Ǫ�$�ʻ矒hۏPsK`-<-G\�\�u\�\�MJ�[�Y����4��\�\�]ݏ\�G�ԋn�{|\��M !\�A�xq\�]sq\�|\�3\��\0\�Vn�^\�\�Wr������p�d\��\�\�\'\�:�f[w;W摳\��\�\��^�\�7oN�j�\�\�o$�~�\���pSc��h�#;�\���\���\nw\�_sI�\�\����i�~QMs\Z�\�0H`�u8\�V���Hm�\�\�\Z��vъ�$EX�U\�\�\�ӹ�C1F6\�\�\����LB})\�\�\�J\�Z\0\�\�\�\�A�D���Lv1\�C\��νYj�~lgּR0|��\�\�\�{W�-\�[C��[+�2�K�G��VS�oCX�,8���͑��\�f�\�\�k�\�\�\�$R��aު�\�*�@|�P\�4\�D\�SH���\��\�\�\��}I�\0��\0R�\�M\�A�����\�\����\Z\�f)\Z\�\����*?�A\�\��#�0\�*\��(�<\�i�č�\0RG\�(�FV�9�εy?st6�8 H:g\�\��/\�\���\��S���#*GN*\�\��|\�\�B�\�0\�>g(\�\��\�O\���\�R7�@R@X\r�:��\0Z�JV\�.��Wrn-ϔA\0��8o\��VrE6�u�yf��9R{U\�>y,/�Y@Eo\�\\\�\�\�HkR�\� �-\n\�%`�A\�\�(\�\�\�\�\�\�>R��{�\��\0>Իj���.�\�\�`۹E\�9S\���:\�\�R\� \�J�M��- !+\��\0\\W\r\�{\�ܖ�\n�\0\�O\�`�ӧ\�]�\�\�<q��\0���\�\�2\���\�R\�\�\�\�I\�\�Q-J�\0v��ksB\�o�=c݁R\"|���Ոa\�\��l�=Wl\�T\�2�o|\Z�hv\\)<W��2�\�\�Ce\�#b�mZ�aܣ\�S M�Q�\�\�\�uG\�+&\�\�V2n����\�Te�>�\Z\�\�0\�y\�U0\'\�n���R�2�g<km?�\���/ZԸ�\�\�8\�\�P5�ۺ.Gq\�W\�b\�V*x�v�j�!~��\��Zi�\�\�09\�:zW��:\�\�t�&3\�,Y\'+3*)\�@\0\�NG��.^\�\��7K^\�\�\�$\�\�@\�\�ȩ���\r%\���\ZI[�;~�?�\�KR㷥7�\��\�M\�S��\�  \�I��\�&\�\0���\�E��\��jw�Qӹ�\�\�v�XEK��.h����I;0Yw\�\��p\��\0Z�ak%��\�\�aT����\�\'��\�\�3\�]�?�?*w�?�?*��c�\�4y6\�vm�\�9\�\�9b�\�\�GQ\�~�,v�Q�X�\�q� \�\�\�Q\�gڷ(��Q�S��\�Qq\�@�p\�9�\�\�X����C\�v\�\�\��s޺�\�&F6�\� \�kݐ��ӁZ>Oڴ\�,�\�g\�4�\�\�\�=c\�@AK\�H\�\�SǮCD?zI@\�	��\�\�\�\�\�\'\�+�[٦y.H�_֪;�\�y��,\�\�Oݓ\�s\r�\�b�\�[#\�\�g9�\�\�\�\�\�vx\�\n�YE6YU#\�=1Y\�J[��T$٣��z\�\�\�\��\�J6\�8#�VO\�\�ԑ��!\�nnV`{��I�y\�Y�\�j�b\�Y�l�L\�c���&����\�M�����{�����c�C\�\�q\��,\�\�x\�\�Tǵ/�\��\0몲3摷n�*�\�\Z���\�a�CN�yՆ�\Z��ž̷�\�Jі\�F\�\�\���\�^E�,�q\�3\\<_\�\�5\��)\'�\"��:\�^�\�U=���ڄ�\�\�c<����K�H\�\���Y\Z�\�I��P\�\�~�(ݕ�\�׹\��*�U9�\r�2X¼~�^Xcv���c\�m\�#�yazg\�\\�h\�]\�@�h\��FFe8\"�˭o!/!ܲ,+�%>\��3ګ�\�^��\��\0ע\�)yT�:��\�\�\�\��\0^��\�\�v���tvD�X��\0U01>}\�MX��\�G\�3R,6��.N둡!2�ͫ\�\�I�4lT���q]�o\�o~g#\�M�D}Yx?J\�Zw�o\�/��\r\n2\�\�ן�D!o�c\�;\�\�\�V��\0?Rߟ��plm�\�\�\�\�G�㔫\�=�R=s\�T�\'�\�2/\�ElG\�ϦMg\�ۂ=\�[M�8_j\�}	��0gAP��\0=\�jKg�&�Im��i�7�:o_ʢ\��\0\���~n�\�\�Ko�x�i�	]�Y.�6�\�KDۚ�g\�\�F28�l\�_\�\\\�z�P���Hw:j�6\�\�\\V\�\�M��z\�l�1\���\�qh\�q}\nkx�\�\�\����ͅ�\�#R#�>Y�j\�%�%?)����\�ԕ���e@\�l�C�/a�\n����՛����G\'\�\�\�z�-B\�\�0\�s\�Ǧ\0?\�\��=�\"\�ʊ2X\��wAӿ�����|\�u\�\nsf1E\�.�R�-\�:M\�\�\�TE\�T�\�f\�M��\�Y�Eɡ|\�`�31�\0\��^WH\��\�\�\�?\�(˧��`�C�#�?\�5WeX��\�\���Ӹtj\�\��\0�\�\�\��\0\n\0�\"�����i�H\�\�7\0ē\�8�\0\�Q@]۳\�̲��#��b\�\�m\���y��\�i�\\=���\0\�՛3\Z��3�\�^\�=��^U8EW��0L\�7U8<u�D����\��\0צ/&�\"�\�8;\��\0\�\��\0^�Xm?缙�\0�_�zhM�Y\�\�\�&\�\�\�\��\�Ǹ\��VI��\�L��*\�\��<��\�\�\�\��C#(\�@	�#�5M]��\'�T\�M*=KO�\�_�\"\�\�5\�-紟\�\��\0�I\�Z\�i�\�\��\0\Z,6\�\�jmu��\�>\�Gr\�|�\�[?\��e\�\'�\�sA0Wc��s\��\�I�B;�\�\�T�\r\�Yj�����Nƥl;�\n!{sV�T$�˩�q\�I\�U��TUut^\�7���7J���\�Xn{Hbhf�\�5�@\"\�S&)\�ZM��PH�b�Q\�튮�q�n�<�\�\\\������3�5l.<Mj:�7v=q��+\�|�\\O\�}.5�FyQ\�<�\�Bw�\�z\�^�\�[_��_�\��\0\�N[�&��U(�v\0$\�\0:֣Z��7Q��?�\0WV\�4\��יӮc7ȧ��p}=)Z\�rF\\�\�+�r\��~\��\�\�\��>^\�\�\�cnZuϰo\�\�`�\0�\��\0|��P;��U\'�Z~T?\�\�\�\'�)<�{L?h�\�\Z�\r�pB.n\�c�4��\�\��U\�k\r�\�8Y&\�Rݿ\�\�$h����̌rIRj��7��s$�n|\0@¨\�\0\�Eh}�\��{�\0ㆊC,�zx���\���*��~B��}���tC�\��\�\��\0g\��ѕ�A\0qS^\��\�� >^ᴅ\�~\�8<��RW!�S,[ҋzK\'yc�\�\�\�stc�\��x�j}���[I�\�)\�\�9_\�q�jA\�*\�W\�b�E�3\�d[J�h\�4\�Z\�>[��Gc��\�>�����X\�w?\��\�pG򪎺)�\�G٫L\�\�\��\0�$\��\0�EIW<\�\�U�\�{;�\�|dz\�#\\G�\�1\�^\�\�o\���I�m��Ż\�H\�x#g�\��W�\�i��j�7ֲ[\�\�A��\���v)3\Zs\r�5\�,�SY\�r:/Z\�o��J��\�\r�\�4��<zZr\�\"�\'�\�Zhv�28�\�\��Z^�\�0ǵi\�۹\�P-�n\r���mg/\"\�\�@�4�\�Z�\�68e��µ�\�٢��\�\�\�#\���ƻ-\�\�\��f�\�\"�\�O^2zUh�\�\�O/���(I�F^��\�Q\�s\�V�\�H\�\�8\�-a�!�`��\�I\�kZ\�=\�f�#\�`n>������\�[d��n�M\�y�3\��\0x�\n�0�\�Fw\�3�{ּ�$�(��\�)�����\0ߵ�\�A+\�d}�\�}���[d\�O�\��:8&���RO�#��\�\�[Gv\n����\0\�V\�$���{�\�v�\�\�}\�c쑭�r�S*��\0e\�MI<{�\�p<����\�>��zzը\�#�.Ĝ�y$\���9\���H-�\���O-C1\�z��`~�h\�A	e�\0	�=zt$\�\���=�9��Q];\�\�\�\�\�\Z;\0�h\�ܚ)\�\rN�\"1\0\�G\�R,\�*��\0�8\�sR�m\��\�\��*-��\��\�@(\�֭*\npj,\�U.eK�A-��9w#���\�\�e\�m�e|�G\��~��h\�\�r�\�\�\�\�\"{�1l0\�p:�\�\�R\r5�]`\�\��\�z�j)\n\�a��O%=*J)\�\"\�Ҽ\�⾝�\0\�+\�_\�r�l}�\��פ\�7�t\�\�\r\�م\�#&\�\��K\��X�h\�EFM3\�~V\"�9\�Wn���\�|�\�t�F\�}j)&�.z�J�/�\�\�\�&�\�\�{\n9G\�\�>\���}i~֋ʧ>\��^\�Q\�A�\0=8\��ʇy�\�#f�\�;?�<�\���\0�wcJ\�x\�*;\�8��\�l+n�3\n���F\�w�}*\Z*\�\�n\�:�ҽ��\�X�\�\�33\�\�tʼ�H�}GR���e\�p��S_GXi\�\�\�\�m�\"@������\�\"�V�\'!G^*w�>\0QF\0�h�;R\��J\�\�S0ZC�Sŧ�\�Z8�{0\��\���@1\�M�\�Fڈ\�a�1�%��\�V��T\�\';�?a��v\�\nF8\��\0\�~9�=��\�+&0S�\rjb�����l�|�\�Nݣ\��\�.�\�iQ\�GO3-�\�\�WqF)��\�\�\�rrI\���1E/fW9Ҝ>�_\�\�\��t�k�\0�g\�x\�v,\�U�\��\0L\�\�G\�?\��\��\�ܞIv,\�U~\��\0LO\�HoO�\�?�\�\�>Iv-\�U>\�\�\���\0\�T�mo�\�\�|\�\��\�\\�����\0\��\0��>\��\0\�\�\�^\�=\�\�˱v��u�\0�v�\0��0\�l�\�\�\�\�У\�G�{9v<[\�7�Α�H\�-���\";g\�/\�O\�Ey�\�k\Z�+\�6\���I{)mY\�H|�\��=Ex&��\�at\�N�$S\�\"�%-��ijec\�dCS�\�JWw��=\�銅�\�LCґa\\vexЭX\0S��\�i\�P\�ʱ�Z�\�\�������tz��\�-m[\�Y+\�\�\�=\�O�\�I�Θ�,\�o�\�o\�oç�\0��j��뤰��\�\�\�ˆ��\�W�\�ٿ\�Dj\�\�sʜ۽�\Z+?�I�\0\�\��\0\�T�\�O�\0>����\��\�G��cF�\��ӓ�}O�\��\0֤:��\�\�\���\�\�\�\�Ң���7��\��\0�j?���\0�?���\0֣\�ø{)\�5h��\�i�\�\��\0\"\���֛�}?\�\'�\0Z���p\�S\�jQY�T���\�\��\0�K��7������\0֥\�a\�=��\ZTVw\��\�\�\�?\�\��\0Z�=�{�\�˰\�톭\�Z6�\�ز��W\�4yukb�\�1{B��J#�8�{&\�ʤ1՜Q�~͋����<���1Kٱ󲩇ڢh���)\n\n�I�Tf�}e�i\�\�_\�!��e�\�\�\0w\'Ҽ#ĺ\�:\�=\�1�v\�U_�nx\�\�O\�\�&�\��j\"\�i�@F\��.?\�^w\�f�П\�[S�ʮ\�U9�����}=\�S$\�\�֫�Yz\�\�b\�jho�\�T\�zO>���sCx�3U/�m\�J�_�O(��\\��\�\��+[��9fRU\�6*A\�N\reƵa�\�d�\0t�\0*�y���|kӚ\�8��:\�)\�a��ã{\��GӚ\���B\�2�\�B9�Q����(\�*p�\�qj��\�:u\�7���70�\�\�Cʟ\��\�,4^\�^\�\�>\�{R�k��u\�\�\��d�E�W�3$c\�ʽ\�?�\�\�+5A�\�\�f\Za��\�Rm{\n�3\�>\�y5��hؾ�{W�f�=)|�j\�ڴ�G�?`\�\�YDE\�N{Uͣ\�R\��{/jT{QV\��)�/hŢ�+�\�(��\0(��\0(��\0(��\0*����\�^\�\�\n3rq�\�\�kŖ:B��͔<��!\�nu끼�\�a#S�\�?Z\�4�\�d\�\�\�{��&���سz�\�5F\�M��\�\"���s\Z\�/��Z8\�X#;;�2\n�*{T�\�G�\�\r��\Z�ԝ\�\r0\�\��n3�DIY�)T\�\�\�re%bdi.�m����J\rS\�$\�jG\���[��|д\�J�\�r��դ\�5�=F��Ҭ�Ccv#>\�#\�x��բ\\2	쮡R>f>\�\��\�mL��\�h\���ᥐ*��\�\�a\�\���\�\��\�UM��jK\�#~G\�Z\�}jx\�{�\�\�\�\�\�\�\n�Aik\�]~�\�q\�\�Yq�U�|]�D\�]R\�z~��\0ƕ�\�\�W\�z_\�-~̀{ߴ 9+:�\�\�\�\��\�\�\�\Z6\�}����\��\�\��\�J+����\�\�\�6��\�\��{�\0���+��x�!Y`�d��Uс=�&�\�	(��@QE\0QE\0VMω4�Y\�Y\�\��\��\�\�H�A\�X֑�9l��\�w;�8���g\�\�˩YE\�\�b\�*\�=K\�77�y	\�\��iu���:\�a��f2��#\�\�|_�Z�\�\�B=+�\�|ys:�B|�<aO_Ƹi�\'n�\��\�\�\�w�U8Gb�-\�wR\�d�%���\�=s9f\�?�,\���\�S&ZV#�J�\�R�Ug5\r�!\�1_�YIww�\�B\�S�c%sX\�Ɠ\�\�P$\�\�\�}\�\�Jɦl�N��\0\�T�\�~uH�\�4Ze�\���K��A\�\�\�	���\�U$\�\�?�8ǩ��)♚PkC\"\\\���A�@��\�\�\�\0Ӂ��\�\"�V�S@�\�u�\�%jxj�M�+���WK\��\0\���n�W����}A�5^�\�%s\��\�O\��m��#|�\�\'�֊\�;ǁ�+�A\��Ҋ�A\����*9��\��W	\Z\�Ǡ�j\Z���f\�Wr�\�Q\�=��\�k\�|E\�\�Rg�\�6��0�\�q\���\�x�MgPf\�\�\"�=�\�\� e\�\�\���P[�KCTߝ�\�U��\�\�Qyj�K���\�\r_sE��Z�\�\�T\�Q4�\\�|\�i&�J�\�e�I$�RF\�H\�u�]\�6;[�B\�4\�\�2\�\�L-Nj�\��4�s�CQM\�K,�\��E/ڟ\��V\�HZ��rR\�\�O&�\�ݣ4\0�\�\�\�8\Z\0��\Z�u(42�p54\�4��5 55 42�Pj0i�\���2=V\�H�LE\�ފ��b>�\�\�l\�\�\��\0\Z\��\�\�$\�-_m�\r�B\�q\�\�>�\�x�\�\�>��������\�O\�^5\�v%�$\�I=k\nv��\�<��Or]��G��\�\�O\�]pG<�\�H\�\0m\�Ԓ5G���\�K\�.�i\�z&�\�U\�N�<�]ڳl����R�ɩl�&�[ҚZ�MEƐ\�۩�i��7T���i���(i��\"�i\\����斀\r8\Z`4�\0�\\\��\�%�������RPRf�J\r=MB\r=M0\'��V��B\'SEF\rb�\�5S-\� ���\�\���O\��\�\�U�[P7��\�\�u�\�`\��\�1Y�\�)G�)\ZU�\�\�iOJs?Z�S%��N\�\�oQ\�\'\�Aq��\�A~Ԯ$w�\�\�֒�w�\�XGj���ڡf��hqji4\�\�f�\Za4M5% &�iI�R\�\�4��&i)]Թ�\Zu �Lњ\0~isL�\n`;4�\�3HD�Ӂ���@\��\���`J\r=M@\rH���\�Ԁ\�\njEn�\�L\r\�4U\\rI���\�5DZ�\�Be��j����-\�U����3SwS�Vj�����f��\�Q�JM35�Bi���Ci��&�MM\�!4\�h&�M!��nh\��B\�\�QH\�\0\�Q�,\�K�`4\�\�1sN�\�@\nPi�Ӂ�C\�.i�\�恏�SQNSLD\�\���A�T�\�T@\�@ɷT�\�QZD�\rv�sE\�H���\�QJ\�!f�\�QEK)&��(�P��&�)0BL\�T�ni	��%-Rb\n(��\0��*�)A��@(4���(�E�\\њ(�C��E~i�\�ER\�\�QE\0�\�',1),(3,'t','g','','2018-10-30 12:25:02','Germany','j','k','�\��\�\0JFIF\0\0\0\0\0\0�\�\0C\0		\n\r\Z\Z $.\' \",#(7),01444\'9=82<.342�\�\0C			\r\r2!!22222222222222222222222222222222222222222222222222��\0\"\0�\�\0\0\0\0\0\0\0\0\0\0\0	\n�\�\0�\0\0\0}\0!1AQa\"q2���#B��R\�\�$3br�	\n\Z%&\'()*456789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz�����������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0\0\0\0\0\0	\n�\�\0�\0\0w\0!1AQaq\"2�B����	#3R\�br\�\n$4\�%\�\Z&\'()*56789:CDEFGHIJSTUVWXYZcdefghijstuvwxyz������������������������������������\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�\�����\�\0\0\0?\0\�\�^\�z\�\�\�e�\�\�\"�\�\�/\�G��\�\�?\�\\\�`\�tU`;���*:60T�\rFc\�dS���\�!�\�\���	��J~*�����=G��\�P2P�\�S\��\0\��\0�\��x\���\0\0Q�w��X\��\�t}\�?��\0\ZZ\0��5v\��ٷ\��\��U2�??e�P��n\�\�m>\�tj�C(�9\��Q\�\n�T���u��u2ƙ�,z�Q^\�Zi\�\�4��\0\�B7{�\�\�GT�Q��i��\�b�\�AN1�2���R\�\�	-�.\�\�\n=\�ֹ��j��\0Lh\�\�vq�Ʊ�r\�\ZM�y�z\�A#7&\�K�^���\��bs�\�<\�\�5\�A0�w&\�R�\�Ew�J�T\nL\�ź�6��\�J/�c\��U��&\���\�=�\0\n\�A\�Hr�*y�\�\�z�]0�H�!2v�֢ʍ\�s\�j\�n^&ʻ#	RFkj\�\�\�ֻCL\�\�8�n��f\�\�S=C�o֗C�kJ\�\�ԣ\�W$8�pG�֦\�\���\�M�&+M#\�ʿ�\�4�_o�\�E���a²\�C\�Қcv\�\�o�\�\�BF�=�JF�n>y	=r憄�;\�}�\�@I���\Z\�W�Z9��v\n�\0�(vѶ�I\�o\�Rn>�\�\�;m\�\�\�\������\0U8%?m(_�\0]0#e\�})�\nx(�(�ϥ8-</�8-+���?\�)Bԁ\np_O�&ӂ\����P��@?�:y�KW�T�\'�~qQ\�2[\�\�\�*�\�\'��5\�\�ֿ܊�Wyc\�$\�\�\�\�`\�N*\�nüY���\��\�\�E	x=2?\�+�\'\�4�z\�\��[-�m��\�\��c�\�\�J\�\�H0\Z(\'m\rI�`�\0\�F\�n\\zSqHC�\�\�J�W�\�4�\�\�z\\���\�e�*\�\��\0�w�?��o6�rW�O(\�C\�\�6kU�s�r0zTJ7.2�\�n\�ߊB?\�k�\�\�\�T�S�\�\�9�/��t\��Edi{�\"�ݽ�=�}��b\�}\�\�P!w1\��z~u(M��֚W�\0C�n?ȩ���M	Znژ�&\�`G���m�e)Oʴ\�\�f�5n�y\�X^Ō�gu9twe$_i�q}�\�4�\�m�e\�FͭZ3\�WV��o������\0Ǫ�$�ʻ矒hۏPsK`-<-G\�\�u\�\�MJ�[�Y����4��\�\�]ݏ\�G�ԋn�{|\��M !\�A�xq\�]sq\�|\�3\��\0\�Vn�^\�\�Wr������p�d\��\�\�\'\�:�f[w;W摳\��\�\��^�\�7oN�j�\�\�o$�~�\���pSc��h�#;�\���\���\nw\�_sI�\�\����i�~QMs\Z�\�0H`�u8\�V���Hm�\�\�\Z��vъ�$EX�U\�\�\�ӹ�C1F6\�\�\����LB})\�\�\�J\�Z\0\�\�\�\�A�D���Lv1\�C\��νYj�~lgּR0|��\�\�\�{W�-\�[C��[+�2�K�G��VS�oCX�,8���͑��\�f�\�\�k�\�\�\�$R��aު�\�*�@|�P\�4\�D\�SH���\��\�\�\��}I�\0��\0R�\�M\�A�����\�\����\Z\�f)\Z\�\����*?�A\�\��#�0\�*\��(�<\�i�č�\0RG\�(�FV�9�εy?st6�8 H:g\�\��/\�\���\��S���#*GN*\�\��|\�\�B�\�0\�>g(\�\��\�O\���\�R7�@R@X\r�:��\0Z�JV\�.��Wrn-ϔA\0��8o\��VrE6�u�yf��9R{U\�>y,/�Y@Eo\�\\\�\�\�HkR�\� �-\n\�%`�A\�\�(\�\�\�\�\�\�>R��{�\��\0>Իj���.�\�\�`۹E\�9S\���:\�\�R\� \�J�M��- !+\��\0\\W\r\�{\�ܖ�\n�\0\�O\�`�ӧ\�]�\�\�<q��\0���\�\�2\���\�R\�\�\�\�I\�\�Q-J�\0v��ksB\�o�=c݁R\"|���Ոa\�\��l�=Wl\�T\�2�o|\Z�hv\\)<W��2�\�\�Ce\�#b�mZ�aܣ\�S M�Q�\�\�\�uG\�+&\�\�V2n����\�Te�>�\Z\�\�0\�y\�U0\'\�n���R�2�g<km?�\���/ZԸ�\�\�8\�\�P5�ۺ.Gq\�W\�b\�V*x�v�j�!~��\��Zi�\�\�09\�:zW��:\�\�t�&3\�,Y\'+3*)\�@\0\�NG��.^\�\��7K^\�\�\�$\�\�@\�\�ȩ���\r%\���\ZI[�;~�?�\�KR㷥7�\��\�M\�S��\�  \�I��\�&\�\0���\�E��\��jw�Qӹ�\�\�v�XEK��.h����I;0Yw\�\��p\��\0Z�ak%��\�\�aT����\�\'��\�\�3\�]�?�?*w�?�?*��c�\�4y6\�vm�\�9\�\�9b�\�\�GQ\�~�,v�Q�X�\�q� \�\�\�Q\�gڷ(��Q�S��\�Qq\�@�p\�9�\�\�X����C\�v\�\�\��s޺�\�&F6�\� \�kݐ��ӁZ>Oڴ\�,�\�g\�4�\�\�\�=c\�@AK\�H\�\�SǮCD?zI@\�	��\�\�\�\�\�\'\�+�[٦y.H�_֪;�\�y��,\�\�Oݓ\�s\r�\�b�\�[#\�\�g9�\�\�\�\�\�vx\�\n�YE6YU#\�=1Y\�J[��T$٣��z\�\�\�\��\�J6\�8#�VO\�\�ԑ��!\�nnV`{��I�y\�Y�\�j�b\�Y�l�L\�c���&����\�M�����{�����c�C\�\�q\��,\�\�x\�\�Tǵ/�\��\0몲3摷n�*�\�\Z���\�a�CN�yՆ�\Z��ž̷�\�Jі\�F\�\�\���\�^E�,�q\�3\\<_\�\�5\��)\'�\"��:\�^�\�U=���ڄ�\�\�c<����K�H\�\���Y\Z�\�I��P\�\�~�(ݕ�\�׹\��*�U9�\r�2X¼~�^Xcv���c\�m\�#�yazg\�\\�h\�]\�@�h\��FFe8\"�˭o!/!ܲ,+�%>\��3ګ�\�^��\��\0ע\�)yT�:��\�\�\�\��\0^��\�\�v���tvD�X��\0U01>}\�MX��\�G\�3R,6��.N둡!2�ͫ\�\�I�4lT���q]�o\�o~g#\�M�D}Yx?J\�Zw�o\�/��\r\n2\�\�ן�D!o�c\�;\�\�\�V��\0?Rߟ��plm�\�\�\�\�G�㔫\�=�R=s\�T�\'�\�2/\�ElG\�ϦMg\�ۂ=\�[M�8_j\�}	��0gAP��\0=\�jKg�&�Im��i�7�:o_ʢ\��\0\���~n�\�\�Ko�x�i�	]�Y.�6�\�KDۚ�g\�\�F28�l\�_\�\\\�z�P���Hw:j�6\�\�\\V\�\�M��z\�l�1\���\�qh\�q}\nkx�\�\�\����ͅ�\�#R#�>Y�j\�%�%?)����\�ԕ���e@\�l�C�/a�\n����՛����G\'\�\�\�z�-B\�\�0\�s\�Ǧ\0?\�\��=�\"\�ʊ2X\��wAӿ�����|\�u\�\nsf1E\�.�R�-\�:M\�\�\�TE\�T�\�f\�M��\�Y�Eɡ|\�`�31�\0\��^WH\��\�\�\�?\�(˧��`�C�#�?\�5WeX��\�\���Ӹtj\�\��\0�\�\�\��\0\n\0�\"�����i�H\�\�7\0ē\�8�\0\�Q@]۳\�̲��#��b\�\�m\���y��\�i�\\=���\0\�՛3\Z��3�\�^\�=��^U8EW��0L\�7U8<u�D����\��\0צ/&�\"�\�8;\��\0\�\��\0^�Xm?缙�\0�_�zhM�Y\�\�\�&\�\�\�\��\�Ǹ\��VI��\�L��*\�\��<��\�\�\�\��C#(\�@	�#�5M]��\'�T\�M*=KO�\�_�\"\�\�5\�-紟\�\��\0�I\�Z\�i�\�\��\0\Z,6\�\�jmu��\�>\�Gr\�|�\�[?\��e\�\'�\�sA0Wc��s\��\�I�B;�\�\�T�\r\�Yj�����Nƥl;�\n!{sV�T$�˩�q\�I\�U��TUut^\�7���7J���\�Xn{Hbhf�\�5�@\"\�S&)\�ZM��PH�b�Q\�튮�q�n�<�\�\\\������3�5l.<Mj:�7v=q��+\�|�\\O\�}.5�FyQ\�<�\�Bw�\�z\�^�\�[_��_�\��\0\�N[�&��U(�v\0$\�\0:֣Z��7Q��?�\0WV\�4\��יӮc7ȧ��p}=)Z\�rF\\�\�+�r\��~\��\�\�\��>^\�\�\�cnZuϰo\�\�`�\0�\��\0|��P;��U\'�Z~T?\�\�\�\'�)<�{L?h�\�\Z�\r�pB.n\�c�4��\�\��U\�k\r�\�8Y&\�Rݿ\�\�$h����̌rIRj��7��s$�n|\0@¨\�\0\�Eh}�\��{�\0ㆊC,�zx���\���*��~B��}���tC�\��\�\��\0g\��ѕ�A\0qS^\��\�� >^ᴅ\�~\�8<��RW!�S,[ҋzK\'yc�\�\�\�stc�\��x�j}���[I�\�)\�\�9_\�q�jA\�*\�W\�b�E�3\�d[J�h\�4\�Z\�>[��Gc��\�>�����X\�w?\��\�pG򪎺)�\�G٫L\�\�\��\0�$\��\0�EIW<\�\�U�\�{;�\�|dz\�#\\G�\�1\�^\�\�o\���I�m��Ż\�H\�x#g�\��W�\�i��j�7ֲ[\�\�A��\���v)3\Zs\r�5\�,�SY\�r:/Z\�o��J��\�\r�\�4��<zZr\�\"�\'�\�Zhv�28�\�\��Z^�\�0ǵi\�۹\�P-�n\r���mg/\"\�\�@�4�\�Z�\�68e��µ�\�٢��\�\�\�#\���ƻ-\�\�\��f�\�\"�\�O^2zUh�\�\�O/���(I�F^��\�Q\�s\�V�\�H\�\�8\�-a�!�`��\�I\�kZ\�=\�f�#\�`n>������\�[d��n�M\�y�3\��\0x�\n�0�\�Fw\�3�{ּ�$�(��\�)�����\0ߵ�\�A+\�d}�\�}���[d\�O�\��:8&���RO�#��\�\�[Gv\n����\0\�V\�$���{�\�v�\�\�}\�c쑭�r�S*��\0e\�MI<{�\�p<����\�>��zzը\�#�.Ĝ�y$\���9\���H-�\���O-C1\�z��`~�h\�A	e�\0	�=zt$\�\���=�9��Q];\�\�\�\�\�\Z;\0�h\�ܚ)\�\rN�\"1\0\�G\�R,\�*��\0�8\�sR�m\��\�\��*-��\��\�@(\�֭*\npj,\�U.eK�A-��9w#���\�\�e\�m�e|�G\��~��h\�\�r�\�\�\�\�\"{�1l0\�p:�\�\�R\r5�]`\�\��\�z�j)\n\�a��O%=*J)\�\"\�Ҽ\�⾝�\0\�+\�_\�r�l}�\��פ\�7�t\�\�\r\�م\�#&\�\��K\��X�h\�EFM3\�~V\"�9\�Wn���\�|�\�t�F\�}j)&�.z�J�/�\�\�\�&�\�\�{\n9G\�\�>\���}i~֋ʧ>\��^\�Q\�A�\0=8\��ʇy�\�#f�\�;?�<�\���\0�wcJ\�x\�*;\�8��\�l+n�3\n���F\�w�}*\Z*\�\�n\�:�ҽ��\�X�\�\�33\�\�tʼ�H�}GR���e\�p��S_GXi\�\�\�\�m�\"@������\�\"�V�\'!G^*w�>\0QF\0�h�;R\��J\�\�S0ZC�Sŧ�\�Z8�{0\��\���@1\�M�\�Fڈ\�a�1�%��\�V��T\�\';�?a��v\�\nF8\��\0\�~9�=��\�+&0S�\rjb�����l�|�\�Nݣ\��\�.�\�iQ\�GO3-�\�\�WqF)��\�\�\�rrI\���1E/fW9Ҝ>�_\�\�\��t�k�\0�g\�x\�v,\�U�\��\0L\�\�G\�?\��\��\�ܞIv,\�U~\��\0LO\�HoO�\�?�\�\�>Iv-\�U>\�\�\���\0\�T�mo�\�\�|\�\��\�\\�����\0\��\0��>\��\0\�\�\�^\�=\�\�˱v��u�\0�v�\0��0\�l�\�\�\�\�У\�G�{9v<[\�7�Α�H\�-���\";g\�/\�O\�Ey�\�k\Z�+\�6\���I{)mY\�H|�\��=Ex&��\�at\�N�$S\�\"�%-��ijec\�dCS�\�JWw��=\�銅�\�LCґa\\vexЭX\0S��\�i\�P\�ʱ�Z�\�\�������tz��\�-m[\�Y+\�\�\�=\�O�\�I�Θ�,\�o�\�o\�oç�\0��j��뤰��\�\�\�ˆ��\�W�\�ٿ\�Dj\�\�sʜ۽�\Z+?�I�\0\�\��\0\�T�\�O�\0>����\��\�G��cF�\��ӓ�}O�\��\0֤:��\�\�\���\�\�\�\�Ң���7��\��\0�j?���\0�?���\0֣\�ø{)\�5h��\�i�\�\��\0\"\���֛�}?\�\'�\0Z���p\�S\�jQY�T���\�\��\0�K��7������\0֥\�a\�=��\ZTVw\��\�\�\�?\�\��\0Z�=�{�\�˰\�톭\�Z6�\�ز��W\�4yukb�\�1{B��J#�8�{&\�ʤ1՜Q�~͋����<���1Kٱ󲩇ڢh���)\n\n�I�Tf�}e�i\�\�_\�!��e�\�\�\0w\'Ҽ#ĺ\�:\�=\�1�v\�U_�nx\�\�O\�\�&�\��j\"\�i�@F\��.?\�^w\�f�П\�[S�ʮ\�U9�����}=\�S$\�\�֫�Yz\�\�b\�jho�\�T\�zO>���sCx�3U/�m\�J�_�O(��\\��\�\��+[��9fRU\�6*A\�N\reƵa�\�d�\0t�\0*�y���|kӚ\�8��:\�)\�a��ã{\��GӚ\���B\�2�\�B9�Q����(\�*p�\�qj��\�:u\�7���70�\�\�Cʟ\��\�,4^\�^\�\�>\�{R�k��u\�\�\��d�E�W�3$c\�ʽ\�?�\�\�+5A�\�\�f\Za��\�Rm{\n�3\�>\�y5��hؾ�{W�f�=)|�j\�ڴ�G�?`\�\�YDE\�N{Uͣ\�R\��{/jT{QV\��)�/hŢ�+�\�(��\0(��\0(��\0(��\0*����\�^\�\�\n3rq�\�\�kŖ:B��͔<��!\�nu끼�\�a#S�\�?Z\�4�\�d\�\�\�{��&���سz�\�5F\�M��\�\"���s\Z\�/��Z8\�X#;;�2\n�*{T�\�G�\�\r��\Z�ԝ\�\r0\�\��n3�DIY�)T\�\�\�re%bdi.�m����J\rS\�$\�jG\���[��|д\�J�\�r��դ\�5�=F��Ҭ�Ccv#>\�#\�x��բ\\2	쮡R>f>\�\��\�mL��\�h\���ᥐ*��\�\�a\�\���\�\��\�UM��jK\�#~G\�Z\�}jx\�{�\�\�\�\�\�\�\n�Aik\�]~�\�q\�\�Yq�U�|]�D\�]R\�z~��\0ƕ�\�\�W\�z_\�-~̀{ߴ 9+:�\�\�\�\��\�\�\�\Z6\�}����\��\�\��\�J+����\�\�\�6��\�\��{�\0���+��x�!Y`�d��Uс=�&�\�	(��@QE\0QE\0VMω4�Y\�Y\�\��\��\�\�H�A\�X֑�9l��\�w;�8���g\�\�˩YE\�\�b\�*\�=K\�77�y	\�\��iu���:\�a��f2��#\�\�|_�Z�\�\�B=+�\�|ys:�B|�<aO_Ƹi�\'n�\��\�\�\�w�U8Gb�-\�wR\�d�%���\�=s9f\�?�,\���\�S&ZV#�J�\�R�Ug5\r�!\�1_�YIww�\�B\�S�c%sX\�Ɠ\�\�P$\�\�\�}\�\�Jɦl�N��\0\�T�\�~uH�\�4Ze�\���K��A\�\�\�	���\�U$\�\�?�8ǩ��)♚PkC\"\\\���A�@��\�\�\�\0Ӂ��\�\"�V�S@�\�u�\�%jxj�M�+���WK\��\0\���n�W����}A�5^�\�%s\��\�O\��m��#|�\�\'�֊\�;ǁ�+�A\��Ҋ�A\����*9��\��W	\Z\�Ǡ�j\Z���f\�Wr�\�Q\�=��\�k\�|E\�\�Rg�\�6��0�\�q\���\�x�MgPf\�\�\"�=�\�\� e\�\�\���P[�KCTߝ�\�U��\�\�Qyj�K���\�\r_sE��Z�\�\�T\�Q4�\\�|\�i&�J�\�e�I$�RF\�H\�u�]\�6;[�B\�4\�\�2\�\�L-Nj�\��4�s�CQM\�K,�\��E/ڟ\��V\�HZ��rR\�\�O&�\�ݣ4\0�\�\�\�8\Z\0��\Z�u(42�p54\�4��5 55 42�Pj0i�\���2=V\�H�LE\�ފ��b>�\�\�l\�\�\��\0\Z\��\�\�$\�-_m�\r�B\�q\�\�>�\�x�\�\�>��������\�O\�^5\�v%�$\�I=k\nv��\�<��Or]��G��\�\�O\�]pG<�\�H\�\0m\�Ԓ5G���\�K\�.�i\�z&�\�U\�N�<�]ڳl����R�ɩl�&�[ҚZ�MEƐ\�۩�i��7T���i���(i��\"�i\\����斀\r8\Z`4�\0�\\\��\�%�������RPRf�J\r=MB\r=M0\'��V��B\'SEF\rb�\�5S-\� ���\�\���O\��\�\�U�[P7��\�\�u�\�`\��\�1Y�\�)G�)\ZU�\�\�iOJs?Z�S%��N\�\�oQ\�\'\�Aq��\�A~Ԯ$w�\�\�֒�w�\�XGj���ڡf��hqji4\�\�f�\Za4M5% &�iI�R\�\�4��&i)]Թ�\Zu �Lњ\0~isL�\n`;4�\�3HD�Ӂ���@\��\���`J\r=M@\rH���\�Ԁ\�\njEn�\�L\r\�4U\\rI���\�5DZ�\�Be��j����-\�U����3SwS�Vj�����f��\�Q�JM35�Bi���Ci��&�MM\�!4\�h&�M!��nh\��B\�\�QH\�\0\�Q�,\�K�`4\�\�1sN�\�@\nPi�Ӂ�C\�.i�\�恏�SQNSLD\�\���A�T�\�T@\�@ɷT�\�QZD�\rv�sE\�H���\�QJ\�!f�\�QEK)&��(�P��&�)0BL\�T�ni	��%-Rb\n(��\0��*�)A��@(4���(�E�\\њ(�C��E~i�\�ER\�\�QE\0�\�',4);
/*!40000 ALTER TABLE `Juniors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sponsors`
--

DROP TABLE IF EXISTS `Sponsors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Sponsors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sponsors`
--

LOCK TABLES `Sponsors` WRITE;
/*!40000 ALTER TABLE `Sponsors` DISABLE KEYS */;
INSERT INTO `Sponsors` VALUES (1,'Спонсор 1',1),(2,'Спонсор 2',2);
/*!40000 ALTER TABLE `Sponsors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ToolboxTest`
--

DROP TABLE IF EXISTS `ToolboxTest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ToolboxTest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `toolbox` int(11) DEFAULT NULL,
  `expert` int(11) DEFAULT NULL,
  `isPassed` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Toolbox` (`toolbox`),
  KEY `Expert` (`expert`),
  CONSTRAINT `ToolboxTest_ibfk_1` FOREIGN KEY (`toolbox`) REFERENCES `Toolboxes` (`id`),
  CONSTRAINT `ToolboxTest_ibfk_2` FOREIGN KEY (`expert`) REFERENCES `Experts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ToolboxTest`
--

LOCK TABLES `ToolboxTest` WRITE;
/*!40000 ALTER TABLE `ToolboxTest` DISABLE KEYS */;
INSERT INTO `ToolboxTest` VALUES (1,1,1,''),(2,1,1,'\0');
/*!40000 ALTER TABLE `ToolboxTest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Toolboxes`
--

DROP TABLE IF EXISTS `Toolboxes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Toolboxes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `junior` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Junior` (`junior`),
  CONSTRAINT `Toolboxes_ibfk_1` FOREIGN KEY (`junior`) REFERENCES `Juniors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Toolboxes`
--

LOCK TABLES `Toolboxes` WRITE;
/*!40000 ALTER TABLE `Toolboxes` DISABLE KEYS */;
INSERT INTO `Toolboxes` VALUES (1,'Test1',1),(2,'Test2',1),(3,'Test3',2),(4,'Test3',2);
/*!40000 ALTER TABLE `Toolboxes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'root','admin','admin'),(2,'witalij','test','expert'),(3,'otherExpert','test','expert'),(4,'k','m','junior');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Works`
--

DROP TABLE IF EXISTS `Works`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Works` (
  `id` int(11) NOT NULL,
  `junior` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `data` longblob,
  `raiting` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Junior` (`junior`),
  CONSTRAINT `Works_ibfk_1` FOREIGN KEY (`junior`) REFERENCES `Juniors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Works`
--

LOCK TABLES `Works` WRITE;
/*!40000 ALTER TABLE `Works` DISABLE KEYS */;
/*!40000 ALTER TABLE `Works` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-12 23:00:03
