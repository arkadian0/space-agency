INSERT INTO roles (role) VALUES ('ROLE_CUSTOMER'), ('ROLE_MANAGER');

INSERT INTO users (username, password, enabled) VALUES ('customer', '$2a$10$SVbXIzA0I9ft7tThU6R0Xer9NBd22JZWms1oGpunL340wloPW4q.e', true), ('admin', '$2a$10$gPdXuXAf7QUgXXEVHJem..nsIYOtBpV61HV1cuUGehWmkW3n2QrKi', true);

INSERT INTO user_role (user_id, role_id) VALUES (1, 1), (2, 2);

INSERT INTO coordinate(coordinate_a,coordinate_b,coordinate_c,coordinate_d) VALUES (2.32,4.32,3.42,3.11);

INSERT INTO mission(name,imagery_type,start_date,finish_date) VALUES ('mission1','PANCHROMATIC','2016-10-17T15:10:55','2017-10-17T15:10:55');

INSERT INTO mission(name,imagery_type,start_date,finish_date) VALUES ('mission2','MULTISPECTRAL','2016-10-17T15:10:55','2018-10-17T15:10:55');

INSERT INTO product(id,price,product_url,acquisition_date,mission_name,coordinate_id) VALUES (1,500,'www.github.com/arkadian0','2018-10-17T15:10:55','mission1',1);

INSERT INTO product(id,price,product_url,acquisition_date,mission_name,coordinate_id) VALUES (2,200,'www.github.com/arkadian0','2018-10-17T15:10:55','mission1',1);

INSERT INTO product(id,price,product_url,acquisition_date,mission_name,coordinate_id) VALUES (3,2001,'www.github.com/arkadian0','2018-10-17T15:10:55','mission2',1);

INSERT INTO product(id,price,product_url,acquisition_date,mission_name,coordinate_id) VALUES (4,350,'www.github.com/arkadian0','2018-10-17T15:10:55','mission2',1);