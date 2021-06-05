package com.example.domain.task;

public class ProjectIsOverdueSpecification implements ProjectSpecification{
    @Override
    public boolean isSatisfiedBy(Project project) {
        return false;
    }
}
