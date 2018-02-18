-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 19 Feb 2018 pada 00.41
-- Versi Server: 10.1.25-MariaDB
-- PHP Version: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `delivery_db`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `location`
--

CREATE TABLE `location` (
  `id_loc` int(35) NOT NULL,
  `latitude` varchar(50) NOT NULL,
  `longitude` varchar(50) NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_user` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `location`
--

INSERT INTO `location` (`id_loc`, `latitude`, `longitude`, `insert_date`, `id_user`) VALUES
(1, '1234', '1234', '2018-02-17 14:58:52', 1),
(2, '1234', '1234', '2018-02-17 14:59:16', 1),
(3, '54164127', '9821784', '2018-02-17 15:06:51', 1),
(4, '9236236', '83724683', '2018-02-17 15:30:12', 1),
(5, '9236236', '83724683', '2018-02-17 15:30:28', 2),
(6, '-6.2196046', '106.9206587', '2018-02-17 15:30:45', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `location_log`
--

CREATE TABLE `location_log` (
  `id_loc_log` int(35) NOT NULL,
  `latitude` varchar(50) NOT NULL,
  `longitude` varchar(50) NOT NULL,
  `id_user` int(2) NOT NULL,
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `location_log`
--

INSERT INTO `location_log` (`id_loc_log`, `latitude`, `longitude`, `id_user`, `insert_date`) VALUES
(1, '37.4219983', '-122.084', 1, '2018-02-17 14:58:03'),
(2, '-6.2196046', '106.9206587', 1, '2018-02-17 14:58:18'),
(4, '37.4219983', '-122.084', 1, '2018-02-18 09:00:27'),
(5, '37.4219983', '-122.084', 1, '2018-02-18 09:01:47'),
(6, '37.4219983', '-122.084', 1, '2018-02-18 09:02:41'),
(7, '37.4219983', '-122.084', 1, '2018-02-18 09:03:21'),
(8, '37.4219983', '-122.084', 1, '2018-02-18 09:03:31'),
(9, '37.4219983', '-122.084', 1, '2018-02-18 09:03:41'),
(10, '37.4219983', '-122.084', 1, '2018-02-18 09:03:51'),
(11, '37.4219983', '-122.084', 1, '2018-02-18 09:04:01'),
(12, '37.4219983', '-122.084', 1, '2018-02-18 09:04:11'),
(13, '37.4219983', '-122.084', 1, '2018-02-18 09:04:21'),
(14, '37.4219983', '-122.084', 1, '2018-02-18 09:04:31'),
(15, '37.4219983', '-122.084', 1, '2018-02-18 09:04:41'),
(16, '37.4219983', '-122.084', 1, '2018-02-18 09:04:51'),
(17, '-6.2195509', '106.9205194', 1, '2018-02-18 09:07:34'),
(18, '-6.2195818', '106.9205405', 1, '2018-02-18 09:07:41'),
(19, '-6.219562', '106.9205865', 1, '2018-02-18 09:07:46'),
(20, '-6.219562', '106.9205865', 1, '2018-02-18 09:07:51'),
(21, '-6.2195938', '106.920638', 1, '2018-02-18 09:07:57'),
(22, '-6.2195925', '106.9206608', 1, '2018-02-18 09:08:02'),
(23, '-6.219584', '106.9206524', 1, '2018-02-18 09:08:06'),
(24, '-6.219584', '106.9206524', 1, '2018-02-18 09:08:11'),
(25, '-6.2196046', '106.9206587', 1, '2018-02-18 09:08:17'),
(26, '37.4219983', '-122.084', 1, '2018-02-18 15:21:00'),
(27, '37.4219983', '-122.084', 1, '2018-02-18 15:21:10'),
(28, '37.4219983', '-122.084', 1, '2018-02-18 15:23:44'),
(29, '37.4219983', '-122.084', 1, '2018-02-18 15:27:45'),
(30, '37.4219983', '-122.084', 1, '2018-02-18 15:44:22'),
(31, '37.4219983', '-122.084', 1, '2018-02-18 15:45:43'),
(32, '37.4219983', '-122.084', 1, '2018-02-18 15:46:44'),
(33, '37.4219983', '-122.084', 1, '2018-02-18 15:50:34'),
(34, '37.4219983', '-122.084', 1, '2018-02-18 15:53:25'),
(35, '37.4219983', '-122.084', 1, '2018-02-18 15:53:30'),
(36, '37.4219983', '-122.084', 1, '2018-02-18 16:07:53'),
(37, '37.4219983', '-122.084', 1, '2018-02-18 16:40:16'),
(38, '37.4219983', '-122.084', 1, '2018-02-18 16:40:20'),
(39, '37.4219983', '-122.084', 1, '2018-02-18 16:42:18'),
(40, '37.4219983', '-122.084', 1, '2018-02-18 16:42:30'),
(41, '37.4219983', '-122.084', 1, '2018-02-18 16:42:40'),
(42, '37.4219983', '-122.084', 1, '2018-02-18 16:42:50'),
(43, '37.4219983', '-122.084', 1, '2018-02-18 16:43:00'),
(44, '37.4219983', '-122.084', 1, '2018-02-18 17:08:54'),
(45, '37.4219983', '-122.084', 1, '2018-02-18 17:09:01'),
(46, '37.4219983', '-122.084', 1, '2018-02-18 17:12:44'),
(47, '37.4219983', '-122.084', 1, '2018-02-18 17:14:27'),
(48, '37.4219983', '-122.084', 1, '2018-02-18 17:14:43'),
(49, '37.4219983', '-122.084', 1, '2018-02-18 17:14:44'),
(50, '37.4219983', '-122.084', 1, '2018-02-18 17:14:54'),
(51, '37.4219983', '-122.084', 1, '2018-02-18 17:14:55'),
(52, '37.4219983', '-122.084', 1, '2018-02-18 17:15:01'),
(53, '37.4219983', '-122.084', 1, '2018-02-18 17:17:17'),
(54, '37.4219983', '-122.084', 1, '2018-02-18 17:17:21'),
(55, '37.4219983', '-122.084', 8, '2018-02-18 17:21:08'),
(56, '37.4219983', '-122.084', 8, '2018-02-18 17:21:21'),
(57, '37.4219983', '-122.084', 8, '2018-02-18 17:21:31'),
(58, '37.4219983', '-122.084', 8, '2018-02-18 17:21:41'),
(59, '37.4219983', '-122.084', 8, '2018-02-18 17:21:51');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` int(10) NOT NULL,
  `full_name` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `flag` int(1) NOT NULL DEFAULT '0',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `full_name`, `username`, `password`, `flag`, `insert_date`, `update_date`) VALUES
(1, 'Firas', 'vrras', '202cb962ac59075b964b07152d234b70', 0, '2018-02-04 16:19:23', '2018-02-18 16:07:40'),
(2, 'Vian', 'vian', '202cb962ac59075b964b07152d234b70', 0, '2018-02-04 16:27:04', '2018-02-18 14:14:37'),
(3, 'Febry', 'febry', '202cb962ac59075b964b07152d234b70', 0, '2018-02-04 16:35:35', '2018-02-04 17:18:44'),
(7, 'woy', 'woy', 'woy', 0, '2018-02-04 17:22:59', '2018-02-04 17:23:32'),
(8, 'Saya', 'admin', '21232f297a57a5a743894a0e4a801fc3', 0, '2018-02-18 04:52:55', '2018-02-18 17:22:00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `location`
--
ALTER TABLE `location`
  ADD PRIMARY KEY (`id_loc`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `location_log`
--
ALTER TABLE `location_log`
  ADD PRIMARY KEY (`id_loc_log`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
  MODIFY `id_loc` int(35) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `location_log`
--
ALTER TABLE `location_log`
  MODIFY `id_loc_log` int(35) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=60;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
