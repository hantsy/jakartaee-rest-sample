package com.example.interfaces.task;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
public class TaskForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 10, max = 2000)
    private String description;
}
