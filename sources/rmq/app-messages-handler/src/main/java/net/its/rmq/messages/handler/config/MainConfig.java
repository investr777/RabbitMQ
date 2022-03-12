package net.its.rmq.messages.handler.config;

import lombok.val;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.DefaultMessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.MessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.factory.DefaultMessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.messages.handler.services.PersonQueueMessageProcessor;
import net.its.rmq.messages.handler.services.CarQueueMessageProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = "net.its.rmq.cmn.rabbitmq")
public class MainConfig {

    @Bean
    MessageReceiverProcessor carQueueListener(
        RabbitmqChannelPool channelPool,
        MessageConsumerFactory carConsumerFactory,
        MessageHandlerProperties messageHandlerProperties
    ) {

        return new DefaultMessageReceiverProcessor(
            channelPool,
            carConsumerFactory,
            messageHandlerProperties.getCarQueueName()
        );
    }

    @Bean
    MessageReceiverProcessor personQueueListener(
        RabbitmqChannelPool channelPool,
        MessageConsumerFactory personConsumerFactory,
        MessageHandlerProperties messageHandlerProperties
    ) {

        return new DefaultMessageReceiverProcessor(
            channelPool,
            personConsumerFactory,
            messageHandlerProperties.getPersonQueueName()
        );
    }

    @Bean
    MessageConsumerFactory carConsumerFactory(MessageProcessor carQueueMessageProcessor) {

        return new DefaultMessageConsumerFactory(carQueueMessageProcessor);
    }

    @Bean
    MessageConsumerFactory personConsumerFactory(MessageProcessor personQueueMessageProcessor) {

        return new DefaultMessageConsumerFactory(personQueueMessageProcessor);
    }

    @Bean
    MessageProcessor carQueueMessageProcessor() {

        return new CarQueueMessageProcessor();
    }

    @Bean
    MessageProcessor personQueueMessageProcessor() {

        return new PersonQueueMessageProcessor();
    }

    @Bean
    ExecutorService carListenerExecutor(MessageReceiverProcessor carQueueListener) {

        val executor = Executors.newSingleThreadExecutor();
        executor.submit(carQueueListener::process);

        return executor;
    }

    @Bean
    ExecutorService personListenerExecutor(MessageReceiverProcessor personQueueListener) {

        val executor = Executors.newSingleThreadExecutor();
        executor.submit(personQueueListener::process);

        return executor;
    }
}
