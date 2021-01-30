/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.infrastructure.security.jwt;

import javax.security.enterprise.credential.Credential;
import java.util.Set;

/**
 *
 * @author hantsy
 */
public class JwtCredential implements Credential {

    private final String principal;
    private final Set<String> authorities;

    public JwtCredential(String principal, Set<String> authorities) {
        this.principal = principal;
        this.authorities = authorities;
    }

    public String getPrincipal() {
        return principal;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

}
