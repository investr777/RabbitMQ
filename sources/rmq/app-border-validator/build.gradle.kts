import deps.ExternalDeps.*
import deps.ProjectDeps.*

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(project(cmnDao))
    implementation(project(cmnDomain))
    implementation(project(cmnRabbitmq))

    implementation(Spring.Bom.starterDataJpa)
    implementation(jacksonCore)
    implementation(postgresql)
}
