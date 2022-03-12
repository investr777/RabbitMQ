rootProject.name = "rmq"

include(
    "app-border-validator",
    ":modules:cmn-domain",
    ":modules:cmn-rabbitmq",
    "app-messages-handler"
)
