package net.its.rmq.cmn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeName("person")
public record Person(
    String identifier,
    String firstName,
    String lastName,
    String address
) implements Migrant {
}
