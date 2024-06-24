package com.coordinates.controllers;

import com.coordinates.exceptions.InvalidCoordinateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerErrorHandler {

    public ResponseEntity<String> handlerInvalidCoordinateException(InvalidCoordinateException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
