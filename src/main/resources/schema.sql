DROP TABLE IF EXISTS PERSONA;
CREATE TABLE PERSONA (
id_persona INT AUTO_INCREMENT  PRIMARY KEY,
nombre VARCHAR(50) NOT NULL,
tipo_documento INT(8) NOT NULL,
documento INT(15) NOT NULL,
direccion VARCHAR(100) NOT NULL,
telefono VARCHAR(20) NOT NULL
);
