create database IF NOT EXISTS bd_grupo12;

use bd_grupo12;

/*----------------------- [Usuario] --------------------------*/
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `user_role` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `baja` bit(1) DEFAULT b'0',  
  PRIMARY KEY (`id_usuario`)
);
/*----------------------- [Usuario] --------------------------*/

/*---------------------- [Producto] -------------------------*/
CREATE TABLE `producto` (
  `id_producto` bigint NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio_total` float NOT NULL,
  `stock` int NOT NULL,
  `stock_minimo` int NOT NULL,
  PRIMARY KEY (`id_producto`)
);
/*---------------------- [Producto] -------------------------*/

/*------------------------ [Lote] ---------------------------*/
CREATE TABLE `lote` (
  `id_lote` int NOT NULL AUTO_INCREMENT,
  `id_producto` bigint NOT NULL,
  `id_pedido` int NOT NULL,
  `cantidad` int NOT NULL,
  `fecha_recepcion` date NOT NULL,
  `proveedor` varchar(50) NOT NULL,
  `precio` float NOT NULL,
   PRIMARY KEY (`id_lote`),
   KEY `fk_lote_idx` (`id_producto`),
   CONSTRAINT `fk_lote_producto` FOREIGN KEY (`id_producto`)
   REFERENCES `producto` (`id_producto`),
   CONSTRAINT `fk_lote_pedido` FOREIGN KEY (`id_pedido`)
   REFERENCES `pedido` (`id_pedido`)
);
/*------------------------ [Lote] ---------------------------*/

/*------------------------ [Venta] --------------------------*/
CREATE TABLE `venta` (
  `id_venta` bigint NOT NULL AUTO_INCREMENT,
  `id_producto` bigint NOT NULL,
  `id_usuario` int NOT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `fk_venta_idx` (`id_producto`),
  CONSTRAINT `fk_venta_producto` FOREIGN KEY (`id_producto`)
  REFERENCES `producto` (`id_producto`),
  CONSTRAINT `fk_venta_usuario` FOREIGN KEY (`id_usuario`)
  REFERENCES `usuario` (`id_usuario`) 
);
/*------------------------ [Venta] --------------------------*/

/*----------------------- [Pedido] --------------------------*/
CREATE TABLE `pedido` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `id_producto` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `fecha` date NOT NULL,
  `proveedor` varchar(50) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `fk_pedido_idx` (`id_producto`),
  CONSTRAINT `fk_pedido` FOREIGN KEY (`id_producto`)
  REFERENCES `producto` (`id_producto`) 
);
/*----------------------- [Pedido] --------------------------*/

