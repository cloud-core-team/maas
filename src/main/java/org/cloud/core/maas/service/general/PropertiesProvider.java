package org.cloud.core.maas.service.general;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Getter
public class PropertiesProvider {

    @ConfigProperty(name = "maas.rabbitmq.credentials.username")
    private String rabbitMqUsername;

    @ConfigProperty(name = "maas.rabbitmq.credentials.password")
    private String rabbitMqPassword;

    @ConfigProperty(name = "maas.rabbitmq.connection.host")
    private String rabbitMqHost;

    @ConfigProperty(name = "maas.rabbitmq.connection.port")
    private Integer rabbitMqPort;
}
