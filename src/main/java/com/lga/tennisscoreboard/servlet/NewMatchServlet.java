package com.lga.tennisscoreboard.servlet;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.repository.MatchRepository;
import com.lga.tennisscoreboard.repository.PlayerRepository;
import com.lga.tennisscoreboard.service.OngoingMatchesService;
import com.lga.tennisscoreboard.util.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final MatchRepository matchRepository = new MatchRepository();
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/newMatch.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player playerOne = playerBuilder(req.getParameter("playerOne"));
        Player playerTwo = playerBuilder(req.getParameter("playerTwo"));
        if (checkNamesUniqueness(playerOne, playerTwo)) {
            ErrorPage.sendErrorPage("Duplicate names not allowed", req, resp, getServletContext(), resp.getWriter());
            return;
        }

        savePlayer(playerOne);
        savePlayer(playerTwo);

        MatchDto matchDto = MatchDto.builder()
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .build();

        UUID matchId = ongoingMatchesService.addMatch(matchDto);

        resp.sendRedirect("/match-score?uuid=%s".formatted(matchId.toString()));
    }

    private Player playerBuilder(String name) {
        return Player.builder()
                .name(name)
                .build();
    }

    private void savePlayer(Player player) {
        if (playerRepository.findPlayerByName(player.getName()).isEmpty()) {
            playerRepository.save(player);
        }
    }

    private boolean checkNamesUniqueness(Player playerOne, Player playerTwo) {
        return playerOne.getName().equals(playerTwo.getName());
    }
}
