package net.its.rmq.cmn.rabbitmq.pool.exceptions;

public class CreateChannelException extends ChannelPoolException {

    public CreateChannelException(String message) {
        super(message);
    }

    public CreateChannelException(String message, Throwable cause) {
        super(message, cause);
    }
}
