package org.cloud.core.maas.exception.rabbitmq.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.cloud.core.maas.exception.rabbitmq.CreateExchangeException;

@Provider
@Slf4j
public class CreateExchangeExceptionMapper implements ExceptionMapper<CreateExchangeException> {
    @Override
    public Response toResponse(CreateExchangeException e) {
        log.error(e.getMessage(), e);
        return Response.status(400).entity(e.getMessage()).build();
    }
}
