create table car (
    id serial primary key, 
    series text,
	model varchar(255),
	doors numeric
);
insert into car (series, model, doors) values ('X51-1', 'Fiat', 3);
update car set series='LX51' where id=1;
select * from car;
delete from car;