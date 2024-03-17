package org.cloud.core.maas.exception.rabbitmq;

import org.cloud.core.maas.exception.MaasException;

import static org.cloud.core.maas.constant.ErrorCodes.UNABLE_TO_CREATE_QUEUE;

public class CreateQueueException extends MaasException {
    public CreateQueueException(String errorMessage, Throwable throwable) {
        super(UNABLE_TO_CREATE_QUEUE, errorMessage, throwable);
    }
}
