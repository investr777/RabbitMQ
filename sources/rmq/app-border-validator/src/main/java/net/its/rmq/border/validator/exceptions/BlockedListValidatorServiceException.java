package net.its.rmq.border.validator.exceptions;

public class BlockedListValidatorServiceException extends RuntimeException {

    public BlockedListValidatorServiceException(String message) {
        super(message);
    }

    public BlockedListValidatorServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
