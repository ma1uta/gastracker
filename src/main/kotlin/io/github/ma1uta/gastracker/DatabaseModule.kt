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

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.ApplicationStarted
import io.ktor.util.KtorExperimentalAPI
import liquibase.Contexts
import liquibase.LabelExpression
import liquibase.Liquibase
import liquibase.database.DatabaseFactory
import liquibase.database.jvm.JdbcConnection
import liquibase.resource.ClassLoaderResourceAccessor
import org.jetbrains.exposed.sql.Database

@KtorExperimentalAPI
fun Application.databaseModule() {

    fun getString(name: String): String = environment.config.property(name).getString()
    fun getInt(name: String): Int = environment.config.property(name).getString().toInt()

    val config = HikariConfig().apply {
        jdbcUrl = getString("db.jdbcUrl")
        driverClassName = getString("db.driver")
        username = getString("db.username")
        password = getString("db.password")
        maximumPoolSize = getInt("db.poolSize")
    }

    val datasource = HikariDataSource(config)

    val database =
        DatabaseFactory.getInstance().findCorrectDatabaseImplementation(JdbcConnection(datasource.connection))
    val liquibase = Liquibase("/db/master-changelog.xml", ClassLoaderResourceAccessor(), database)
    liquibase.update(Contexts(), LabelExpression())

    environment.monitor.subscribe(ApplicationStarted) {
        Database.connect(datasource)
    }
}

