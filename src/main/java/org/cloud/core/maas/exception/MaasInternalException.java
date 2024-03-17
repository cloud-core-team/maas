package org.cloud.core.maas.exception;

public class MaasInternalException extends MaasException {

    public MaasInternalException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

    public MaasInternalException(String errorCode, String errorMessage, Throwable throwable) {
        super(errorCode, errorMessage, throwable);
    }
}
