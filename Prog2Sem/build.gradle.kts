plugins {
    id("org.jetbrains.dokka") version "1.8.10"
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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
        classpath("com.android.tools.build:gradle:7.0.4")
    }
}

group = "com.prog2sem"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
    }
}