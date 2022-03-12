package net.its.rmq.incoming.web;

import lombok.extern.slf4j.Slf4j;
import net.its.rmq.incoming.exception.IncomingMessageParseException;
import net.its.rmq.incoming.exception.IncomingServiceException;
import net.its.rmq.incoming.web.resources.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class IncomingExceptionHandler {

    @ExceptionHandler(IncomingServiceException.class)
    ResponseEntity<ErrorResponse> handleIncomingServiceException(IncomingServiceException ex) {

        log.error("Incoming message sending failed", ex);

        return ResponseEntity
            .status(BAD_REQUEST)
            .body(new ErrorResponse("Incoming message sending failed"));
    }

    @ExceptionHandler(IncomingMessageParseException.class)
    ResponseEntity<ErrorResponse> handleIncomingMessageParseException(IncomingMessageParseException ex) {

        log.error("Incoming message parsing failed", ex);

        return ResponseEntity
            .status(BAD_REQUEST)
            .body(new ErrorResponse("Incoming message parsing failed"));
    }


}
