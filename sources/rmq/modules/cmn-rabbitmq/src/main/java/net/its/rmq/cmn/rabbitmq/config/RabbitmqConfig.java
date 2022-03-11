package net.its.rmq.cmn.rabbitmq.config;

import com.rabbitmq.client.ConnectionFactory;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.factory.DefaultMessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.pool.DefaultRabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.DefaultMessagePublisher;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitmqConfig {

    @Bean
    RabbitmqProperties rabbitmqProperties(Environment env) {

        return RabbitmqProperties.builder()
            .host(env.getRequiredProperty("RABBITMQ_HOST"))
            .port(env.getRequiredProperty("RABBITMQ_PORT", Integer.class))
            .username(env.getRequiredProperty("RABBITMQ_USERNAME"))
            .password(env.getRequiredProperty("RABBITMQ_PASSWORD"))
            .build();
    }


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
