package ru.rrenat358.core.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.rrenat358.api.exceptions.AppError;
import ru.rrenat358.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

/*
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        log.error(resourceNotFoundException.getMessage(), resourceNotFoundException);
        return new ResponseEntity<>(new AppError(
                HttpStatus.NOT_FOUND.value(),
                resourceNotFoundException.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<FieldsValidationError> catchFieldValidationException(FieldValidationException fieldValidationException) {
        log.error(fieldValidationException.getMessage(), fieldValidationException);
        return new ResponseEntity<>(new FieldsValidationError(
                fieldValidationException.getErrorMessages()),
                HttpStatus.BAD_REQUEST);
    }
*/


@ExceptionHandler
public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND_EXCEPTION", e.getMessage()), HttpStatus.NOT_FOUND);
}

    @ExceptionHandler
    public ResponseEntity<AppError> catchCartServiceIntegrationException(CartServiceIntegrationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError("CART_SERVICE_INTEGRATION_ERROR", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<FieldsValidationError> catchValidationException(FieldValidationException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidationError(e.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }
}
