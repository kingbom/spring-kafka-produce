package com.kingbom.springkafkaproducer.messaging.event;

import com.kingbom.springkafkaproducer.handle.error.InvalidEntityException;
import com.kingbom.springkafkaproducer.messaging.payload.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;

import java.util.Map;
import java.util.function.Function;

@Slf4j
public class EventSender<T extends Entity> {

    private final String entityType;
    private final MessageChannel messageChannel;
    private final Function<T, ?> entityIDExtractor;

    EventSender(String entityType, MessageChannel messageChannel, Function<T, ?> entityIDExtractor) {
        this.entityType = entityType;
        this.messageChannel = messageChannel;
        this.entityIDExtractor = entityIDExtractor;
    }
    /**
     * Send event message
     * @param event the entity that need to be sent as a payload
     */
     boolean sendEventMessage(Event<T> event) {
        return sendEventMessage(event, null);
    }

    /**
     * Send event message with arbitrary message header
     * @param event the entity that need to be sent as a payload
     * @param messageHeaderMap the map that will be sent as a message header
     */
    boolean sendEventMessage(Event<T> event, Map<String, Object> messageHeaderMap) {
        T entity = event.getData();
        validateEventEntity(entity);

        String entityId = getEntityIdAsString(entity);
        String eventInfo = String.format("%s %s(id=%s)", event.getAction(), entityType, entityId);
        log.debug("Sending {} event message.", eventInfo);

        Message<Event<T>> message = MessageBuilder.withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(KafkaHeaders.MESSAGE_KEY, entityId.getBytes())
                .copyHeaders(messageHeaderMap)
                .build();

        boolean isSent = messageChannel.send(message);

        if (isSent) {
            log.debug("{} event message is sent successfully.", eventInfo);
            return true;
        } else {
            String errorMessage = String.format("Unable to send %s event message.", eventInfo);
            log.error(errorMessage);
            throw new MessageDeliveryException(errorMessage);
        }
    }

    public void validateEventEntity(T entity) {
        if (entity == null) {
            throw InvalidEntityException.entityIsNull();
        }

        String entityId = getEntityIdAsString(entity);
    }

    public String getEntityIdAsString(T entity) {
        Object entityId = entityIDExtractor.apply(entity);
        return entityId == null ? "" : entityId.toString();
    }
}
