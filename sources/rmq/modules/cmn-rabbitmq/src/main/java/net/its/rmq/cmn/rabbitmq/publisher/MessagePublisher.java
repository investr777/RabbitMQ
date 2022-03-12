package net.its.rmq.cmn.rabbitmq.publisher;

import java.io.IOException;

public interface MessagePublisher {

    void publish(String exchange, String routingKey, byte[] message) throws IOException;
}
