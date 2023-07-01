plugins {
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.androidApplication)

    kotlin("android")
}

group "com.artkorchagin.scrubler"
version "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))

    implementation(libs.androidx.activityCompose)
}

android {
    namespace = "com.artkorchagin.scrubler.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.artkorchagin.scrubler.android"
        minSdk = 27
        targetSdk = 33
        versionCode = 1
        versionName = "1.0-SNAPSHOT"
    }
    //TODO
    // compileOptions {
    //     sourceCompatibility = JavaVersion.VERSION_1_8
    //     targetCompatibility = JavaVersion.VERSION_1_8
    // }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}