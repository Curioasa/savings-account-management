package com.ing.technicaltest.config;

import com.ing.technicaltest.error.ApiError;
import com.ing.technicaltest.exceptions.InvalidCurrencyException;
import com.ing.technicaltest.exceptions.OutsideOfWorkingHoursException;
import com.ing.technicaltest.exceptions.UserAlreadyHasAccountException;
import com.ing.technicaltest.exceptions.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFound(
            UserNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler({InvalidCurrencyException.class, OutsideOfWorkingHoursException.class, UserAlreadyHasAccountException.class})
    protected ResponseEntity<Object> handleBadRequestCases(
            RuntimeException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex.getMessage(), ex);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}