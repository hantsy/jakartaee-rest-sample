package com.example.bootstrap;

import com.example.domain.User;
import com.example.repository.UserRepository;
import com.example.security.hash.Crypto;
import com.example.security.hash.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.RoleNames.ADMIN;
import static com.example.RoleNames.USER;

/**
 * @author hantsy
 */
@Startup
@Singleton
public class SampleUserPopulator {

    @Inject
    Logger LOG;

    @Inject
    UserRepository users;

    @Inject
    @Crypto(Crypto.Type.BCRYPT)
    PasswordEncoder passwordHash;

    @PostConstruct
    public void init() {
        LOG.log(Level.INFO, " >> startup application ...");

        var deleted = this.users.deleteAll();
        LOG.log(Level.INFO, " deleted existed users: {0}", new Object[]{deleted});

        var user = User.builder()
                .username("user")
                .password(passwordHash.encode("password"))
                .firstName("user firstName")
                .lastName("user lastName")
                .email("user@example.com")
                .authorities(Set.of(USER))
                .build();

        var admin = User.builder()
                .username("admin")
                .password(passwordHash.encode("password"))
                .firstName("admin firstName")
                .lastName("admin lastName")
                .email("admin@example.com")
                .authorities(Set.of(USER, ADMIN))
                .build();

        Stream.of(user, admin)
                .map(u -> users.save(u))
                .collect(Collectors.toList())
                .forEach(u -> LOG.log(Level.INFO, "saved user: {0}", new Object[]{u}));
    }
}
