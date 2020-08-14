import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val ktor_version: String by project

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "io.github.ma1uta.gastracker"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("io.netty:netty-transport-native-epoll:4.1.44.Final:linux-x86_64")
    implementation("io.netty:netty-tcnative:2.0.31.Final")
}

java {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow")
        mergeServiceFiles()
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to "io.ktor.server.netty.EngineMain"
                )
            )
        }
    }

    build {
        dependsOn(shadowJar)
    }
}
