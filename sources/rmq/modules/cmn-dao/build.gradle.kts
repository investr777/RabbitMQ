import deps.ExternalDeps.Spring

plugins {
    id("spring-lib-conventions")
}

dependencies {

    api(Spring.Bom.starterDataJpa)
}