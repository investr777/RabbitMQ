package net.its.rmq.mng.api.api.car.web.contsructors;

import net.its.rmq.cmn.dao.car.CarEntity;
import net.its.rmq.mng.api.api.car.web.resources.CarOutResource;

public class CarOutResourceConstructor {

    public CarOutResource construct(CarEntity entity) {

        return new CarOutResource(
            entity.getBrand(),
            entity.getModel(),
            entity.getVin(),
            entity.getNumberPlate()
        );
    }
}
