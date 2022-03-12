package net.its.rmq.incoming.config;

import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    @Bean
    IncomingProperties incomingProperties(
        Environment env,
        RabbitmqProperties rabbitmqProperties
    ) {

        return IncomingProperties.builder()
            .rabbitmqProperties(rabbitmqProperties)
            .incomingExchange(env.getRequiredProperty("INCOMING_EXCHANGE"))
            .build();
    }
}
