rootProject.name = "rmq"

include(
    "app-border-validator",
    "app-incoming-handler",
    "app-messages-handler",
    "app-management-api",
    ":modules:cmn-domain",
    ":modules:cmn-dao",
    ":modules:cmn-rabbitmq",
    "app-messages-handler"
)
