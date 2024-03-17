package org.cloud.core.maas.exception.rabbitmq.mapper;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.cloud.core.maas.exception.rabbitmq.BindQueueException;

@Provider
@Slf4j
public class BindQueueExceptionMapper implements ExceptionMapper<BindQueueException> {
    @Override
    public Response toResponse(BindQueueException e) {
        log.error(e.getMessage(), e);
        return Response.status(400).entity(e.getMessage()).build();
    }
}
