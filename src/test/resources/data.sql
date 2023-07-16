drop table if exists users cascade;
drop sequence if exists users_seq;
create sequence users_seq start with 1 increment by 50;
create table users (
    active boolean,
    created_at timestamp(6),
    id bigint not null,
    updated_at timestamp(6),
    username varchar(20),
    password varchar(255),
    primary key (id)
);

insert into users(id, active, username, password) values (1, true, 'bruno.carneiro', '123456');