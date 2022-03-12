package net.its.rmq.mng.api.api.car.web.contsructors;

import lombok.val;
import net.its.rmq.cmn.dao.car.CarEntity;
import net.its.rmq.mng.api.api.car.web.resources.CarInResource;

public class CarEntityConstructor {

    public CarEntity construct(CarInResource inResource) {

        val entity = new CarEntity();

        entity.setBrand(inResource.brand());
        entity.setModel(inResource.model());
        entity.setNumberPlate(inResource.numberPlate());
        entity.setVin(inResource.vin());

        return entity;
    }
}
