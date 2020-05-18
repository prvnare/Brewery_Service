package com.prvn.spring.ms.brew.brewery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * File    : MvcExceptionHandler
 * Created : 17/05/20
 * Last Changed  : 17/05/20 12:09 AM Sun
 * Author  : apple
 * History :
 * Initial impound
 */

@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> errorHandlerForValidations(ConstraintViolationException exception) {
        List<String> errors = new ArrayList<>(exception.getConstraintViolations().size());
        exception.getConstraintViolations().forEach(constraintViolation -> errors.add(constraintViolation.getPropertyPath() + "::" + constraintViolation.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException exception) {
        return new ResponseEntity(exception.getAllErrors(), HttpStatus.BAD_REQUEST);
    }
}
