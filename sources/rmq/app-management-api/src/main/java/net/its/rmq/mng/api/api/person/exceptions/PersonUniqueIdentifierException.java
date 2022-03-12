package net.its.rmq.mng.api.api.person.exceptions;

import net.its.rmq.cmn.dao.person.PersonEntity;
import net.its.rmq.mng.api.cmn.exceptions.InvalidRequestException;

public class PersonUniqueIdentifierException extends InvalidRequestException {

    public PersonUniqueIdentifierException(PersonEntity entity) {

        super("Person's identifier already exists in db: " + entity, "Person's identifier is not unique", null);
    }
}
