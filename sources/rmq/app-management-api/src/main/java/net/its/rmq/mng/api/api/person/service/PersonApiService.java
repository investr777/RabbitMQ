package net.its.rmq.mng.api.api.person.service;

import net.its.rmq.mng.api.api.person.web.resources.PersonInResource;
import net.its.rmq.mng.api.api.person.web.resources.PersonOutResource;

import java.util.List;

public interface PersonApiService {

    PersonOutResource create(PersonInResource inResource);

    PersonOutResource findByIdentifier(String identifier);

    List<PersonOutResource> findAll();

    void deleteByIdentifier(String identifier);
}
