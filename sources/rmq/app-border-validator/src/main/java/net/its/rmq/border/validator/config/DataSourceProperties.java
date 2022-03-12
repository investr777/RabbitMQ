package net.its.rmq.border.validator.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataSourceProperties {

    private final String url;
    private final String username;
    private final String password;
}
