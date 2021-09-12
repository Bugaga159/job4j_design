create table type(
				  id serial primary key,
				  name varchar(255)
);
create table product(
					 id serial primary key,
					 name varchar(255),
					 type_id int references type(id),
					 expired_date date,
					 price float
);
insert into type(name) values ('Сыр');
insert into type(name) values ('Молоко');
insert into type(name) values ('Замороженные продукты');
select * from type;
insert into product(name, type_id, expired_date, price)
VALUES ('Ламбер', 1, '2021-11-01', 99.89);
insert into product(name, type_id, expired_date, price)
VALUES ('Galbani', 1, '2021-11-03', 205.90);
insert into product(name, type_id, expired_date, price)
VALUES ('Almette', 1, '2021-07-01', 124.90);
insert into product(name, type_id, expired_date, price)
VALUES ('Простоквашино', 2, '2021-02-12', 68.90);
insert into product(name, type_id, expired_date, price)
VALUES ('Parmalat', 2, '2021-11-01', 90.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Домик в деревне', 2, '2021-11-01', 72.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Смесь овощная Hortex', 3, '2023-04-01', 87.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Капуста брокколи Hortex', 3, '2023-02-01', 129.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Мороженое Cornetto', 3, '2022-04-01', 77.90);
insert into product(name, type_id, expired_date, price)
VALUES ('Эkzo мороженое с вишней ', 3, '2023-04-01', 450.90);
select * from product;
-- все продукты с типом "СЫР"
select p.name, t.name from product p
join type t on p.type_id = t.id
where t.name = 'Сыр';
-- получения всех продуктов, у кого в имени есть слово "мороженое"
select name from product where name ~* '(М|м)ороженое';
-- выводит все продукты, срок годности которых уже истек
select name, expired_date from product p where p.expired_date < current_date;
-- выводит самый дорогой продукт
select * from product where price = (select max(price) from product);
-- выводит для каждого типа количество продуктов к нему принадлежащих
select t.name, count(t.name) from type t
join product p on p.type_id = t.id
group by t.name;
-- получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name as name, t.name as type from product p
join type t on p.type_id = t.id
where t.name = 'Сыр' or t.name = 'Молоко';
-- Тип продуктов, которых осталось меньше 10 штук
select t.name, count(t.name) from type t
join product p on p.type_id = t.id
group by t.name
having count(t.name) < 10;
-- Вывести все продукты и их тип.
select p.name as name, t.name as type from product p
join type t on p.type_id = t.id;