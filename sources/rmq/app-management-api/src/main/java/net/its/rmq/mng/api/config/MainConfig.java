package net.its.rmq.mng.api.config;

import lombok.val;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MainConfig {

    @Bean
    public DataSource dataSource(ManagementApiProperties appProperties) {

        val dataSourceProperties = appProperties.getDataSource();

        return DataSourceBuilder
            .create()
            .username(dataSourceProperties.getUsername())
            .password(dataSourceProperties.getPassword())
            .url(dataSourceProperties.getUrl())
            .driverClassName(DatabaseDriver.POSTGRESQL.getDriverClassName())
            .build();
    }
}
