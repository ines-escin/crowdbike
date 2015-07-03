insert into roles (role_description) values ("ROLE_ADMIN");
insert into roles (role_description) values ("ROLE_USER");

insert into users (user_username, user_password) values ("admin", "admin");
insert into users (user_username, user_password)  values ("user", "user");

insert into user_roles values (1, 1);
insert into user_roles values (2, 2);
