package net.its.rmq.mng.api.api.person.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.cmn.dao.person.PersonDao;
import net.its.rmq.cmn.dao.person.PersonEntity;
import net.its.rmq.mng.api.api.person.exceptions.PersonEntityNotFoundException;
import net.its.rmq.mng.api.api.person.exceptions.PersonUniqueIdentifierException;
import net.its.rmq.mng.api.api.person.web.constructor.PersonEntityConstructor;
import net.its.rmq.mng.api.api.person.web.constructor.PersonOutResourceConstructor;
import net.its.rmq.mng.api.api.person.web.resources.PersonInResource;
import net.its.rmq.mng.api.api.person.web.resources.PersonOutResource;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DefaultPersonApiService implements PersonApiService {

    private final PersonDao dao;
    private final PersonOutResourceConstructor outResourceConstructor;
    private final PersonEntityConstructor entityConstructor;

    @Override
    public PersonOutResource create(PersonInResource inResource) {

        isUniqueIdentifier(inResource.identifier());

        val entity = entityConstructor.construct(inResource);

        val savedPerson = dao.saveAndFlush(entity);
        log.info("Person was saved successfully: " + savedPerson);

        return outResourceConstructor.construct(savedPerson);
    }

    @Override
    public PersonOutResource findByIdentifier(String identifier) {

        final PersonEntity entity = getPersonEntityByIdentifier(identifier);

        return outResourceConstructor.construct(entity);
    }

    @Override
    public List<PersonOutResource> findAll() {

        return dao.findAll().stream()
            .map(outResourceConstructor::construct)
            .toList();
    }

    @Override
    public void deleteByIdentifier(String identifier) {

        val entity = getPersonEntityByIdentifier(identifier);

        dao.delete(entity);

        log.info("Person was removed, identifier: " + identifier);
    }

    private void isUniqueIdentifier(String identifier) {

        dao.findByIdentifier(identifier)
            .ifPresent(entity -> {
                throw new PersonUniqueIdentifierException(entity);
            });
    }

    private PersonEntity getPersonEntityByIdentifier(String identifier) {

        val entity = dao.findByIdentifier(identifier).orElse(null);
        if (entity == null) throw new PersonEntityNotFoundException("identifier", identifier);

        return entity;
    }
}
