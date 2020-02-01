package com.example.web;

import com.example.domain.Task;
import com.example.domain.TaskNotFoundException;
import com.example.domain.TaskRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.*;

@Path("tasks")
@RequestScoped
public class TaskController {

    @Inject
    Logger log;

    @Inject
    TaskRepository taskRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTasks(
            @QueryParam("q") String keyword,
            @QueryParam("status") String status,
            @BeanParam PageRequest page
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

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response taskDetails(@PathParam("id") @NotNull Long id) {
        log.log(Level.INFO, "get task by id@{0}", id);

        return taskRepository.findOptionalById(id)
                .map(data -> ok(data).build())
                .orElseThrow(() -> new TaskNotFoundException(id));
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid TaskForm form) {
        log.log(Level.INFO, "saving new task @{0}", form);

        Task task = new Task();
        task.setName(form.getName());
        task.setDescription(form.getDescription());

        Task saved = taskRepository.save(task);

        return created(URI.create("/api/tasks/" + saved.getId())).build();
    }


    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam(value = "id") Long id, @Valid TaskForm form) {
        log.log(Level.INFO, "updating existed task@id:{0}, form data:{1}", new Object[]{id, form});

        return taskRepository.findOptionalById(id)
                .map(data -> {
                    data.setName(form.getName());
                    data.setDescription(form.getDescription());

                    taskRepository.update(data);
                    return noContent().build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PUT
    @Path("{id}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatus(@PathParam(value = "id") Long id, @Valid UpdateStatusRequest status) {
        log.log(Level.INFO, "updating status of the existed task@id:{0}, status:{1}", new Object[]{id, status});

        Task.Status taskStatus = null;
        try {
            taskStatus = Task.Status.valueOf(status.getStatus());
        } catch (Exception e) {
            log.log(Level.SEVERE, "can not parse task status value:{}", status);
            taskStatus = null;
        }

        Task.Status finalTaskStatus = taskStatus;
        return taskRepository.findOptionalById(id)
                .map(data -> {
                    data.setStatus(finalTaskStatus);
                    taskRepository.update(data);
                    return noContent().build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        log.log(Level.INFO, "deleting task @{0}", id);

        return taskRepository.findOptionalById(id)
                .map(data -> {
                    taskRepository.delete(data);
                    return noContent().build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostConstruct
    private void init() {
        log.config(() -> this.getClass().getSimpleName() + " created");
    }
}
