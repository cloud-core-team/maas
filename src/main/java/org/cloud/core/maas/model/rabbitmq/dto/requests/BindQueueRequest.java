package org.cloud.core.maas.model.rabbitmq.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BindQueueRequest {

    @NotBlank(message = "Field 'queue' must not be blank")
    private String queue;

    @NotBlank(message = "Field 'exchange' must not be blank")
    private String exchange;

    @NotBlank(message = "Field 'routingKey' must not be blank")
    private String routingKey;
    private Map<String, Object> arguments;
}
