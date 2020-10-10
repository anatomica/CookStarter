DROP TABLE IF EXISTS Restaurants CASCADE;
CREATE TABLE Restaurants (id bigserial not null, name varchar(255), description varchar(5000), address varchar(5000), telephone varchar(255), primary key (id));
INSERT INTO Restaurants
(name, description, address, telephone) VALUES
('HardRock', 'HardRock - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('McDonald''s', 'McDonald''s - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('PabloChef', 'PabloChef - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('Root''s Logo', 'Root''s Logo - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('Roppolos', 'Roppolos- сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('TacoBell', 'TacoBell - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67');

DROP TABLE IF EXISTS ProductsMenu CASCADE;
create table ProductsMenu (id bigserial, title varchar(255), description varchar(5000), category int, price int, primary key (id));
insert into ProductsMenu
(title, description, category, price) values
('Cheese', 'Fresh cheese', 1, 320),
('Milk', 'Fresh milk', 2, 80),
('Apples', 'Fresh apples', 3, 80),
('Bread', 'Fresh bread', 1, 30),
('A1', '', 2, 1),
('A2', '', 3, 2),
('A3', '', 1, 3),
('A4', '', 2, 4),
('A5', '', 3, 5),
('A6', '', 1, 6),
('A7', '', 2, 7),
('A8', '', 3, 8),
('A9', '', 1, 9),
('A10', '', 2, 10),
('A11', '', 3, 11),
('A12', '', 1, 12),
('A13', '', 2, 13),
('A14', '', 3, 14),
('A15', '', 1, 15),
('A16', '', 2, 16),
('A17', '', 3, 17),
('A18', '', 1, 18),
('A19', '', 2, 19),
('A20', '', 3, 20);

DROP TABLE IF EXISTS Menu CASCADE;
CREATE TABLE Menu (id bigserial PRIMARY KEY, id_category INT, name VARCHAR(255), foreign key (id_category) references ProductsMenu (id));
INSERT INTO Menu
(id_category, name) values
(1, 'Сыры'),
(2, 'Молочная продукция'),
(3, 'Фрукты');
