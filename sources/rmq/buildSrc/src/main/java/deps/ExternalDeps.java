package deps;

import static deps.ExternalDeps.Versions.springBom;

public class ExternalDeps {

    public static class Spring {

        public static String bom = "org.springframework.boot:spring-boot-dependencies:" + springBom;

        // spring-version is configured via bom
        public static class Bom {
            public static String starter = "org.springframework.boot:spring-boot-starter";
            public static String starterWeb = "org.springframework.boot:spring-boot-starter-web";
            public static String context = "org.springframework:spring-context";
        }
    }

    public static String rabbitmq = "com.rabbitmq:amqp-client:" + Versions.rabbitmq;

    public static String jackson = "com.fasterxml.jackson.core:jackson-databind:" + Versions.jackson;

    public static class Versions {

        static String springBom = "2.6.4";

        static String rabbitmq = "5.14.2";

        static String jackson = "2.13.1";
    }
}
