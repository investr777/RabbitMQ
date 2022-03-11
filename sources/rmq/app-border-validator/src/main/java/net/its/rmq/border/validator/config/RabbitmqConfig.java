package net.its.rmq.border.validator.config;

import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;
import net.its.rmq.cmn.rabbitmq.consumer.factory.DefaultMessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.pool.DefaultRabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.DefaultMessagePublisher;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitmqConfig {

    @Bean
    ConnectionFactory connectionFactory(RabbitmqProperties prop) {

        return new ConnectionFactory() {{
            setHost(prop.getHost());
            setPort(prop.getPort());
            setUsername(prop.getUsername());
            setPassword(prop.getPassword());
        }};
    }

    @Bean
    RabbitmqChannelPool channelPool(ConnectionFactory connectionFactory) {

        return DefaultRabbitmqChannelPool.create(connectionFactory);
    }

    @Bean
    MessageConsumerFactory messageConsumerFactory(MessageProcessor messageProcessor) {

        return new DefaultMessageConsumerFactory(messageProcessor);
    }

    @Bean
    MessagePublisher messagePublisher(RabbitmqChannelPool channelPool) {

        return new DefaultMessagePublisher(channelPool);
    }
}
