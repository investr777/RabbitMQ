package net.its.rmq.mng.api.api.car.web.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record CarInResource(

    @NotNull
    @NotBlank
    @Size(max = 36)
    String brand,

    @NotNull
    @NotBlank
    @Size(max = 36)
    String model,

    @NotNull
    @NotBlank
    @Size(min = 17, max = 17)
    String vin,

    @NotNull
    @NotBlank
    @Size(max = 10)
    String numberPlate
) {
}
