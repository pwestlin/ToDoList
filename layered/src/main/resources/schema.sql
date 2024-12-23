create table if not exists application_user
(
    id    int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email varchar(30) not null
);

create table if not exists to_do_list
(
    id       int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name     varchar(30)                          not null,
    user_id  int references application_user (id) not null,
    reminder timestamp
);

create table if not exists to_do_list_item
(
    id         int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name       varchar(30)                    not null,
    to_do_list int references to_do_list (id) not null
);
