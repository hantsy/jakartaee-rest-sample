package com.example.domain.task;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Milestone extends AbstractActivity {
    String name;

    @ManyToOne
    Project project;
}
