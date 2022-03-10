package net.its.rmq.cmn.rabbitmq.pool.exceptions;

public class ChannelPoolException extends RuntimeException {

    public ChannelPoolException(String message) {
        super(message);
    }

    public ChannelPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
