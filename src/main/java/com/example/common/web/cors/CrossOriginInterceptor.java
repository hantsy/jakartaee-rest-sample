/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.common.web.cors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;

import static com.example.common.web.cors.CorsResponseFilter.*;

/**
 * @author hantsy
 */
@Interceptor
@CrossOrigin
@Dependent
public class CrossOriginInterceptor {

    @Inject
    HttpServletRequest request;

    @AroundInvoke
    protected Object invoke(InvocationContext ctx) throws Exception {
        ctx.getParameters();
        if (request.getHeader("Origin") != null) {
            return crossOriginResponse((Response) ctx.proceed(), request.getHeader("Origin"));
        } else {
            return ctx.proceed();
        }
    }

    public Response crossOriginResponse(Response response, String origin) {
        if (origin != null) {
            return Response
                    .fromResponse(response)
                    .header("Access-Control-Allow-Origin", origin)
                    .header("Access-Control-Allow-Credentials", "true")
                    .header("Access-Control-Allow-Methods", ALLOWED_METHODS)
                    .header("Access-Control-Max-Age", MAX_AGE)
                    .header("Access-Control-Allow-Headers", DEFAULT_ALLOWED_HEADERS)
                    .build();
        }
        return response;
    }
}
