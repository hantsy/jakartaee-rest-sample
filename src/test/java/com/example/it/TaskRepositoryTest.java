package com.example.it;

import com.example.domain.common.AbstractEntity;
import com.example.domain.task.TaskRepository;
import com.example.domain.task.Task;
import com.example.infrastructure.persistence.jpa.AbstractRepository;
import com.example.infrastructure.persistence.jpa.JpaTaskRepository;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class TaskRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(TaskRepositoryTest.class.getName());

    @Deployment()
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(AbstractEntity.class.getPackage())
                .addPackage(Task.class.getPackage())
                .addClass(JpaTaskRepository.class).addClass(AbstractRepository.class)
                //Add JPA persistence configuration.
                //WARN: In a jar archive, persistence.xml should be put into /META-INF
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");

        LOGGER.log(Level.INFO, "deployment unit: {0}", jar);

        return jar;
    }

    @Inject
    TaskRepository tasks;

    @PersistenceContext
    EntityManager em;

    Task saved;

    @Before
    public void setup() {
        saved = tasks.save(Task.of("test task", "desc of test task"));
    }

    @After
    public void teardown() {

    }

    @Test
    public void shouldCreated() {
        Task found = em.find(Task.class, saved.getId());

        assertEquals("name is 'test task'", "test task", found.getName());
        assertEquals("description is 'desc of test task'", "desc of test task", found.getDescription());
    }
}
