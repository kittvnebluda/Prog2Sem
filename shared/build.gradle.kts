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

    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")

}
