package org.cloud.core.maas.service.auth;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.cloud.core.maas.model.rabbitmq.RabbitMqCredentials;
import org.cloud.core.maas.service.general.PropertiesProvider;

@ApplicationScoped
public class RabbitMqCredentialsProvider {

    @Inject
    PropertiesProvider propertiesProvider;

    public RabbitMqCredentials get() {
        return RabbitMqCredentials.builder()
                .username(propertiesProvider.getRabbitMqUsername())
                .password(propertiesProvider.getRabbitMqPassword())
                .build();
    }
}
