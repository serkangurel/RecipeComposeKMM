plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.serkangurel.recipecomposekmm.android"
    compileSdk = Versions.androidCompileSdk
    defaultConfig {
        applicationId = "com.serkangurel.recipecomposekmm.android"
        minSdk = Versions.androidMinSdk
        targetSdk = Versions.androidTargetSdk
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.Koin.android)
}