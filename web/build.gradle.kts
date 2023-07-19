plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.mokoResources)
}

@OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
kotlin {

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":common"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}

multiplatformResources {
    multiplatformResourcesPackage = "com.artkorchagin.scrubler.common.resources"
    disableStaticFrameworkWarning = true
}