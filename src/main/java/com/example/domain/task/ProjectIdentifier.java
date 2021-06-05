package com.example.domain.task;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ProjectIdentifier implements Serializable {

    @Column(name = "project_id")
    String id;

    @Override
    public String toString() {
        return id;
    }
}
