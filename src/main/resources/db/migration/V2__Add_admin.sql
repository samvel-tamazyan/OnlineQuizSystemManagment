insert into user (id, username, password, active)
  values (1, 'admin', '$2a$10$K.Ax2GBYpJzGfwHtfOP/SOUJuQni0aoD47mb5F7slvo7PPN9Zydse', true);

insert into user_role (user_id, roles)
  values (1, 'ADMIN'), (1, 'USER');