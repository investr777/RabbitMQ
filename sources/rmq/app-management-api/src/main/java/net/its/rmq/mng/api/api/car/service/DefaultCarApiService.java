package net.its.rmq.mng.api.api.car.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.cmn.dao.car.CarDao;
import net.its.rmq.cmn.dao.car.CarEntity;
import net.its.rmq.mng.api.api.car.exceptions.CarEntityNotFoundException;
import net.its.rmq.mng.api.api.car.exceptions.CarUniqueNumberPlateException;
import net.its.rmq.mng.api.api.car.exceptions.CarUniqueVinException;
import net.its.rmq.mng.api.api.car.web.contsructors.CarEntityConstructor;
import net.its.rmq.mng.api.api.car.web.contsructors.CarOutResourceConstructor;
import net.its.rmq.mng.api.api.car.web.resources.CarInResource;
import net.its.rmq.mng.api.api.car.web.resources.CarOutResource;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class DefaultCarApiService implements CarApiService {

    private final CarDao dao;
    private final CarOutResourceConstructor outResourceConstructor;
    private final CarEntityConstructor entityConstructor;

    @Override
    public CarOutResource create(CarInResource inResource) {

        isUniqueVinAndNumberPlate(inResource);

        val carToSave = entityConstructor.construct(inResource);

        val savedCar = dao.saveAndFlush(carToSave);
        log.info("Car was saved successfully: " + savedCar);

        return outResourceConstructor.construct(savedCar);
    }

    @Override
    public CarOutResource findByVin(String vin) {

        final CarEntity entity = getCarEntityByVin(vin);

        return outResourceConstructor.construct(entity);
    }

    @Override
    public List<CarOutResource> findAll() {

        return dao.findAll().stream()
            .map(outResourceConstructor::construct)
            .toList();
    }

    @Override
    public void deleteByVin(String vin) {

        val entity = getCarEntityByVin(vin);

        dao.delete(entity);

        log.info("Car was removed, vin: " + vin);
    }

    private void isUniqueVinAndNumberPlate(CarInResource inResource) {

        dao.findByVin(inResource.vin())
            .ifPresent(car -> {
                throw new CarUniqueVinException(car);
            });

        dao.findByNumberPlate(inResource.numberPlate())
            .ifPresent(car -> {
                throw new CarUniqueNumberPlateException(car);
            });
    }

    private CarEntity getCarEntityByVin(String vin) {

        val entity = dao.findByVin(vin).orElse(null);
        if (entity == null) throw new CarEntityNotFoundException("vin", vin);

        return entity;
    }
}
