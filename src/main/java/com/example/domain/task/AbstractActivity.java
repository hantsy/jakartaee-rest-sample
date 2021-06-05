package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class AbstractActivity extends AbstractAuditableEntity<Long> implements Activity {

    LocalDateTime due;

    @Override
    public LocalDateTime due() {
        return due;
    }
}
