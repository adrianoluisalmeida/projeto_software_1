drop database blog;
create database blog;
use blog;

create table post(
	id int auto_increment,
    titulo varchar(255) not null,
    conteudo text not null,
    data_pub timestamp not null default current_timestamp, 
    primary key(id)
);

create table arquivo(
	id int auto_increment,
    post_id int not null,
    nome char(20) not null,
    descr varchar(255),
    primary key(id),
    foreign key (post_id) references post(id)
);

create table tag(
	id int auto_increment,
    nome char(20) not null,
    primary key(id)
);

create table post_tag(
	post_id int,
    tag_id int,
    primary key(post_id, tag_id),
    foreign key(post_id) references post(id),
    foreign key(tag_id) references tag(id)
);