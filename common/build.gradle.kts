val coroutinesVersion = "1.7.1"
val ktorVersion = "2.3.1"
val sqlDelightVersion = "2.0.0-alpha05"
val dateTimeVersion = "0.4.0"

plugins {
    kotlin("multiplatform")
    // kotlin("plugin.serialization") version "1.8.20"
    kotlin("native.cocoapods")

    id("org.jetbrains.compose")
    id("com.android.library")
    id("app.cash.sqldelight")
}

group = "com.artkorchagin.scrubler"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
     targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                // jvmTarget = "1.8"
                jvmTarget = "17"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    ios()

    cocoapods {
        // pod("SQlite")
        summary = "Some description for the Common Module"
        homepage = "Link to the Shared Common homepage"
        version = "1.0"
        // ios.deploymentTarget = "14.1"
        ios.deploymentTarget = "16.4"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "common"
            isStatic = true
            linkerOpts("-lsqlite3")
        }
        // extraOp
        // extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // api(compose.runtime)
                // api(compose.foundation)
                // @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                // api(compose.material3)
                // @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                // api(compose.components.resources)

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.appcompat:appcompat:1.2.0")
                api("androidx.core:core-ktx:1.3.1")

                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("app.cash.sqldelight:android-driver:$sqlDelightVersion")
                // implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")
            }
        }
        // val iosMain by getting {
        //     dependencies {
        //         implementation("io.ktor:ktor-client-darwin:$ktorVersion")
        //         implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
        //     }
        // }

        // val iosX64Main by getting
        // val iosArm64Main by getting
        // val iosSimulatorArm64Main by getting


        // def sdkName = System.getenv("SDK_NAME")
        // if (sdkName != null && sdkName.startsWith("iphoneos")) {
        //     iosArm64("ios")
        // } else {
        // iosX64("ios")
        // iosSimulatorArm64("ios")
        // }


        val iosMain by getting {
            // dependsOn(commonMain)
            // iosX64Main.dependsOn(this)
            // iosArm64Main.dependsOn(this)
            // iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation("app.cash.sqldelight:sqlite-driver:$sqlDelightVersion")
            }
        }
        val desktopTest by getting
    }
}

android {
    namespace = "com.artkorchagin.scrubler"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 27
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}


sqldelight {
    databases {
        create("Database") {
            packageName.set("com.artkorchagin.scrubler")
        }
    }
    linkSqlite.set(true)
}