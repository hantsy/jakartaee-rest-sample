package com.example.application.util;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;
import com.example.domain.user.User;
import com.example.domain.user.UserRepository;
import com.example.application.util.hash.Crypto;
import com.example.application.util.hash.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.infrastructure.security.SecurityConstant.ROLE_ADMIN;
import static com.example.infrastructure.security.SecurityConstant.ROLE_USER;

/**
 * @author hantsy
 */
@Startup
@Singleton
public class SampleDataPopulator {
    
    @Inject
    Logger LOG;
    
    @Inject
    TaskRepository tasks;
    
    @Inject
    UserRepository users;
    
    @Inject
    @Crypto(Crypto.Type.BCRYPT)
    PasswordEncoder passwordHash;
    
    @PersistenceContext
    EntityManager entityManager;
    
    @PostConstruct
    public void init() {
        initTasks();
        initUsers();
    }
    
    public void initTasks() {
        LOG.log(Level.INFO, " >> initializing tasks ...");
        
        var deleted = this.entityManager.createQuery("delete from Task").executeUpdate();
        LOG.log(Level.INFO, " deleted existed tasks: {0}", new Object[]{deleted});
        
        Stream.of("first", "second")
                .map(s -> {
                    Task task = new Task();
                    task.setName("My " + s + " task");
                    task.setDescription("The description of my " + s + " task");
                    task.setStatus(Task.Status.TODO);
                    return task;
                })
                .map(data -> tasks.save(data))
                .collect(Collectors.toList())
                .forEach(task -> LOG.log(Level.INFO, "saved task: {0}", new Object[]{task}));
    }
    
    public void initUsers() {
        LOG.log(Level.INFO, " >> initializing users ...");
        
        var deleted = this.entityManager.createQuery("delete from User").executeUpdate();
        LOG.log(Level.INFO, " deleted existed users: {0}", new Object[]{deleted});
        
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
    }
}
