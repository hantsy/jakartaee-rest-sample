package com.example.domain.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Progress {
    int tasksOpened;
    int tasksAssigned;
    int tasksWorkInProgress;
    int tasksDone;
    int tasksRejected;
    int percentageOfCompletedTasks;
}
