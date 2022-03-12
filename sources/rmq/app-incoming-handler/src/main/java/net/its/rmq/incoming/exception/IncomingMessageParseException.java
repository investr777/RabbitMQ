package net.its.rmq.incoming.exception;

public class IncomingMessageParseException extends RuntimeException {

    public IncomingMessageParseException(String message) {
        super(message);
    }

    public IncomingMessageParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
