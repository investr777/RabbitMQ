import deps.ExternalDeps.*
import deps.ProjectDeps.cmnDomain
import deps.ProjectDeps.cmnRabbitmq

plugins {
    id("spring-app-conventions")
}

dependencies {

    implementation(Spring.starter)
    implementation(rabbitmq)
    implementation(jackson)

    implementation(project(cmnDomain))
    implementation(project(cmnRabbitmq))
}