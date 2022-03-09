package net.its.rmq.cmn.rabbitmq.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import lombok.extern.slf4j.Slf4j;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;
import net.its.rmq.cmn.rabbitmq.consumer.exceptions.AcknowledgeException;

import java.util.Arrays;

@Slf4j
public class DefaultMessagesConsumer extends MessagesConsumer {

    private final Channel channel;
    private final MessageProcessor messageProcessor;

    public DefaultMessagesConsumer(Channel channel, MessageProcessor messageProcessor) {
        super(channel);
        this.channel = channel;
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {

        log.trace("Receive message: " + Arrays.toString(body));

        try {
            messageProcessor.process(body);
            acknowledgeMessage(envelope.getDeliveryTag());
        } catch (Exception ex) {
            rejectMessage(envelope.getDeliveryTag(), body, ex);
        }
    }

    private void acknowledgeMessage(long deliveryTag) {

        try {
            channel.basicAck(deliveryTag, false);
        } catch (Exception ex) {
            throw new AcknowledgeException("Unable to acknowledge message, delivery tag " + deliveryTag, ex);
        }
    }

    private void rejectMessage(long deliveryTag, byte[] body, Exception cause) {

        log.error("Error while processing message: " + Arrays.toString(body), cause);

        try {
            channel.basicReject(deliveryTag, false);
        } catch (Exception ex) {
            log.error("Unable to reject message, delivery tag " + deliveryTag, ex);
        }
    }
}
