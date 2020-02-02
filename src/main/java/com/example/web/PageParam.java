package com.example.web;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

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
}
