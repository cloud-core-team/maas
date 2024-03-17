package org.cloud.core.maas.exception.rabbitmq;

import org.cloud.core.maas.exception.MaasException;

import static org.cloud.core.maas.constant.ErrorCodes.UNABLE_TO_BIND_QUEUE_TO_EXCHANGE;

public class BindQueueException extends MaasException {
    public BindQueueException(String errorMessage, Throwable throwable) {
        super(UNABLE_TO_BIND_QUEUE_TO_EXCHANGE, errorMessage, throwable);
    }
}
