package com.danielkim.laboratory.springbootweb.api.configurations.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handle404Exception() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ConversionToBeanValidationListExceptionData>> handle400Exception(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(ConversionToBeanValidationListExceptionData::new).toList());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ConversionToSecondaryValidationExceptionData> handle422SQLIntegrityException(SQLIntegrityConstraintViolationException ex) {
        return ResponseEntity.unprocessableEntity().body(new ConversionToSecondaryValidationExceptionData(ex.getMessage()));
    }
}