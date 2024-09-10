package com.lga.tennisscoreboard.dto;

import com.lga.tennisscoreboard.entity.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchDto {
    Player playerOne;

    Player playerTwo;

    int currentSet;

    int scorePlayerOne;

    int scorePlayerTwo;

    int setWinsByPlayerOne;

    int setWinsByPlayerTwo;
}
