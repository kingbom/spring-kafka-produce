package com.kingbom.springkafkaproducer.handle.error;

public final class InvalidEntityException extends RuntimeException {

    public static final String MSG_NULL_ENTITY = "Entity must not be null.";
    public static final String MSG_MISSING_ID = "Entity ID is not defined.";

    public InvalidEntityException(String message) {
        super(message);
    }

    public static InvalidEntityException entityIsNull() {
        return new InvalidEntityException(MSG_NULL_ENTITY);
    }

    public static InvalidEntityException missingId() {
        return new InvalidEntityException(MSG_MISSING_ID);
    }
}
