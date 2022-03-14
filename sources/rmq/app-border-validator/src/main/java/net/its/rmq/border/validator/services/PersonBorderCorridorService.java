package net.its.rmq.border.validator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.border.validator.exceptions.BorderCorridorServiceException;
import net.its.rmq.border.validator.services.block.BlockedListValidatorService;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

@Slf4j
@RequiredArgsConstructor
public class PersonBorderCorridorService implements BorderCorridorService<Person> {

    private final String exchange;
    private final String routingKey;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;
    private final BlockedListValidatorService<Person> personBlockedListValidatorService;

    @Override
    public void pass(Person migrant) {

        if (isPersonBlocked(migrant)) return;

        try {
            val message = objectMapper.writeValueAsBytes(migrant);
            publisher.publish(exchange, routingKey, message);
        } catch (Exception ex) {
            throw new BorderCorridorServiceException("Unable to pass through border", ex);
        }
    }

    private boolean isPersonBlocked(Person person) {

        try {
            personBlockedListValidatorService.check(person);
        } catch (BlockedListValidatorServiceException ex) {
            log.warn("Person is in blocked list: " + person);
            return true;
        }

        return false;
    }
}
