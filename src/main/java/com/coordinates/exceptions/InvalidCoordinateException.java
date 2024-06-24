package com.coordinates.exceptions;

public class InvalidCoordinateException extends RuntimeException {

    public InvalidCoordinateException(String errorMessage) {
        super(errorMessage);
    }
}
