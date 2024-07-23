package com.example.wsiwt_back.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Page<T> {

    private List<T> content;
    private int totalElements;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasNext;
    private boolean hasPrevious;

    public Page(List<T> content, int totalElements, int pageNumber, int pageSize, int totalPages,
                boolean isFirst, boolean isLast, boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.totalElements = totalElements;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

}
