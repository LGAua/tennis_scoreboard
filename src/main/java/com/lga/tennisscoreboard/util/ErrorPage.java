package com.lga.tennisscoreboard.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.WebContext;

import java.io.Writer;

public class ErrorPage {

    public static void sendErrorPage(String errorMessage, HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext, Writer writer) {
        WebContext webContext = new WebContext(ThymeleafUtil.getWebExchange(req, resp, servletContext));

        String referer = req.getHeader("referer");
        webContext.setVariable("errorMessage", errorMessage);
        webContext.setVariable("sendBack", referer);
        webContext.setVariable("status", resp.getStatus());

        ThymeleafUtil.startRenderingPage("error-page", writer, webContext);
    }
}
