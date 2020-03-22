--- Sentencias SQL para la creación del esquema de AlohAndes


-- USO
-- Copie el contenido de este archivo en una pestaña SQL de SQL Developer
-- Ejecútelo como un script - Utilice el botón correspondiente de la pestaña utilizada

-- Creación del secuenciador
create sequence alohandes_sequence;

-- Creaación de la tabla RECINTOS y especificación de sus restricciones
CREATE TABLE RECINTOS
   (ID NUMBER, 
	CAPACIDADTOTAL NUMBER, 
	CONSTRAINT RECINTOS_PK PRIMARY KEY (ID));
	
--ALTER TABLE A_TIPOBEBIDA
	--ADD CONSTRAINT UN_TIPOBEB_NOMBRE 
	--UNIQUE (NOMBRE)
--ENABLE;

-- Creaación de la tabla OPERARIOS y especificación de sus restricciones
CREATE TABLE OPERARIOS 
   (ID NUMBER, 
	FECHAREGISTRO DATE,	 
	CONSTRAINT OPERARIOS_PK PRIMARY KEY (ID));
	
--ALTER TABLE OPERARIOS 
--ADD CONSTRAINT fecha mayor hoy
--    FOREIGN KEY (idtipobebida)
 --   REFERENCES a_tipobebida(id)
--ENABLE;

-- Creaación de la tabla PERSONAS y especificación de sus restricciones
CREATE TABLE A_BAR
   (ID NUMBER, 
	CANTSEDES NUMBER(3,0), 
	CIUDAD VARCHAR2(255 BYTE), 
	NOMBRE VARCHAR2(255 BYTE), 
	PRESUPUESTO VARCHAR2(255 BYTE), 
	CONSTRAINT A_BAR_PK PRIMARY KEY (ID));
	 
ALTER TABLE A_BAR
	ADD CONSTRAINT CK_BAR_PPTO 
	CHECK (PRESUPUESTO IN ('Alto', 'Medio', 'Bajo'))
ENABLE;

-- Creaación de la tabla BEBEDOR y especificación de sus restricciones
CREATE TABLE A_BEBEDOR
   (ID NUMBER, 
	CIUDAD VARCHAR2(255 BYTE), 
	NOMBRE VARCHAR2(255 BYTE), 
	PRESUPUESTO VARCHAR2(255 BYTE), 
	CONSTRAINT A_BEBEDOR_PK PRIMARY KEY (ID));

ALTER TABLE A_BEBEDOR
	ADD CONSTRAINT CK_BDOR_PPTO 
	CHECK (PRESUPUESTO IN ('Alto', 'Medio', 'Bajo'))
ENABLE;

-- Creaación de la tabla GUSTAN y especificación de sus restricciones
CREATE TABLE A_GUSTAN
(
  IDBEBEDOR NUMBER,
  IDBEBIDA NUMBER,
  CONSTRAINT A_GUSTAN_PK PRIMARY KEY (IDBEBEDOR, IDBEBIDA));

ALTER TABLE A_GUSTAN
ADD CONSTRAINT fk_g_bebedor
    FOREIGN KEY (idbebedor)
    REFERENCES a_bebedor(id)
ENABLE;
    
ALTER TABLE A_GUSTAN
ADD CONSTRAINT fk_g_bebida
    FOREIGN KEY (idbebida)
    REFERENCES a_bebida(id)
ENABLE;

-- Creaación de la tabla SIRVEN y especificación de sus restricciones
CREATE TABLE A_SIRVEN 
(
  IDBAR NUMBER,
  IDBEBIDA NUMBER,
  HORARIO VARCHAR2(20 BYTE), 
  CONSTRAINT A_SIRVEN_PK PRIMARY KEY (IDBAR, IDBEBIDA, HORARIO));

ALTER TABLE A_SIRVEN
ADD CONSTRAINT fk_s_bar
    FOREIGN KEY (idbar)
    REFERENCES a_bar(id)
ENABLE;
    
ALTER TABLE A_SIRVEN
ADD CONSTRAINT fk_s_bebida
    FOREIGN KEY (idbebida)
    REFERENCES a_bebida(id)
ENABLE;
    
ALTER TABLE A_SIRVEN
ADD CONSTRAINT CK_S_HORARIO 
	CHECK (horario IN ('diurno', 'nocturno', 'todos'))
ENABLE;

-- Creaación de la tabla VISITAN y especificación de sus restricciones
CREATE TABLE A_VISITAN 
(
  IDBAR NUMBER,
  IDBEBEDOR NUMBER,
  HORARIO VARCHAR2(20 BYTE), 
  FECHAVISITA DATE, 
  CONSTRAINT A_VISITAN_PK PRIMARY KEY (IDBAR, IDBEBEDOR, FECHAVISITA, HORARIO));

ALTER TABLE A_VISITAN
ADD CONSTRAINT fk_v_bebedor
    FOREIGN KEY (idbebedor)
    REFERENCES a_bebedor(id)
ENABLE;
    
ALTER TABLE A_VISITAN
ADD CONSTRAINT fk_v_bar
    FOREIGN KEY (idbar)
    REFERENCES a_bar(id)
ENABLE;
    
ALTER TABLE A_VISITAN
ADD CONSTRAINT CK_V_HORARIO
	CHECK (horario IN ('diurno', 'nocturno', 'todos'))
ENABLE;

COMMIT;
