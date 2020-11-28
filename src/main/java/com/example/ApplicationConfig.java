package com.example;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.example.Constants.ROLE_ADMIN;
import static com.example.Constants.ROLE_USER;

@DeclareRoles({ROLE_USER, ROLE_ADMIN})
@ApplicationPath("api")
public class ApplicationConfig extends Application {

}
