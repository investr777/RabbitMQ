package net.its.rmq.incoming.service;

import lombok.RequiredArgsConstructor;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import net.its.rmq.incoming.exception.IncomingServiceException;

@RequiredArgsConstructor
public class DefaultIncomingService implements IncomingService {

    private final String exchange;
    private final String routingKey;
    private final MessagePublisher publisher;

    @Override
    public void send(byte[] message) {

        try {
            publisher.publish(exchange, routingKey, message);
        } catch (Exception ex) {
            throw new IncomingServiceException("Incoming message sending failed", ex);
        }
    }
}
