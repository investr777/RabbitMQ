import deps.ExternalDeps.Spring

plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform(Spring.bom))
}