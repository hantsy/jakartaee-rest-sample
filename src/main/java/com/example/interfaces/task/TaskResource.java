package com.example.interfaces.task;

import com.example.domain.task.Task;
import com.example.domain.task.TaskRepository;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

import static jakarta.ws.rs.core.Response.*;

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
    public Response update(@Valid UpdateTaskCommand form) {
        log.log(Level.INFO, "updating existed task@id:{0}, form data:{1}", new Object[]{id, form});

        return taskRepository.findOptionalById(id)
                .map(data -> {
                    data.setName(form.name());
                    data.setDescription(form.description());

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
            taskStatus = Task.Status.valueOf(status.status());
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
