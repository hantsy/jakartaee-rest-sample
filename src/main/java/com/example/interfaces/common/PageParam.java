package com.example.interfaces.common;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;


public class PageParam {
    @QueryParam("offset")
    @DefaultValue("0")
    private int offset;

    @QueryParam("limit")
    @DefaultValue("10")
    private int limit;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "PageParam{" +
                "offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
// see: https://github.com/jakartaee/rest/issues/1300s
/*
public record PageParam(
        @QueryParam("offset")
        @DefaultValue("0")
        int offset,

        @QueryParam("limit")
        @DefaultValue("10")
        int limit
) {
}*/
