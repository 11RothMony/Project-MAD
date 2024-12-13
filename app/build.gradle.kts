plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "kh.edu.rupp.ite.projectmad"
    compileSdk = 34

    defaultConfig {
        applicationId = "kh.edu.rupp.ite.projectmad"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    flavorDimensions += "FoodDelivery"
    productFlavors {
        create("dev") {
            dimension = "FoodDelivery"
            applicationId = "kh.edu.rupp.ite.projectmad.dev"
            resValue("string", "app_name", "Food Delivery Test")
            buildConfigField("String", "apiBaseUrl", "\"http://10.0.2.2:3000/testing/\"")
        }
        create("prd") {
            dimension = "FoodDelivery"
            applicationId = "kh.edu.rupp.ite.projectmad"
            resValue("string", "app_name", "Food Delivery Test")
            buildConfigField("String", "apiBaseUrl", "\"https://dummyjson.com/c/\"")
        }
    }

    signingConfigs {
        create("release") {
            storeFile =
                file("D:\\Royal University of Phnom Penh\\Year_4 _Semester_1\\Mobile Applicattion Development  FoodDelivery.jks")
            storePassword = "123456"
            keyAlias = "FoodDelivery"
            keyPassword = "123456"
        }
    }




    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
//        sourceCompatibility = JavaVersion.VERSION_1_8
//        targetCompatibility = JavaVersion.VERSION_1_8
        sourceCompatibility = JavaVersion.VERSION_17 // Update to Java 17 or higher
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
//        jvmTarget = "1.8"
        jvmTarget = "17"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:21.0.0")
    implementation("com.google.firebase:firebase-auth-ktx:23.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.6.1")
    implementation("com.squareup.okhttp3:okhttp:4.11.0") // Use the latest version
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.google.code.gson:gson:2.10.1")


    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))
//    implementation ("com.google.firebase:firebase-auth-ktx")
//    implementation ("com.google.firebase:firebase-functions-ktx")


}