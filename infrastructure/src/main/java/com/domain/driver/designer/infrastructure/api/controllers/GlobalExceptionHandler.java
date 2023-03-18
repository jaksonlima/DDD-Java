package com.domain.driver.designer.infrastructure.api.controllers;

import com.domain.driver.designer.domain.exceptions.DomainException;
import com.domain.driver.designer.domain.validation.Errors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<?> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity()
                .body(ApiError.from(ex));
    }

    record ApiError(String message, List<Errors> errors) {
        public static ApiError from(final DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }

}
