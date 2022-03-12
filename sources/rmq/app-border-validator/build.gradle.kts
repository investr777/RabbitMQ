import deps.ExternalDeps.*
import deps.ProjectDeps.cmnDomain
import deps.ProjectDeps.cmnRabbitmq

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(Spring.Bom.starter)
    implementation(jacksonCore)

    implementation(project(cmnDomain))
    implementation(project(cmnRabbitmq))
}