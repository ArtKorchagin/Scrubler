plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")

    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.mokoResources)
    alias(libs.plugins.parcelizeDarwin)
    alias(libs.plugins.kotlinParcelize)
}

group = "com.artkorchagin.scrubler"
version = "1.0-SNAPSHOT"

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                // TODO: jvmTarget = "1.8"
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
        summary = "Some description for the Common Module"
        homepage = "Link to the Shared Common homepage"
        version = "1.0"
        ios.deploymentTarget = "16.4"
        podfile = project.file("../ios/Podfile")
        framework {
            baseName = "common"
            isStatic = true
            linkerOpts("-lsqlite3")

            export(libs.arkivanov.decompose)
            export(libs.arkivanov.essenty.lifecycle)
            export(libs.arkivanov.mvi.kotlin)

            //TODO: export(libs.arkivanov.essenty.parcelable)
            //TODO: export("com.arkivanov.essenty:state-keeper:<essenty_version>")
            //TODO: export("com.arkivanov.parcelize.darwin:runtime:<parcelize_darwin_version>")
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.ui)

                api(libs.moko.resources)
                api(libs.moko.resources.compose)

                api(libs.arkivanov.mvi.kotlin)
                api(libs.arkivanov.mvi.kotlinMain)
                api(libs.arkivanov.mvi.kotlinExtensionsCoroutines)
                api(libs.arkivanov.decompose)
                api(libs.arkivanov.decompose.extensionsCompose)

                implementation(libs.arkivanov.essenty.lifecycle)
                implementation(libs.arkivanov.essenty.parcelable)
                implementation(libs.kamel)
                implementation(libs.kotlin.coroutinesCore)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.contentNegotiation)
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(libs.kotlin.datetime)
            }
        }
        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.coreKtx)
                implementation(compose.uiTooling)
                implementation(libs.ktor.client.android)
                implementation(libs.sqldelight.androidDriver)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.nativeDriver)
                implementation(libs.arkivanov.essenty.parcelable.darwin)

                api(libs.arkivanov.essenty.lifecycle)
                api(libs.arkivanov.decompose)
                api(libs.arkivanov.mvi.kotlin)
            }
        }

        val desktopMain by getting {
            dependencies {
                api(compose.preview)
                implementation(compose.uiTooling)
                implementation(libs.sqldelight.jvmDriver)
                implementation(libs.ktor.client.java)
                implementation(libs.ktor.client.cio)
            }
        }
        val desktopTest by getting
    }
}

android {
    namespace = "com.artkorchagin.scrubler"
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDir(File(buildDir, "generated/moko/androidMain/res"))
    sourceSets["main"].resources.srcDir("src/commonMain/resources")

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

multiplatformResources {
    multiplatformResourcesPackage = "com.artkorchagin.scrubler.common.resources"
    disableStaticFrameworkWarning = true
}