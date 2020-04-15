package com.kingbom.springkafkaproducer.messaging.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event<T> {
    private String eventId;
    private EventAction action;
    private T data;
}
