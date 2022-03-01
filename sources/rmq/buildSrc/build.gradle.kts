plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.6.4")

    implementation("io.freefair.gradle:lombok-plugin:6.4.1")
}