package net.its.rmq.cmn.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonIgnoreProperties(value = {"passengers"}, allowSetters = true, ignoreUnknown = true)
@JsonTypeName("car")
public record Car(
    String brand,
    String model,
    String vin,
    String numberPlate,
    Person owner,
    List<Person> passengers
) implements Migrant {
}
