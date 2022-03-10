package net.its.rmq.cmn.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("car")
public record Car(
    String brand,
    String model,
    String vin,
    String numberPlate,
    Person owner
) implements Migrant {

}
