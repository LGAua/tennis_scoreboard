-- Вставка игроков
INSERT INTO players (name) VALUES ('Roger Federer');
INSERT INTO players (name) VALUES ('Rafael Nadal');
INSERT INTO players (name) VALUES ('Serena Williams');
INSERT INTO players (name) VALUES ('Lebron James');
INSERT INTO players (name) VALUES ('Cristiano Ronaldo');
INSERT INTO players (name) VALUES ('Muhamed Ali');
INSERT INTO players (name) VALUES ('Dwayne Wade');
INSERT INTO players (name) VALUES ('Stephen Curry');
INSERT INTO players (name) VALUES ('Lionel Messi');

-- Вставка матчей
INSERT INTO matches (player_1, player_2, winner) VALUES (1, 2, 2); -- Roger Federer vs Rafael Nadal (Winner: Rafael Nadal)
INSERT INTO matches (player_1, player_2, winner) VALUES (1, 3, 3); -- Roger Federer vs Serena Williams (Winner: Serena Williams)
INSERT INTO matches (player_1, player_2, winner) VALUES (3, 2, 2); -- Serena Williams vs Rafael Nadal (Winner: Rafael Nadal)
INSERT INTO matches (player_1, player_2, winner) VALUES (4, 5, 5); -- Lebron James vs Cristiano Ronaldo (Winner: Cristiano Ronaldo)
INSERT INTO matches (player_1, player_2, winner) VALUES (4, 6, 6); -- Lebron James vs Muhamed Ali (Winner: Muhamed Ali)
INSERT INTO matches (player_1, player_2, winner) VALUES (5, 2, 2); -- Cristiano Ronaldo vs Rafael Nadal (Winner: Rafael Nadal)
INSERT INTO matches (player_1, player_2, winner) VALUES (5, 6, 5); -- Cristiano Ronaldo vs Muhamed Ali (Winner: Cristiano Ronaldo)
INSERT INTO matches (player_1, player_2, winner) VALUES (3, 6, 6); -- Serena Williams vs Muhamed Ali (Winner: Muhamed Ali)
INSERT INTO matches (player_1, player_2, winner) VALUES (4, 5, 5); -- Lebron James vs Cristiano Ronaldo (Winner: Cristiano Ronaldo)
INSERT INTO matches (player_1, player_2, winner) VALUES (4, 8, 8); -- Lebron James vs Stephen Curry (Winner: Stephen Curry)
INSERT INTO matches (player_1, player_2, winner) VALUES (5, 2, 2); -- Cristiano Ronaldo vs Rafael Nadal (Winner: Rafael Nadal)
INSERT INTO matches (player_1, player_2, winner) VALUES (5, 9, 5);
INSERT INTO matches (player_1, player_2, winner) VALUES (7, 6, 7);