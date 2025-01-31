pluginManagement {
    includeBuild("build-logic")
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
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pidkova"
include(":app")
include(":core:database")
include(":core:network")
include(":domain")
include(":core:ui")
include(":features:main")
include(":features:home")
include(":features:auth")
include(":features:onboarding")
include(":features:products")
include(":features:cart")
include(":features:profile")
include(":core:domain")
include(":network")
