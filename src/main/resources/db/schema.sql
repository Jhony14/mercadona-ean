CREATE TABLE Product (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  ean VARCHAR(13) NOT NULL,
  name VARCHAR(255) NOT NULL,
  supplier_id BIGINT,
  destination_id BIGINT,
  FOREIGN KEY (supplier_id) REFERENCES Supplier(id),
  FOREIGN KEY (destination_id) REFERENCES Destination(id)
);

CREATE TABLE Destination (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE Supplier (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(255)
);
