package com.example.domain.task;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class CheckList implements Serializable {

    @OneToMany(targetEntity = CheckItem.class, cascade = CascadeType.ALL)
    @MapKeyJoinColumn(name = "task_id")
    @MapKeyClass(CheckItem.class)
    private Map<CheckItem, Boolean> items = new HashMap<>();

    public int progressPercentage() {
        if (this.items.isEmpty()) {
            throw new IllegalArgumentException("empty checklist should contain progress computation.");
        }
        int size = this.items.size();
        var completed = this.items.entrySet().stream()
                .filter(Map.Entry::getValue)
                .count();
        return (int) (completed * 100.0 / size);
    }
}