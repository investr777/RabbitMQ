package net.its.rmq.border.validator.services.block;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.border.validator.exceptions.UnablePassToBlockedQueueException;
import net.its.rmq.cmn.dao.car.CarDao;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

@RequiredArgsConstructor
public class CarBlockedListValidatorService implements BlockedListValidatorService<Car> {

    private final CarDao carDao;
    private final String exchange;
    private final String routingKey;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;

    @Override
    public void check(Car migrant) throws BlockedListValidatorServiceException {

        if (carDao.findByVin(migrant.vin()).isEmpty()) return;

        try {
            val message = objectMapper.writeValueAsBytes(migrant);
            publisher.publish(exchange, routingKey, message);
        } catch (Exception ex) {
            throw new UnablePassToBlockedQueueException(ex);
        }

        throw new BlockedListValidatorServiceException("Car exists in blocked list");
    }
}
