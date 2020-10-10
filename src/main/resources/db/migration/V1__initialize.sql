DROP TABLE IF EXISTS Client CASCADE;
CREATE TABLE Client (id bigserial PRIMARY KEY, name VARCHAR(255));

DROP TABLE IF EXISTS OrderClient CASCADE;
CREATE TABLE OrderClient (id bigserial PRIMARY KEY, id_Client INT, id_Product INT);