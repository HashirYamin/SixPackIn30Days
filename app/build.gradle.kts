plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.fitnessforwomanapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fitnessforwomanapp"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    //responsive size UI
    implementation ("com.intuit.sdp:sdp-android:1.1.1")
    //responsive size texts
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.25")
    implementation ("com.intuit.ssp:ssp-android:1.1.1")
    implementation ("com.google.android.gms:play-services-auth:20.6.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.material:material:1.9.0")
    implementation ("com.airbnb.android:lottie:3.7.0")
    implementation ("com.airbnb.android:lottie:6.4.1")
    implementation ("com.pierfrancescosoffritti.androidyoutubeplayer:core:12.1.1")





}