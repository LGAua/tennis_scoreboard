package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;

public class MatchCalculationService {

    public MatchDto addPointToPlayerOne(MatchDto match) {
        int currentScore = match.getScorePlayerOne();
        match.setScorePlayerOne(currentScore >= 30 ? currentScore + 10 : currentScore + 15);
        return match;

    }

    public MatchDto addPointToPlayerTwo(MatchDto match) {
        int currentScore = match.getScorePlayerTwo();
        match.setScorePlayerTwo(currentScore >= 30 ? currentScore + 10 : currentScore + 15);
        return match;
    }


}
