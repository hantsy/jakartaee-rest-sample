package com.example.interfaces;

import jakarta.annotation.security.DeclareRoles;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import static com.example.infrastructure.security.SecurityConstant.ROLE_ADMIN;
import static com.example.infrastructure.security.SecurityConstant.ROLE_USER;

@DeclareRoles({ROLE_USER, ROLE_ADMIN})
@ApplicationPath("api")
public class RestConfiguration extends Application {

}
