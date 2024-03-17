package org.cloud.core.maas.validation;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.cloud.core.maas.exception.internal.ValidationException;

import java.util.Set;

@ApplicationScoped
public class SimpleEntitiesValidator {

    @Inject
    Validator validator;

    public <T> void validateRequest(T entity) throws ValidationException {
        Set<ConstraintViolation<T>> validationResult = validator.validate(entity);

        if (!validationResult.isEmpty()) {
            throw new ValidationException(generateErrorMessage(validationResult));
        }
    }

    private <T> String generateErrorMessage(Set<ConstraintViolation<T>> validationResult) {

        int errorNum = 0;
        StringBuilder errorMessageBuilder = new StringBuilder("Validation failed. Problems:");
        for (ConstraintViolation<T> currentConstraintViolation : validationResult) {
            errorMessageBuilder.append(" ")
                    .append(++errorNum)
                    .append(". ")
                    .append(currentConstraintViolation.getMessage());
        }
        return errorMessageBuilder.toString();
    }
}
