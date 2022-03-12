package net.its.rmq.mng.api.api.car.service;

import net.its.rmq.mng.api.api.car.web.resources.CarInResource;
import net.its.rmq.mng.api.api.car.web.resources.CarOutResource;

import java.util.List;

public interface CarApiService {

    CarOutResource create(CarInResource inResource);

    CarOutResource findByVin(String vin);

    List<CarOutResource> findAll();

    void deleteByVin(String vin);
}
