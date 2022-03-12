import deps.ExternalDeps.*
import deps.ProjectDeps.cmnDao

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(project(cmnDao))

    implementation(Spring.Bom.starterWeb)
    implementation(Spring.Bom.starterValidation)

    implementation(flywayCore)
    implementation(postgresql)
}