insert into book(description, title, quantity) values ('good book', 'java',3) ON CONFLICT DO NOTHING;
insert into book(description, title ,quantity) values ('normal book', 'java11',1) ON CONFLICT DO NOTHING;
insert into book(description, title ,quantity) values ('normal book', 'java8',4) ON CONFLICT DO NOTHING;
insert into book(description, title ,quantity) values ('bad book', 'javascript',5) ON CONFLICT DO NOTHING;

insert into author(name) values ('author1') ON CONFLICT DO NOTHING;
insert into author(name) values ('author2') ON CONFLICT DO NOTHING;
insert into author(name) values ('author3') ON CONFLICT DO NOTHING;

insert into book_author(author_id, book_id) VALUES (1,1) ON CONFLICT DO NOTHING;
insert into book_author(author_id, book_id) VALUES (1,2) ON CONFLICT DO NOTHING;
insert into book_author(author_id, book_id) VALUES (1,3) ON CONFLICT DO NOTHING;
insert into book_author(author_id, book_id) VALUES (1,4) ON CONFLICT DO NOTHING;
insert into book_author(author_id, book_id) VALUES (2,2) ON CONFLICT DO NOTHING;
insert into book_author(author_id, book_id) VALUES (3,2) ON CONFLICT DO NOTHING;

insert into customer (email, first_name, last_name, login)
values ('xfe66323@bcaoo.com', 'Alex', 'Shirokov', 'Hydropumpon') ON CONFLICT DO NOTHING;
insert into customer (email, first_name, last_name, login)
values ('shirokov.alex1989@yandex.ru', 'Igor', 'Shirokov', 'Shi4r') ON CONFLICT DO NOTHING;;



