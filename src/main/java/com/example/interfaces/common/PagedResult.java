package com.example.interfaces.common;

import java.util.List;


public record PagedResult<T>(
        List<T> content,
        long count
) {
    public static <T> PagedResult<T> of(List<T> content, long count) {
        return new PagedResult<>(content, count);
    }
}
