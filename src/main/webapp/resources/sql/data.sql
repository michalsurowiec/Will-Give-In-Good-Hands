INSERT INTO category VALUES (null, 'ubrania, które nadają się do ponownego użycia'), (null, 'ubrania do wyrzucenia'), (null, 'zabawki'), (null, 'książki'), (null, 'inne')

INSERT INTO institution VALUES (null, 'Pomoc dzieciom z ubogich rodzin.', 'Dbam o Zdrowie'), (null, 'Pomoc wybudzaniu dzieci ze śpiączki.', 'A kogo'), (null, 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Dla dzieci'), (null, 'Pomoc dla osób nie posiadających miejsca zamieszkania', 'Bez domu')

INSERT INTO role VALUES (null, 'ROLE_USER'), (null, 'ROLE_ADMIN'), (null, 'ROLE_BANNED'), (null, 'ROLE_UNAUTHORISED')

# TODO delete information about passwords after everything is done

# michal@o2.pl password - 123
# kamil@o2.pl password - 123
INSERT INTO user VALUES (null, 'c6efccbb-b128-4310-bd88-9a94b793d1bb', 'michalsurowiec94@o2.pl', 'Michał', '$2a$10$k/5lxkDRWVsLDXqD5inwOe5ANN/9jlwPC38POSCHlb1spbgGslyny', 'Surowiec'), (null, 'd0334faf-f26b-4b54-8b59-f8887ee34378', 'kamil@o2.pl', 'Kamil', '$2a$10$k/5lxkDRWVsLDXqD5inwOe5ANN/9jlwPC38POSCHlb1spbgGslyny', 'Surowiec')

INSERT INTO status VALUES (null, 'Nieodebrane'), (null, 'Odebrane')

INSERT INTO donation VALUES (null, 'Wrocław', '2019-08-28', '2019-08-01', 'Po prostu to weźcie', '2019-07-19', '15:08', 1, 'Daszyńskiego', '50-156', 1, 2, 2), (null, 'Wałbrzych', null, '2019-08-02', 'Nara', '2019-07-18', '12:08', 2, 'Paderewskiego', '30-156', 2, 1, 2), (null, 'Inowrocław', '2019-08-27', '2019-07-31', 'Kai mosz bilet', '2019-07-17', '15:39', 1, 'Wyszyńskiego', '50-789', 3, 2, 2), (null, 'Kożuszki', null, '2019-08-04', 'Hasiok', '2019-07-16', '10:08', 2, 'Piłsudskiego', '19-156', 4, 1, 2), (null, 'Wrocław', '2019-08-20', '2019-07-30', 'Nudzi mi się', '2019-07-15', '9:09', 1, '3 Maja', '42-400', 1, 2, 2)

INSERT INTO donation_categories VALUES (1, 1), (2, 2), (3, 3), (4, 1), (5, 2), (2, 3), (3, 2)

INSERT INTO user_roles VALUES (2, 1), (1, 2)

