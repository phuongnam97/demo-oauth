drop table if exists users cascade;
create table users
(
    id                serial                    primary key,
    username          varchar(50)              not null,
    password          varchar(255)              not null,

    uuid              varchar(50)              not null,
    created_time      timestamp with time zone not null,
    creator_id        integer                           default 0,
    last_updated_time timestamp with time zone,
    last_updated_id   integer                           default 0
);

drop table if exists roles cascade;
create table roles
(
    id                serial                    primary key,
    name              varchar(50)               not null,

    uuid              varchar(50)              not null,
    created_time      timestamp with time zone not null,
    creator_id        integer                           default 0,
    last_updated_time timestamp with time zone,
    last_updated_id   integer                           default 0
);

drop table if exists clients cascade;
create table clients
(
    id                          varchar(50)              primary key,
    secret                      varchar(255)             not null,
    scopes                      varchar(255)             not null,
    authorized_grant_types      varchar(255)             not null,
    registered_redirect_uri     varchar(255)             not null,
    access_token_validity_seconds  integer                  not null,
    refresh_token_validity_seconds integer                  not null,


    uuid              varchar(50)              not null,
    created_time      timestamp with time zone not null,
    creator_id        integer                           default 0,
    last_updated_time timestamp with time zone,
    last_updated_id   integer                           default 0
);

drop table if exists resources cascade;
create table resources
(
    id                          serial                   primary key,
    description                 varchar(255)             not null,

    uuid              varchar(50)              not null,
    created_time      timestamp with time zone not null,
    creator_id        integer                           default 0,
    last_updated_time timestamp with time zone,
    last_updated_id   integer                           default 0
);

drop table if exists client_resources cascade;
create table client_resources
(
    client_id   varchar(50) not null references clients (id),
    resource_id serial      not null references resources (id),
    CONSTRAINT detail_client_resource_pkey PRIMARY KEY (resource_id, client_id)
);

drop table if exists users_roles cascade;
create table users_roles
(
    user_id   serial not null references users (id),
    role_id   serial not null references roles (id),
    CONSTRAINT detail_user_role_pkey PRIMARY KEY (user_id, role_id)
);

drop table if exists access_token cascade;
create table access_token
(
    id                serial                    primary key,
    tokenId           varchar(255)              not null,
    token             bytea                     not null,
    client_id         varchar(50)               not null,
    username          varchar(50)               not null,
    authentication_id varchar(255)              not null,
    authentication    bytea                     not null,

    uuid              varchar(50)              not null,
    created_time      timestamp with time zone not null,
    creator_id        integer                           default 0,
    last_updated_time timestamp with time zone,
    last_updated_id   integer                           default 0
);