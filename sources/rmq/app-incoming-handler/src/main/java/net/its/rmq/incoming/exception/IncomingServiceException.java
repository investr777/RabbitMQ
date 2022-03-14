package net.its.rmq.incoming.exception;

public class IncomingServiceException extends RuntimeException {

    public IncomingServiceException(String message) {
        super(message);
    }

    public IncomingServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
