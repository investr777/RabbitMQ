package net.its.rmq.border.validator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BorderCorridorServiceException;
import net.its.rmq.cmn.domain.Migrant;
import net.its.rmq.cmn.rabbitmq.publisher.factory.MessagePublisherFactory;

@RequiredArgsConstructor
public class DefaultBorderCorridorService implements BorderCorridorService {

    private final String exchange;
    private final String routingKey;
    private final MessagePublisherFactory messagePublisherFactory;
    private final ObjectMapper objectMapper;

    @Override
    public void pass(Migrant migrant) {

        val publisher = messagePublisherFactory.create(exchange, routingKey);

        try {
            val message = objectMapper.writeValueAsBytes(migrant);
            publisher.publish(message);
        } catch (Exception ex) {
            throw new BorderCorridorServiceException("Unable to pass through border", ex);
        }
    }
}
