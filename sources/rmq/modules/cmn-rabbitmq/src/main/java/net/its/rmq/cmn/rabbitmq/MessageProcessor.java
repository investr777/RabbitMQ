package net.its.rmq.cmn.rabbitmq;

import net.its.rmq.cmn.rabbitmq.exceptions.MessageProcessorException;

public interface MessageProcessor {

    void process(byte[] message) throws MessageProcessorException;
}
