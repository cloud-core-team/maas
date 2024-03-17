package org.cloud.core.maas.exception;

public class MaasException extends Exception {

    private final String errorCode;

    public MaasException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public MaasException(String errorCode, String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return String.format("[%s] %s", errorCode, super.getMessage());
    }
}
