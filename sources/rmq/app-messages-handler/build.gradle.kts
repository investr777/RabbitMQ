import deps.ExternalDeps.Spring
import deps.ProjectDeps.cmnRabbitmq

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(Spring.Bom.starter)

    implementation(project(cmnRabbitmq))
}
