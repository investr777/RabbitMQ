package net.its.rmq.mng.api.config;

import lombok.val;
import net.its.rmq.cmn.dao.PersistenceConfig;
import net.its.rmq.mng.api.config.properties.ManagementApiProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import({PersistenceConfig.class})
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
