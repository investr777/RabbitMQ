package net.its.rmq.border.validator.config;

import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    @Bean
    BorderValidatorProperties borderValidatorProperties(
        Environment env,
        RabbitmqProperties rabbitmqProperties,
        DataSourceProperties dataSourceProperties
    ) {

        return BorderValidatorProperties.builder()
            .rabbitmqProperties(rabbitmqProperties)
            .dataSource(dataSourceProperties)
            .incomingQueue(env.getRequiredProperty("INCOMING_QUEUE_NAME"))
            .borderCorridorExchange(env.getRequiredProperty("BORDER_CORRIDOR_EXCHANGE"))
            .personRoutingKey(env.getRequiredProperty("PERSON_ROUTING_KEY"))
            .carRoutingKey(env.getRequiredProperty("CAR_ROUTING_KEY"))
            .blockedCarRoutingKey(env.getRequiredProperty("BLOCKED_CAR_ROUTING_KEY"))
            .blockedPersonRoutingKey(env.getRequiredProperty("BLOCKED_PERSON_ROUTING_KEY"))
            .build();
    }

    @Bean
    DataSourceProperties dataSourceProperties(Environment env) {

        return DataSourceProperties.builder()
            .url(env.getRequiredProperty("MANAGEMENT_DB_URL"))
            .password(env.getRequiredProperty("MANAGEMENT_DB_PASSWORD"))
            .username(env.getRequiredProperty("MANAGEMENT_DB_USERNAME"))
            .build();
    }
}
