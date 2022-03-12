package net.its.rmq.mng.api.api.car.config;

import net.its.rmq.cmn.dao.car.CarDao;
import net.its.rmq.mng.api.api.car.service.CarApiService;
import net.its.rmq.mng.api.api.car.service.DefaultCarApiService;
import net.its.rmq.mng.api.api.car.web.contsructors.CarEntityConstructor;
import net.its.rmq.mng.api.api.car.web.contsructors.CarOutResourceConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarConfig {

    @Bean
    CarOutResourceConstructor carOutResourceConstructor() {

        return new CarOutResourceConstructor();
    }

    @Bean
    CarEntityConstructor carEntityConstructor() {

        return new CarEntityConstructor();
    }

    @Bean
    CarApiService carApiService(
        CarDao carDao,
        CarOutResourceConstructor carOutResourceConstructor,
        CarEntityConstructor carEntityConstructor
    ) {

        return new DefaultCarApiService(
            carDao,
            carOutResourceConstructor,
            carEntityConstructor
        );
    }
}
