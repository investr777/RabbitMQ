package net.its.rmq.mng.api.api.car.web.resources;

public record CarOutResource(
    String brand,
    String model,
    String vin,
    String numberPlate
) {
}
