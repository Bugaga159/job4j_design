create table people(
    id serial primary key,
    name varchar(255)
);

create table seria(
    id serial primary key,
    name varchar(12)
);
create table car(
    id serial primary key,
    seria_id int references seria(id) unique,
    number int,
	people_id int references people(id)
);
create table body(
    id serial primary key,
    name varchar(255)
);
create table body_car(
	id serial primary key,
	car_id int references car(id),
	body_id int references body(id)
);