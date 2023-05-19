insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2000, 'George Orwell', 0, 'Animal farm', 1200, '2023-05-15', '2002-03-12');
insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2001, 'George Orwell', 0, '1984', 800, '2023-05-10', '2012-04-13');
insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2002, 'Jovan Ducic', 1, 'Blago cara Radovana', 1400, '2020-01-10', '2000-10-10');
insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2003, 'Marcus Aurelius', 3, 'Meditations', 950, '2018-05-15', '2018-07-11');
insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2004, 'Stefan Tosic', 1, 'Book', 3500, '2023-03-22', '2023-02-27');
insert into books (id, author, category, name, price, added_to_store_date, published_date) values (2006, 'Vojin', 3, 'Knjiga', 4000, '2023-05-14', '2023-05-10');

insert into users (id, username, password, name) values (2005, 'vojin', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'vojin');
insert into users (id, username, password, name) values (2007, 'stefan', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'stefan');
insert into users (id, username, password, name) values (2008, 'ogi', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'ogi');
insert into users (id, username, password, name) values (2009, 'vanja', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'vanja');
insert into users (id, username, password, name) values (2010, 'miki', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'miki');
insert into users (id, username, password, name) values (2011, 'saska', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'saska');
insert into users (id, username, password, name) values (2012, 'milan', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'milan');
insert into users (id, username, password, name) values (2013, 'marko', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'marko');
insert into users (id, username, password, name) values (2014, 'bogi', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'bogi');
insert into users (id, username, password, name) values (2015, 'roki', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'roki');
insert into users (id, username, password, name) values (2016, 'neso', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'neso');
insert into users (id, username, password, name) values (2017, 'rade', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'rade');
insert into users (id, username, password, name) values (2018, 'mile', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'mile');
insert into users (id, username, password, name) values (2019, 'sanja', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'sanja');
insert into users (id, username, password, name) values (2020, 'sandra', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'sandra');

-- za Animal farm ocjene
insert into rates (id, rate, book_id, user_id) values (2007, 4, 2000, 2005);
insert into rates (id, rate, book_id, user_id) values (2021, 3, 2000, 2007);
insert into rates (id, rate, book_id, user_id) values (2022, 4, 2000, 2008);
insert into rates (id, rate, book_id, user_id) values (2023, 4, 2000, 2009);
insert into rates (id, rate, book_id, user_id) values (2024, 5, 2000, 2010);
insert into rates (id, rate, book_id, user_id) values (2025, 3, 2000, 2011);
insert into rates (id, rate, book_id, user_id) values (2026, 5, 2000, 2012);
insert into rates (id, rate, book_id, user_id) values (2027, 4, 2000, 2013);
insert into rates (id, rate, book_id, user_id) values (2028, 5, 2000, 2014);
insert into rates (id, rate, book_id, user_id) values (2029, 2, 2000, 2015);
insert into rates (id, rate, book_id, user_id) values (2030, 3, 2000, 2016);
insert into rates (id, rate, book_id, user_id) values (2031, 5, 2000, 2017);

-- za Meditations ocjene
insert into rates (id, rate, book_id, user_id) values (2032, 5, 2003, 2017);
insert into rates (id, rate, book_id, user_id) values (2033, 5, 2003, 2016);
insert into rates (id, rate, book_id, user_id) values (2034, 4, 2003, 2015);
insert into rates (id, rate, book_id, user_id) values (2035, 5, 2003, 2014);
insert into rates (id, rate, book_id, user_id) values (2036, 4, 2003, 2013);
insert into rates (id, rate, book_id, user_id) values (2037, 5, 2003, 2012);
insert into rates (id, rate, book_id, user_id) values (2038, 4, 2003, 2011);
