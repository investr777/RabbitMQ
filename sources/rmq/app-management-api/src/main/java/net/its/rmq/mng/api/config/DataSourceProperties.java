package net.its.rmq.mng.api.config;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DataSourceProperties {

    private final String url;
    private final String username;
    private final String password;
}
