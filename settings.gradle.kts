rootProject.name = "Scrubler"
include(":android", ":desktop", ":common")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }


    // Below not needed!
    plugins {
        // val kotlinVersion = extra["kotlin.version"] as String
        // val composeVersion = extra["compose.version"] as String
        // val sqldelightVersion = extra["sqldelight.version"] as String
        // val agpVersion = extra["agp.version"] as String
        // val mokoVersion = extra["moko.version"] as String
        //
        // kotlin("jvm") version kotlinVersion
        // kotlin("multiplatform") version kotlinVersion
        // kotlin("android") version kotlinVersion
        // id("com.android.application") version agpVersion
        // id("com.android.library") version agpVersion
        // id("org.jetbrains.compose") version composeVersion
        // id("app.cash.sqldelight") version sqldelightVersion
        // id("dev.icerock.mobile.multiplatform-resources") version mokoVersion


        // alias(libs.plugins.mokoResources) apply false
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

