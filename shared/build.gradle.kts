plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose") version Versions.jbComposeVersion
    id("com.squareup.sqldelight") version Versions.sqlDelightVersion
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

kotlin {
    android()

    if (System.getProperty("os.arch") == "aarch64") {
        iosSimulatorArm64() {
            binaries.framework {
                baseName = "shared"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    } else {
        iosX64() {
            binaries.framework {
                baseName = "shared"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)

                api(Libs.Other.imageLoader)
                api(Libs.Decompose.decompose)
                api(Libs.Decompose.decomposeExtensions)

                with(Libs.Ktor) {
                    implementation(clientCore)
                    implementation(clientJson)
                    implementation(clientLogging)
                    implementation(contentNegotiation)
                    implementation(clientSerialization)
                    implementation(json)
                }

                with(Libs.Kotlinx) {
                    implementation(coroutinesCore)
                    implementation(serializationCore)
                }

                with(Libs.Koin) {
                    api(core)
                    api(test)
                }

                implementation(Libs.SqlDelight.runtime)
                implementation(Libs.Log.napier)
                implementation(Libs.Kotlinx.datetime)
                implementation(Libs.Paging.multiplatformPaging)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libs.AndroidX.activityCompose)
                implementation(Libs.Ktor.clientAndroid)
                implementation(Libs.SqlDelight.androidDriver)
            }
        }
        val androidTest by getting

        val simTarget = if (System.getProperty("os.arch") == "aarch64") {
            val iosSimulatorArm64Main by getting
            iosSimulatorArm64Main
        } else {
            val iosX64Main by getting
            iosX64Main
        }

        val iosMain by creating {
            dependsOn(commonMain)
            simTarget.dependsOn(this)

            dependencies {
                implementation(Libs.Ktor.clientIos)
                implementation(Libs.SqlDelight.nativeDriver)
            }
        }
    }
}

android {
    namespace = "com.serkangurel.recipecomposekmm"
    compileSdk = Versions.androidCompileSdk
    defaultConfig {
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
    }
}

sqldelight {
    database("RecipeDatabase") {
        packageName = "com.serkangurel.recipecomposekmm.data.cache"
        sourceFolders = listOf("sqldelight")
    }
}