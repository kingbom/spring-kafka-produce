package com.kingbom.springkafkaproducer.messaging.event;

import com.kingbom.springkafkaproducer.messaging.binding.MessagingBinding;
import com.kingbom.springkafkaproducer.messaging.payload.MessagingPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Lazy
@Slf4j
@Component
@RequiredArgsConstructor
public class MessagingProducer {

    private final EventSender<MessagingPayload> eventSender;

    @Autowired
    public MessagingProducer(MessagingBinding binding) {
        MessageChannel messageChannel = binding.messagingOutput();
        eventSender = new EventSender<>("MessagingEvent", messageChannel, MessagingPayload::getId);
    }

    public boolean producerMessaging(MessagingPayload  payload) {
        return eventSender.sendEventMessage(Events.created(payload));
    }
}
