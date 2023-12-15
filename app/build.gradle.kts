plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.minorproject2"
    compileSdk = 34
    buildFeatures {
        viewBinding =true
    }

    defaultConfig {
        applicationId = "com.example.minorproject2"
        minSdk = 24
        targetSdk = 33
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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-auth:22.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    /* For Loading images from internet - Start */
    implementation ("com.github.bumptech.glide:glide:4.13.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.13.0")
    /* For Rounded Image View */
    implementation ("com.makeramen:roundedimageview:2.3.0")
//    search bar
    implementation ("com.github.mancj:MaterialSearchBar:0.8.5")
    /* For Slider - Start */
    // Material Components for Android. Replace the version with the latest version of Material Components library.
    // Circle Indicator (To fix the xml preview "Missing classes" error)
    implementation ("me.relex:circleindicator:2.1.6")
    implementation ("org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:2.1.0")
    /* For Slider - End */
    implementation ("com.android.volley:volley:1.2.1")

    implementation ("com.github.hishd:TinyCart:1.0.1")
    implementation ("com.github.delight-im:Android-AdvancedWebView:v3.2.1")
    implementation ("com.airbnb.android:lottie:4.2.2")
//fused location provider
    implementation ("com.google.android.gms:play-services-location:17.1.0")

}