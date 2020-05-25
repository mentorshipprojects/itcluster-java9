    create schema forest collate utf8_general_ci;

--postgreSQL

create table tickets
(
	id serial not null
		constraint tickets_pk
			primary key,
	number varchar(20) not null,
	region varchar(100) not null,
	forest_user varchar(100) not null,
	start_date date not null,
	finish_date date not null,
	forestry varchar(100),
	cutting_type varchar(100),
	ticket_status varchar(20),
	cutting_status varchar(20)
);

create unique index tickets_number_uindex
	on tickets (number);





create table tracts
(
	id serial not null
		constraint tracts_pk
			primary key,
	ticket_number varchar(20) not null,
	quarter varchar(20),
	division varchar(20),
	range varchar(20),
	area real,
	forest_type varchar(20),
	general_allowed_extent double precision,
	allowed_extent double precision,
	cutting_status varchar(20),
	contributor varchar(100),
	map_id varchar(20)
);


