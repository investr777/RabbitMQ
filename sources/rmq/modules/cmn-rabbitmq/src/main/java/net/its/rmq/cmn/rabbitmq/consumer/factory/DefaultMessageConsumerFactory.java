package net.its.rmq.cmn.rabbitmq.consumer.factory;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.DefaultMessagesConsumer;
import net.its.rmq.cmn.rabbitmq.consumer.MessagesConsumer;

@RequiredArgsConstructor
public class DefaultMessageConsumerFactory implements MessageConsumerFactory {

    private final MessageProcessor messageProcessor;

    @Override
    public MessagesConsumer create(Channel channel) {

        return new DefaultMessagesConsumer(channel, messageProcessor);
    }
}
