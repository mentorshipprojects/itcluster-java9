
-- user management
create table users (
  USER_NAME           varchar(15) not null primary key,
  USER_PASSWD         varchar(15) not null
);
insert into users (USER_NAME, USER_PASSWD) values('moderator','moderator');
insert into users (USER_NAME, USER_PASSWD) values('admin','admin');

create table user_roles (
  USER_NAME         varchar(15) not null,
  ROLE_NAME         varchar(15) not null,
  primary key (USER_NAME, ROLE_NAME)
);
insert into user_roles (USER_NAME, ROLE_NAME) values('admin','admin');
insert into user_roles (USER_NAME, ROLE_NAME) values('admin','moderator-api');
insert into user_roles (USER_NAME, ROLE_NAME) values('admin','moderator-gui');
insert into user_roles (USER_NAME, ROLE_NAME) values('moderator','moderator-api');
insert into user_roles (USER_NAME, ROLE_NAME) values('moderator','moderator-gui');

-- media content

-- sessions
create table tomcat_sessions
(
    session_id    varchar(100) not null primary key,
    valid_sessions char(1)      not null,
    max_inactive  int          not null,
    last_access   bigint       not null,
    app_name      varchar(255),
    session_data  bytea
);
CREATE INDEX kapp_name ON tomcat_sessions USING hash (app_name);