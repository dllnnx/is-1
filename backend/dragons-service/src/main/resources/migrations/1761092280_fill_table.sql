INSERT INTO location (x, y, name)
VALUES (10.5, 20, 'Mountain Peak'),
       (25.3, 15, 'Dark Forest'),
       (5.2, 8, 'Golden Valley'),
       (100.1, 50, 'Crystal Lake'),
       (77.7, 33, 'Ancient Ruins');

INSERT INTO coordinates (x, y)
VALUES (120.5, 300.2),
       (150.0, 280.0),
       (100.3, 150.7),
       (170.5, 330.8),
       (160.8, 260.1),
       (90.5, 200.0),
       (180.0, 350.0),
       (130.0, 250.5),
       (145.5, 275.5),
       (110.0, 225.0);

INSERT INTO dragon_cave (depth, number_of_treasures)
VALUES (500.0, 10),
       (350.5, 20),
       (420.0, 15),
       (600.0, 25),
       (700.7, 30);

INSERT INTO dragon_head (tooth_count)
VALUES (120.5),
       (98.0),
       (110.0),
       (130.3),
       (90.9);

INSERT INTO person (name, eye_color, hair_color, location_id, height, weight, passport_id)
VALUES ('Arthur', 'GREEN', 'BROWN', 1, 180.5, 75.0, 'A12345'),
       ('Merlin', 'RED', 'WHITE', 2, 170.0, 68.0, 'B23456'),
       ('Morgana', 'WHITE', 'RED', 3, 165.0, 60.0, 'C34567'),
       ('Galahad', 'BROWN', 'GREEN', 4, 182.0, 82.0, 'D45678'),
       ('Lancelot', 'GREEN', 'BROWN', 5, 178.0, 79.0, 'E56789');

INSERT INTO dragon (name, coordinates_id, cave_id, killer_id, age, color, type, character, head_id)
VALUES ('Smaug', 1, 1, 1, 500, 'RED', 'FIRE', 'CUNNING', 1),
       ('Firnen', 2, 2, 2, 250, 'GREEN', 'AIR', 'WISE', 2),
       ('Glaedr', 3, 3, 3, 400, 'BROWN', 'FIRE', 'GOOD', 3),
       ('Saphira', 4, 4, 4, 120, 'RED', 'WATER', 'FICKLE', 4),
       ('Thorn', 5, 5, 5, 150, 'RED', 'AIR', 'CHAOTIC_EVIL', 5),
       ('Abraxas', 6, 1, NULL, 300, 'WHITE', 'UNDERGROUND', 'WISE', 1),
       ('Nidhogg', 7, 2, 2, 900, 'BROWN', 'UNDERGROUND', 'CHAOTIC_EVIL', 2),
       ('Leviathan', 8, 3, NULL, 800, 'GREEN', 'WATER', 'GOOD', 3),
       ('Fafnir', 9, 4, 1, 600, 'RED', 'FIRE', 'CUNNING', 4),
       ('Tiamat', 10, 5, 3, 1000, 'WHITE', 'AIR', 'FICKLE', 5);

