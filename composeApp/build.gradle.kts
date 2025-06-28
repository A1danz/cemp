import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.resources)
    alias(libs.plugins.google.services)
}

kotlin {
    jvmToolchain(17)

    androidTarget()

    jvm() {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }

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
                export(project(":shared"))
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
            implementation(libs.decompose.ext)
            api(project(":shared"))
            implementation(libs.coil.compose)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activityCompose)
            implementation(libs.decompose.ext)
            implementation(libs.koin.android)
            implementation(libs.coil)
            implementation(libs.coil.okhttp)
        }

        jvmMain.dependencies {
            implementation(libs.decompose.ext)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.sqlDelight.driver.sqlite)
            implementation(libs.coil.okhttp)
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
        mainClass = "sample.app.MainKt"
        
        jvmArgs += listOf(
            "-Xmx2G",
            "-Dfile.encoding=UTF-8",
            "-Dapple.awt.application.appearance=system",
            "-Dskiko.renderApi=SOFTWARE"
        )

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "cemp"
            packageVersion = "1.0.0"
            
            macOS {
                bundleID = "sample.app.cemp"
                iconFile.set(project.file("src/jvmMain/resources/icon.icns"))
            }
            
            windows {
                iconFile.set(project.file("src/jvmMain/resources/icon.ico"))
            }
            
            linux {
                iconFile.set(project.file("src/jvmMain/resources/icon.png"))
            }
        }
    }
}