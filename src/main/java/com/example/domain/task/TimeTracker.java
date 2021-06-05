package com.example.domain.task;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class TimeTracker implements Serializable {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id")
    @OrderColumn(name = "ts_index")
    List<TimeSlot> history = new ArrayList<>();

    public void setHistory(List<TimeSlot> workingHistory) {
        this.history = workingHistory;
    }

    public List<TimeSlot> getHistory() {
        return history;
    }

    public TimeSlot lastSlot() {
        if (this.history.isEmpty()) return null;
        var sorted = this.history.stream()
                .sorted(Comparator.comparing(TimeSlot::getCreatedDate).reversed())
                .collect(Collectors.toList());
        return sorted.get(0);
    }

    public void start(String remarks) {
        TimeSlot sheet = new TimeSlot();
        sheet.setRemarks(remarks);
        sheet.setStartTime(Instant.now());
        this.history.add(sheet);
    }

    public void stop() {
        var lastSlot = lastSlot();
        if (!lastSlot.ended()) {
            lastSlot.setEndTime(Instant.now());
        }
    }
}
