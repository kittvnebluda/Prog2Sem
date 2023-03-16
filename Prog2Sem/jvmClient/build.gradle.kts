plugins {
    kotlin("jvm")
    application
    kotlin("plugin.serialization") version "1.8.10"
}

group = "com.prog2sem"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":application"))
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(16)
}

application {
    mainClass.set("com.prog2sem.client.MainKt")
}

tasks.withType<Jar> {
    // Otherwise you'll get a "No main manifest attribute" error
    manifest {
        attributes["Main-Class"] = application.mainClass
    }

    // To avoid the duplicate handling strategy error
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // To add all the dependencies
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}