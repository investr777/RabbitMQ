package net.its.rmq.border.validator.config;

import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    @Autowired
    private Environment env;

    @Bean
    BorderValidatorProperties borderValidatorProperties() {

        return BorderValidatorProperties.builder()
            .rabbitmqProperties(rabbitmqProperties())
            .incomingQueue(env.getRequiredProperty("INCOMING_QUEUE_NAME"))
            .borderCorridorExchange(env.getRequiredProperty("BORDER_CORRIDOR_EXCHANGE"))
            .personRoutingKey(env.getRequiredProperty("PERSON_ROUTING_KEY"))
            .carRoutingKey(env.getRequiredProperty("CAR_ROUTING_KEY"))
            .build();
    }

    @Bean
    RabbitmqProperties rabbitmqProperties() {

        return RabbitmqProperties.builder()
            .host(env.getRequiredProperty("RABBITMQ_HOST"))
            .port(env.getRequiredProperty("RABBITMQ_PORT", Integer.class))
            .username(env.getRequiredProperty("RABBITMQ_USERNAME"))
            .password(env.getRequiredProperty("RABBITMQ_PASSWORD"))
            .build();
    }
}
