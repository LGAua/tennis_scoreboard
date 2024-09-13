package com.lga.tennisscoreboard.dto;

import com.lga.tennisscoreboard.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {
    Player playerOne;

    Player playerTwo;

    Player winner;

    String scorePlayerOne;

    String scorePlayerTwo;

    int currentSet;

    int currentGame;

    int gameWinsByPlayerOne;

    int gameWinsByPlayerTwo;

    int setWinsByPlayerOne;

    int setWinsByPlayerTwo;
}
