package com.example.interfaces.task;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hantsy
 */
@Provider
public class TaskNotFoundExceptionMapper implements ExceptionMapper<TaskNotFoundException> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(TaskNotFoundException exception) {
        log.log(Level.INFO, "handling exception : PostNotFoundException");

        return Response.status(Response.Status.NOT_FOUND)
                .header("Content-Type", "application/json")
                .entity(exception.getMessage())
                .build();
    }

}
