import deps.ExternalDeps.Spring
import deps.ExternalDeps.rabbitmqClient
import deps.ProjectDeps.cmnDomain

plugins {
    id("spring-lib-conventions")
}

dependencies {

    implementation(Spring.Bom.context)
    implementation(rabbitmqClient)

    implementation(project(cmnDomain))
}