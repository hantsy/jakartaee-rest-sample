package com.example;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.example.RoleNames.ADMIN;
import static com.example.RoleNames.USER;

@DeclareRoles({USER, ADMIN})
@ApplicationPath("api")
public class ApplicationConfig extends Application {

}
