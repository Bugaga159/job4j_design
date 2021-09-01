create table devices(
     id serial primary key,
     name varchar(255),
     price float
 );

create table people(
     id serial primary key,
     name varchar(255)
 );

create table devices_people(
     id serial primary key,
     device_id int references devices(id),
     people_id int references people(id)
);

insert into devices(name, price) values ('Iphone X', 9500);
insert into devices(name, price) values ('Iphone 7+', 7000);
insert into devices(name, price) values ('Iphone SE', 4500);
insert into devices(name, price) values ('Samsung S8+', 4900);
insert into devices(name, price) values ('Samsung S9', 6730);
insert into people(name) values ('Andrey'), ('Alex'), ('Mark'), ('Bob');
insert into devices_people(device_id, people_id) values (1,1),(2,3),(3,2),(5,4);
insert into devices_people(device_id, people_id) values (4,3),(2,4),(1,2);
-- средняя цена устройства
select avg(price) from devices;
-- средняя цена устройства по человеку
select p.name, avg(d.price) from people p 
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name;
-- средняя цена устройства по человеку более 5000
select p.name, avg(d.price) from people p 
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 5000;