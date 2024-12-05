plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android") // Áp dụng plugin tại đây
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.warehousemanagement"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.warehousemanagement"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("androidx.core:core-ktx:1.15.0")
    implementation ("com.google.android.material:material:1.12.0")
    implementation ("androidx.compose.material:material:1.7.5")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.8.4")
    implementation("androidx.room:room-ktx:2.6.1")
    implementation("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.1")
    implementation("com.google.firebase:firebase-storage-ktx:21.0.1")
    implementation("com.google.firebase:firebase-storage:21.0.1")
    implementation("androidx.datastore:datastore-core-android:1.1.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    implementation ("com.google.dagger:hilt-android:2.51.1")

    //  Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.google.code.gson:gson:2.11.0")

    // https://mvnrepository.com/artifact/androidx.hilt/hilt-navigation-compose
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation ("com.google.zxing:core:3.5.2")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("androidx.camera:camera-core:1.4.0")
    implementation ("androidx.camera:camera-lifecycle:1.4.0")
    implementation ("androidx.camera:camera-view:1.4.0")
    implementation ("androidx.camera:camera-camera2:1.4.0")

    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))

    implementation("com.google.firebase:firebase-analytics")
    implementation ("androidx.datastore:datastore-preferences:1.1.1")

    implementation ("io.ktor:ktor-client-android:1.6.4")
    implementation ("io.ktor:ktor-client-websockets:1.6.4")
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

}
kapt {
    correctErrorTypes = true
}


