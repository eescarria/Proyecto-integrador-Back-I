DROP TABLE IF EXISTS DOMICILIOS;
CREATE TABLE DOMICILIOS (ID INT AUTO_INCREMENT PRIMARY KEY,
CALLE VARCHAR(100),
NUMERO VARCHAR(20),
LOCALIDAD VARCHAR(20),
PROVINCIA VARCHAR(20));
DROP TABLE IF EXISTS PACIENTES;
CREATE TABLE PACIENTES (ID INT AUTO_INCREMENT PRIMARY KEY,
APELLIDO VARCHAR(100),
NOMBRE VARCHAR(100),
DOCUMENTO VARCHAR(100),
FECHA_INGRESO DATE,
DOMICILIO_ID INT,
EMAIL VARCHAR(100));
DROP TABLE IF EXISTS ODONTOLOGOS;
CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY,
NUM_MATRICULA VARCHAR(100),
NOMBRE VARCHAR(100),
APELLIDO VARCHAR(100));

INSERT INTO DOMICILIOS (CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES('Avenida', 'Sexta', 'Cali', 'Valle');
INSERT INTO PACIENTES (APELLIDO, NOMBRE, DOCUMENTO, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(
'Escarria', 'Estefania', '1234', '2023-03-13', 1, 'estefy@mail.com');

INSERT INTO ODONTOLOGOS (NUM_MATRICULA, NOMBRE, APELLIDO) VALUES('ABC123', 'Juan', 'Alvear');
