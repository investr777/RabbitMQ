package deps;

import static deps.ExternalDeps.Versions.flyway;
import static deps.ExternalDeps.Versions.jackson;
import static deps.ExternalDeps.Versions.postgres;
import static deps.ExternalDeps.Versions.rabbitmq;
import static deps.ExternalDeps.Versions.springBom;

public class ExternalDeps {

    public static class Spring {

        public static String bom = "org.springframework.boot:spring-boot-dependencies:" + springBom;

        // spring-version is configured via bom
        public static class Bom {
            public static String starter = "org.springframework.boot:spring-boot-starter";
            public static String starterWeb = "org.springframework.boot:spring-boot-starter-web";
            public static String starterDataJpa = "org.springframework.boot:spring-boot-starter-data-jpa";
            public static String context = "org.springframework:spring-context";
        }
    }

    public static String rabbitmqClient = "com.rabbitmq:amqp-client:" + rabbitmq;

    public static String jacksonCore = "com.fasterxml.jackson.core:jackson-databind:" + jackson;

    public static String flywayCore = "org.flywaydb:flyway-core:" + flyway;
    public static String postgresql = "org.postgresql:postgresql:" + postgres;

    public static class Versions {

        static String springBom = "2.6.4";

        static String rabbitmq = "5.14.2";

        static String jackson = "2.13.1";

        static String flyway = "8.5.2";
        static String postgres = "42.3.3";
    }
}
