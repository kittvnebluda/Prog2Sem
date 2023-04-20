plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.8.10"
}

group = "com.prog2sem"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
}
