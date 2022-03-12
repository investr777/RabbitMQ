package net.its.rmq.cmn.dao.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarDao extends JpaRepository<CarEntity, String> {

    List<CarEntity> findAll();

    Optional<CarEntity> findByVin(String vin);

    Optional<CarEntity> findByNumberPlate(String numberPlate);
}
