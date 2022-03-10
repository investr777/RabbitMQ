package net.its.rmq.cmn.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;

public abstract class MessagesConsumer extends DefaultConsumer {

    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public MessagesConsumer(Channel channel) {
        super(channel);
    }
}
