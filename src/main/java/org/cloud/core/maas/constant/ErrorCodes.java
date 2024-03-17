package org.cloud.core.maas.constant;

public abstract class ErrorCodes {

    // RabbitMQ
    public static final String INCORRECT_EXCHANGE_TYPE = "MAAS-RABBITMQ-0001";
    public static final String UNABLE_TO_CREATE_EXCHANGE = "MAAS-RABBITMQ-0002";
    public static final String UNABLE_TO_CREATE_QUEUE = "MAAS-RABBITMQ-0003";
    public static final String UNABLE_TO_BIND_QUEUE_TO_EXCHANGE = "MAAS-RABBITMQ-0004";
    public static final String RABBIT_MQ_CONNECTION_ERROR = "MAAS-RABBITMQ-0005";
}
