package com.example.application.util;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.Startup;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @author hantsy
 */
@ApplicationScoped
@Transactional
public class SampleDataPopulator {

    @Inject
    Logger LOG;

    @Inject
    TaskRepository tasks;

    @PersistenceContext
    EntityManager entityManager;

    public void init(@Observes Startup startup) {
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
                .toList()
                .forEach(task -> LOG.log(Level.INFO, "saved task: {0}", new Object[]{task}));
    }
}
