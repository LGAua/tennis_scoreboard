package com.lga.tennisscoreboard.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/*")
public class ContentTypeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Получаем путь запрашиваемого ресурса
        String path = ((HttpServletRequest) request).getRequestURI();

        // Проверяем, является ли запрос HTML страницей
        if (path.endsWith(".html") || path.endsWith(".jsp")) {
            response.setContentType("text/html");
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        }

        // Продолжаем выполнение фильтра
        chain.doFilter(request, response);
    }
}
