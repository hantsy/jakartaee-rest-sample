package com.example.interfaces.task;

import com.example.domain.task.TaskRepository;
import com.example.interfaces.common.PageParam;
import com.example.domain.task.Task;
import com.example.interfaces.common.PagedResult;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.*;

@Path("tasks")
@RequestScoped
public class TaskResources {

    @Inject
    Logger log;

    @Inject
    TaskRepository taskRepository;

    @Context
    ResourceContext resourceContext;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTasks(
            @QueryParam("q") String keyword,
            @QueryParam("status") String status,
            @BeanParam PageParam page
    ) {
        log.log(Level.INFO, "fetching all tasks, keyword: {0} status:{1}", new Object[]{keyword, status});

        Task.Status taskStatus;
        try {
            taskStatus = Task.Status.valueOf(status);
        } catch (Exception e) {
            log.log(Level.SEVERE, "can not parse task status value:{0}", status);
            taskStatus = null;
        }

        List<Task> tasks = taskRepository.searchByKeyword(keyword, taskStatus, page.getOffset(), page.getLimit());
        long count = taskRepository.countByKeyword(keyword, taskStatus);
        return ok(PagedResult.of(tasks, count)).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid TaskForm form) {
        log.log(Level.INFO, "saving new task @{0}", form);

        Task task = new Task();
        task.setName(form.getName());
        task.setDescription(form.getDescription());

        Task saved = taskRepository.save(task);

        return created(uriInfo.getBaseUriBuilder().path("/tasks/{id}").build(saved.getId())).build();
    }

    @Path("{id}")
    public TaskResource taskResource() {
        return resourceContext.getResource(TaskResource.class);
    }


    @PostConstruct
    private void init() {
        log.config(() -> this.getClass().getSimpleName() + " created");
    }
}
