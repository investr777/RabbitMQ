import deps.ExternalDeps.*

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(Spring.Bom.starterWeb)

    implementation(flywayCore)
    implementation(postgresql)
}