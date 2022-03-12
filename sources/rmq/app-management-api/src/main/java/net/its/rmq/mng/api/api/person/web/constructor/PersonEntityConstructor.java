package net.its.rmq.mng.api.api.person.web.constructor;

import lombok.val;
import net.its.rmq.cmn.dao.person.PersonEntity;
import net.its.rmq.mng.api.api.person.web.resources.PersonInResource;

public class PersonEntityConstructor {

    public PersonEntity construct(PersonInResource inResource) {

        val entity = new PersonEntity();

        entity.setIdentifier(inResource.id());
        entity.setFirstName(inResource.firstName());
        entity.setLastName(inResource.lastName());
        entity.setAddress(inResource.address());

        return entity;
    }
}
