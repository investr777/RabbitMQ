package net.its.rmq.mng.api.api.car.exceptions;

import net.its.rmq.cmn.dao.car.CarEntity;
import net.its.rmq.mng.api.cmn.exceptions.InvalidRequestException;

public class CarUniqueVinException extends InvalidRequestException {

    public CarUniqueVinException(CarEntity entity) {

        super("Car's vin already exists in db: " + entity, "Car's vin is not unique", null);
    }
}
