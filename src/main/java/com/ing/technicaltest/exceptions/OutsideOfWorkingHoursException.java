package com.ing.technicaltest.exceptions;

public class OutsideOfWorkingHoursException extends RuntimeException {
    public OutsideOfWorkingHoursException(String message) {
        super(message);
    }
}
