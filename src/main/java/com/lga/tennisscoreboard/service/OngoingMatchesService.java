package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, MatchDto> MATCHES = new ConcurrentHashMap<>();

    public UUID addMatch(MatchDto matchDto) {
        UUID uuid = UUID.randomUUID();
        MATCHES.put(uuid, matchDto);
        return uuid;
    }

    public MatchDto getMatch(UUID uuid) {
        return MATCHES.get(uuid);
    }

    public void finalizeMatch(UUID uuid) {
        MATCHES.remove(uuid);
    }

    public boolean isMatchWithSuchPlayerOngoing(Player playerOne, Player playerTwo) {
        for (Map.Entry<UUID, MatchDto> entry : MATCHES.entrySet()) {
            String playerOneName = entry.getValue().getPlayerOne().getName();
            String playerTwoName = entry.getValue().getPlayerTwo().getName();

            if (playerOneName.equals(playerOne.getName())
                    && playerTwoName.equals(playerTwo.getName())) {
                return true;
            }
        }
        return false;
    }

    public UUID getMatchIdByPlayersName(Player playerOne, Player playerTwo) {
        for (Map.Entry<UUID, MatchDto> entry : MATCHES.entrySet()) {
            String playerOneName = entry.getValue().getPlayerOne().getName();
            String playerTwoName = entry.getValue().getPlayerTwo().getName();

            if (playerOneName.equals(playerOne.getName())
                    && playerTwoName.equals(playerTwo.getName())) {
                return entry.getKey();
            }
        }
        return null;
    }
}
