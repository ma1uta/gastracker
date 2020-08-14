package io.github.ma1uta.gastracker

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.features.callIdMdc
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.ktor.kodein
import org.slf4j.event.Level
import java.security.SecureRandom
import java.util.*

fun Application.main() {

    kodein {
        bind<Random>() with singleton { SecureRandom() }
    }

    install(DefaultHeaders)
    install(CallLogging) {
        level = Level.INFO
        callIdMdc()
    }
    install(Routing) {
        get("/") {
            val random by kodein().instance<Random>()
            call.respondText("Hello, World ${random.nextInt()}", ContentType.Text.Html)
        }
    }
}
