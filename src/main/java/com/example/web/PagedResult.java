package com.example.web;

import java.util.List;

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

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
