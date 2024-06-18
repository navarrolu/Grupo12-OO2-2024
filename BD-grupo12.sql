create database IF NOT EXISTS bd_grupo12;
use bd_grupo12;

/*----------------------- [User] --------------------------*/
CREATE TABLE user (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    baja BOOLEAN,
    createdat DATETIME DEFAULT CURRENT_TIMESTAMP,
    updateat DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

/*----------------------- [User_Role] --------------------------*/
CREATE TABLE user_role (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	role VARCHAR(100) NOT NULL,
	createdat DATETIME DEFAULT CURRENT_TIMESTAMP,
	updatedat DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    id_usuario BIGINT NOT NULL,
	CONSTRAINT fkUser FOREIGN KEY (id_usuario) REFERENCES user (id_usuario)
);

/*----------------------- [Producto] --------------------------*/
CREATE TABLE producto (
  id_producto BIGINT AUTO_INCREMENT,
  descripcion varchar(50) NOT NULL,
  precio_base float NOT NULL,
  precio_total float NOT NULL,
  stock int NOT NULL,
  stock_minimo int NOT NULL,
  PRIMARY KEY (id_producto)
);

/*----------------------- [Pedido] --------------------------*/
CREATE TABLE pedido (
  id_pedido int NOT NULL AUTO_INCREMENT,
  id_producto bigint NOT NULL,
  cantidad int NOT NULL,
  total float NOT NULL,
  fecha date NOT NULL,
  proveedor varchar(50) NOT NULL,
  PRIMARY KEY (id_pedido),
  KEY fk_pedido_idx (id_producto),
  CONSTRAINT fk_pedido FOREIGN KEY (id_producto)
  REFERENCES producto (id_producto) 
);

/*----------------------- [Lote] --------------------------*/
CREATE TABLE lote (
  id_lote BIGINT AUTO_INCREMENT PRIMARY KEY,
  cantidad int NOT NULL,
  fecha_recepcion DATETIME DEFAULT CURRENT_TIMESTAMP,
  proveedor varchar(60) NOT NULL,
  precio float NOT NULL,
  id_producto bigint not null,
  id_pedido int,
  CONSTRAINT fkProducto FOREIGN KEY (id_producto) REFERENCES producto (id_producto),
  CONSTRAINT fk_lote_pedido FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido)
);

/*----------------------- [Venta] --------------------------*/
CREATE TABLE venta (
  id_venta bigint NOT NULL AUTO_INCREMENT,
  id_producto bigint NOT NULL,
  id_usuario bigint NOT NULL,
  cantidad int NOT NULL,
  total float NOT NULL,
  fecha date NOT NULL,
  PRIMARY KEY (id_venta),
  KEY fk_venta_idx (id_producto),
  CONSTRAINT fk_venta_producto FOREIGN KEY (id_producto)
  REFERENCES producto (id_producto),
  CONSTRAINT fk_venta_user FOREIGN KEY (id_usuario)
  REFERENCES user (id_usuario) 
);

/* --------------------------------------- mostrar registros----------------------------------------------------------
SELECT * FROM user left join user_role on user.id_usuario = user_role.id_usuario;
SELECT * FROM user_role;
SELECT * FROM producto;
SELECT * FROM lote;
SELECT * FROM pedido;
SELECT * FROM venta;
 -------------------------------------------- eliminar una tabla ------------------------------------------------------
DROP TABLE user_role;
DROP TABLE user;
DROP table producto;
DROP TABLE lote;
DROP TABLE pedido;
*/