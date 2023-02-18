package com.example.it;

import com.example.application.util.SampleDataPopulator;
import com.example.domain.common.AbstractEntity;
import com.example.domain.task.Task;
import com.example.infrastructure.persistence.jpa.AbstractRepository;
import com.example.infrastructure.persistence.jpa.JpaTaskRepository;
import com.example.interfaces.RestConfiguration;
import com.example.interfaces.common.PageParam;
import com.example.interfaces.common.PagedResult;
import com.example.interfaces.task.TaskResources;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(ArquillianExtension.class)
public class TaskResourceTest {
    private static final Logger LOGGER = Logger.getLogger(TaskResourceTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class)

            // entities
            .addPackage(AbstractEntity.class.getPackage())
            .addPackage(Task.class.getPackage())
            .addClass(JpaTaskRepository.class).addClass(AbstractRepository.class)

            //sample data
            .addPackages(true, SampleDataPopulator.class.getPackage())

            // rest
            .addPackage(TaskResources.class.getPackage())
            .addPackage(PageParam.class.getPackage())

            //rest config
            .addClass(RestConfiguration.class)

            //Add JPA persistence configuration.
            //WARN: In a war archive, persistence.xml should be put into /WEB-INF/classes/META-INF/, not /META-INF
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            // Enable CDI
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.log(Level.INFO, "deployment unit: {0}", war.toString(true));

        return war;
    }

    @ArquillianResource
    private URL base;

    private Client client;

    @BeforeEach
    public void setup() {
        this.client = ClientBuilder.newClient();
    }

    @AfterEach
    public void teardown() {
        if (this.client != null) {
            this.client.close();
        }
    }

    @Test
    public void shouldFoundTasks() throws MalformedURLException {
        final WebTarget getAllTasksTarget = client.target(URI.create(new URL(base, "api/tasks").toExternalForm()));
        try (final Response getAllTasksResponse = getAllTasksTarget.request()
            .accept(MediaType.APPLICATION_JSON)
            .get()) {
            assertTrue(getAllTasksResponse.getStatus() == 200, "status is ok");
            assertTrue(getAllTasksResponse.readEntity(new GenericType<PagedResult<Task>>() {
                }).getContent().size() == 2,
                "response should contain two tasks"
            );

        }
    }
}
