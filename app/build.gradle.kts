import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions") version "0.42.0"
}

android {
    compileSdk = Versions.CompileSdk

    defaultConfig {
        applicationId = "me.zama.itunes_most_played"
        minSdk = 23
        targetSdk = Versions.TargetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "me.zama.itunes_most_played.ITunesMostPlayedTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
            "-opt-in=androidx.paging.ExperimentalPagingApi",
            "-opt-in=kotlin.ExperimentalStdlibApi",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    coreLibraryDesugaring(Libs.desugaring)

    implementation(Libs.AndroidX.activityCompose)
    implementation(Libs.AndroidX.coreKtx)

    implementation(Libs.Accompanist.webView)

    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.ui)
    implementation(Libs.AndroidX.Compose.uiToolingPreview)

    implementation(Libs.AndroidX.Lifecycle.viewModelCompose)

    implementation(Libs.AndroidX.Navigation.compose)

    implementation(Libs.AndroidX.Paging.runtime)
    implementation(Libs.AndroidX.Paging.compose)

    implementation(Libs.Coil.coil)
    implementation(Libs.Coil.compose)

    implementation(Libs.retrofit)

    implementation(Libs.Moshi.moshi)
    implementation(Libs.Moshi.retrofit)
    kapt(Libs.Moshi.codeGen)

    implementation(Libs.Hilt.navigationCompose)
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)
    kaptAndroidTest(Libs.Hilt.compiler)
    androidTestImplementation(Libs.Hilt.testing)

    testImplementation(Libs.jUnit)
    androidTestImplementation(Libs.AndroidX.androidJUnit)
    androidTestImplementation(Libs.AndroidX.espresso)
    androidTestImplementation(Libs.AndroidX.Compose.uiTestJUnit)

    debugImplementation(Libs.AndroidX.Compose.uiTooling)
    debugImplementation(Libs.leakCanary)
    debugImplementation(Libs.AndroidX.Compose.testManifest)
}

kapt {
    correctErrorTypes = true
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    val regex = """^[0-9,.v-]+(-r)?$""".toRegex()
    val stableKeywords = setOf("RELEASE", "FINAL", "GA")

    fun isNonStable(version: String) = stableKeywords
            .none(version.toUpperCase()::contains)
            && !regex.matches(version)

    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}