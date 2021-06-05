package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project extends AbstractAuditableEntity<Long> {

    @Column(name = "name")
    String name;

    @Embedded
    ProjectIdentifier projectId;

    @OneToMany
    @Size(min = 1)
    List<Milestone> milestones = new ArrayList<>();

    @OneToMany
    List<Milestone> tasks = new ArrayList<>();

    @Embedded
    Progress progress;

    Long nextTaskNumber = 1L;

    public TaskIdentifier nextTaskId() {
        var id = TaskIdentifier.of(this.getProjectId() + "-" + this.getNextTaskNumber());
        this.nextTaskNumber= this.getNextTaskNumber()+1;
        return id;
    }

}
