insert into book(description, title, quantity) values ('good book', 'java',3);
insert into book(description, title ,quantity) values ('normal book', 'java11',1);
insert into book(description, title ,quantity) values ('normal book', 'java8',4);
insert into book(description, title ,quantity) values ('bad book', 'javascript',5);

insert into author(name) values ('author1');
insert into author(name) values ('author2');
insert into author(name) values ('author3');

insert into book_author(author_id, book_id) VALUES (1,1);
insert into book_author(author_id, book_id) VALUES (1,2);
insert into book_author(author_id, book_id) VALUES (1,3);
insert into book_author(author_id, book_id) VALUES (1,4);
insert into book_author(author_id, book_id) VALUES (2,2);
insert into book_author(author_id, book_id) VALUES (3,2);

insert into customer (email, first_name, last_name, login)
values ('xfe66323@bcaoo.com', 'Alex', 'Shirokov', 'Hydropumpon');
insert into customer (email, first_name, last_name, login)
values ('shirokov.alex1989@yandex.ru', 'Igor', 'Shirokov', 'Shi4r');



