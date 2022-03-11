package net.its.rmq.messages.handler.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.its.rmq.cmn.rabbitmq.MessageProcessor;

@RequiredArgsConstructor
@Slf4j
public class CarQueueMessageProcessor implements MessageProcessor {

    @Override
    public void process(byte[] message) {

        log.info("Car queue. Received message: {}", new String(message));
    }
}
