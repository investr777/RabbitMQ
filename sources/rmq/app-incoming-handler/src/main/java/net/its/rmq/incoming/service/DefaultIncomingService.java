package net.its.rmq.incoming.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import net.its.rmq.incoming.exception.IncomingMessageParseException;
import net.its.rmq.incoming.exception.IncomingServiceException;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultIncomingService implements IncomingService {

    private final String exchange;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;

    private final static String EMPTY_ROUTING_KEY = "";

    @Override
    public void send(String text) {

        try {
            val message = objectMapper.writeValueAsBytes(text);
            publisher.publish(exchange, EMPTY_ROUTING_KEY, message);
        } catch (IOException ex) {
            throw new IncomingMessageParseException("Incoming message parsing failed", ex);
        } catch (Exception ex) {
            throw new IncomingServiceException("Incoming message sending failed", ex);
        }
    }
}
