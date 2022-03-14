package net.its.rmq.cmn.rabbitmq.publisher;

public interface MessagePublisher {

    void publish(String exchange, String routingKey, byte[] message);
}
