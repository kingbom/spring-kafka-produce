package com.kingbom.springkafkaproducer.messaging.event;

import com.kingbom.springkafkaproducer.messaging.payload.MessagingPayload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagingEvent {
    private String eventId;
    private EventAction action;
    private MessagingPayload data;
}
