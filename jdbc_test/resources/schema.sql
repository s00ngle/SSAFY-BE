use ssafydb;

create table board
(
	article_no int not null auto_increment primary key,
	user_id varchar(16) null default null,
	subject varchar(100) null default null,
	content varchar(2000) null default null,
	register_time timestamp not null default current_timestamp
);