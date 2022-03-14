package net.its.rmq.incoming.service;

public interface IncomingService {

    void send(byte[] message);
}
