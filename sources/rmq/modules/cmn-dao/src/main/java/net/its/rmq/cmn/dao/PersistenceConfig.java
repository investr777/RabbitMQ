package net.its.rmq.cmn.dao;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("net.its.rmq.cmn.dao")
@EnableJpaRepositories("net.its.rmq.cmn.dao")
public class PersistenceConfig {
}
