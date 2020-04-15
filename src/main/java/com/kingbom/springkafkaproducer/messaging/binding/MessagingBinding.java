package com.kingbom.springkafkaproducer.messaging.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MessagingBinding {

    String OUTPUT = "messagingOutput";

    @Output(OUTPUT)
    MessageChannel messagingOutput();
}
