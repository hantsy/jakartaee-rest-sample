package com.example.infrastructure.security.jwt;

import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Named("jwtProperties")
@Getter
public class JwtProperties {
    private static final Logger LOGGER = Logger.getLogger(JwtProperties.class.getName());

    @Inject
    @ConfigProperty(name = "jwt.secretKey", defaultValue = "rzxlszyykpbgqcflzxsqcysyhljt")
    private String secretKey;

    @Inject
    @ConfigProperty(name = "jwt.tokenValidityInSeconds", defaultValue = "3600")
    private int tokenValidityInSeconds;

    @Inject
    @ConfigProperty(name = "jwt.remembermeValidityInSeconds")
    private int remembermeValidityInSeconds;

    @PostConstruct
    public void init() {
        LOGGER.log(Level.INFO,
                "Configured jwt properties: secretKey={0}, tokenValidityInSeconds={1}, remembermeValidityInSeconds={2}",
                new Object[]{secretKey, tokenValidityInSeconds, remembermeValidityInSeconds});
    }
}
