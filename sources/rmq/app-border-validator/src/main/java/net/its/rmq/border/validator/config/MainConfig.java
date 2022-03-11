package net.its.rmq.border.validator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import net.its.rmq.border.validator.services.BorderCorridorService;
import net.its.rmq.border.validator.services.DefaultBorderCorridorService;
import net.its.rmq.border.validator.services.DefaultMessageProcessor;
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

        return new ObjectMapper();
    }

    @Bean
    BorderCorridorService carBorderCorridorService(
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher
    ) {

        return new DefaultBorderCorridorService(
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getCarRoutingKey(),
            messagePublisher,
            objectMapper()
        );
    }

    @Bean
    BorderCorridorService personBorderCorridorService(
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher
    ) {

        return new DefaultBorderCorridorService(
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getPersonRoutingKey(),
            messagePublisher,
            objectMapper()
        );
    }

    @Bean
    MessageProcessor messageProcessor(
        BorderCorridorService carBorderCorridorService,
        BorderCorridorService personBorderCorridorService
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
