package com.example;

import com.example.domain.Task;
import com.example.domain.User;
import com.example.repository.TaskRepository;
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

import static com.example.Constants.ROLE_ADMIN;
import static com.example.Constants.ROLE_USER;

/**
 * @author hantsy
 */
@Startup
@Singleton
public class Bootstrap {

    @Inject
    Logger LOG;

    @Inject
    TaskRepository taskRepository;

    @Inject
    UserRepository users;

    @Inject
    @Crypto(Crypto.Type.BCRYPT)
    PasswordEncoder passwordHash;

    @PostConstruct
    public void init() {
        LOG.log(Level.INFO, " >> startup application ...");

        var user = User.builder()
                .username("user")
                .password(passwordHash.encode("password"))
                .firstName("user firstName")
                .lastName("user lastName")
                .email("user@example.com")
                .authorities(Set.of(ROLE_USER))
                .build();

        var admin = User.builder()
                .username("admin")
                .password(passwordHash.encode("password"))
                .firstName("admin firstName")
                .lastName("admin lastName")
                .email("admin@example.com")
                .authorities(Set.of(ROLE_USER, ROLE_ADMIN))
                .build();

        Stream.of(user, admin)
                .map(u -> users.save(u))
                .collect(Collectors.toList())
                .forEach(u -> LOG.log(Level.INFO, "saved user: {0}", new Object[]{u}));

        Stream.of("first", "second")
                .map(s -> {
                    Task task = new Task();
                    task.setName("My " + s + " task");
                    task.setDescription("The description of my " + s + " task");
                    task.setStatus(Task.Status.TODO);
                    return task;
                })
                .map(data -> taskRepository.save(data))
                .collect(Collectors.toList())
                .forEach(task -> LOG.log(Level.INFO, "saved task: {0}", new Object[]{task}));
    }
}
