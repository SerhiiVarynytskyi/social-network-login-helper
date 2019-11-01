@file:Suppress("unused")

object App {
    const val Id = "com.login.helper"
}

object Versions {
    const val appVersionCode = 1
    const val appVersionName = "1.0"

    const val compileSdk = 29
    const val minSdk = 21
    const val targetSdk = 29
    const val buildTools = "29.0.2"

    const val gradle = "3.5.1"
    const val googleServicesGradlePluginVersion = "4.3.2"

    const val kotlin = "1.3.50"
    const val kotlinxCoroutines = "1.3.1"
    const val ktxCore = "1.1.0"
    const val appCompat = "1.1.0"
    const val ktlint = "0.35.0"

    const val dagger = "2.16"

    const val material = "1.0.0"
    const val androidxAnnotation = "1.1.0"
    const val androidxConstraintLayout = "1.1.3"
    const val androidxLifecycle = "2.1.0"
    const val playServicesAuth = "17.0.0"
    const val firebaseAuth = "19.1.0"
    const val firebaseAnalytics = "17.2.0"
    const val facebookLogin = "5.8.0"
    const val glide = "4.10.0"

    // test
    const val junit5 = "1.4.2.0"
    const val junitJupiter = "5.4.2"
    const val mockito = "2.28.2"
    const val mockitoKotlin = "1.6.0"
    const val assertJ = "3.12.2"
}

object Plugins {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val googleServicesGradlePlugin =
        "com.google.gms:google-services:${Versions.googleServicesGradlePluginVersion}"
}

object PluginsTest {
    const val junitGradlePlugin =
        "de.mannodermaus.gradle.plugins:android-junit5:${Versions.junit5}"
}

object Libraries {
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktxCore}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val androidxAnnotation = "androidx.annotation:annotation:${Versions.androidxAnnotation}"
    const val androidxConstraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidxLifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.androidxLifecycle}"
    const val androidxLifecycleViewModelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val firebaseAnalytics =
        "com.google.firebase:firebase-analytics:${Versions.firebaseAnalytics}"

    const val playServicesAuth =
        "com.google.android.gms:play-services-auth:${Versions.playServicesAuth}"
    const val firebaseAuth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    const val facebookLogin = "com.facebook.android:facebook-login:${Versions.facebookLogin}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    //DI
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

//Test Libraries
object LibrariesTest {
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    const val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:${Versions.junitJupiter}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman:mockito-kotlin:${Versions.mockitoKotlin}"
    const val assertj = "org.assertj:assertj-core:${Versions.assertJ}"
}