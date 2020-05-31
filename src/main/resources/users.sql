    create schema forest collate utf8_general_ci;

--postgreSQL

create table users
(
	email varchar(30) not null
		constraint users_pkey
			primary key,
	password varchar(80) not null,
	first_name varchar(15),
	last_name varchar(15),
	avatar varchar(50) default '/img/no-ava.png'::character varying
);

alter table users owner to fepmwlwrizhqqr;



create table user_roles
(
	email varchar(30) not null,
	role_name varchar(15) not null,
	constraint user_roles_pkey
		primary key (email, role_name)
);

create table tomcat_sessions
(
	session_id varchar(100) not null
		constraint tomcat_sessions_pkey
			primary key,
	valid_sessions char not null,
	max_inactive integer not null,
	last_access bigint not null,
	app_name varchar(255),
	session_data bytea
);

create index kapp_name
	on tomcat_sessions (app_name);


