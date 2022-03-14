package net.its.rmq.border.validator.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import net.its.rmq.border.validator.services.block.BlockedListValidatorService;
import net.its.rmq.border.validator.services.block.CarBlockedListValidatorService;
import net.its.rmq.border.validator.services.block.PersonBlockedListValidatorService;
import net.its.rmq.border.validator.services.BorderCorridorService;
import net.its.rmq.border.validator.services.CarBorderCorridorService;
import net.its.rmq.border.validator.services.DefaultMessageProcessor;
import net.its.rmq.border.validator.services.PersonBorderCorridorService;
import net.its.rmq.cmn.dao.PersistenceConfig;
import net.its.rmq.cmn.dao.car.CarDao;
import net.its.rmq.cmn.dao.person.PersonDao;
import net.its.rmq.cmn.domain.Car;
import net.its.rmq.cmn.domain.Person;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.DefaultMessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.MessageReceiverProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.factory.DefaultMessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@ComponentScan(basePackages = "net.its.rmq.cmn.rabbitmq")
@Import({PersistenceConfig.class})
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
    BlockedListValidatorService<Person> personBlockedListCheckerService(
        PersonDao personDao,
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher
    ) {

        return new PersonBlockedListValidatorService(
            personDao,
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getBlockedPersonRoutingKey(),
            messagePublisher,
            objectMapper()
        );
    }

    @Bean
    BlockedListValidatorService<Car> carBlockedListCheckerService(
        CarDao carDao,
        BorderValidatorProperties borderValidatorProperties,
        MessagePublisher messagePublisher,
        BorderCorridorService<Person> personBorderCorridorService
    ) {

        return new CarBlockedListValidatorService(
            carDao,
            borderValidatorProperties.getBorderCorridorExchange(),
            borderValidatorProperties.getBlockedCarRoutingKey(),
            messagePublisher,
            objectMapper(),
            personBorderCorridorService
        );
    }

    @Bean
    MessageProcessor messageProcessor(
        BorderCorridorService<Car> carBorderCorridorService,
        BorderCorridorService<Person> personBorderCorridorService,
        BlockedListValidatorService<Car> carBlockedListValidatorService,
        BlockedListValidatorService<Person> personBlockedListValidatorService
    ) {

        return new DefaultMessageProcessor(
            objectMapper(),
            carBorderCorridorService,
            personBorderCorridorService,
            carBlockedListValidatorService,
            personBlockedListValidatorService
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

    @Bean
    public DataSource dataSource(BorderValidatorProperties appProperties) {

        val dataSourceProperties = appProperties.getDataSource();

        return DataSourceBuilder
            .create()
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword())
            .url(dataSourceProperties.getUrl())
            .driverClassName(DatabaseDriver.POSTGRESQL.getDriverClassName())
            .build();
    }
}
