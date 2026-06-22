package com.practive.springboot.globalexceptions;

import com.practive.springboot.entities.ExceptionMessage;
import com.practive.springboot.exceptions.DuplicateResourceException;
import com.practive.springboot.exceptions.ResourceNotFoundException;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionMessage> resourceNotFoundException(ResourceNotFoundException exception){
        ExceptionMessage message = ExceptionMessage.builder()
                .errorMessage(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ExceptionMessage> handleDuplicateEmployeeException(DuplicateResourceException exception){
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .errorMessage(exception.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR).build();

        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
