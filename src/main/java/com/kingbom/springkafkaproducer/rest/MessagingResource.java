package com.kingbom.springkafkaproducer.rest;

import com.kingbom.springkafkaproducer.messaging.payload.MessagingPayload;
import com.kingbom.springkafkaproducer.messaging.event.MessagingProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessagingResource {

    private final MessagingProducer messagingProducer;

    @PostMapping("/messaging")
    public boolean producerMessaging(@RequestBody MessagingPayload payload) {
        return messagingProducer.producerMessaging(payload);
    }
}
