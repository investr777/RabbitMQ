package net.its.rmq.mng.api.config.properties;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagementApiProperties {

    private final DataSourceProperties dataSource;
}
