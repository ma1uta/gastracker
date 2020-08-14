package io.github.ma1uta.gastracker

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.callIdMdc
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.slf4j.event.Level

fun Application.main() {
    install(CallLogging) {
        level = Level.INFO
        callIdMdc()
    }
    install(Routing) {
        get("/") {
            call.respondText("Hello, World", ContentType.Text.Html)
        }
    }
}
