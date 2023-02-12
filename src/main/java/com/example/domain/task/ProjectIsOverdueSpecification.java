package com.example.domain.task;

import java.time.LocalDateTime;

public class ProjectIsOverdueSpecification implements ProjectSpecification {
    @Override
    public boolean isSatisfiedBy(Project project) {
        return project.due.isBefore(LocalDateTime.now())
            && project.progress.percentageOfCompletedTasks < 100;
    }
}
