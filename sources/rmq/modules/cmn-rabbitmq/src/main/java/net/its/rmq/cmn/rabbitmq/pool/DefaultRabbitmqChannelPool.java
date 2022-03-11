package net.its.rmq.cmn.rabbitmq.pool;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.val;
import net.its.rmq.cmn.rabbitmq.pool.exceptions.ChannelPoolException;
import net.its.rmq.cmn.rabbitmq.pool.exceptions.ConnectionException;
import net.its.rmq.cmn.rabbitmq.pool.exceptions.CreateChannelException;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;

public class DefaultRabbitmqChannelPool implements RabbitmqChannelPool {

    private final Connection connection;
    private final ConcurrentLinkedDeque<Channel> channelPool = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Channel> usedChannels = new ConcurrentLinkedDeque<>();

    private static final int INITIAL_POOL_SIZE = 5;
    private static final int DEFAULT_MAX_POOL_SIZE = 20;

    private static int maxPoolSize = DEFAULT_MAX_POOL_SIZE;

    public static DefaultRabbitmqChannelPool create(ConnectionFactory factory) {

        return new DefaultRabbitmqChannelPool(factory);
    }

    public static DefaultRabbitmqChannelPool create(ConnectionFactory factory, int maxSize) {

        if (maxSize < INITIAL_POOL_SIZE) {
            throw new ChannelPoolException("Max pool size is less than initial pool size: " + INITIAL_POOL_SIZE);
        }

        maxPoolSize = maxSize;

        return new DefaultRabbitmqChannelPool(factory);
    }

    private DefaultRabbitmqChannelPool(ConnectionFactory factory) {

        try {
            this.connection = factory.newConnection();

            val pool = new ArrayList<Channel>(INITIAL_POOL_SIZE) {{
                for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                    add(createChannel());
                }
            }};

            channelPool.addAll(pool);
        } catch (CreateChannelException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ConnectionException("Unable to connect to RabbitMq", ex);
        }
    }

    @Override
    public Channel getChannel() {

        if (channelPool.isEmpty()) {
            if (usedChannels.size() < maxPoolSize) {
                channelPool.add(createChannel());
            } else {
                throw new ChannelPoolException("Max pool size was reached: " + maxPoolSize);
            }
        }

        val channel = channelPool.poll();

        usedChannels.add(channel);

        return channel;
    }

    @Override
    public void releaseChannel(Channel channel) {

        channelPool.add(channel);
        usedChannels.remove(channel);
    }

    @Override
    public void shutdown() {

        usedChannels.forEach(this::releaseChannel);

        try {
            for (Channel channel : channelPool) {
                channel.close();
            }
            connection.close();
        } catch (Exception ex) {
            throw new ConnectionException("Unable to close RabbitMq connection", ex);
        } finally {
            channelPool.clear();
        }
    }

    private Channel createChannel() {

        try {
            return connection.createChannel();
        } catch (Exception ex) {
            throw new CreateChannelException("Unable to create channel", ex);
        }
    }
}
