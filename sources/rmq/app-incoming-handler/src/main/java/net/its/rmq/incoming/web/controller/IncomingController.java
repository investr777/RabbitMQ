package net.its.rmq.incoming.web.controller;

import lombok.RequiredArgsConstructor;
import net.its.rmq.incoming.service.IncomingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/incoming")
public class IncomingController {

    private final IncomingService publisher;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postIncoming(@RequestBody byte[] text) {

         publisher.send(text);
    }
}
