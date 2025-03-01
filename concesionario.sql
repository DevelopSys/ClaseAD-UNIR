
CREATE TABLE `coches` (
  `id` int(11) NOT NULL,
  `marca` varchar(100) DEFAULT NULL,
  `modelo` varchar(100) DEFAULT NULL,
  `cv` int(11) DEFAULT NULL,
  `precio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `coches` (`id`, `marca`, `modelo`, `cv`, `precio`) VALUES
(1, 'mercedes', 'c300', 350, 80000),
(2, 'mercedes', 'glc', 300, 70000),
(3, 'ford', 'focus', 150, 30000),
(4, 'ford', 'fiesta', 300, 20000);


CREATE TABLE `empleados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `empleados` (`id`, `nombre`, `apellido`, `correo`, `telefono`, `tipo`) VALUES
(1, 'Borja', 'Martin', 'correo@gmail', 123123, 1),
(3, 'Juan', 'Gomez', 'juan@gmail.com', 234, 3),


CREATE TABLE `tipos` (
  `id` int(11) NOT NULL,
  `siglas` varchar(100) DEFAULT NULL,
  `descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `tipos` (`id`, `siglas`, `descripcion`) VALUES
(1, 'EXT', 'trabajador externo a la empresa'),
(2, 'IND', 'trabajador indefinido'),
(3, 'BEC', 'trabajador becario');


CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  `id_coche` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `ventas` (`id`, `id_empleado`, `id_coche`) VALUES
(1, 1, 1),
(2, 1, 3);


ALTER TABLE `coches`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`),
  ADD KEY `empleados_tipos_FK` (`tipo`);

ALTER TABLE `tipos`
  ADD PRIMARY KEY (`id`);

ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ventas_empleados_FK` (`id_empleado`),
  ADD KEY `ventas_coches_FK` (`id_coche`);


ALTER TABLE `coches`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;


ALTER TABLE `empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;


ALTER TABLE `tipos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;


ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_tipos_FK` FOREIGN KEY (`tipo`) REFERENCES `tipos` (`id`);

ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_coches_FK` FOREIGN KEY (`id_coche`) REFERENCES `coches` (`id`),
  ADD CONSTRAINT `ventas_empleados_FK` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id`);
COMMIT;

