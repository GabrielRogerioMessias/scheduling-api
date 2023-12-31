package com.messias.schedulingapi.controllers.exceptions;

import com.messias.schedulingapi.exceptions.CustomBadCredentialsException;
import com.messias.schedulingapi.exceptions.InvalidJwtAuthenticationException;
import com.messias.schedulingapi.services.exceptionsServices.CannotScheduleException;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceAlreadyRegisteredException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(CannotScheduleException.class)
    public ResponseEntity<StandardError> cannotScheduleException(CannotScheduleException e, HttpServletRequest request) {
        String error = "Schedule error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public ResponseEntity<StandardError> cannotScheduleException(InvalidJwtAuthenticationException e, HttpServletRequest request) {
        String error = "Invalid JWT Authentication";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(ResourceAlreadyRegisteredException.class)
    public ResponseEntity<StandardError> cannotScheduleException(ResourceAlreadyRegisteredException e, HttpServletRequest request) {
        String error = "username already in use";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(CustomBadCredentialsException.class)
    public ResponseEntity<StandardError> cannotScheduleException(CustomBadCredentialsException e, HttpServletRequest request) {
        String error = "Username or Password Invalid";
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(standardError);
    }


}
