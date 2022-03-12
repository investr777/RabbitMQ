package net.its.rmq.incoming.service;

import lombok.RequiredArgsConstructor;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import net.its.rmq.incoming.exception.IncomingServiceException;

@RequiredArgsConstructor
public class DefaultIncomingService implements IncomingService {

    // todo create empty routing key
    private final String exchange;
    private final MessagePublisher publisher;

    @Override
    public void send(byte[] message) {

        try {
            System.out.println("exchange" + exchange);
            publisher.publish(exchange, "", message);

        } catch (Exception ex) {
            throw new IncomingServiceException("Incoming message sending failed", ex);
        }
    }
}
