package net.its.rmq.mng.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    @Bean
    ManagementApiProperties appProperties(DataSourceProperties dataSourceProperties) {

        return ManagementApiProperties.builder()
            .dataSource(dataSourceProperties)
            .build();
    }

    @Bean
    DataSourceProperties dataSourceProperties(Environment env) {

        return DataSourceProperties.builder()
            .url(env.getRequiredProperty("MANAGEMENT_DB_URL"))
            .password(env.getRequiredProperty("MANAGEMENT_DB_PASSWORD"))
            .username(env.getRequiredProperty("MANAGEMENT_DB_USERNAME"))
            .build();
    }
}
