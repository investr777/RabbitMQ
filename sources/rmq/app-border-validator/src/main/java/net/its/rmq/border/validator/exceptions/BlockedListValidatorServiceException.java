package net.its.rmq.border.validator.exceptions;

import lombok.Getter;
import net.its.rmq.cmn.domain.Migrant;

@Getter
public class BlockedListValidatorServiceException extends RuntimeException {

    private final Migrant migrant;

    public BlockedListValidatorServiceException(String message, Migrant migrant) {
        super(message);
        this.migrant = migrant;
    }

    public BlockedListValidatorServiceException(String message, Throwable cause, Migrant migrant) {
        super(message, cause);
        this.migrant = migrant;
    }
}
