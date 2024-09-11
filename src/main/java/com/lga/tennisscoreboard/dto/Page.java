package com.lga.tennisscoreboard.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private final List<T> content; // Содержимое текущей страницы

    private final int currentPage;  // Номер текущей страницы

    private final int totalPages;   // Общее количество страниц

    private final long totalElements; // Общее количество элементов
}