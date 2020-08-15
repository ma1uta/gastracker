import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kodein_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("com.bmuschko.docker-remote-api") version "6.6.1"
    id("io.gitlab.arturbosch.detekt") version "1.10.0"
}

group = "io.github.ma1uta.gastracker"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")

    implementation("org.kodein.di:kodein-di-framework-ktor-server-controller-jvm:$kodein_version")

    implementation("io.netty:netty-transport-native-epoll:4.1.44.Final")
    implementation("io.netty:netty-tcnative:2.0.31.Final")
}

tasks.withType(KotlinCompile::class).all {
    kotlinOptions {
        jvmTarget = "13"
    }
}

tasks.create("dockerBuildImage", DockerBuildImage::class) {
    inputDir.set(file("./"))
    images.add("ma1uta/gastracker:latest")
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("gastracker")
        archiveClassifier.set("")
        archiveVersion.set("")
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
