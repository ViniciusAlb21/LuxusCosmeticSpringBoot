INSERT INTO CATEGORY (id, name, description) VALUES (1,'Skincare','Tratamentos de pele'),(2,'Maquiagem','Beleza e make');
INSERT INTO BRAND (id, name) VALUES (1,'Luxus'),(2,'Cosmetic');

INSERT INTO PRODUCT (id, name, price, stock, sku, description, category_id, brand_id)
VALUES (1,'Sérum Vitamina C', 89.90, 100,'LC-SERUM-VC','Sérum antioxidante',1,1),
       (2,'Batom Matte Ruby', 39.90, 50,'LC-BATOM-R','Batom matte de longa duração',2,1),
       (3,'Hidratante Facial', 59.90, 70,'GU-HID-FA','Hidratação intensa',1,2);

INSERT INTO CUSTOMER (id, name, email, phone) VALUES (1,'Mohamed Salah','mosalah@example.com','1199999999');
INSERT INTO ADDRESS (id, street, number, city, state, zip, customer_id) VALUES (1,'Rua A','123','São Paulo','SP','01000-000',1);

INSERT INTO REVIEW (id, rating, comment, createdAt, product_id, customer_id) VALUES (1,5,'Excelente!', CURRENT_TIMESTAMP(), 1,1);

INSERT INTO COUPON (id, code, percent, validUntil, active) VALUES (1,'BOASVINDAS',10, DATEADD('DAY', 30, CURRENT_DATE()), true);
