-- Insertar datos en la tabla PROVEEDORES
INSERT INTO PROVEEDORES (NOMBRE, DIRECCION, SALDO, FECHA_ULTIMO_PEDIDO)
VALUES ('Proveedor 1', 'Dirección 1', 1000, '2023-05-01 10:00:00'),
       ('Proveedor 2', 'Dirección 2', 2000, '2023-05-02 11:30:00'),
       ('Proveedor 3', 'Dirección 3', 1500, '2023-05-03 09:15:00');

-- Insertar datos en la tabla TELEFONOS_PROV
INSERT INTO TELEFONOS_PROV (PROVEEDOR_ID, NUMERO_TELEFONO)
VALUES (1, '123456789'),
       (1, '987654321'),
       (2, '555555555'),
       (3, '999999999');

-- Insertar datos en la tabla CORREOS_PROV
INSERT INTO CORREOS_PROV (PROVEEDOR_ID, CORREO)
VALUES (1, 'proveedor1@example.com'),
       (2, 'proveedor2@example.com'),
       (3, 'proveedor3@example.com');

-- Insertar datos en la tabla SUCURSALES
INSERT INTO SUCURSALES (NOMBRE)
VALUES ('Sucursal 1'),
       ('Sucursal 2'),
       ('Sucursal 3');
