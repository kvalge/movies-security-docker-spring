INSERT INTO role (id, name)
VALUES (DEFAULT, 'ADMIN');

INSERT INTO role (id, name)
VALUES (DEFAULT, 'USER');


INSERT INTO user_data (id, name, username, email, password)
VALUES (DEFAULT, 'Admin', 'admin', 'admin@mail.com', '$2a$12$pIC6l37ABefWb0KvKespoOMILsJ44NcNPWZQqOti9tgwbJkdF5w7C');


INSERT INTO user_data (id, name, username, email, password)
VALUES (DEFAULT, 'Kati Karu', 'kati', 'kati@mail.com', '$2a$12$CMaN6YX3cWVIG.ayrVlIiuYXvJ3ThI7zBKmoNb.r8wMM6oYVdFFGu');

INSERT INTO user_data (id, name, username, email, password)
VALUES (DEFAULT, 'Uudu Hunt', 'uudu', 'uudu@mail.com', '$2a$10$hLXNhMuXgnsX2OSFxJv3S.vQ.20A00nN.SJE0.1s3FxvN/klBih0e');


INSERT INTO user_data_role(user_data_id, role_id)
VALUES (1, 1);

INSERT INTO user_data_role(user_data_id, role_id)
VALUES (2, 2);

INSERT INTO user_data_role(user_data_id, role_id)
VALUES (3, 2);


INSERT INTO category (id, name)
VALUES (DEFAULT, 'Action');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Comedy');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Crime');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Documentary');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Drama');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Drama-Comedy');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Horror');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Thriller');


INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'Man on Fire', 1);

INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'General Magic', 4);

INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'Another Round', 6);

INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'Spy Game', 1);

INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'Lost in Translation', 5);

INSERT INTO movie (id, name, category_id)
VALUES (DEFAULT, 'Bohemian Rhapsody', 6);


INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Tony Scott',
        'A.J. Quinnell, Brian Helgeland',
        'Denzel Washington, Dakota Fanning',
        '2004',
        'Switzerland, United Kingdom, United States, Mexico',
        'John Creasy, a despondent, alcoholic, former CIA officer turned bodyguard, who goes on a revenge rampage after his charge, nine-year-old Pita is abducted in Mexico City.',
        'https://images.bauerhosting.com/legacy/empire-tmdb/films/9509/images/2cBmeSLCyLqkn6YIERKdRUToqsa.jpg?format=jpg&quality=80&width=960&height=540&ratio=16-9&resize=aspectfill',
        1);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Sarah Kerruish, Matt Maude',
        'Sarah Kerruish, Jonathan Key, Matt Maude, Michael Stern, Ceri Tallett',
        'Tony Fadell, Andy Hertzfeld, Marc Porat, John Sculley, Megan Smith',
        '2018',
        'United States',
        'In 1990, at a secretive Silicon Valley startup, a small and passionate group of engineers and visionaries formed one of history’s greatest tech teams to build a magical device that would enable anyone to connect everyone, everywhere to everything.',
        'https://www.hollywoodreporter.com/wp-content/uploads/2018/04/4._not_for_commercial_2f_print_use_-_the_macintosh_team_-_h_2018.jpg',
        2);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Thomas Vinterberg',
        'Thomas Vinterberg, Tobias Lindholm',
        'Mads Mikkelsen, Thomas Bo Larsen, Magnus Millang, Lars Ranthe',
        '2020',
        'Denmark, Sweden, Netherlands',
        'Four friends decide to embark on an experiment to test Skårderud theory. They start a group log of what occurs when they start drinking at regular intervals to maintain this blood alcohol level.',
        'https://cdn.cnn.com/cnnnext/dam/assets/210415155417-another-round-film-still-1-full-169.jpg',
        3);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Tony Scott',
        'Michael Frost Beckner, David Arata',
        'Robert Redford, Brad Pitt, Catherine McCormack',
        '2001',
        'United States, Germany, Japan, France, United Kingdom',
        'Retiring CIA agent Nathan Muir recalls his training of Tom Bishop while working against agency politics to free him from his Chinese captors.',
        'https://cdn.gelestatic.it/kataweb/tvzap/2018/07/Spy-Game-5_1000.jpg',
        4);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Sofia Coppola',
        'Sofia Coppola',
        'Bill Murray, Scarlett Johansson',
        '2003',
        'United States, Japan',
        'A faded movie star and a neglected young woman form an unlikely bond after crossing paths in Tokyo.',
        'https://images.squarespace-cdn.com/content/v1/5a59a10d4c326de87b455acd/1535389898147-UZ4KFUQEP0S5DRWL8NUG/Charlotte-Bob-lost-in-translation-1041685_1400_930.jpg?format=2500w',
        5);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Bryan Singer',
        'Anthony McCarten, Peter Morgan',
        'Rami Malek, Lucy Boynton, Gwilym Lee',
        '2018',
        'United KingdomUnited States',
        'The story of the legendary British rock band Queen and lead singer Freddie Mercury, leading up to their famous performance at Live Aid (1985).',
        'https://neiloseman.com/wp-content/uploads/2021/12/bohemian.jpeg',
        6);
