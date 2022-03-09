package net.its.rmq.cmn.rabbitmq.consumer.exceptions;

public class AcknowledgeException extends RuntimeException {

    public AcknowledgeException(String message) {
        super(message);
    }

    public AcknowledgeException(String message, Throwable cause) {
        super(message, cause);
    }
}
