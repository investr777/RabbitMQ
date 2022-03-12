package net.its.rmq.incoming.service;

import lombok.RequiredArgsConstructor;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import net.its.rmq.incoming.exception.IncomingServiceException;

@RequiredArgsConstructor
public class DefaultIncomingService implements IncomingService {

    private final String exchange;
    private final MessagePublisher publisher;

    private final static String EMPTY_ROUTING_KEY = "";

    @Override
    public void send(byte[] message) {

        try {
            publisher.publish(exchange, EMPTY_ROUTING_KEY, message);

        } catch (Exception ex) {
            throw new IncomingServiceException("Incoming message sending failed", ex);
        }
    }
}
