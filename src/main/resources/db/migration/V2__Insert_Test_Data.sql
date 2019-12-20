insert into book(id,description, title) values (1,'good book', 'java');
insert into book(id,description, title) values (2,'normal book', 'java11');
insert into book(id,description, title) values (3,'normal book', 'java8');
insert into book(id,description, title) values (4,'bad book', 'javascript');

insert into author(id,name) values (1,'author1');
insert into author(id,name) values (2,'author2');
insert into author(id,name) values (3,'author3');

insert into author_book(author_id, book_id) VALUES (1,1);
insert into author_book(author_id, book_id) VALUES (1,2);
insert into author_book(author_id, book_id) VALUES (1,3);
insert into author_book(author_id, book_id) VALUES (1,4);
insert into author_book(author_id, book_id) VALUES (2,2);
insert into author_book(author_id, book_id) VALUES (3,2);

insert into customer (email, first_name, last_name, login)
values ('shirokov.aleksander@gmail.com', 'Alex', 'Shirokov', 'Hydropumpon');
insert into customer (email, first_name, last_name, login)
values ('shirokov.alex1989@yandex.ru', 'Igor', 'Shirokov', 'Shi4r');



