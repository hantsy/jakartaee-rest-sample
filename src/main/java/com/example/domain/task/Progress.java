package com.example.domain.task;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Progress {
    int tasksOpened;
    int tasksAssigned;
    int tasksWorkInProgress;
    int tasksDone;
    int tasksRejected;
    int percentageOfCompletedTasks;
}
