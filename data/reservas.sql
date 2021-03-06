CREATE TABLE RESERVAS 
(
  ID NUMBER NOT NULL 
, RECINTOID NUMBER NOT NULL 
, PERSONAID NUMBER NOT NULL 
, FECHARESERVA DATE NOT NULL 
, FECHAINICIO DATE NOT NULL 
, FECHAFIN DATE NOT NULL 
, PERSONAS NUMBER NOT NULL 
, SUBTOTAL NUMBER NOT NULL 
, FECHACANCELACION DATE 
, COBROADICIONAL NUMBER 
, ACTIVA NUMBER NOT NULL 
, CONSTRAINT RESERVAS_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_FK1 FOREIGN KEY
(
  RECINTOID 
)
REFERENCES RECINTOS
(
  ID 
)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_FK2 FOREIGN KEY
(
  PERSONAID 
)
REFERENCES PERSONAS
(
  IDOPERARIO 
)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK1 CHECK 
(fechainicio >fechareserva)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK2 CHECK 
(fechafin < fechainicio)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK3 CHECK 
(personas >0 )
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK4 CHECK 
(subtotal >0)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK5 CHECK 
(cobroadicional >0)
ENABLE;

ALTER TABLE RESERVAS
ADD CONSTRAINT RESERVAS_CHK6 CHECK 
(activa in (0,1))
ENABLE;
