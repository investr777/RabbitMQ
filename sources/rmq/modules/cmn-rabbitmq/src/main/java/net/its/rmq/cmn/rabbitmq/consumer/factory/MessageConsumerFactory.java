package net.its.rmq.cmn.rabbitmq.consumer.factory;

import com.rabbitmq.client.Channel;
import net.its.rmq.cmn.rabbitmq.consumer.MessagesConsumer;

public interface MessageConsumerFactory {

    MessagesConsumer create(Channel channel);
}
