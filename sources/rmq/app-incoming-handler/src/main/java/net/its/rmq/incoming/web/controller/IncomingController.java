package net.its.rmq.incoming.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.incoming.exception.IncomingMessageParseException;
import net.its.rmq.incoming.service.IncomingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/incoming")
public class IncomingController {

    private final IncomingService publisher;
    private final ObjectMapper objectMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void postIncoming(@RequestBody String text) {

        try {
            val message = objectMapper.writeValueAsBytes(text);
            publisher.send(message);

        } catch (JsonProcessingException e) {
            throw new IncomingMessageParseException("Can't convert incoming message", e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getIncoming(@RequestBody String text) {

        try {
            val message = objectMapper.writeValueAsBytes(text);
            publisher.send(message);

        } catch (JsonProcessingException e) {
            throw new IncomingMessageParseException("Can't convert incoming message", e);
        }
    }
}