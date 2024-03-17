package org.cloud.core.maas.exception.rabbitmq;

import org.cloud.core.maas.exception.MaasException;

import static org.cloud.core.maas.constant.ErrorCodes.UNABLE_TO_CREATE_EXCHANGE;

public class CreateExchangeException extends MaasException {
    public CreateExchangeException(String errorMessage, Throwable throwable) {
        super(UNABLE_TO_CREATE_EXCHANGE, errorMessage, throwable);
    }
}
