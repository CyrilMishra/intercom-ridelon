pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BikeTalk"
include(":app")
include(":audio-engine")
include(":networking-engine")
include(":bluetooth-manager")
include(":music-sync")
include(":voice-processing")
include(":connection-service")
