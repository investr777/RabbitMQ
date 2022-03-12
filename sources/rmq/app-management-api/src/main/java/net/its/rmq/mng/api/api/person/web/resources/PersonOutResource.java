package net.its.rmq.mng.api.api.person.web.resources;

public record PersonOutResource(
    String id,
    String firstName,
    String lastName,
    String address
) {
}
