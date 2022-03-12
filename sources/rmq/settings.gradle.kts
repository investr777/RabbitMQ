rootProject.name = "rmq"

include(
    "app-border-validator",
    "app-messages-handler",
    "app-management-api",
    ":modules:cmn-domain",
    ":modules:cmn-rabbitmq",
    ":modules:cmn-dao"
)
