CREATE TABLE HABI_RESIDENCIA_TIENE_MENAJES 
(
  IDHABITACION NUMBER NOT NULL 
, IDMENAJE NUMBER NOT NULL 
, CANTIDAD NUMBER 
, VALORUNIDAD NUMBER 
, CONSTRAINT HABI_RESIDENCIA_TIENE_MENA_PK PRIMARY KEY 
  (
    IDHABITACION 
  , IDMENAJE 
  )
  ENABLE 
);

ALTER TABLE HABI_RESIDENCIA_TIENE_MENAJES
ADD CONSTRAINT HABI_RESIDENCIA_TIENE_MEN_FK1 FOREIGN KEY
(
  IDHABITACION 
)
REFERENCES HABITACIONES_RESIDENCIA
(
  IDRECINTO 
)
ENABLE;

ALTER TABLE HABI_RESIDENCIA_TIENE_MENAJES
ADD CONSTRAINT HABI_RESIDENCIA_TIENE_MEN_FK2 FOREIGN KEY
(
  IDMENAJE 
)
REFERENCES MENAJES
(
  ID 
)
ENABLE;