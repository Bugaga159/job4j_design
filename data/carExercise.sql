create table body(
				  id serial primary key,
				  name varchar(255)
);
create table engine(
					id serial primary key,
					name varchar(255)
);
create table transmission(
						  id serial primary key,
						  name varchar(255)
);
create table car(
				 id serial primary key,
				 name varchar(255),
				 body_id int references body(id),
				 engine_id int references engine(id),
				 transmission_id int references transmission(id)
);
insert into body(name) values
	('Sedan'),
	('Coupe'), 
	('Station Wagon'),
	('Hatchback'),
	('SUV'),
	('Minivan'),
	('Pickup Truck');
insert into engine(name) values
	('NISSAN 1.4'),
	('TOYOTA 1.6'),
	('TOYOTA 2.0'),
	('HONDA 2.0'),
	('RENAULT 1.4'),
	('RENAULT 1.6'),
	('FORD 2.0'),
	('FORD 3.5'),
	('FORD 6.4');
insert into transmission(name) values
	('Manual'),
	('Automatic'),
	('Robot'),
	('CVT');
insert into car(name, body_id, engine_id, transmission_id) values('VW Polo', 1, 6, 2);
insert into car(name, body_id, engine_id, transmission_id) values('VW Polo', 4, 4, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Opel Astra', 4, 3, 2);;
insert into car(name, body_id, engine_id, transmission_id) values('Nissan Terrano', 3, 4, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Ford F-150', 7, 8, 2);
insert into car(name, body_id, engine_id, transmission_id) values('Mazda 6', 1, 3, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Mazda 3', 4, 4, 3);
insert into car(name, body_id, engine_id, transmission_id) values('Renault Kangoo', 6, 5, 2);
insert into car(name, body_id, engine_id, transmission_id) values('Renault Logan', 1, 7, 1);
insert into car(name, body_id, engine_id, transmission_id) values('Toyota Corolla', 1, 3, 4);
insert into car(name, body_id, engine_id, transmission_id) values('Dodge Challenger SRT 3', 2, 9, 1);
select * from car;
-- Вывести список всех машин и все привязанные к ним детали.
select c.id, c.name as car, b.name as body, e.name as engine, t.name as transmission
from car c
	  left join body b on c.body_id = b.id
	  left join engine e on c.engine_id = e.id
	  left join transmission t on c.transmission_id = t.id;
-- Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.name as body
from body b
	  left join car c on b.id = c.body_id
where c.id is null;
select e.name as engine
from engine e
	  left join car c on e.id = c.engine_id
where c.id is null;
select t.name as transmission
from transmission t
	  left join car c on t.id = c.transmission_id
where c.id is null;