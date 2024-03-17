package org.cloud.core.maas.service;

import org.cloud.core.maas.exception.MaasException;

public abstract class MessagingService<ConnectionT> {
    protected abstract ConnectionT obtainConnection() throws MaasException;
}
