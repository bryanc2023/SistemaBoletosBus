-- TABLA ADMINISTRADOR

-- Finalmente, aseguramos que los valores solo puedan ser 'Masculino' o 'Femenino'
ALTER TABLE administrador ADD CHECK (genero IN ('Masculino', 'Femenino','No definido'));

-- -----------------------------------------------------------------

-- TABLA USUARIO
-- Check constraint de correo de la tabla usuario
ALTER TABLE usuario ADD CHECK (correo_usuario LIKE '%_@__%.__%');

-- Finalmente, para futuras inserciones o actualizaciones, se asegura que los valores sean solo 'Activo' o 'Inactivo'
ALTER TABLE usuario ADD CHECK (estado_actividad IN ('Activo', 'Inactivo'));

-- -----------------------------------------------------------------

-- TABLA BOLETO
ALTER TABLE boleto ADD CHECK (dia IN ('Lunes', 'Martes','Miercoles','Jueves','Viernes','Sabado','Domingo'));

ALTER TABLE boleto ADD CHECK (hora_salida REGEXP '^[0-2][0-9]:[0-5][0-9]$');

ALTER TABLE boleto ADD CHECK (metodo_pago IN ('Efectivo', 'Tarjeta'));

ALTER TABLE boleto ADD CHECK (stock_boletos IN (1, 0));

ALTER TABLE boleto ADD CHECK (numero_asiento > 0);
-- -------------------------------------------------------------------------------

-- TABLA CRONOGRAMA

ALTER TABLE cronograma ADD CHECK (anio BETWEEN 2000 AND 2500);

-- ----------------------------------------------------------------------
-- TABLA PASAJERO
ALTER TABLE pasajero ADD CHECK (genero IN ('Masculino', 'Femenino','No definido'));

ALTER TABLE pasajero ADD CHECK (telefono REGEXP '^[0-9]{10}$')

ALTER TABLE pasajero ADD CHECK (edad BETWEEN 18 AND 50)

-- ----------------------------------------------------------------------
-- TABLA PERSONAL 
ALTER TABLE personal ADD CHECK (genero IN ('Masculino', 'Femenino','No definido'));

-- -----------------------------------------------------------
-- TABLA RUTA
ALTER TABLE ruta ADD CHECK (costo_ruta > 0);

-- -----------------------------------------------------------
-- TABLA UNIDAD 
ALTER TABLE unidad ADD CHECK (cantidad_asientos > 0);

ALTER TABLE unidad ADD CHECK (estado_actividad IN ('Activo', 'Inactivo'));

ALTER TABLE unidad ADD CHECK (numero> 0);




