package com.example.interfaces.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTaskCommand(
    @NotBlank
    String name,

    @NotBlank
    @Size(min = 10, max = 2000)
    String description
) {

}
