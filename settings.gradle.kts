pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "CATravel"//AirlineTickets
include(":app")
include(":feature")
include(":common")
include(":feature:tickets_search")
include(":feature:tickets_search")
include(":feature:tickets_search:tickets_search_presentation")
include(":feature:tickets_search:tickets_search_data")
include(":feature:tickets_search:tickets_search_domain")
