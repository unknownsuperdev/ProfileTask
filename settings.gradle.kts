pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Task"
include(":app")
include(
    ":core:network:api",
    ":core:network:impl",
    ":core:network:entities",
    ":core:designsystem",
    ":core:dispatchers:api",
    ":core:dispatchers:impl",
    ":core:utils",
    ":core:request",
    ":core:mvi",
    ":core:room:api",
    ":core:room:impl",
    ":core:room:entities"
)

include(":feature:profile:api")
include(":feature:profile:impl")
include(":navigation")
