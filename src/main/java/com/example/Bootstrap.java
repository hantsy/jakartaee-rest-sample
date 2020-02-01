package com.example;

import com.example.domain.Task;
import com.example.domain.TaskRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author hantsy
 */
@Startup
@Singleton
public class Bootstrap {

    @Inject
    Logger LOG;

    @Inject
    TaskRepository taskRepository;

    @PostConstruct
    public void init() {
        LOG.log(Level.INFO, "bootstraping application...");

        Task task = new Task();
        task.setName("My first task");
        task.setDescription("The description of my first task");
        task.setStatus(Task.Status.TODO);

        task = taskRepository.save(task);

        LOG.log(Level.INFO, " task saved: {0}", new Object[]{task});
    }
}
