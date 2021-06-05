package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;
import com.example.domain.common.AbstractEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "check_items")
@Entity
@Data
public class CheckItem extends AbstractAuditableEntity<Long> {

    @Column(name = "title")
    private String title;
}