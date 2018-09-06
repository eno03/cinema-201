-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2018 at 06:46 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `se201_bioskop`
--

-- --------------------------------------------------------

--
-- Table structure for table `film`
--

CREATE TABLE `film` (
  `film_id` int(11) NOT NULL,
  `film_naziv` text NOT NULL,
  `film_zanr` varchar(10) NOT NULL,
  `film_godina` smallint(6) NOT NULL,
  `film_opis` text NOT NULL,
  `glavne_uloge` text NOT NULL,
  `vreme_trajanja` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `film`
--

INSERT INTO `film` (`film_id`, `film_naziv`, `film_zanr`, `film_godina`, `film_opis`, `glavne_uloge`, `vreme_trajanja`) VALUES
(0, 'Gone girl', 'Drama', 2014, 'U napetoj pri?i o supruzi koja se ne pojavi na proslavi 5. godišnjice braka u glavnim ?emo ulogama gledati Bena Afflecka i Rosamund Pike. Oni glume savršen bra?ni par, Nicka i Amy, koji mirno živi u provinciji nakon preseljenja iz New Yorka. Omiljeni ?lanovi malene zajednice postat ?e nacionalna vijest broj 1 kada potraga za nestalom Amy pokaže da je Nick glavni osumnji?enik, a njezin dnevnik otkrije potpuno druga?iju stranu njihovog braka...', 'Rosamund Pike, Ben Affleck, Missi Pyle', '02:25:00'),
(1, 'Daddy’s Home 2', 'Komedija', 2017, 'Brad i Dusty su uspjeli posti?i nemogu?e: primirje i zajedni?ko o?instvo. Sve djeluje skladno sve dok Dustyjev macho tata Kurt i Bradov ultra emotivan otac Don ne najave svoj dolazak za Boži?. Kurt (Mel Gibson) ?e unijeti razdor izme?u Dustyja i Brada svojim nametanjem mišljena i neslaganjem s njihovim zajedni?kim odlukama vezanim za odgoj njihove djece. Ho?e li Dusty i Brad uspjeti dokazati Kurtu da je njihov moderan na?in odgoja u?inkovit i spasiti zajedni?ki Boži??', 'Will Ferrell, Mark Wahlberg, Mel Gibson', '01:40:00'),
(2, 'A Bad Moms Christmas', 'Komedija', 2017, 'Mila Kunis, Kristen Bell i Kathryn Hahn repriziraju svoje uloge u komediji smještenoj u dobrostoje?e ?ikaško predgra?e kojim vladaju chic supermame i odlu?uju o tome što je prihvatljivo ponašanje, a što nije. Veseli trojac ?e se ovaj put suo?iti sa stresom koji sa sobom donose blagdani s obitelji.', 'Mila Kunis, Kristen Bell, Kathryn Hahn', '01:36:00'),
(3, 'Nikola', 'Misterija', 2013, 'Opis filma..', 'Nikola Obucina, Jessica Alba', '01:20:00'),
(4, 'Blade Runner 2049', 'Drama', 2017, 'Trideset godina nakon doga?aja iz prvog filma, LAPD službenik K (Ryan Gosling), istražuje dugo zakopanu tajnu. K-ovo otkri?e ga vodi u potragu za Rick Deckard-om (Harrison Ford), koji je nestao prije 30 godina …', 'Harrison Ford, Ryan Gosling, Robin Wright, Jared Leto...', '02:20:00'),
(5, 'Split', 'Misterija', 2017, 'Kevin, ?ovjek s najmanje 23 razli?ite li?nosti, je prisiljen da otme tri tinejdžerke. Dok ih drži u zato?eništvu, njegova kona?na li?nost – “The Beast” – po?inje da preuzima kontrolu …', 'James McAvoy, Anya Taylor-Joy, Haley Lu Richardson, ...', '03:03:00'),
(6, 'Justice League', 'Misterija', 2017, 'Nakon što je ponovo po?eo da vjeruje u ljudskost i nakon Supermanovog nesebi?nog ?ina, Bruce Wayne angažuje svog novoste?enog saveznika, Dianu Prince, da se zajedno suo?e sa još ve?im neprijateljem …', 'Ben Affleck, Gal Gadot, Jason Momoa, Ezra Miller,...', '02:10:00'),
(7, 'Guardians of the Galaxy Vol. 2', 'Akcija', 2017, '?uvari galaksije nastavljaju svoje avanture nakon što otkriju misteriozno porijeklo Peter-a Quill-a …', 'Chris Pratt, Zoe Saldana, Dave Bautista, Bradley Cooper,...', '01:50:00'),
(8, 'It', 'Horor', 2017, 'U malom gradu Derry, u Mainu, sedmero djece susre?e se licem u lice sa životnim problemima, nasilnicima i ?udovištem koje uzima oblik klovna koji se zove Pennywise …', 'Bill Skarsgård, Finn Wolfhard, Javier Botet, ...', '01:45:00'),
(9, 'Zikina dinastija 2', 'Komedija', 1967, 'Veseli Zika Pavlovic..', 'Dragomir Bojanic Gidra', '01:20:00');

-- --------------------------------------------------------

--
-- Table structure for table `karta`
--

CREATE TABLE `karta` (
  `karta_id` int(11) NOT NULL,
  `projekcija_id` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `parter` text NOT NULL,
  `red` int(11) NOT NULL,
  `sediste` int(11) NOT NULL,
  `popust` text NOT NULL,
  `cena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `karta`
--

INSERT INTO `karta` (`karta_id`, `projekcija_id`, `username`, `parter`, `red`, `sediste`, `popust`, `cena`) VALUES
(1, 1, 'matejP', 'dsa', 2, 2, 'Ne', 2200),
(2, 0, 'anica.obucina', 'levo', 5, 6, 'Ne', 2500),
(3, 0, 'anica.obucina', 'levo', 5, 7, 'Ne', 3000),
(4, 0, 'anica.obucina', 'levo', 5, 8, 'Ne', 3200),
(5, 0, 'anica.obucina', 'levo', 3, 3, 'Ne', 1500),
(6, 0, 'anica.obucina', 'levo', 3, 4, 'Ne', 1500),
(7, 2, 'anica.obucina', 'levo', 7, 5, 'Ne', 1500),
(8, 2, 'anica.obucina', 'levo', 7, 6, 'Ne', 1200),
(9, 0, 'anica.obucina', 'levo', 7, 2, 'Ne', 2000),
(10, 0, 'anica.obucina', 'levo', 7, 3, 'Ne', 3000),
(11, 1, 'matejP', '', 2, 3, 'Da', 1400);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status_id` int(11) NOT NULL,
  `ime` text NOT NULL,
  `prezime` text NOT NULL,
  `email` text NOT NULL,
  `kontakt` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`username`, `password`, `status_id`, `ime`, `prezime`, `email`, `kontakt`) VALUES
('anica.obucina', 'ancxi93', 1, 'Anica', 'Obucina', 'ancxi93@gmail.com', '0112321452'),
('marko.markovic93', 'marko1234', 1, 'Marko', 'Markovic', 'marko.markovic93@gmail.com', '066213321'),
('matejP', 'matej123', 1, 'Matej', 'Petrovic', 'matejp@gmailc.om', '06342132132'),
('nikola.obucina', 'admin1234', 0, 'Nikola', 'Obucina', 'nikola.obucina97@gmail.com', '0645622541');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik_status`
--

CREATE TABLE `korisnik_status` (
  `status_id` int(11) NOT NULL,
  `value` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik_status`
--

INSERT INTO `korisnik_status` (`status_id`, `value`) VALUES
(0, 'admin'),
(1, 'korisnik');

-- --------------------------------------------------------

--
-- Table structure for table `projekcija`
--

CREATE TABLE `projekcija` (
  `projekcija_id` int(11) NOT NULL,
  `film_id` int(11) DEFAULT NULL,
  `datum` date NOT NULL,
  `broj_karata` int(11) NOT NULL,
  `cena` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `projekcija`
--

INSERT INTO `projekcija` (`projekcija_id`, `film_id`, `datum`, `broj_karata`, `cena`) VALUES
(0, 0, '2018-01-17', 10, 2400),
(1, 0, '2018-01-20', 80, 2400),
(2, 1, '2018-02-24', 92, 1500),
(3, 1, '2018-02-26', 100, 1800),
(4, 1, '2018-01-24', 64, 3000),
(5, 1, '2018-01-27', 75, 2500);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `film`
--
ALTER TABLE `film`
  ADD PRIMARY KEY (`film_id`);

--
-- Indexes for table `karta`
--
ALTER TABLE `karta`
  ADD PRIMARY KEY (`karta_id`),
  ADD KEY `fk_relationship_2` (`projekcija_id`),
  ADD KEY `fk_relationship_3` (`username`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`username`),
  ADD KEY `fk_relationship_4` (`status_id`);

--
-- Indexes for table `korisnik_status`
--
ALTER TABLE `korisnik_status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `projekcija`
--
ALTER TABLE `projekcija`
  ADD PRIMARY KEY (`projekcija_id`),
  ADD KEY `fk_relationship_1` (`film_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `karta`
--
ALTER TABLE `karta`
  MODIFY `karta_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `karta`
--
ALTER TABLE `karta`
  ADD CONSTRAINT `fk_relationship_2` FOREIGN KEY (`projekcija_id`) REFERENCES `projekcija` (`projekcija_id`),
  ADD CONSTRAINT `fk_relationship_3` FOREIGN KEY (`username`) REFERENCES `korisnik` (`username`);

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `fk_relationship_4` FOREIGN KEY (`status_id`) REFERENCES `korisnik_status` (`status_id`);

--
-- Constraints for table `projekcija`
--
ALTER TABLE `projekcija`
  ADD CONSTRAINT `fk_relationship_1` FOREIGN KEY (`film_id`) REFERENCES `film` (`film_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
