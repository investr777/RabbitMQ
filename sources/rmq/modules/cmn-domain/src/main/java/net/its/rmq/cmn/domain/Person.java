package net.its.rmq.cmn.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("person")
public record Person(
    String id,
    String firstName,
    String lastName,
    String address
) implements Migrant {

}
