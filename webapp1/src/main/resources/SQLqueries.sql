/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Victor
 * Created: 20-feb-2022
 */

drop table users;
drop table videos;

create table users (
    userId      numeric         primary key,
    username    varchar(100)    not null,
    realName    varchar(100)    not null, 
    surname     varchar(30)     not null,
    password    varchar(100)    not null,
    email       varchar(100)    not null,

    age         numeric,
    description varchar(300)

     );

create table videos (
    videoId      numeric         primary key,
    title        varchar(100)    not null,
    author       varchar(100)    not null,
    creationDate varchar(100)    not null,
    duration     time            not null,
    views        numeric         not null default 0,
    description  varchar(255)    not null,
    format       varchar(5)      not null
    );

alter table users add constraint age_between_range check (age >= 12 and age <= 100);


INSERT INTO users (userID, username, realName, surname, password, email, age, description)
VALUES (1,'Uriel27','Uriel','Andrango','123','uriel@hotmail.com',22,'No español, no comprendo');

INSERT INTO users (userID, username, realName, surname, password, email, age, description)
VALUES (2,'AdriByte','Adri','Alv','1234','adri@hotmail.com',22,'UWU');

INSERT INTO videos (videoId, title, author, creationDate, duration, views, description, format)
VALUES (1,'Trailer Spiderman No way Home','Marvel', '2015-12-17','13:30',3000,'No me quiero ir Sr Stark :(','mp4');