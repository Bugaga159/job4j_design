create table seria(
    id serial primary key,
    name varchar(12)
);
create table car(
    id serial primary key,
    seria_id int references seria(id) unique,
    number varchar(255),
	color varchar(255),
	completed boolean not null
);
insert into seria(name) values ('M12');
insert into seria(name) values ('M12B');
insert into seria(name) values ('ZX51');

insert into car(number, seria_id, color, completed) values ('Ksn3ff39', 2, 'black', '1');
insert into car(number, seria_id, color, completed) values ('KffsK2O9', 1, 'blue', '0');
insert into car(number, seria_id, color, completed) values ('KsnQj3JI', 3, 'green', '1');

select c.number, s.name from car as c join seria as s on c.seria_id = s.id;
select c.number as Номер, s.name as Серия from car as c join seria as s on c.seria_id = s.id;
select c.number, s.name, c.color, c.completed from car as c join seria as s on c.seria_id = s.id and c.completed = true;