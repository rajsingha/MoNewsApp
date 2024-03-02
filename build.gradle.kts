buildscript {
    dependencies {
        classpath("com.google.gms:google-services:4.4.1")
    }
    repositories {
        google()
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    kotlin("kapt") version "1.8.0"
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
    id("com.android.dynamic-feature") version "8.2.0" apply false
    id("com.android.library") version "8.2.0" apply false
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
}
