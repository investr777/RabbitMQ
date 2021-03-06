package net.its.rmq.border.validator.services;

import net.its.rmq.border.validator.exceptions.BorderCorridorServiceException;
import net.its.rmq.cmn.domain.Migrant;

public interface BorderCorridorService<T extends Migrant> {

    void pass(T migrant) throws BorderCorridorServiceException;
}
