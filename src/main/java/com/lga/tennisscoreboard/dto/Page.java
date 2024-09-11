package com.lga.tennisscoreboard.dto;

import lombok.Data;

import java.util.List;

@Data
public class Page<T> {
    private final List<T> content;

    private final int currentPage;

    private final int totalPages;

    private final long totalElements;
}