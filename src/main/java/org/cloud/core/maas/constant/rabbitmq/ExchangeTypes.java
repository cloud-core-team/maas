package org.cloud.core.maas.constant.rabbitmq;

import java.util.Set;

public abstract class ExchangeTypes {
    public static final String TOPIC = "topic";
    public static final String HEADERS = "headers";
    public static final String FANOUT = "fanout";
    public static final String DIRECT = "direct";

    public static final Set<String> AVAILABLE_EXCHANGE_TYPES = Set.of(TOPIC, HEADERS, FANOUT, DIRECT);
}
