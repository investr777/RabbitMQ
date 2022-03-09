package net.its.rmq.border.validator.exceptions;

public class BorderCorridorServiceException extends RuntimeException {

    public BorderCorridorServiceException(String message) {
        super(message);
    }

    public BorderCorridorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
