package com.example.interfaces.common;

import lombok.Data;

import java.util.List;

@Data
public class PagedResult<T> {
    private List<T> content;
    private long count;

    public PagedResult()  {
    }

    public static <T> PagedResult<T> of(List<T> content, long count) {
        PagedResult<T> result = new PagedResult<>();
        result.setContent(content);
        result.setCount(count);
        return result;
    }

}
