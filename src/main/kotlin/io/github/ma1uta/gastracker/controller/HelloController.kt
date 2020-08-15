/*
 * Copyright tolya@sablin.xyz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
