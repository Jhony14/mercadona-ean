INSERT INTO Supplier (id, name) VALUES (1, 'Hacendado');
INSERT INTO Supplier (id, name) VALUES (2, 'Other Supplier');

INSERT INTO Destination (id, name) VALUES (1, 'Mercadona España');
INSERT INTO Destination (id, name) VALUES (2, 'Mercadona Portugal');
INSERT INTO Destination (id, name) VALUES (3, 'Almacenes');
INSERT INTO Destination (id, name) VALUES (4, 'Oficinas Mercadona');
INSERT INTO Destination (id, name) VALUES (5, 'Colmenas');

INSERT INTO Product (id, ean, name, supplier_id, destination_id) VALUES (1, '8437008459059', 'Product 1', 1, 1);
INSERT INTO Product (id, ean, name, supplier_id, destination_id) VALUES (2, '8480000160072', 'Product 2', 2, 2);
