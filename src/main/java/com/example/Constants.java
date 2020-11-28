
package com.example;

/**
 *
 * @author hantsy
 */
public class Constants {

    private Constants() {
    }

    // two roles will be used in this application.
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    public static final String AUTHORIZATION_PREFIX = "Bearer ";

    public static final int TOKEN_VALIDITY_SECONDS = 24 * 60 * 60; //24hrs
    public static final int REMEMBERME_VALIDITY_SECONDS = 14 * 24 * 60 * 60; //2 weeks

}
