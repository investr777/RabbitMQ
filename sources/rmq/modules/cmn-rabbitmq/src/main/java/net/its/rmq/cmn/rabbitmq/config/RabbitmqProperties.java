package net.its.rmq.cmn.rabbitmq.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RabbitmqProperties {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
}
