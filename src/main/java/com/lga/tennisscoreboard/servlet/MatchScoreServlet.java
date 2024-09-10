package com.lga.tennisscoreboard.servlet;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.service.MatchCalculationService;
import com.lga.tennisscoreboard.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchCalculationService matchCalculationService = new MatchCalculationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", ongoingMatchesService.getMatch(UUID.fromString(uuid)));
        req.getRequestDispatcher("/WEB-INF/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        MatchDto match = ongoingMatchesService.getMatch(uuid);
        addPoint(req,match);
        req.setAttribute("uuid", uuid);
        req.setAttribute("match", ongoingMatchesService.getMatch(uuid));

        req.getRequestDispatcher("/WEB-INF/match-score.jsp").forward(req, resp);
    }

    private MatchDto addPoint(HttpServletRequest req, MatchDto match) {
        MatchDto matchDto = null;
        if (req.getParameterMap().containsKey("playerOne")) {
            matchDto = matchCalculationService.addPointToPlayerOne(match);
        } else if (req.getParameterMap().containsKey("playerTwo")) {
            matchDto = matchCalculationService.addPointToPlayerTwo(match);
        }
        return matchDto;
    }
}
