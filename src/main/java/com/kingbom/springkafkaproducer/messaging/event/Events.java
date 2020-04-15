package com.kingbom.springkafkaproducer.messaging.event;

import org.springframework.util.AlternativeJdkIdGenerator;
import org.springframework.util.IdGenerator;

public class Events {

    private static final IdGenerator EVENT_ID_GENERATOR = new AlternativeJdkIdGenerator();

    private Events() {
    }

    public static <T> Event<T> created(T eventData) {
        return createEvent(eventData, EventAction.CREATE);
    }

    public static <T> Event<T> updated(T eventData) {
        return createEvent(eventData, EventAction.UPDATE);
    }

    private static <T> Event<T> createEvent(T eventData, EventAction eventAction) {
        Event<T> event = new Event<>();
        event.setEventId(EVENT_ID_GENERATOR.generateId().toString());
        event.setAction(eventAction);
        event.setData(eventData);
        return event;
    }
}
