package net.its.rmq.border.validator.services.block;

import net.its.rmq.border.validator.exceptions.BlockedListValidatorServiceException;
import net.its.rmq.cmn.domain.Migrant;

public interface BlockedListValidatorService<T extends Migrant> {
    
    void check(T migrant) throws BlockedListValidatorServiceException;
}
