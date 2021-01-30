/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Function;

import static com.example.domain.task.Task.Status.TODO;

/**
 * @author hantsy
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task extends AbstractAuditableEntity<Long> {

    private static final long serialVersionUID = 1L;

    public static enum Status {
        TODO, DOING, DONE;
    }

    public static Comparator<Task> COMPARATOR = Comparator
            .comparing(Task::getName)
            .thenComparing(Task::getDescription);

    public static Function<Task, String> TO_STRING = t
            -> "Post["
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = TODO;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;

}
