package com.lga.tennisscoreboard.service;

import com.lga.tennisscoreboard.dto.MatchDto;
import lombok.Getter;

public class MatchCalculationService {

    private static final String ADVANTAGE = "AD";
    private static final String GAME_POINT = "GAME";

    private static boolean isTieBreak;

    public void addPointToPlayerOne(MatchDto match) {
        if (checkForTieBreak(match)) {
            match.setScorePlayerOne(increaseScoreTieBreak(match.getScorePlayerOne()));
            if (match.getScorePlayerOne().equals(ADVANTAGE) && match.getScorePlayerTwo().equals(ADVANTAGE)) {
                equalizeScoreTieBreak(match);
            }
            checkForWin(match);
            return;
        }

        if (match.getScorePlayerOne().equals("40") && match.getScorePlayerTwo().equals(ADVANTAGE)) {
            equalizeScore(match);
        } else {
            String currentScore = match.getScorePlayerOne();
            match.setScorePlayerOne(increaseScore(currentScore));
        }

        checkForWin(match);
    }

    public void addPointToPlayerTwo(MatchDto match) {
        if (checkForTieBreak(match)) {
            match.setScorePlayerTwo(increaseScoreTieBreak(match.getScorePlayerTwo()));
            if (match.getScorePlayerTwo().equals(ADVANTAGE) && match.getScorePlayerOne().equals(ADVANTAGE)) {
                equalizeScoreTieBreak(match);
            }
            checkForWin(match);
            return;
        }

        if (match.getScorePlayerTwo().equals("40") && match.getScorePlayerOne().equals(ADVANTAGE)) {
            equalizeScore(match);
        } else {
            String currentScore = match.getScorePlayerTwo();
            match.setScorePlayerTwo(increaseScore(currentScore));
        }

        checkForWin(match);
    }

    private void checkForWin(MatchDto match) {
        if (isGameIsOver(match)) {
            match.setScorePlayerOne("0");
            match.setScorePlayerTwo("0");
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
        if (isTieBreak) {
            if (match.getGameWinsByPlayerOne() == 7) {
                int current = match.getSetWinsByPlayerOne();
                match.setSetWinsByPlayerOne(++current);
                isTieBreak = false;
                return true;
            } else if (match.getGameWinsByPlayerTwo() == 7) {
                int current = match.getSetWinsByPlayerTwo();
                match.setSetWinsByPlayerTwo(++current);
                isTieBreak = false;
                return true;
            }
        }

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
        if (isTieBreak) {
            int scorePlayerOne = 0;
            int scorePlayerTwo = 0;

            if (!match.getScorePlayerOne().equals(ADVANTAGE) && !match.getScorePlayerOne().equals(GAME_POINT)) {
                scorePlayerOne = Integer.parseInt(match.getScorePlayerOne());
            }

            if (!match.getScorePlayerTwo().equals(ADVANTAGE) && !match.getScorePlayerTwo().equals(GAME_POINT)) {
                scorePlayerTwo = Integer.parseInt(match.getScorePlayerTwo());
            }




            if (match.getScorePlayerOne().equals(ADVANTAGE) && scorePlayerTwo < 7) {
                int current = match.getGameWinsByPlayerOne();
                match.setGameWinsByPlayerOne(++current);
                return true;
            } else if (match.getScorePlayerTwo().equals(ADVANTAGE) && scorePlayerOne < 7) {
                int current = match.getGameWinsByPlayerTwo();
                match.setGameWinsByPlayerTwo(++current);
                return true;
            } else if (match.getScorePlayerOne().equals(GAME_POINT) && scorePlayerTwo == 7) {
                int current = match.getGameWinsByPlayerOne();
                match.setGameWinsByPlayerOne(++current);
                return true;
            } else if (match.getScorePlayerTwo().equals(GAME_POINT) && scorePlayerOne == 7) {
                int current = match.getGameWinsByPlayerTwo();
                match.setGameWinsByPlayerTwo(++current);
                return true;
            }

            if (scorePlayerOne == 7 && scorePlayerTwo < 6) {
                int current = match.getGameWinsByPlayerOne();
                match.setGameWinsByPlayerOne(++current);
                return true;
            } else if (scorePlayerTwo == 7 && scorePlayerOne < 6) {
                int current = match.getGameWinsByPlayerTwo();
                match.setGameWinsByPlayerTwo(++current);
                return true;
            }

            return false;
        }

        int scorePlayerOne = 0;
        int scorePlayerTwo = 0;

        if (!match.getScorePlayerOne().equals(ADVANTAGE) && !match.getScorePlayerOne().equals(GAME_POINT)) {
            scorePlayerOne = Integer.parseInt(match.getScorePlayerOne());
        }

        if (!match.getScorePlayerTwo().equals(ADVANTAGE) && !match.getScorePlayerTwo().equals(GAME_POINT)) {
            scorePlayerTwo = Integer.parseInt(match.getScorePlayerTwo());
        }


        if (match.getScorePlayerOne().equals(ADVANTAGE) && scorePlayerTwo < 40) {
            int current = match.getGameWinsByPlayerOne();
            match.setGameWinsByPlayerOne(++current);
            return true;
        } else if (match.getScorePlayerTwo().equals(ADVANTAGE) && scorePlayerOne < 40) {
            int current = match.getGameWinsByPlayerTwo();
            match.setGameWinsByPlayerTwo(++current);
            return true;
        } else if (match.getScorePlayerOne().equals(GAME_POINT) && scorePlayerTwo == 40) {
            int current = match.getGameWinsByPlayerOne();
            match.setGameWinsByPlayerOne(++current);
            return true;
        } else if (match.getScorePlayerTwo().equals(GAME_POINT) && scorePlayerOne == 40) {
            int current = match.getGameWinsByPlayerTwo();
            match.setGameWinsByPlayerTwo(++current);
            return true;
        }

        return false;
    }

    private boolean checkForTieBreak(MatchDto match) {
        if (match.getGameWinsByPlayerOne() == 6 && match.getGameWinsByPlayerTwo() == 6) {
            isTieBreak = true;
            return true;
        }
        return false;
    }

    private String increaseScoreTieBreak(String currentScore) {
        if (currentScore.equals(ADVANTAGE)) {
            return GAME_POINT;
        }

        int scoreInteger = Integer.parseInt(currentScore);

        if (scoreInteger < 7) {
            scoreInteger++;
            return String.valueOf(scoreInteger);
        } else {
            return ADVANTAGE;
        }
    }

    private String increaseScore(String currentScore) {
        if (currentScore.equals(ADVANTAGE)) {
            return GAME_POINT;
        }

        int scoreInteger = Integer.parseInt(currentScore);

        if (scoreInteger < 40) {
            scoreInteger = scoreInteger == 30 ? scoreInteger + 10 : scoreInteger + 15;
            return String.valueOf(scoreInteger);
        } else {
            return ADVANTAGE;
        }
    }

    private void equalizeScore(MatchDto match) {
        match.setScorePlayerOne("40");
        match.setScorePlayerTwo("40");
    }

    private void equalizeScoreTieBreak(MatchDto match) {
        match.setScorePlayerOne("7");
        match.setScorePlayerTwo("7");
    }

    public boolean getTieBreak() {
        return isTieBreak;
    }
}
