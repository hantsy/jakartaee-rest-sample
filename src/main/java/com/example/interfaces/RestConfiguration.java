package com.example.interfaces;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.example.infrastructure.security.SecurityConstant.ROLE_ADMIN;
import static com.example.infrastructure.security.SecurityConstant.ROLE_USER;

@DeclareRoles({ROLE_USER, ROLE_ADMIN})
@ApplicationPath("api")
public class RestConfiguration extends Application {

}
