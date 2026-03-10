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
include(
    ":app",
    ":audio-engine",
    ":networking-engine",
    ":bluetooth-manager",
    ":music-sync",
    ":voice-processing",
    ":connection-service"
)
