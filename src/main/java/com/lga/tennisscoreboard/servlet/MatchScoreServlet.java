package com.lga.tennisscoreboard.servlet;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.service.FinishedMatchesPersistenceService;
import com.lga.tennisscoreboard.service.MatchCalculationService;
import com.lga.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.lga.tennisscoreboard.util.UrlPathStorage.MATCH_SCORE_PAGE;
import static com.lga.tennisscoreboard.util.UrlPathStorage.WINNER_PAGE;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchCalculationService matchCalculationService = new MatchCalculationService();
    private final FinishedMatchesPersistenceService matchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", ongoingMatchesService.getMatch(UUID.fromString(uuid)));
        req.getRequestDispatcher(MATCH_SCORE_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchDto match = ongoingMatchesService.getMatch(uuid);

        addPoint(req, match);

        if (match.getWinner() == null) {
            req.setAttribute("uuid", uuid);
            req.setAttribute("match", match);
            req.getRequestDispatcher(MATCH_SCORE_PAGE).forward(req, resp);
        } else {
            matchesPersistenceService.saveMatch(match);
            ongoingMatchesService.finalizeMatch(uuid);
            req.setAttribute("match", match);
            req.getRequestDispatcher(WINNER_PAGE).forward(req, resp);
        }
    }

    private void addPoint(HttpServletRequest req, MatchDto match) {
        if (req.getParameterMap().containsKey("playerOne")) {
            matchCalculationService.addPointToPlayerOne(match);
        } else if (req.getParameterMap().containsKey("playerTwo")) {
            matchCalculationService.addPointToPlayerTwo(match);
        }
    }
}
