package net.its.rmq.cmn.rabbitmq.consumer.exceptions;

public class MessageReceiverProcessorException extends RuntimeException {

    public MessageReceiverProcessorException(String message) {
        super(message);
    }

    public MessageReceiverProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
