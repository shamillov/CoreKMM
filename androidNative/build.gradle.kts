plugins {
    id("com.android.application")
    kotlin("android")
}

group = "com.shamilov"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":core"))

    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.fragment:fragment-ktx:1.3.4")
    implementation("io.coil-kt:coil:1.2.1")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.shamilov.androidNative"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}