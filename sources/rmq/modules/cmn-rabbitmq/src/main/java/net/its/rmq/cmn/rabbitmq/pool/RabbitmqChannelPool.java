package net.its.rmq.cmn.rabbitmq.pool;

import com.rabbitmq.client.Channel;

public interface RabbitmqChannelPool {

    Channel getChannel();

    void releaseChannel(Channel channel);

    void shutdown();
}
