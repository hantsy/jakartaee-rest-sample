package com.example.web;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UpdateStatusRequest {
    @NotBlank
    private String status;

    public UpdateStatusRequest() {
    }

    public static UpdateStatusRequest of(String status) {
        final UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
        updateStatusRequest.setStatus(status);
        return updateStatusRequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateStatusRequest that = (UpdateStatusRequest) o;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }

    @Override
    public String toString() {
        return "UpdateStatusRequest{" +
                "status='" + status + '\'' +
                '}';
    }
}
