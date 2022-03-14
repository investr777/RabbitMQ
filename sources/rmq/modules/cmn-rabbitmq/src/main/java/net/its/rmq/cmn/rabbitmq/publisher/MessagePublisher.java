package net.its.rmq.cmn.rabbitmq.publisher;

import net.its.rmq.cmn.rabbitmq.publisher.exceptions.MessagePublisherException;

public interface MessagePublisher {

    void publish(String exchange, String routingKey, byte[] message) throws MessagePublisherException;
}
