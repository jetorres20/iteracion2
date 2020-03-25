CREATE TABLE HABITACION_RESIDENCIA_INCLUYE 
(
  IDHABITACION NUMBER NOT NULL 
, IDSERVICIO NUMBER NOT NULL 
, CONSTRAINT HABITACION_RESIDENCIA_INCL_PK PRIMARY KEY 
  (
    IDHABITACION 
  , IDSERVICIO 
  )
  ENABLE 
);

ALTER TABLE HABITACION_RESIDENCIA_INCLUYE
ADD CONSTRAINT HABITACION_RESIDENCIA_INC_FK1 FOREIGN KEY
(
  IDHABITACION 
)
REFERENCES HABITACIONES_RESIDENCIA
(
  IDRECINTO 
)
ENABLE;

ALTER TABLE HABITACION_RESIDENCIA_INCLUYE
ADD CONSTRAINT HABITACION_RESIDENCIA_INC_FK2 FOREIGN KEY
(
  IDSERVICIO 
)
REFERENCES SERVICIO_RESIDENCIA
(
  ID 
)
ENABLE;