package org.cloud.core.maas.exception.rabbitmq.internal;

import org.cloud.core.maas.exception.MaasInternalException;

import static org.cloud.core.maas.constant.ErrorCodes.INCORRECT_EXCHANGE_TYPE;

public class IncorrectExchangeTypeException extends MaasInternalException {

    public IncorrectExchangeTypeException(String errorMessage) {
        super(INCORRECT_EXCHANGE_TYPE, errorMessage);
    }

    public IncorrectExchangeTypeException(String errorMessage, Throwable throwable) {
        super(INCORRECT_EXCHANGE_TYPE, errorMessage, throwable);
    }
}
