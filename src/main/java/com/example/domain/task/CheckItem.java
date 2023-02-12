package com.example.domain.task;

import com.example.domain.common.AbstractAuditableEntity;
import com.example.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "check_items")
@Entity
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CheckItem extends AbstractAuditableEntity<Long> {

    @Column(name = "title")
    private String title;
}