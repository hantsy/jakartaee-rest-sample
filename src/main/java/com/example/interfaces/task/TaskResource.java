package com.example.interfaces.task;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.Response.*;

@RequestScoped
public class TaskResource {

    @Inject
    Logger log;

    @PathParam("id")
    Long id;

    @Inject
    TaskRepository taskRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response taskDetails() {
        log.log(Level.INFO, "get task by id@{0}", id);

        return taskRepository.findOptionalById(id)
                .map(data -> ok(data).build())
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid TaskForm form) {
        log.log(Level.INFO, "updating existed task@id:{0}, form data:{1}", new Object[]{id, form});

        return taskRepository.findOptionalById(id)
                .map(data -> {
                    data.setName(form.getName());
                    data.setDescription(form.getDescription());

                    taskRepository.save(data);
                    return noContent().build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PUT
    @Path("status")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStatus(@Valid UpdateStatusRequest status) {
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
                    taskRepository.save(data);
                    return noContent().build();
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @DELETE
    public Response delete() {
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
