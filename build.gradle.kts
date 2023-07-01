plugins {

    // alias(libs.plugins.android.application) apply false

    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kotlinSerialization) apply false
    alias(libs.plugins.mokoResources) apply false
    alias(libs.plugins.sqlDelight) apply false

    // kotlin("multiplatform") apply false
    // kotlin("android") apply false

    //id("com.android.application") apply false
    // id("com.android.library") apply false

   // id("org.jetbrains.compose") apply false
    //id("app.cash.sqldelight") apply false
}

group "com.artkorchagin.scrubler"
version "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
