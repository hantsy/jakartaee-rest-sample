package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Table(name = "timesheets")
@Entity
@Data
public class TimeSlot extends AbstractAuditableEntity<Long> {
    private String remarks;
    private Instant startTime;
    private Instant endTime;

    public boolean ended() {
        return this.endTime != null;
    }
}