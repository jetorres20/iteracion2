CREATE TABLE HABITACIONES_RESIDENCIA 
(
  IDRECINTO NUMBER NOT NULL 
, IDRESIDENCIA NUMBER NOT NULL 
, COMPARTIDO NUMBER NOT NULL 
, BANIOCOMPARTIDO NUMBER NOT NULL 
, NUMERO NUMBER NOT NULL 
, PRECIONOCHE NUMBER NOT NULL 
, PRECIOMES NUMBER NOT NULL 
, PRECIOSEMESTRE NUMBER NOT NULL 
, CAPACIDADDISPONIBLE NUMBER NOT NULL 
, CONSTRAINT HABITACIONES_RESIDENCIA_PK PRIMARY KEY 
  (
    IDRECINTO 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX HABITACIONES_RESIDENCIA_PK ON HABITACIONES_RESIDENCIA (IDRECINTO ASC) 
      NOLOGGING 
      TABLESPACE TBSPROD 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
NOLOGGING 
TABLESPACE TBSPROD 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NO INMEMORY 
NOPARALLEL;

ALTER TABLE HABITACIONES_RESIDENCIA
ADD CONSTRAINT HABITACIONES_RESIDENCIA_FK1 FOREIGN KEY
(
  IDRECINTO 
)
REFERENCES RECINTOS
(
  ID 
)
ENABLE;

ALTER TABLE HABITACIONES_RESIDENCIA
ADD CONSTRAINT HABITACIONES_RESIDENCIA_FK2 FOREIGN KEY
(
  IDRESIDENCIA 
)
REFERENCES RESIDENCIAS
(
  IDOPERARIO 
)
ENABLE;

ALTER TABLE HABITACIONES_RESIDENCIA
ADD CONSTRAINT HABITACIONES_RESIDENCIA_CHK1 CHECK 
(COMPARTIDO IN (0,1) AND BANIOCOMPARTIDO IN (0,1))
ENABLE;

ALTER TABLE HABITACIONES_RESIDENCIA
ADD CONSTRAINT HABITACIONES_RESIDENCIA_CHK2 CHECK
(PRECIONOCHE>=0 AND PRECIOMES>=0 AND PRECIOSEMESTRE>=0)
ENABLE;