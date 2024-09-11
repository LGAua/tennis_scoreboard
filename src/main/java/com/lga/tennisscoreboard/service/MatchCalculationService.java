package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;

public class MatchCalculationService {

    public void addPointToPlayerOne(MatchDto match) {
        int currentScore = match.getScorePlayerOne();
        match.setScorePlayerOne(currentScore >= 30 ? currentScore + 10 : currentScore + 15);
        checkForWin(match);
    }

    public void addPointToPlayerTwo(MatchDto match) {
        int currentScore = match.getScorePlayerTwo();
        match.setScorePlayerTwo(currentScore >= 30 ? currentScore + 10 : currentScore + 15);
        checkForWin(match);
    }

    private void checkForWin(MatchDto match) {
        if (isGameIsOver(match)) {
            match.setScorePlayerOne(0);
            match.setScorePlayerTwo(0);
            if (isSetIsOver(match)) {
                match.setGameWinsByPlayerOne(0);
                match.setGameWinsByPlayerTwo(0);
                if (match.getSetWinsByPlayerOne() == 2) {
                    match.setWinner(match.getPlayerOne());
                } else if (match.getSetWinsByPlayerTwo() == 2) {
                    match.setWinner(match.getPlayerTwo());
                }
            }
        }
    }

    private boolean isSetIsOver(MatchDto match) {
        if (match.getGameWinsByPlayerOne() >= 6 && (match.getGameWinsByPlayerOne() - match.getGameWinsByPlayerTwo()) >= 2) {
            int current = match.getSetWinsByPlayerOne();
            match.setSetWinsByPlayerOne(++current);
            return true;
        } else if (match.getGameWinsByPlayerTwo() >= 6 && (match.getGameWinsByPlayerTwo() - match.getGameWinsByPlayerOne()) >= 2) {
            int current = match.getSetWinsByPlayerTwo();
            match.setSetWinsByPlayerTwo(++current);
            return true;
        }
        return false;
    }


    private boolean isGameIsOver(MatchDto match) {
        if (match.getScorePlayerOne() > 40 && match.getScorePlayerTwo() < 40) {
            int current = match.getGameWinsByPlayerOne();
            match.setGameWinsByPlayerOne(++current);
            return true;
        } else if (match.getScorePlayerTwo() > 40 && match.getScorePlayerOne() < 40) {
            int current = match.getGameWinsByPlayerTwo();
            match.setGameWinsByPlayerTwo(++current);
            return true;
        } else if ((match.getScorePlayerOne() - match.getScorePlayerTwo()) == 20) {
            int current = match.getGameWinsByPlayerOne();
            match.setGameWinsByPlayerOne(++current);
            return true;
        } else if ((match.getScorePlayerTwo() - match.getScorePlayerOne()) == 20) {
            int current = match.getGameWinsByPlayerTwo();
            match.setGameWinsByPlayerTwo(++current);
            return true;
        }
        return false;
    }
}
