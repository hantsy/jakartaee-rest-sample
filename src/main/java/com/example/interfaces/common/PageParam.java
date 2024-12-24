package com.example.interfaces.common;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;

/*
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class PageParam {
    @QueryParam("offset")
    @DefaultValue("0")
    private int offset;

    @QueryParam("limit")
    @DefaultValue("10")
    private int limit;
}
*/

public record PageParam(
        @QueryParam("offset")
        @DefaultValue("0")
        int offset,

        @QueryParam("limit")
        @DefaultValue("10")
        int limit
) {
}
