package org.cloud.core.maas.exception.rabbitmq.internal;

import org.cloud.core.maas.exception.MaasInternalException;

import static org.cloud.core.maas.constant.ErrorCodes.INCORRECT_EXCHANGE_TYPE;
import static org.cloud.core.maas.constant.ErrorCodes.RABBIT_MQ_CONNECTION_ERROR;

public class ConnectionErrorException extends MaasInternalException {
    public ConnectionErrorException(String errorMessage, Throwable throwable) {
        super(RABBIT_MQ_CONNECTION_ERROR, errorMessage, throwable);
    }
}
