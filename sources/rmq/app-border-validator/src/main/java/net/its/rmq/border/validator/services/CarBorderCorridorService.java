package net.its.rmq.border.validator.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.border.validator.exceptions.BorderCorridorServiceException;
import net.its.rmq.border.validator.services.block.BlockedListValidatorService;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

@Slf4j
@RequiredArgsConstructor
public class CarBorderCorridorService implements BorderCorridorService<Car> {

    private final String exchange;
    private final String routingKey;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;
    private final BlockedListValidatorService<Car> carBlockedListValidatorService;
    private final BorderCorridorService<Person> personBorderCorridorService;

    @Override
    public void pass(Car migrant) {

        if (isCarBlocked(migrant)) {
            migrant.passengers().forEach(personBorderCorridorService::pass);
            return;
        }

        migrant.passengers().forEach(personBorderCorridorService::pass);

        try {
            val message = objectMapper.writeValueAsBytes(migrant);
            publisher.publish(exchange, routingKey, message);
        } catch (Exception ex) {
            throw new BorderCorridorServiceException("Unable to pass through border", ex);
        }
    }

    private boolean isCarBlocked(Car car) {

        try {
            carBlockedListValidatorService.check(car);
        } catch (BlockedListValidatorServiceException ex) {
            log.warn("Car is in blocked list: " + car);
            return true;
        }

        return false;
    }
}
