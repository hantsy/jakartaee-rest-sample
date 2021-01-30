/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.interfaces.profile;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;
import com.example.domain.user.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * @author hantsy
 */
@Path("user")
@Stateless
public class CurrentUserResource {

    @Context
    UriInfo uriInfo;

    @Inject
    UserRepository users;

    @Inject
    TaskRepository tasks;

    @Context
    ResourceContext resourceContext;

    @Inject
    SecurityContext securityContext;

    @GET
    @Path("profile")
    public Response user() {
        return users.findByUsername(securityContext.getCallerPrincipal().getName())
                .map(p -> ok(p).build())
                .orElse(status(NOT_FOUND).build());
    }

    @GET
    @Path("posts")
    public Response posts() {
        List<Task> tasksByCreatedBy = tasks.findByCreatedBy(securityContext.getCallerPrincipal().getName());
        return ok(tasksByCreatedBy).build();
    }

}
