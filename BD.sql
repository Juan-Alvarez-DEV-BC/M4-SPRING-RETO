CREATE TABLE cuentas (
     id         SERIAL        PRIMARY KEY,
     nro_cuenta Varchar(10)   NOT NULL,
     titular    Varchar(255)  NOT NULL,
     saldo      Numeric(19,2) NOT NULL
     tipo_cuenta Varchar(10)   NOT NULL,
   );

CREATE TABLE log-transacciones (
    id        SERIAL         PRIMARY KEY,
    tipo      VARCHAR(50)    NOT NULL,
    valor     NUMERIC(15, 2) NOT NULL,
    fecha     TIMESTAMP      NOT NULL,
    id_cuenta INTEGER REFERENCES cuenta(id)
);

Delete From cuenta  ;

INSERT INTO cuentas (nro_cuenta, titular, saldo, tipo_cuenta)
        VALUES
          ('1111', 'Juan Alvarez'  , 1000.50,"BASICA"),
          ('2222', 'Tatiana Arias' , 2500.00,"PREMIUM"),
          ('3333', 'Salome Alvarez', 1500.75,"PREMIUM")
;

Select * From cuenta;