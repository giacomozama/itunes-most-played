buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin}")
        classpath(Libs.Hilt.gradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}