package net.its.rmq.border.validator.services.block;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.border.validator.exceptions.UnablePassToBlockedQueueException;
import net.its.rmq.cmn.dao.person.PersonDao;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

@RequiredArgsConstructor
public class PersonBlockedListValidatorService implements BlockedListValidatorService<Person> {

    private final PersonDao personDao;
    private final String exchange;
    private final String routingKey;
    private final MessagePublisher publisher;
    private final ObjectMapper objectMapper;

    @Override
    public void check(Person migrant) throws BlockedListValidatorServiceException {

        if (personDao.findByIdentifier(migrant.identifier()).isEmpty()) return;

        try {
            val message = objectMapper.writeValueAsBytes(migrant);
            publisher.publish(exchange, routingKey, message);
        } catch (Exception ex) {
            throw new UnablePassToBlockedQueueException(ex);
        }

        throw new BlockedListValidatorServiceException("Person exists in blocked list");
    }
}
