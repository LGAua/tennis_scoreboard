package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.repository.PlayerRepository;

public class PlayerPersistenceService {

    private static final PlayerRepository playerRepository = new PlayerRepository();

    public void savePlayer(Player player) {
        if (playerRepository.findPlayerByName(player.getName()).isEmpty()) {
            playerRepository.save(player);
        }
    }
}
