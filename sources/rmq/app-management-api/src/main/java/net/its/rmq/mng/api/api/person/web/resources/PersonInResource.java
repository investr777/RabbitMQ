package net.its.rmq.mng.api.api.person.web.resources;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record PersonInResource(

    @NotNull
    @NotBlank
    @Size(max = 36)
    String id,

    @NotNull
    @NotBlank
    @Size(max = 32)
    String firstName,

    @NotNull
    @NotBlank
    @Size(max = 32)
    String lastName,

    @NotNull
    @NotBlank
    @Size(max = 128)
    String address
) {
}
