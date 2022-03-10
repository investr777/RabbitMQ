package net.its.rmq.border.validator.services;

import net.its.rmq.cmn.domain.Migrant;

public interface BorderCorridorService {

    void pass(Migrant migrant);
}
