/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Comparator;
import java.util.function.Function;

import static com.example.domain.task.TaskStatus.TODO;

/**
 * @author hantsy
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends AbstractActivity {

    private static final long serialVersionUID = 1L;


    public static Comparator<Task> COMPARATOR = Comparator
            .comparing(Task::getName)
            .thenComparing(Task::getDescription);

    public static Function<Task, String> TO_STRING = t
            -> "Task["
            + "\n taskId:" + t.getTaskId()
            + "\n title:" + t.getName()
            + "\n content:" + t.getDescription()
            + "\n status:" + t.getStatus()
            + "\n createdAt:" + t.getCreatedDate()
            + "\n lastModifiedAt:" + t.getLastModifiedDate()
            + "]";


    public static Task of(String name, String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);

        return task;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status = TODO;

    @Embedded
    private TaskIdentifier taskId;

    @Min(value = 0)
    private int estimatedDuration;

    @Min(value = 0)
    private int duration;

    @Embedded
    private CheckList checkList;

    @Embedded
    private TimeTracker tracker;

    public void startTracker(String remarks) {
        this.tracker.start(remarks);
    }

    public void stopTracker(String remarks) {
        this.tracker.stop();
    }

}
