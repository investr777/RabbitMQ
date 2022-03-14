package net.its.rmq.border.validator.exceptions;

public class UnablePassToBlockedQueueException extends RuntimeException {

    public UnablePassToBlockedQueueException(Throwable cause) {
        super("Unable to pass to blocked queue", cause);
    }
}
