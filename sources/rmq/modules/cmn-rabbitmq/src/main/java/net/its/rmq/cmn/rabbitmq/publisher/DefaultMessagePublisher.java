package net.its.rmq.cmn.rabbitmq.publisher;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultMessagePublisher implements MessagePublisher {

    private final String exchange;
    private final String routingKey;
    private final Channel channel;

    @Override
    public void publish(byte[] message) {

        try {
            channel.basicPublish(exchange, routingKey, null, message);
        } catch (Exception ex) {
            log.error("Unable to publish message, exchange %s, routingKey: %s", exchange, routingKey);
        }
    }
}
