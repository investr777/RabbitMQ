package net.its.rmq.cmn.rabbitmq.publisher.exceptions;

public class MessagePublisherException extends RuntimeException {

    public MessagePublisherException(String message) {
        super(message);
    }

    public MessagePublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
