/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.interfaces.auth;

import com.example.infrastructure.security.Authenticated;
import com.example.infrastructure.security.UserInfo;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;
import static jakarta.ws.rs.core.Response.ok;
import static jakarta.ws.rs.core.Response.status;

/**
 *
 * @author hantsy
 */
@Path("auth")
public class AuthResource {

    @Inject
    Logger LOGGER;

    @Inject
    private SecurityContext securityContext;

    @Inject
    @Authenticated
    UserInfo userInfo;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login() {
        LOGGER.log(Level.INFO, "login");
        if (securityContext.getCallerPrincipal() != null) {
            JsonObject result = Json.createObjectBuilder()
                .add("user", securityContext.getCallerPrincipal().getName())
                .build();
            return ok(result).build();
        }
        return status(UNAUTHORIZED).build();
    }

    @GET
    @Path("userinfo")
    public Response userInfo() {
        LOGGER.log(Level.INFO, "user info:{0}", userInfo);
        if (securityContext.getCallerPrincipal() != null) {
            return ok(userInfo).build();
        }
        return status(UNAUTHORIZED).build();
    }

}
