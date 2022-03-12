package net.its.rmq.mng.api.cmn.web;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.mng.api.cmn.exceptions.ManagementApiException;
import net.its.rmq.mng.api.cmn.web.resources.ErrorOutResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@Slf4j
public class ManagementApiExceptionHandler {

    @ExceptionHandler(ManagementApiException.class)
    ResponseEntity<ErrorOutResource> handleManagementException(ManagementApiException ex) {

        log.error("Error during processing request", ex);

        return new ResponseEntity<>(
            new ErrorOutResource(ex.getErrorDetails()),
            HttpStatus.valueOf(ex.getHttpStatus())
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorOutResource> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        log.error("Invalid input format", ex);

        return new ResponseEntity<>(
            new ErrorOutResource("Invalid request body, expecting JSON"),
            BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorOutResource> handleValidationException(MethodArgumentNotValidException ex) {

        log.error("Invalid input request", ex);

        val validationErrorDetails = ex.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> format("'%s' %s", fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(joining(", "));

        return new ResponseEntity<>(
            new ErrorOutResource("Invalid request body property: " + validationErrorDetails),
            BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorOutResource> handleUnexpectedException(Exception ex) {

        log.error("Unexpected exception during request execution", ex);

        return new ResponseEntity<>(
            new ErrorOutResource("Internal server error"),
            INTERNAL_SERVER_ERROR
        );
    }
}
