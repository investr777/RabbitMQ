package deps;

public class ExternalDeps {

    // spring-version is configured under 'buildSrc/build.gradle.kts'
    public static class Spring {

        public static String starter = "org.springframework.boot:spring-boot-starter";
    }

    public static String rabbitmq = "com.rabbitmq:amqp-client:" + Versions.rabbitmq;

    public static String jackson = "com.fasterxml.jackson.core:jackson-databind:" + Versions.jackson;

    public static class Versions {

        static String rabbitmq = "5.14.2";

        static String jackson = "2.13.1";
    }
}
