package net.its.rmq.border.validator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.exceptions.MessageProcessorException;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.domain.Migrant;
import net.its.rmq.cmn.domain.Person;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultMessageProcessor implements MessageProcessor {

    private final ObjectMapper objectMapper;
    private final BorderCorridorService carBorderCorridorService;
    private final BorderCorridorService personBorderCorridorService;

    @Override
    public void process(byte[] message) {

        try {
            val migrant = objectMapper.readValue(message, Migrant.class);

            if (migrant instanceof Car) {
                carBorderCorridorService.pass(migrant);
            } else if (migrant instanceof Person) {
                personBorderCorridorService.pass(migrant);
            } else
                throw new MessageProcessorException("No appropriate border corridor service for " + migrant.getClass().getSimpleName());
        } catch (IOException ex) {
            throw new MessageProcessorException("Unable to parse message", ex);
        }
    }
}
