import org.gradle.api.JavaVersion

object Config {
    const val kotlinVersion = "1.4.21"
    const val composeVersion = "1.0.0-alpha09"

    const val minSdk = 24
    const val compileSdk = 30
    const val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
}

object Dependencies {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Config.kotlinVersion}"

    // Compose
    private const val composeVersion = "0.1.0-dev10"

    const val composeRuntime = "androidx.compose:compose-runtime:$composeVersion"
    const val composeFramework = "androidx.ui:ui-framework:$composeVersion"
    const val composeLayout = "androidx.ui:ui-layout:$composeVersion"
    const val composeMaterial = "androidx.ui:ui-material:$composeVersion"
    const val composeTooling = "androidx.ui:ui-tooling:$composeVersion"
    const val composeLiveData = "androidx.ui:ui-livedata:$composeVersion"

    // Android X
    private const val appCompatVersion = "1.3.0-alpha02"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val coreVersion = "1.5.0-alpha05"
    const val coreKtx = "androidx.core:core-ktx:$coreVersion"

    private const val materialVersion = "1.3.0-beta01"
    const val material = "com.google.android.material:material:$materialVersion"

    private const val constraintLayoutVersion = "2.1.0-alpha2"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    const val browser = "androidx.browser:browser:1.3.0"

    const val coil = "io.coil-kt:coil:1.1.0"

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"

    private const val lifecycleVersion = "2.3.0-rc01"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"

    private const val navigationVersion = "2.3.2"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    private const val fragmentVersion = "1.3.0-rc01"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentVersion"

    private const val viewpager2Version = "1.1.0-alpha01"
    const val viewpager2 = "androidx.viewpager2:viewpager2:$viewpager2Version"

    private const val coroutinesVersion = "1.4.2"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"

    private const val flowbindingVersion = "1.0.0"
    const val flowBindingCommon = "io.github.reactivecircus.flowbinding:flowbinding-common:$flowbindingVersion"
    const val flowBindingPlatform  = "io.github.reactivecircus.flowbinding:flowbinding-android:$flowbindingVersion"
    const val flowBindingMaterial = "io.github.reactivecircus.flowbinding:flowbinding-material:$flowbindingVersion"

    private const val flowPreferencesVersion = "1.3.3"
    const val flowPreferences = "com.github.tfcporciuncula.flow-preferences:flow-preferences:$flowPreferencesVersion"

    // Koin
    private const val koinVersion = "2.2.0"
    const val koin = "org.koin:koin-android:$koinVersion"
    const val koinScope = "org.koin:koin-androidx-scope:$koinVersion"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"

    // Epoxy
    private const val epoxyVersion = "4.3.1"
    const val epoxy = "com.airbnb.android:epoxy:$epoxyVersion"
    const val epoxyProcessor = "com.airbnb.android:epoxy-processor:$epoxyVersion"

    // Room
    private const val roomVersion = "2.3.0-alpha04"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomProcessor = "androidx.room:room-compiler:$roomVersion"

    // DataStore
    private const val dataStoreVersion = "1.0.0-alpha05"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$dataStoreVersion"

    // Work manager
    private const val workVersion = "2.5.0-beta02"
    const val workKtx = "androidx.work:work-runtime-ktx:$workVersion"

    // Networking
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    // OkHttp
    private const val okhttpVersion = "4.10.0-RC1"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

    // Moshi
    private const val moshiVersion = "1.11.0"
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$moshiVersion"
    const val moshiProcessor = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    // Timber
    private const val timberVersion = "4.7.1"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    // ThreeTen
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.0"

    // Testing
    private const val junitVersion = "4.13"
    const val junit = "junit:junit:$junitVersion"
}