package net.its.rmq.mng.api.api.person.config;

import net.its.rmq.cmn.dao.person.PersonDao;
import net.its.rmq.mng.api.api.person.service.DefaultPersonApiService;
import net.its.rmq.mng.api.api.person.service.PersonApiService;
import net.its.rmq.mng.api.api.person.web.constructor.PersonEntityConstructor;
import net.its.rmq.mng.api.api.person.web.constructor.PersonOutResourceConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    PersonOutResourceConstructor personOutResourceConstructor() {

        return new PersonOutResourceConstructor();
    }

    @Bean
    PersonEntityConstructor personEntityConstructor() {

        return new PersonEntityConstructor();
    }

    @Bean
    PersonApiService personApiService(
        PersonDao personDao,
        PersonOutResourceConstructor personOutResourceConstructor,
        PersonEntityConstructor personEntityConstructor
    ) {

        return new DefaultPersonApiService(
            personDao,
            personOutResourceConstructor,
            personEntityConstructor
        );
    }
}
