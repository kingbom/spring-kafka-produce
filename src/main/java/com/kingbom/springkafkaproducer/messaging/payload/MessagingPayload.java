package com.kingbom.springkafkaproducer.messaging.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagingPayload implements Entity {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String mobile;
    private String sex;
    private Integer age;
}
