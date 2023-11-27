drop sequence if exists P_USER_ID_SEQ;
create sequence P_USER_ID_SEQ start with 11 increment by 1;

drop table if exists P_USER cascade;
create table P_USER (
    user_id serial primary key,
    firstname varchar(256) null,
    lastname varchar(256) null,
    birthday varchar(256) null,
    email varchar(256) not null,
    country varchar(256) null,
    provider_user_id varchar(256) null,
    enabled boolean,
    display_name varchar(256) null,
    created_date timestamp with time zone not null,
    modified_date timestamp with time zone null,
    password varchar(2048) null,
    provider varchar(256) null
);
alter table P_USER
    add constraint p_user_unique unique(email);

drop sequence if exists P_ROLE_ID_SEQ;
create sequence P_ROLE_ID_SEQ start with 101 increment by 1;

drop table if exists P_ROLE;
create table P_ROLE (
    role_id serial primary key,
    name varchar(64) not null
);
alter table P_ROLE
    add constraint role_unique unique(name);

drop table if exists A_USER_ROLE;
create table A_USER_ROLE (
    user_id integer null,
    role_id integer null
);
alter table A_USER_ROLE add constraint a_user_role_unique unique(user_id, role_id);
alter table A_USER_ROLE add constraint a_user_role_fk_user foreign key (user_id) references P_USER(user_id);
alter table A_USER_ROLE add constraint a_user_role_fk_role foreign key (role_id) references P_ROLE(role_id);
