package io.github.ma1uta.gastracker

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.features.callIdMdc

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging) {
        callIdMdc()
    }
}
