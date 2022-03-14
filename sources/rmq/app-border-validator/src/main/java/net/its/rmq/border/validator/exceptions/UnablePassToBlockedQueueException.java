package net.its.rmq.border.validator.exceptions;

import net.its.rmq.cmn.domain.Migrant;

public class UnablePassToBlockedQueueException extends BlockedListValidatorServiceException {

    public UnablePassToBlockedQueueException(Throwable cause, Migrant migrant) {
        super("Unable to pass to blocked queue", cause, migrant);
    }
}
