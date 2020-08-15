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

fun Application.helloModule() {
    di {
        bind<Random>() with singleton { SecureRandom() }
    }

    routing {
        controller { HelloController(instance()) }
    }
}
