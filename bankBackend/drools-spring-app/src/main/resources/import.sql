insert into users (id, username, password, name, role) values (2005, 'vojin', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'vojin', 0);
insert into users (id, username, password, name, role) values (2006, 'stefan', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'stefan', 0);
insert into users (id, username, password, name, role) values (2007, 'ogi', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'ogi', 0);
insert into users (id, username, password, name, role) values (2008, 'vanja', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'vanja', 0);
insert into users (id, username, password, name, role) values (2009, 'sandra', '$2a$12$qpW8fwoq457m0A3KRe0S..W3lpn1mjJ/N2ULgKixsy/WNN0/G7bdy', 'sandra', 1);

insert into bank_accounts (id, card_expiration_year, card_expiration_month, card_number, account_number, cvv_number, user_id, balance) values (2010, 2025, 6, 3245678456432457, 6754321456543247, 789, 2005, 12000);
insert into bank_accounts (id, card_expiration_year, card_expiration_month, card_number, account_number, cvv_number, user_id, balance) values (2011, 2024, 4, 9095678456432324, 4364321456543189, 426, 2006, 1500);
insert into bank_accounts (id, card_expiration_year, card_expiration_month, card_number, account_number, cvv_number, user_id, balance) values (2012, 2024, 9, 1462678456438371, 7847321456542362, 426, 2007, 0);


insert into transactions (id, amount, date_time, location_latitude, location_longitude, receiver_bank_account_id, sender_bank_account_id, fraudulent_warning, sender_id, fraudulent_warning_message) values (2013, 100, '2023-06-21 14:30:00', 44.869566, 17.665829, 2011, 2010, false, 2005, '');
insert into transactions (id, amount, date_time, location_latitude, location_longitude, receiver_bank_account_id, sender_bank_account_id, fraudulent_warning, sender_id, fraudulent_warning_message) values (2014, 200, '2023-06-21 14:49:00', 44.869566, 17.665829, 2011, 2010, false, 2005, '');



