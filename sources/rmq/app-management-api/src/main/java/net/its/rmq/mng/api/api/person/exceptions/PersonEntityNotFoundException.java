package net.its.rmq.mng.api.api.person.exceptions;

import net.its.rmq.mng.api.cmn.exceptions.EntityNotFoundException;

public class PersonEntityNotFoundException extends EntityNotFoundException {

    public PersonEntityNotFoundException(String searchBy, String value) {

        super(
            "Person is not found in db by " + searchBy + ": " + value,
            "Person is not found by " + searchBy + ": " + value
        );
    }
}
