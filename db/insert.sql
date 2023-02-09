INSERT INTO category (id, name)
VALUES (DEFAULT, 'Action');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Documentary');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Comedy');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Horror');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Crime');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Drama');

INSERT INTO category (id, name)
VALUES (DEFAULT, 'Thriller');


INSERT INTO movie (id, name, description, category_id)
VALUES (DEFAULT, 'Man on Fire', 'John Creasy, a despondent, alcoholic, former CIA officer turned bodyguard, who goes on a revenge rampage after his charge, nine-year-old Pita is abducted in Mexico City.', 1);

INSERT INTO movie (id, name, description, category_id)
VALUES (DEFAULT, 'General Magic', 'In 1990, at a secretive Silicon Valley startup, a small and passionate group of engineers and visionaries formed one of history’s greatest tech teams to build a magical device that would enable anyone to connect everyone, everywhere to everything.', 2);

INSERT INTO movie (id, name, description, category_id)
VALUES (DEFAULT, 'Another Round', 'Four friends decide to embark on an experiment to test Skårderud''s theory. They start a group log of what occurs when they start drinking at regular intervals to maintain this blood alcohol level.', 3);


INSERT INTO role (id, name)
VALUES (DEFAULT, 'ADMIN');

INSERT INTO role (id, name)
VALUES (DEFAULT, 'USER');