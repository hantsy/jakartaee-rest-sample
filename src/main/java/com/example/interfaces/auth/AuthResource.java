/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.interfaces.auth;

import com.example.infrastructure.security.Authenticated;
import com.example.infrastructure.security.UserInfo;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

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
