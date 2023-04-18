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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
        classpath("com.android.tools.build:gradle:7.4.1")
    }
}

group = "com.prog2sem"

allprojects {
    repositories {
        mavenCentral()
    }
}