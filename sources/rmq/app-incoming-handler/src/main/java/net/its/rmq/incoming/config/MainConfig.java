package net.its.rmq.incoming.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import net.its.rmq.incoming.service.DefaultIncomingService;
import net.its.rmq.incoming.service.IncomingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.its.rmq.cmn.rabbitmq")
public class MainConfig {

    @Bean
    IncomingService incomingService(
        IncomingProperties incomingProperties,
        MessagePublisher messagePublisher
    ) {

        return new DefaultIncomingService(
            incomingProperties.getIncomingExchange(),
            messagePublisher,
            objectMapper()
        );
    }

    @Bean
    ObjectMapper objectMapper() {

        return new ObjectMapper()
            .enable(
                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES
            )
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
}
