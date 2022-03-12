package net.its.rmq.mng.api.api.car.exceptions;

import net.its.rmq.cmn.dao.car.CarEntity;
import net.its.rmq.mng.api.cmn.exceptions.InvalidRequestException;

public class CarUniqueNumberPlateException extends InvalidRequestException {

    public CarUniqueNumberPlateException(CarEntity entity) {

        super("Car's number plate already exists in db: " + entity, "Car's number plate is not unique", null);
    }
}
