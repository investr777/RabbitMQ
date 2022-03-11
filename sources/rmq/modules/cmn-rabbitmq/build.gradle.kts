import deps.ExternalDeps.Spring
import deps.ExternalDeps.rabbitmq
import deps.ProjectDeps.cmnDomain

plugins {
    id("spring-lib-conventions")
}

dependencies {

    implementation(Spring.Bom.context)
    implementation(rabbitmq)

    implementation(project(cmnDomain))
}