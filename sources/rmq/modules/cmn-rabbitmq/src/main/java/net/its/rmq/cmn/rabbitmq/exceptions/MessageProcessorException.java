package net.its.rmq.cmn.rabbitmq.exceptions;

public class MessageProcessorException extends RuntimeException {

    public MessageProcessorException(String message) {
        super(message);
    }

    public MessageProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
