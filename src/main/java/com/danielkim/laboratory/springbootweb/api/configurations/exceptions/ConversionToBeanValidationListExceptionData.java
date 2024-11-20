package com.danielkim.laboratory.springbootweb.api.configurations.exceptions;

import org.springframework.validation.FieldError;

public record ConversionToBeanValidationListExceptionData(String field, String message) {
    public ConversionToBeanValidationListExceptionData(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}