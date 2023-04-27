plugins {
    id("org.jetbrains.dokka") version "1.8.10"
    id("io.freefair.lombok") version "8.0.1"
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}

group = "com.prog2sem"

allprojects {
    repositories {
        mavenCentral()
    }
}