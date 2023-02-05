insert into Roles values(1,'ADMIN');
insert into Roles values(2,'USER');

insert into Users values(1, '$2a$12$zZjpygPKcWB5PUMsh39Qyugn.8oeYQUj2mMxq3w21Zpyr/giGt7/W', 'sahooanupam');--password is anupam
insert into Users values(2, '$2a$12$V/l50RNCx3z2LKdbojRCle8e0lUgF2LqAsyxOWyRLFrZa9QDiDDnq', 'sahooatanu');--password is atanu

insert into users_roles values(1,1);
insert into users_roles values(2,2);