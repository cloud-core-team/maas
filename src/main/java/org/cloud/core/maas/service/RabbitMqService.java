package org.cloud.core.maas.service;

import com.rabbitmq.client.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.cloud.core.maas.exception.internal.ValidationException;
import org.cloud.core.maas.exception.rabbitmq.BindQueueException;
import org.cloud.core.maas.exception.rabbitmq.CreateExchangeException;
import org.cloud.core.maas.exception.rabbitmq.CreateQueueException;
import org.cloud.core.maas.exception.rabbitmq.internal.ConnectionErrorException;
import org.cloud.core.maas.exception.rabbitmq.internal.IncorrectExchangeTypeException;
import org.cloud.core.maas.model.rabbitmq.dto.requests.BindQueueRequest;
import org.cloud.core.maas.model.rabbitmq.dto.requests.CreateExchangeRequest;
import org.cloud.core.maas.model.rabbitmq.dto.requests.CreateQueueRequest;
import org.cloud.core.maas.service.auth.RabbitMqCredentialsProvider;
import org.cloud.core.maas.service.converters.BuiltinExchangeTypeConverter;
import org.cloud.core.maas.service.general.PropertiesProvider;
import org.cloud.core.maas.validation.SimpleEntitiesValidator;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
@Slf4j
public class RabbitMqService extends MessagingService<Connection> {

    @Inject
    RabbitMqCredentialsProvider credentialsProvider;

    @Inject
    PropertiesProvider propertiesProvider;

    @Inject
    SimpleEntitiesValidator entitiesValidator;

    public void createExchange(final CreateExchangeRequest createExchangeRequest) throws CreateExchangeException {

        try {
            entitiesValidator.validateRequest(createExchangeRequest);
        } catch (ValidationException e) {
            String errorMessage = String.format("Unable to create exchange '%s' because of error: %s",
                    createExchangeRequest.getName(), e.getMessage());
            throw new CreateExchangeException(errorMessage, e);
        }

        try (Connection connection = obtainConnection()) {
            Channel channel = connection.createChannel();

            String name = createExchangeRequest.getName();
            BuiltinExchangeType type = BuiltinExchangeTypeConverter.fromStringType(createExchangeRequest.getType());
            Boolean durable = Optional.ofNullable(createExchangeRequest.getDurable()).orElse(false);
            Boolean autoDelete = Optional.ofNullable(createExchangeRequest.getAutoDelete()).orElse(false);
            Boolean internal = Optional.ofNullable(createExchangeRequest.getInternal()).orElse(false);
            Map<String, Object> arguments = createExchangeRequest.getArguments();

            channel.exchangeDeclare(name, type, durable, autoDelete, internal, arguments);
        } catch (IOException | IncorrectExchangeTypeException | ConnectionErrorException e) {
            String errorMessage = String.format("Unable to create exchange '%s' because of error: %s",
                    createExchangeRequest.getName(), e.getMessage());
            throw new CreateExchangeException(errorMessage, e);
        }
    }

    public void createQueue(final CreateQueueRequest createQueueRequest) throws CreateQueueException {

        try {
            entitiesValidator.validateRequest(createQueueRequest);
        } catch (ValidationException e) {
            String errorMessage = String.format("Unable to create queue '%s' because of error: %s",
                    createQueueRequest.getName(), e.getMessage());
            throw new CreateQueueException(errorMessage, e);
        }

        try (Connection connection = obtainConnection()) {
            Channel channel = connection.createChannel();

            String name = createQueueRequest.getName();
            Boolean durable = Optional.ofNullable(createQueueRequest.getDurable()).orElse(false);
            Boolean exclusive = Optional.ofNullable(createQueueRequest.getExclusive()).orElse(false);
            Boolean autoDelete = Optional.ofNullable(createQueueRequest.getAutoDelete()).orElse(false);
            Map<String, Object> arguments = createQueueRequest.getArguments();

            channel.queueDeclare(name, durable, exclusive, autoDelete, arguments);
        } catch (IOException | ConnectionErrorException e) {
            String errorMessage = String.format("Unable to create queue '%s' because of error: %s",
                    createQueueRequest.getName(), e.getMessage());
            throw new CreateQueueException(errorMessage, e);
        }
    }

    public void bindQueue(final BindQueueRequest bindQueueRequest) throws BindQueueException {

        try {
            entitiesValidator.validateRequest(bindQueueRequest);
        } catch (ValidationException e) {
            String errorMessage = String.format("Unable to bind queue '%s' to exchange '%s' by routing key '%s' because of " +
                            "error: %s", bindQueueRequest.getQueue(), bindQueueRequest.getExchange(),
                    bindQueueRequest.getRoutingKey(), e.getMessage());
            throw new BindQueueException(errorMessage, e);
        }

        try (Connection connection = obtainConnection()) {
            Channel channel = connection.createChannel();

            String queue = bindQueueRequest.getQueue();
            String exchange = bindQueueRequest.getExchange();
            String routingKey = bindQueueRequest.getRoutingKey();
            Map<String, Object> arguments = bindQueueRequest.getArguments();

            channel.queueBind(queue, exchange, routingKey, arguments);
        } catch (IOException | ConnectionErrorException e) {
            String errorMessage = String.format("Unable to bind queue '%s' to exchange '%s' by routing key '%s' because of " +
                            "error: %s", bindQueueRequest.getQueue(), bindQueueRequest.getExchange(),
                    bindQueueRequest.getRoutingKey(), e.getMessage());
            throw new BindQueueException(errorMessage, e);
        }
    }

    @Override
    protected Connection obtainConnection() throws ConnectionErrorException {
        String host = propertiesProvider.getRabbitMqHost();
        Integer port = propertiesProvider.getRabbitMqPort();

        try {
            String username = credentialsProvider.get().getUsername();
            String password = credentialsProvider.get().getPassword();

            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(username);
            factory.setPassword(password);
            factory.setHost(host);
            factory.setPort(port);

            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            String errorMessage = String.format("Unable to connect to RabbitMQ [%s:%s] because of error: %s", host,
                    port, e.getMessage());
            throw new ConnectionErrorException(errorMessage, e);
        }
    }
}
