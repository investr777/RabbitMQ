CREATE TABLE car (
    id           varchar(36) primary key,
    brand        varchar(36) not null,
    model        varchar(36) not null,
    vin          varchar(17) not null UNIQUE,
    number_plate varchar(10) not null UNIQUE
);

CREATE TABLE person (
    id         varchar(36) primary key,
    identifier varchar(36)  not null UNIQUE,
    first_name varchar(32)  not null,
    last_name  varchar(32)  not null,
    address    varchar(128) not null
);
