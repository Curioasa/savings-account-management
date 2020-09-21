package com.ing.technicaltest.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ApiError {
    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, String message, Throwable ex) {
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }
}
