/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.interfaces.user;


import com.example.domain.task.Count;
import com.example.domain.task.Existence;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.application.util.hash.Crypto;
import com.example.application.util.hash.PasswordEncoder;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.example.application.util.hash.Crypto.Type.BCRYPT;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.*;

/**
 * @author hantsy
 */
@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Context
    UriInfo uriInfo;

    @Inject
    UserRepository users;

    @Context
    ResourceContext resourceContext;

    @Inject
    @Crypto(BCRYPT)
    PasswordEncoder passwordEncoder;

    @GET
    public Response all() {
        return ok(users.findAll()).build();
    }

    @GET
    @Path("count")
    public Response count() {
        return ok(
                Count.builder().count(users.countAll()).build()
        ).build();
    }

    @GET
    @Path("exists")
    public Response exists(@QueryParam("username") String username, @QueryParam("email") String email) {
        if (username != null && username.length() > 0) {
            return ok(Existence.builder().existed(users.findByUsername(username).isPresent()).build()).build();
        }

        if (email != null && email.length() > 0) {
            return ok(Existence.builder().existed(users.findByEmail(email).isPresent()).build()).build();
        }

        return status(BAD_REQUEST)
                .entity("username or email query params is required")
                .build();
    }

    @POST
    // there is a bug when adding @Valid to request form data
    // https://github.com/javaee/glassfish/issues/22317
    public Response createUser(RegisterForm form) {

        if (users.findByUsername(form.getUsername()).isPresent()) {
            throw new UsernameWasTakenException(form.getUsername());
        }

        if (users.findByEmail(form.getEmail()).isPresent()) {
            throw new EmailWasTakenException(form.getEmail());
        }

        User user = User.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .build();

        User saved = users.save(user);
        return created(
                uriInfo.getBaseUriBuilder()
                        .path("users/{username}")
                        .build(saved.getUsername())
        )
                .build();
    }

    @Path("{username}")
    public UserResource user() {
        return resourceContext.getResource(UserResource.class);
    }

}
