package com.lga.tennisscoreboard.servlet;

import com.lga.tennisscoreboard.dto.Page;
import com.lga.tennisscoreboard.entity.Match;
import com.lga.tennisscoreboard.repository.MatchRepository;
import com.lga.tennisscoreboard.service.FinishedMatchesPersistenceService;
import com.lga.tennisscoreboard.util.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/matches")
public class MatchHistoryServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService matchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filterByPlayerName = req.getParameter("filter_by_player_name");
        String pageStr = req.getParameter("page");

        Page<Match> matches = matchesPersistenceService.getMatches(filterByPlayerName, pageStr);

        WebContext webContext = new WebContext(ThymeleafUtil.getWebExchange(req, resp,getServletContext()));
        webContext.setVariable("matches", matches.getContent());
        webContext.setVariable("currentPage", matches.getCurrentPage());
        webContext.setVariable("totalPages", matches.getTotalPages());
        webContext.setVariable("filterByPlayerName", filterByPlayerName);

        ThymeleafUtil.startRenderingPage("matches", resp.getWriter(), webContext);
    }
}

