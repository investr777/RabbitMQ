package net.its.rmq.cmn.rabbitmq.pool.exceptions;

public class ConnectionException extends ChannelPoolException {

    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
