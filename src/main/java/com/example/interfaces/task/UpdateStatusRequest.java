package com.example.interfaces.task;

import jakarta.validation.constraints.NotBlank;


public record UpdateStatusRequest(
        @NotBlank
        String status
) {

}
