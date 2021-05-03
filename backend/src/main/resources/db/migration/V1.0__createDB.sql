create sequence hibernate_sequence start with 1 increment by 1;

create table user (
    username varchar(255) not null,
    firstname varchar(255),
    lastname varchar(255),
    email varchar(255),
    enabled boolean not null,
    password varchar(255) not null,
    primary key (username)
);

create table movie(
    id bigint generated by default as identity,
    title varchar(255) not null,
    director varchar(255) not null,
    date_of_release date not null,
    primary key(id)
);

/*create table movie_users(
    users_username varchar(255) not null,
    movie_id bigint not null
);
create table user_movies(
   user_username varchar(255) not null,
   movies_id bigint not null
);*/

create table review
(
    id bigint generated by default as identity,
    movie_id bigint not null,
    review varchar(255) not null,
    date_written date not null,
    user_username varchar(255) not null,
    stars integer not null check (stars<= 5 AND stars>=1),
    primary key (id)
);

/*alter table movie_users add constraint FKqchb6i03kpgj2gnq4xhmkydxc foreign key (users_username) references user;
alter table movie_users add constraint FKcrnfc5k5lxdfurq3b97o52dnv foreign key (movie_id) references movie;
alter table user_movies add constraint UK_812qgr2woxiu6e0776qiioy12 foreign key (user_username) references user;
alter table user_movies add constraint FK6qbykpe0c8j68iwgm77hjtvhp foreign key (movies_id) references movie;*/
/*

alter table review add constraint FK6qbykpe0c8j68iwgm77hjtvhp foreign key (user_username) references user;
alter table review add constraint UK_812qgr2woxiu6e0776qiioy12 foreign key (movie_id) references movie;
*/

