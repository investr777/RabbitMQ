package net.its.rmq.messages.handler.config;

import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    @Bean
    MessageHandlerProperties messageHandlerProperties(
        Environment env,
        RabbitmqProperties rabbitmqProperties
    ) {

        return MessageHandlerProperties.builder()
            .rabbitmqProperties(rabbitmqProperties)
            .carQueueName(env.getRequiredProperty("CAR_QUEUE_NAME"))
            .personQueueName(env.getRequiredProperty("PERSON_QUEUE_NAME"))
            .build();
    }
}
