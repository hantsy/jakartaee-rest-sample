package com.example.domain.task;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class TaskIdentifier {

    @Column(name = "task_id")
    String id;

    public static TaskIdentifier of(String s) {
        var taskId = new TaskIdentifier();
        taskId.id = s;
        return taskId;
    }

    public TaskIdentifier() {
        //reserved by JPA.
    }

    @Override
    public String toString() {
        return id;
    }
}
