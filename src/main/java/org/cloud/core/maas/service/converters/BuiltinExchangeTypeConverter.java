package org.cloud.core.maas.service.converters;

import com.rabbitmq.client.BuiltinExchangeType;
import org.apache.commons.lang3.StringUtils;
import org.cloud.core.maas.exception.rabbitmq.internal.IncorrectExchangeTypeException;

import static org.cloud.core.maas.constant.rabbitmq.ExchangeTypes.*;

public abstract class BuiltinExchangeTypeConverter {
    public static BuiltinExchangeType fromStringType(String type) throws IncorrectExchangeTypeException {

        if (StringUtils.isEmpty(type)) {
            throw new IncorrectExchangeTypeException("Type of exchange can't be null");
        }

        String lowerCasedType = type.toLowerCase();
        return switch (lowerCasedType) {
            case TOPIC -> BuiltinExchangeType.TOPIC;
            case FANOUT -> BuiltinExchangeType.FANOUT;
            case DIRECT -> BuiltinExchangeType.DIRECT;
            case HEADERS -> BuiltinExchangeType.HEADERS;
            default -> {
                String errorMessage = String.format("Unknown type of exchange: '%s'. Expected one of %s", type, AVAILABLE_EXCHANGE_TYPES);
                throw new IncorrectExchangeTypeException(errorMessage);
            }
        };
    }
}
