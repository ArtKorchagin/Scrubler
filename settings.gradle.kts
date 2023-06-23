// // Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
// rootProject.name = "Scrubler"
//
// //STOPSHIP include(":android", ":desktop", ":common")
// include(":android", ":common")
//
// pluginManagement {
//     repositories {
//         google()
//         //STOPSHIP  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//         gradlePluginPortal()
//         mavenCentral()
//     }
//
//     plugins {
//         val kotlinVersion = extra["kotlin.version"] as String
//
//         println(">>>>>>>>>>>>>> $kotlinVersion")
//
//         val agpVersion = extra["agp.version"] as String
//         val composeVersion = extra["compose.version"] as String
//
//
//         kotlin("jvm").version(kotlinVersion)
//         kotlin("multiplatform").version(kotlinVersion)
//         kotlin("android").version(kotlinVersion)
//
//         id("com.android.application").version(agpVersion)
//         id("com.android.library").version(agpVersion)
//         id("org.jetbrains.compose").version(composeVersion)
//     }
// }
//
// dependencyResolutionManagement {
//     repositories {
//         google()
//         maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//         maven ("https://plugins.gradle.org/m2/")
//         mavenCentral()
//     }
// }


rootProject.name = "Scrubler"
// include(":android", ":common")
include(":android", ":desktop", ":common")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val composeVersion = extra["compose.version"] as String
        val sqldelightVersion = extra["sqldelight.version"] as String
        val agpVersion = extra["agp.version"] as String

        println(">>>>>>>>>>>>>> $kotlinVersion")


        kotlin("jvm") version kotlinVersion
        kotlin("multiplatform") version kotlinVersion
        kotlin("android") version kotlinVersion

        id("com.android.application") version agpVersion
        id("com.android.library") version agpVersion
        id("org.jetbrains.compose") version composeVersion
        id("app.cash.sqldelight") version sqldelightVersion
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven ("https://plugins.gradle.org/m2/")
    }
}

