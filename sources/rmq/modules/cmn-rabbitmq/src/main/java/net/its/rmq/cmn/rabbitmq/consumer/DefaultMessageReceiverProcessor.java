package net.its.rmq.cmn.rabbitmq.consumer;

import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.pool.RabbitmqChannelPool;
import net.its.rmq.cmn.rabbitmq.consumer.factory.MessageConsumerFactory;
import net.its.rmq.cmn.rabbitmq.consumer.exceptions.MessageReceiverProcessorException;

@RequiredArgsConstructor
public class DefaultMessageReceiverProcessor implements MessageReceiverProcessor {

    private final RabbitmqChannelPool channelPool;
    private final MessageConsumerFactory messageConsumerFactory;
    private final String incomingMessageQueue;

    @Override
    public void process() {

        try {
            val channel = channelPool.getChannel();
            val consumer = messageConsumerFactory.create(channel);
            channel.basicConsume(incomingMessageQueue, false, consumer);
        } catch (Exception ex) {
            throw new MessageReceiverProcessorException("Unable consuming from queue: " + incomingMessageQueue, ex);
        }
    }
}
