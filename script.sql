CREATE TABLE proveedores (
  id_proveedor INT PRIMARY KEY,
  nombre VARCHAR(50),
  fecha_alta DATE,
  id_cliente INT
);

COMMIT;

INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente)
VALUES (1, 'Coca-cola', '20/03/1978', 5);

INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente)
VALUES (2, 'Pepsi', '20/03/1978', 5);

INSERT INTO proveedores (id_proveedor, nombre, fecha_alta, id_cliente)
VALUES (3, 'Redbull', '20/03/1978', 6);

COMMIT;