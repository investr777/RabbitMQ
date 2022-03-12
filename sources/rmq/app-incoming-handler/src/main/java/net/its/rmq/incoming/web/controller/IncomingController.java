package net.its.rmq.incoming.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.its.rmq.incoming.service.IncomingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class IncomingController {

    private final IncomingService publisher;
    private final ObjectMapper objectMapper;

    @GetMapping("/incoming/{text}")
    public String getIncoming(@PathVariable("text") String text) throws IOException {

        val message = objectMapper.writeValueAsBytes(text);

        publisher.send(message);

        return "Success";
    }

    @GetMapping("/defaultExchange/{text}")
    public String defaultExchange(@PathVariable("text") String text) {


        return "Success";
    }

}
