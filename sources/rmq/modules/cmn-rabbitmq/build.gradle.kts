import deps.ExternalDeps.rabbitmq
import deps.ProjectDeps.cmnDomain

dependencies {

    implementation(rabbitmq)

    implementation(project(cmnDomain))
}