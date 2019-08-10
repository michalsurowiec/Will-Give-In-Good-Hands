INSERT INTO category VALUES (null, 'ubrania, które nadają się do ponownego użycia'), (null, 'ubrania do wyrzucenia'), (null, 'zabawki'), (null, 'książki'), (null, 'inne')

INSERT INTO institution VALUES (null, 'Pomoc dzieciom z ubogich rodzin.', 'Dbam o Zdrowie'), (null, 'Pomoc wybudzaniu dzieci ze śpiączki.', 'A kogo'), (null, 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.', 'Dla dzieci'), (null, 'Pomoc dla osób nie posiadających miejsca zamieszkania', 'Bez domu')

INSERT INTO donation VALUES (null, 'Wrocław', 'Po prostu to weźcie', '2019-07-19', '15:08', 1, 'Daszyńskiego', '50-156', 1), (null, 'Wałbrzych', 'Nara', '2019-07-18', '12:08', 2, 'Paderewskiego', '30-156', 2), (null, 'Inowrocław', 'Kai mosz bilet', '2019-07-17', '15:39', 1, 'Wyszyńskiego', '50-789', 3), (null, 'Kożuszki', 'Hasiok', '2019-07-16', '10:08', 2, 'Piłsudskiego', '19-156', 4), (null, 'Wrocław', 'Nudzi mi się', '2019-07-15', '9:09', 1, '3 Maja', '42-400', 1)

INSERT INTO donation_categories VALUES (1, 1), (2, 2), (3, 3), (4, 1), (5, 2), (2, 3), (3, 2)

INSERT INTO role VALUES (null, 'ROLE_USER')

# password - 123
INSERT INTO user VALUES (null, 'michal@o2.pl', 'Michał', '$2a$10$k/5lxkDRWVsLDXqD5inwOe5ANN/9jlwPC38POSCHlb1spbgGslyny', 'Surowiec')

INSERT INTO user_roles VALUES (1, 1)