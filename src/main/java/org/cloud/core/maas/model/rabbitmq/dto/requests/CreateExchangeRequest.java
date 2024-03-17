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
public class CreateExchangeRequest {

    @NotBlank(message = "Field 'name' must not be blank")
    private String name;
    private String type;
    private Boolean durable;
    private Boolean autoDelete;
    private Boolean internal;
    private Map<String, Object> arguments;
}
