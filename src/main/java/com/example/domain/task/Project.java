package com.example.domain.task;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Setter
@Getter
public class Project extends AbstractActivity {

    private Progress INITIAL_PROGRESS = new Progress(0, 0, 0, 0, 0, 0);
    @Column(name = "name")
    String name;

    @Embedded
    ProjectIdentifier projectId;

    @OneToMany
    @Size(min = 1)
    List<Milestone> milestones = new ArrayList<>();

    @OneToMany
    List<Task> tasks = new ArrayList<>();

    @Embedded
    Progress progress = INITIAL_PROGRESS;

    Long nextTaskNumber = 1L;

    public TaskIdentifier nextTaskId() {
        var id = TaskIdentifier.of(this.getProjectId() + "-" + this.getNextTaskNumber());
        this.nextTaskNumber = this.getNextTaskNumber() + 1;
        return id;
    }

    public void addMilestone(Milestone newMilestone) {
        newMilestone.setProject(this);
        milestones.add(newMilestone);
        refreshProgress();
    }

    public void addTask(Milestone milestone, Task newTask) {
        newTask.setTaskId(nextTaskId());
        newTask.setMilestone(milestone);
        newTask.setProject(this);
        tasks.add(newTask);
        refreshProgress();
    }

    public Boolean isOverDue() {
        return new ProjectIsOverdueSpecification().isSatisfiedBy(this);
    }

    public void refreshProgress() {
        //...
    }
}
