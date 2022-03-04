package net.its.rmq.cmn.rabbitmq.publisher.factory;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.publisher.DefaultMessagePublisher;
import net.its.rmq.cmn.rabbitmq.publisher.MessagePublisher;

@RequiredArgsConstructor
public class DefaultMessagePublisherFactory implements MessagePublisherFactory {

    private final RabbitmqChannelPool channelPool;

    @Override
    public MessagePublisher create(String exchange, String routingKey) {

        val channel = channelPool.getChannel();

        return new DefaultMessagePublisher(exchange, routingKey, channel);
    }
}
