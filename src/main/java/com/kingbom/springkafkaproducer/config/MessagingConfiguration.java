package com.kingbom.springkafkaproducer.config;

import com.kingbom.springkafkaproducer.messaging.binding.MessagingBinding;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(value = { MessagingBinding.class })
public class MessagingConfiguration {
}
