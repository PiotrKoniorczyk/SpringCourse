create table todo (
    id int unsigned primary key auto_increment,
    text varchar(100) not null,
    done bit
);

insert into todo (text, done) values ('Done todo', 1);
insert into todo (text, done) values ('Undone todo', 0);
