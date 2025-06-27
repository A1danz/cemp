import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.resources)
}

kotlin {
    jvmToolchain(17)

    androidTarget()
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).takeIf { "XCODE_VERSION_MAJOR" in System.getenv().keys }
        ?.forEach {
            it.binaries.framework {
                baseName = "ComposeApp"

                export(libs.decompose)
                export(libs.essenty)
                export("dev.icerock.moko:resources:0.24.5")
                export("dev.icerock.moko:graphics:0.9.0")
                export(project(":shared"))
            }
        }

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries.all {
            linkerOpts("-lsqlite3")
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.material3)
            implementation(compose.runtime)
            api(libs.resources)
            api(libs.resources.graphics)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.essenty.coroutines)
            implementation(libs.koin.core)
            implementation(libs.resources.compose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            api(libs.essenty)
            api(libs.decompose)
            api(project(":shared"))
        }

        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(libs.androidx.activityCompose)
            implementation(libs.decompose.ext)
            implementation(libs.koin.android)
            implementation(libs.coil.compose)
        }

        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "sample.app"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        targetSdk = 35

        applicationId = "sample.app.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
}

multiplatformResources {
    resourcesPackage.set("com.cemp")
    resourcesClassName.set("SharedRes")
}


compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "sample"
            packageVersion = "1.0.0"
        }
    }
}