create table departments(
	id serial primary key,
	name varchar(50)
);
create table emploees(
	id serial primary key,
	name varchar(50),
	departments_id int references departments(id)
);
insert into departments (name) values
	('IT'),
	('Sales'),
	('Support'),
	('Security'),
	('Lawyers');
insert into emploees(name, departments_id) values
	('Ivan', 1),
	('Mike', 1),
	('Bob', 3),
	('Kate', 4),
	('Andrey', 2),
	('Tom', 5),
	('Olga', 1);

-- с left, rigth, full, cross соединениями
select * from departments as d left join emploees as e on e.departments_id = d.id;
select * from departments as d right join emploees as e on e.departments_id = d.id;
select * from departments as d full join emploees as e on e.departments_id = d.id;
select * from employees cross join departments;
-- left join найти департаменты, у которых нет работников
select * from departments as d left join emploees as e 
on e.departments_id = d.id
where e.departments_id is null;
-- left и right join написать запросы, которые давали бы одинаковый результат. 
select * from departments as d left join emploees as e on e.departments_id = d.id;
select * from emploees as e right join departments as d on e.departments_id = d.id;

-- Таблица teens с атрибутами name, gender и заполнить ее. 
create table teens(
	id serial primary key,
	name varchar(30),
	gender varchar(10)
);
insert into teens(name, gender) values
	('Vasja', 'm'),
	('Alex', 'w'),
	('Kolja', 'orc');
-- Используя cross join составить все возможные разнополые пары
select
    t1.name, t1.gender,
    t2.name, t2.gender
from teens t1 cross join teens t2
where t1.gender!=t2.gender;