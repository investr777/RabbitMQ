package net.its.rmq.cmn.rabbitmq.publisher;

public interface MessagePublisher {

    void publish(byte[] message);
}
