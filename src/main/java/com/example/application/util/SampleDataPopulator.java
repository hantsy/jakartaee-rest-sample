package com.example.application.util;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void init() {
        initTasks();
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
}
