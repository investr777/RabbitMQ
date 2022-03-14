package net.its.rmq.incoming.config;

import lombok.Builder;
import lombok.Getter;
import net.its.rmq.cmn.rabbitmq.config.RabbitmqProperties;

@Getter
@Builder
public class IncomingProperties {

    private final RabbitmqProperties rabbitmqProperties;
    private final String incomingExchange;
    private final String incomingRoutingKey;
}
