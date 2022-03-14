package net.its.rmq.cmn.rabbitmq.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.exceptions.MessageProcessorException;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class DefaultMessagePublisher implements MessagePublisher {

    private final RabbitmqChannelPool channelPool;

    @Override
    public void publish(String exchange, String routingKey, byte[] message) {

        val channel = channelPool.getChannel();
        try {
            channel.basicPublish(exchange, routingKey, null, message);
        } catch (IOException e) {
            throw new MessageProcessorException("Error during message publishing, exchange " + exchange
                + "routingKey " + routingKey + " message " + message);
        }
        channelPool.releaseChannel(channel);

    }
}
