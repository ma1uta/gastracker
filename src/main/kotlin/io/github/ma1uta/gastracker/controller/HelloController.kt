package io.github.ma1uta.gastracker.controller

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import org.kodein.di.instance
import org.kodein.di.ktor.controller.AbstractDIController
import java.util.*

class HelloController(
    application: Application
) : AbstractDIController(application) {

    override fun Route.getRoutes() {
        get("/hello") {
            val random by instance<Random>()
            call.respondText("Hello, World ${random.nextInt()}", ContentType.Text.Html)
        }
    }
}
