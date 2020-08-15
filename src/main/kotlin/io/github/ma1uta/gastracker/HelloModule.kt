package io.github.ma1uta.gastracker

import io.github.ma1uta.gastracker.controller.HelloController
import io.ktor.application.Application
import io.ktor.routing.routing
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.ktor.controller.controller
import org.kodein.di.ktor.di
import org.kodein.di.singleton
import java.security.SecureRandom
import java.util.*

fun Application.module() {
    di {
        bind<Random>() with singleton { SecureRandom() }
    }

    routing {
        controller { HelloController(instance()) }
    }
}
