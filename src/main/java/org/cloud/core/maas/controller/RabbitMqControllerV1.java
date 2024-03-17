package org.cloud.core.maas.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.cloud.core.maas.exception.rabbitmq.BindQueueException;
import org.cloud.core.maas.exception.rabbitmq.CreateExchangeException;
import org.cloud.core.maas.exception.rabbitmq.CreateQueueException;
import org.cloud.core.maas.model.rabbitmq.dto.requests.BindQueueRequest;
import org.cloud.core.maas.model.rabbitmq.dto.requests.CreateExchangeRequest;
import org.cloud.core.maas.model.rabbitmq.dto.requests.CreateQueueRequest;
import org.cloud.core.maas.service.RabbitMqService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/v1/rabbitmq")
public class RabbitMqControllerV1 {

    @Inject
    RabbitMqService rabbitMqService;

    @POST
    @Path("/exchanges")
    @Consumes(APPLICATION_JSON)
    public void createExchange(CreateExchangeRequest createExchangeRequest) throws CreateExchangeException {
        rabbitMqService.createExchange(createExchangeRequest);
    }

    @POST
    @Path("/queues")
    @Consumes(APPLICATION_JSON)
    public void createExchange(CreateQueueRequest createQueueRequest) throws CreateQueueException {
        rabbitMqService.createQueue(createQueueRequest);
    }

    @POST
    @Path("/binding")
    @Consumes(APPLICATION_JSON)
    public void createExchange(BindQueueRequest bindQueueRequest) throws BindQueueException {
        rabbitMqService.bindQueue(bindQueueRequest);
    }

}
