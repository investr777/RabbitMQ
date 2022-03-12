package net.its.rmq.mng.api.api.car.exceptions;

import net.its.rmq.mng.api.cmn.exceptions.EntityNotFoundException;

public class CarEntityNotFoundException extends EntityNotFoundException {

    public CarEntityNotFoundException(String searchBy, String value) {

        super("Car is not found in db by " + searchBy + ": " + value, "Car is not found by " + searchBy + ": " + value);
    }
}
