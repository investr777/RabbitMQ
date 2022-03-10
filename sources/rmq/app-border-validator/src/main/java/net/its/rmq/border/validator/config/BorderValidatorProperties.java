package net.its.rmq.border.validator.config;

import lombok.Builder;
import lombok.Getter;
import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;

@Getter
@Builder
public class BorderValidatorProperties {

    private final RabbitmqProperties rabbitmqProperties;
    private final String incomingQueue;
    private final String borderCorridorExchange;
    private final String personRoutingKey;
    private final String carRoutingKey;
}
