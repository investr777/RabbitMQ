package net.its.rmq.cmn.rabbitmq.publisher;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.exceptions.MessagePublisherException;

import java.io.IOException;

@RequiredArgsConstructor
public class DefaultMessagePublisher implements MessagePublisher {

    private final RabbitmqChannelPool channelPool;

    @Override
    public void publish(String exchange, String routingKey, byte[] message) {

        val channel = channelPool.getChannel();

        try {
            channel.basicPublish(exchange, routingKey, null, message);
        } catch (IOException e) {
            throw new MessagePublisherException("Error during message publishing, exchange " + exchange
                + "routingKey " + routingKey + " message " + new String(message));
        }
        channelPool.releaseChannel(channel);
    }
}
