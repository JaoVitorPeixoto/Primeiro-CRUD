create database Cadastro1;

create table Pessoa(
id int auto_increment,
nome varchar(30),
idade int not null,
nacionalidade varchar(20) not null default 'Brasil',
primary key(id)
);
