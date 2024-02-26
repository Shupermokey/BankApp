create database bankapp;

use bankapp;

Create table `users` (
`id` int not null auto_increment,
`username` varchar(45) Not null,
`password` varchar(45) not null,
`enabled` int not null,
primary key(`id`));

Create table `authorities` (
`id` int not null auto_increment,
`username` varchar(45) Not null,
`authority` varchar(45) not null,
primary key(`id`));

insert ignore into `users` values(Null, 'happy','test','1');
insert ignore into `authorities` values(Null, 'happy','write');


