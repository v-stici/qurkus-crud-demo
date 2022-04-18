insert into home (address, year)
values ('str. Stefan cel Mare 103', 2000),
		('bd Dacia 5', 2010),
		('str. Stefan cel Mare 75', 1990);

insert into apartment (haveInternet, phone, rooms, size, home_id)
    values (1, '11-123', 4, 100, 1),
            (1, '22-123', 2, 60, 1),
            (0, '33-123', 1, 40, 1),
            (1, '44-123', 2, 70, 2),
            (0, '55-123', 2, 50, 2),
            (1, '66-123', 3, 90, 2),
            (0, '77-123', 1, 30, 3);

insert into person (genre, lastName, name, apartment_id)
    values ('m', 'Vladz', 'Stici', 1),
        ('m', 'Chung', 'Braxton', 1),
        ('f', 'Chen', 'Trey', 1),
        ('m', 'Mcgrath', 'Darien', 2),
        ('f', 'Hendrix', 'Brennen', 2),
        ('m', 'Bowers', 'Kaden', 2),
        ('f', 'Lawson', 'Talan', 2),
        ('m', 'Knapp', 'Edgar', 3),
        ('f', 'Stark', 'Tyler', 3),
        ('m', 'Foster', 'Jovanny', 3),
        ('f', 'Pittman', 'Leyla', 4),
        ('m', 'Ferrell', 'Kiera', 4),
        ('f', 'Mclaughlin', 'Axmed', 4),
        ('m', 'James', 'Kathi', 4),
        ('f', 'Sutton', 'Darren', 5),
        ('m', 'Charles', 'Madie', 5),
        ('f', 'Fields', 'Andres', 5),
        ('m', 'Morse', 'Elwood', 6),
        ('f', 'Cowan', 'Lucille', 6),
        ('m', 'Salas', 'Jess', 6),
        ('f', 'Young', 'Ayesha', 6),
        ('m', 'Camacho', 'Nannie', 7),
        ('f', 'Hill', 'Debi', 7),
        ('m', 'Valenzuela', 'Collin', 7),
        ('f', 'Gardner', 'Gloria', 7);




