package net.its.rmq.messages.handler.config;

import lombok.Builder;
import lombok.Getter;
import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;

@Getter
@Builder
public class MessageHandlerProperties {

    private final RabbitmqProperties rabbitmqProperties;
    private final String carQueueName;
    private final String personQueueName;
}
