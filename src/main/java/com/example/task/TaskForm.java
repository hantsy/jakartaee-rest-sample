package com.example.task;

import lombok.Data;
import lombok.Value;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
