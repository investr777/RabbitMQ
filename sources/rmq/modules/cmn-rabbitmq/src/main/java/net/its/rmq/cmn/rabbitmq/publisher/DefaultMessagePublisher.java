package net.its.rmq.cmn.rabbitmq.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;

@Slf4j
@RequiredArgsConstructor
public class DefaultMessagePublisher implements MessagePublisher {

    private final RabbitmqChannelPool channelPool;

    @Override
    public void publish(String exchange, String routingKey, byte[] message) {

        try {
            val channel = channelPool.getChannel();
            channel.basicPublish(exchange, routingKey, null, message);
            channelPool.releaseChannel(channel);
        } catch (Exception ex) {
            log.error("Unable to publish message, exchange " + exchange + ", routingKey: " + routingKey, ex);
        }
    }
}
