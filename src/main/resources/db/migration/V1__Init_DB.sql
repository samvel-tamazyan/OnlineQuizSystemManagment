create table user
(
    id              int8    not null,
    activation_code varchar(255),
    active          boolean not null,
    email           varchar(255),
    password        varchar(255) not null,
    username        varchar(255) not null,
    primary key (id)
);


create table quiz
(
  id      int8 not null,
  name    varchar(255),
  tag     varchar(255),
  text    varchar(2048) not null,
  user_id int8 not null ,
  primary key (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

create table user_role
(
  user_id int8 not null,
  roles   varchar(255),
  FOREIGN KEY (user_id) REFERENCES user(id)

);


