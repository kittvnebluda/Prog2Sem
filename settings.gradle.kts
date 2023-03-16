pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "Prog2Sem"

include(":jvmClient")
//include(":androidClient")
include("jvmServer")
include("shared")
