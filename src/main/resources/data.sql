-- Вставка игроков
INSERT INTO players (id, name) VALUES (1, 'Roger Federer');
INSERT INTO players (id, name) VALUES (2, 'Rafael Nadal');
INSERT INTO players (id, name) VALUES (3, 'Serena Williams');
INSERT INTO players (id, name) VALUES (4, 'Lebron James');
INSERT INTO players (id, name) VALUES (5, 'Cristiano Ronaldo');
INSERT INTO players (id, name) VALUES (6, 'Muhamed Ali');
INSERT INTO players (id, name) VALUES (7, 'Dwayne Wade');
INSERT INTO players (id, name) VALUES (8, 'Stephen Curry');
INSERT INTO players (id, name) VALUES (9, 'Lionel Messi');

-- Вставка матчей
INSERT INTO matches (id, player_1, player_2, winner) VALUES (1, 1, 2, 2);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (2, 1, 3, 3);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (3, 3, 2, 2);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (4, 4, 5, 5);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (5, 4, 6, 6);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (6, 5, 2, 2);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (7, 5, 6, 5);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (8, 3, 6, 6);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (9, 4, 5, 5);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (10, 4, 8, 8);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (11, 5, 2, 2);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (12, 5, 9, 5);
INSERT INTO matches (id, player_1, player_2, winner) VALUES (13, 7, 6, 7);
