plugins {
    kotlin("multiplatform")
    application
    kotlin("plugin.serialization") version "1.8.10"
}

group = "com.prog2sem"
version = "1.0-SNAPSHOT"

kotlin {
    jvm("jvmServer") {
        jvmToolchain(16)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmServerMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
            }
        }
        val jvmServerTest by getting
    }
}

application {
    mainClass.set("com.prog2sem.application.ServerKt")
}