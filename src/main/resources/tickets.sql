    create schema forest collate utf8_general_ci;

--postgreSQL

create table tickets
(
	id serial
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
	on table_name (number);



create table tracts
(
	id serial
		constraint tracts_pk
		primary key,
	ticket_number varchar(20) not null,
	quarter varchar(20) null,
	division varchar(20) null,
	range varchar(20) null,
	area float null,
	forest_type varchar(20) null,
	general_allowed_extent float null,
	allowed_extent float null,
	cutting_status varchar(20) null,
	contributor varchar(100) null,
	map_id varchar(20) null
);

