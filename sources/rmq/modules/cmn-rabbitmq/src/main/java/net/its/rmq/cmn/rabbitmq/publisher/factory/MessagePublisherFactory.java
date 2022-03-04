package net.its.rmq.cmn.rabbitmq.publisher.factory;

import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

public interface MessagePublisherFactory {

    MessagePublisher create(String exchange, String routingKey);
}
