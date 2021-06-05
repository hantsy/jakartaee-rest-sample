package com.example.domain.task;

import javax.persistence.Embeddable;

@Embeddable
public class Progress {
    int tasksOpened;
    int tasksAssigned;
    int tasksWorkInProgress;
    int tasksDone;
    int tasksRejected;
    int percentageOfCompletedTasks;
}
