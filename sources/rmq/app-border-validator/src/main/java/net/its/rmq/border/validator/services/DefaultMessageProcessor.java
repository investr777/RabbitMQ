package net.its.rmq.border.validator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.border.validator.exceptions.UnablePassToBlockedQueueException;
import net.its.rmq.border.validator.services.block.BlockedListValidatorService;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.domain.Migrant;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.exceptions.MessageProcessorException;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class DefaultMessageProcessor implements MessageProcessor {

    private final ObjectMapper objectMapper;
    private final BorderCorridorService<Car> carBorderCorridorService;
    private final BorderCorridorService<Person> personBorderCorridorService;
    private final BlockedListValidatorService<Car> carBlockedListValidatorService;
    private final BlockedListValidatorService<Person> personBlockedListValidatorService;

    @Override
    public void process(byte[] message) {

        try {
            val migrant = objectMapper.readValue(message, Migrant.class);

            if (migrant instanceof Car car) {
                carBlockedListValidatorService.check(car);
                carBorderCorridorService.pass(car);
            } else if (migrant instanceof Person person) {
                personBlockedListValidatorService.check(person);
                personBorderCorridorService.pass(person);
            } else
                throw new MessageProcessorException("No appropriate border corridor service for " + migrant.getClass().getSimpleName());
        } catch (IOException ex) {
            throw new MessageProcessorException("Unable to parse message", ex);
        } catch (UnablePassToBlockedQueueException ex) {
            log.warn("Error while checking block list", ex);
        } catch (BlockedListValidatorServiceException ex) {
            log.warn("{} was sent in blocked queue", ex.getMigrant());
        }
    }
}
