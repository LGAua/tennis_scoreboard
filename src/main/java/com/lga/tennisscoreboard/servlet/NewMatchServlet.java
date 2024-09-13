package com.lga.tennisscoreboard.servlet;

import com.lga.tennisscoreboard.dto.MatchDto;
import com.lga.tennisscoreboard.entity.Player;
import com.lga.tennisscoreboard.service.OngoingMatchesService;
import com.lga.tennisscoreboard.service.PlayerPersistenceService;
import com.lga.tennisscoreboard.util.ErrorPage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.lga.tennisscoreboard.util.UrlPathStorage.MATCH_SCORE_UUID_PARAM;
import static com.lga.tennisscoreboard.util.UrlPathStorage.NEW_MATCH_PAGE;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private static final String DUPLICATE_NAMES = "Duplicate names not allowed";

    private final PlayerPersistenceService playerPersistenceService = new PlayerPersistenceService();
    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(NEW_MATCH_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player playerOne = playerBuilder(req.getParameter("playerOne"));
        Player playerTwo = playerBuilder(req.getParameter("playerTwo"));
        if (checkNamesUniqueness(playerOne, playerTwo)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            ErrorPage.sendErrorPage(DUPLICATE_NAMES, req, resp, getServletContext(), resp.getWriter());
            return;
        }

        if(ongoingMatchesService.isMatchWithSuchPlayerOngoing(playerOne,playerTwo)){
            UUID matchIdByPlayersName = ongoingMatchesService.getMatchIdByPlayersName(playerOne, playerTwo);
            resp.sendRedirect(MATCH_SCORE_UUID_PARAM.formatted(matchIdByPlayersName.toString()));
            return;
        }
        savePlayers(playerOne, playerTwo);
        MatchDto matchDto = matchDtoBuilder(playerOne, playerTwo);
        UUID matchId = ongoingMatchesService.addMatch(matchDto);

        resp.sendRedirect(MATCH_SCORE_UUID_PARAM.formatted(matchId.toString()));
    }

    private Player playerBuilder(String name) {
        return Player.builder()
                .name(name)
                .build();
    }

    private MatchDto matchDtoBuilder(Player playerOne, Player playerTwo) {
        return MatchDto.builder()
                .playerOne(playerOne)
                .playerTwo(playerTwo)
                .scorePlayerOne("0")
                .scorePlayerTwo("0")
                .build();
    }

    private void savePlayers(Player... players) {
        for (Player player : players) {
            playerPersistenceService.savePlayer(player);
        }
    }

    private boolean checkNamesUniqueness(Player playerOne, Player playerTwo) {
        return playerOne.getName().equals(playerTwo.getName());
    }
}
