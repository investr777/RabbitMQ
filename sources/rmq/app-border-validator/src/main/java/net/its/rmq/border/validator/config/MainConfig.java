package net.its.rmq.border.validator.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import net.its.rmq.border.validator.services.BorderCorridorService;
import net.its.rmq.border.validator.services.CarBorderCorridorService;
import net.its.rmq.border.validator.services.DefaultMessageProcessor;
import net.its.rmq.border.validator.services.PersonBorderCorridorService;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.DefaultMessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.MessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.factory.DefaultMessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = "net.its.rmq.cmn.rabbitmq")
public class MainConfig {

    @Bean
    ObjectMapper objectMapper() {

        return new ObjectMapper()
            .enable(
                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES
            )
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Bean
    BorderCorridorService<Car> carBorderCorridorService(
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher,
        BorderCorridorService<Person> personBorderCorridorService
    ) {

        return new CarBorderCorridorService(
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getCarRoutingKey(),
            messagePublisher,
            objectMapper(),
            personBorderCorridorService
        );
    }

    @Bean
    BorderCorridorService<Person> personBorderCorridorService(
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher
    ) {

        return new PersonBorderCorridorService(
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getPersonRoutingKey(),
            messagePublisher,
            objectMapper()
        );
    }

    @Bean
    MessageProcessor messageProcessor(
        BorderCorridorService<Car> carBorderCorridorService,
        BorderCorridorService<Person> personBorderCorridorService
    ) {

        return new DefaultMessageProcessor(
            objectMapper(),
            carBorderCorridorService,
            personBorderCorridorService
        );
    }

    @Bean
    MessageConsumerFactory messageConsumerFactory(MessageProcessor messageProcessor) {

        return new DefaultMessageConsumerFactory(messageProcessor);
    }

    @Bean
    MessageReceiverProcessor borderValidateProcessor(
        RabbitmqChannelPool channelPool,
        MessageConsumerFactory messageConsumerFactory,
        BorderValidatorProperties borderValidatorProperties
    ) {

        return new DefaultMessageReceiverProcessor(
            channelPool,
            messageConsumerFactory,
            borderValidatorProperties.getIncomingQueue()
        );
    }

    @Bean
    ExecutorService executor(MessageReceiverProcessor borderValidateProcessor) {

        val executor = Executors.newSingleThreadExecutor();

        executor.submit(borderValidateProcessor::process);

        return executor;
    }
}
