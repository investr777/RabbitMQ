package net.its.rmq.mng.api.api.person.web.constructor;

import net.its.rmq.cmn.dao.person.PersonEntity;
import net.its.rmq.mng.api.api.person.web.resources.PersonOutResource;

public class PersonOutResourceConstructor {

    public PersonOutResource construct(PersonEntity entity) {

        return new PersonOutResource(
            entity.getIdentifier(),
            entity.getFirstName(),
            entity.getLastName(),
            entity.getAddress()
        );
    }
}
