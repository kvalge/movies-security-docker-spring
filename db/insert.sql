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
        'Hard-drinking, burnt-out ex-CIA operative John Creasy has given up on life until he''s hired as a bodyguard to protect 9-year-old Pita Ramos. Bit by bit, Creasy begins to reclaim some of his soul, but when Pita is kidnapped, Creasy''s fiery rage is finally released and he will stop at nothing to save her as he sets out on a dangerous, revenge-fueled rescue mission.',
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
        'Teachers Martin, Tommy, Peter, and Nikolaj struggle with unmotivated students and feel that their lives have become boring and stale. At a dinner celebrating Nikolaj''s 40th birthday, the group begins to discuss the theory of psychiatrist Finn Skårderud — that humans are born with a blood alcohol content (BAC) deficiency of 0.05%, and that being at 0.05% makes one more creative and relaxed. The friends decide to embark on an experiment to test Skårderud''s theory.',
        'https://cdn.cnn.com/cnnnext/dam/assets/210415155417-another-round-film-still-1-full-169.jpg',
        3);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Tony Scott',
        'Michael Frost Beckner, David Arata',
        'Robert Redford, Brad Pitt, Catherine McCormack',
        '2001',
        'United States, Germany, Japan, France, United Kingdom',
        'In 1991, the United States and China are close to a major trade agreement. The Central Intelligence Agency (CIA) learns that its asset Tom has been arrested at a People''s Liberation Army prison in Suzhou and will be executed in 24 hours, unless the U.S. government claims him and bargains for his release. Bishop''s actions, unsanctioned by the CIA, risk jeopardizing the agreement. Nathan D. Muir, a veteran case officer and Bishop''s mentor, who plans to retire from the Agency at the end of the day, recalls his training of Tom Bishop while working against politics to free him from his Chinese captors.',
        'https://cdn.gelestatic.it/kataweb/tvzap/2018/07/Spy-Game-5_1000.jpg',
        4);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Sofia Coppola',
        'Sofia Coppola',
        'Bill Murray, Scarlett Johansson',
        '2003',
        'United States, Japan',
        'Bob Harris is a fading American movie star who is having a midlife crisis when he travels to Tokyo to promote Suntory whisky. There he befriends another estranged American named Charlotte, a young woman and recent college graduate. The film explores themes of alienation and disconnection against a backdrop of cultural displacement in Japan.',
        'https://images.squarespace-cdn.com/content/v1/5a59a10d4c326de87b455acd/1535389898147-UZ4KFUQEP0S5DRWL8NUG/Charlotte-Bob-lost-in-translation-1041685_1400_930.jpg?format=2500w',
        5);

INSERT INTO movie_details (id, director, writer, stars, year, country, description, image, movie_id)
VALUES (DEFAULT,
        'Bryan Singer',
        'Anthony McCarten, Peter Morgan',
        'Rami Malek, Lucy Boynton, Gwilym Lee',
        '2018',
        'United Kingdom, United States',
        'The story of the legendary British rock band Queen and lead singer Freddie Mercury, leading up to their famous performance at Live Aid (1985).',
        'https://neiloseman.com/wp-content/uploads/2021/12/bohemian.jpeg',
        6);

