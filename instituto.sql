-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-11-2018 a las 10:27:43
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `instituto`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Direccion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`DNI`, `Nombre`, `Direccion`) VALUES
('1', '1', '1'),
('12345678P', 'Antonio Prieto Ramírez', 'Jerez de la Frontera'),
('98765432O', 'Cristina Navarro López', 'El Puerto de Santa María');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignatura`
--

CREATE TABLE `asignatura` (
  `Codigo` int(11) NOT NULL,
  `Nombre_A` varchar(30) DEFAULT NULL,
  `Horas` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `asignatura`
--

INSERT INTO `asignatura` (`Codigo`, `Nombre_A`, `Horas`) VALUES
(1, 'Matemáticas', 4),
(2, 'Ciencias Naturales', 3),
(3, 'Lengua', 4),
(4, 'Educación Física', 2),
(5, 'Religión', 1),
(6, 'Tecnología', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula`
--

CREATE TABLE `matricula` (
  `DNI_Alumno` varchar(9) NOT NULL,
  `Cod_Asignatura` int(9) NOT NULL,
  `Nota` decimal(2,0) NOT NULL,
  `Fecha` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `matricula`
--

INSERT INTO `matricula` (`DNI_Alumno`, `Cod_Asignatura`, `Nota`, `Fecha`) VALUES
('12345678P', 1, '9', '0000-00-00'),
('12345678P', 2, '7', '0000-00-00'),
('12345678P', 5, '5', '0000-00-00'),
('98765432O', 3, '9', '0000-00-00'),
('98765432O', 4, '9', '0000-00-00'),
('98765432O', 6, '9', '0000-00-00'),
('234', 2342, '0', '2002');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  ADD PRIMARY KEY (`Codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignatura`
--
ALTER TABLE `asignatura`
  MODIFY `Codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
