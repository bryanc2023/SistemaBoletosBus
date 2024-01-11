

-- TABLA CRONOGRAMA

ALTER TABLE cronograma ADD CHECK (anio BETWEEN 2000 AND 2500);

-------------
-- TABLA RUTA
ALTER TABLE ruta ADD CHECK (costo_ruta > 0);

-- -----------------------------------------------------------
-- TABLA UNIDAD 
ALTER TABLE unidad ADD CHECK (cantidad_asientos > 0);

ALTER TABLE unidad ADD CHECK (estado_actividad IN ('Activo', 'Inactivo'));






