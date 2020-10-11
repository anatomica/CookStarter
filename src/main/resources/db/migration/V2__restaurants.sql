DROP TABLE IF EXISTS Restaurants CASCADE;
CREATE TABLE Restaurants (id bigserial not null, name varchar(255), description varchar(5000), address varchar(5000), telephone varchar(255), primary key (id));
INSERT INTO Restaurants
(name, description, address, telephone) VALUES
('HardRock', 'HardRock - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('McDonald''s', 'McDonald''s - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('PabloChef', 'PabloChef - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('Roots Coffee', 'Roots Coffee - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('Roppolo''s', 'Roppolo''s - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67'),
('TacoBell', 'TacoBell - сеть ресторанов', 'Москва, ул. Ленина д.1', '+7 495 123-45-67');

DROP TABLE IF EXISTS Products_menu CASCADE;
create table Products_menu (id bigserial, title varchar(255), description varchar(5000), category int, price int, restaurant_id int, logo_id int, primary key (id));
insert into Products_menu
(title, description, category, price, restaurant_id, logo_id) values
('Светлое пиво', 'Свежее светлое пиво', 1, 320, 1, 1),
('Темное пиво', 'Свежее темное пиво', 2, 350, 1, 1),
('Гренки', 'Гренки с чесноком', 3, 80, 1, 1),
('Гамбургер', 'Гамбургер с сыром', 1, 50, 2, 2),
('Биг Тейсти', 'Большой гамбургер', 2, 160, 2, 2),
('Кока-кола', 'Прохладительный напиток', 3, 50, 2, 2),
('Свинина на гриле', 'Жареное мясо', 1, 150, 3, 3),
('Жаренные овощи ', 'Запеченные овощи', 2, 100, 3, 3),
('Салат цезарь', 'Салат', 3, 150, 3, 3),
('Кофе', 'Стандартный кофе', 1, 70, 4, 4),
('Американо', 'Большой кофе', 2, 100, 4, 4),
('Капучино', 'Кофе с молоком', 3, 120, 4, 4),
('Маргарита', 'Стандартная пицца', 1, 120, 5, 5),
('Четыре сыра', 'Пицца с 4 видами сыра', 2, 170, 5, 5),
('Перерони', 'Острая пицца', 3, 180, 5, 5),
('Буррито', 'Жаренный буррито', 1, 150, 6, 6),
('Куриные крылышки', 'Жаренная курица', 2, 120, 6, 6),
('Картофель Фри', 'Жаренный картофель', 3, 100, 6, 6);

DROP TABLE IF EXISTS Menu CASCADE;
CREATE TABLE Menu (id bigserial PRIMARY KEY, id_category INT, name VARCHAR(255), foreign key (id_category) references Products_menu (id));
INSERT INTO Menu
(id_category, name) values
(1, 'Горячие блюда'),
(2, 'Холодные закуски'),
(3, 'Десерт');
