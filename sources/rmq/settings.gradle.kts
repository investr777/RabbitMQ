rootProject.name = "rmq"

include(
    "app-border-validator",
    "app-incoming-handler",
    ":modules:cmn-domain",
    ":modules:cmn-rabbitmq",
    "app-messages-handler"
)
