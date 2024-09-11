package com.lga.tennisscoreboard.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

@UtilityClass
public class ThymeleafUtil {
    private static JakartaServletWebApplication webApplication;

    public void startRenderingPage(String pageName, Writer writer, WebContext webContext) {
        TemplateEngine templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(getTemplateResolver());

        templateEngine.process(pageName, webContext, writer);
    }

    private WebApplicationTemplateResolver getTemplateResolver() {
        WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(webApplication);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    public static IWebExchange getWebExchange(HttpServletRequest req, HttpServletResponse resp,ServletContext servletContext) {
        webApplication = JakartaServletWebApplication.buildApplication(servletContext);
        return webApplication.buildExchange(req, resp);
    }
}
