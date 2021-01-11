import org.gradle.api.JavaVersion

object Config {
    const val kotlinVersion = "1.4.21"

    const val minSdk = 24
    const val compileSdk = 30
    const val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
}

object Dependencies {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Config.kotlinVersion}"

    // Android X
    private const val appCompatVersion = "1.3.0-alpha02"
    const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"

    private const val coreVersion = "1.5.0-alpha05"
    const val coreKtx = "androidx.core:core-ktx:$coreVersion"

    private const val materialVersion = "1.3.0-beta01"
    const val material = "com.google.android.material:material:$materialVersion"

    private const val constraintLayoutVersion = "2.1.0-alpha2"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

    const val customTab = "androidx.browser:browser:1.3.0"

    const val coil = "io.coil-kt:coil:1.1.0"

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"

    private const val lifecycleVersion = "2.3.0-rc01"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    const val lifecycleProcess = "androidx.lifecycle:lifecycle-process:$lifecycleVersion"

    private const val navigationVersion = "2.3.0"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:$navigationVersion"

    private const val fragmentVersion = "1.3.0-rc01"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentVersion"

    private const val viewpager2Version = "1.1.0-alpha01"
    const val viewpager2 = "androidx.viewpager2:viewpager2:$viewpager2Version"

    private const val pageIndicatorVersion = "1.0.3"
    const val pageIndicator = "com.romandanylyk:pageindicatorview:$pageIndicatorVersion"

    private const val optPinViewVersion = "2.1.0"
    const val optPinView = "com.github.mukeshsolanki:android-otpview-pinview:$optPinViewVersion"

    const val linkMovementMethod = "me.saket:better-link-movement-method:2.2.0"

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

    // Lottie
    private const val lottieVersion = "3.5.0"
    const val lottie = "com.airbnb.android:lottie:$lottieVersion"

    // Note: do not update unless required, one-time sms verification does not work with newer versions
    private const val playServicesAuthVersion = "17.0.0"
    const val playServicesAuth = "com.google.android.gms:play-services-auth:$playServicesAuthVersion"
    const val playServicesPhoneApi = "com.google.android.gms:play-services-auth-api-phone:17.4.0"

    // Room
    private const val roomVersion = "2.3.0-alpha03"
    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val roomProcessor = "androidx.room:room-compiler:$roomVersion"

    // DataStore
    private const val dataStoreVersion = "1.0.0-alpha05"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:$dataStoreVersion"

    // Work manager
    private const val workVersion = "2.5.0-beta02"
    const val workKtx = "androidx.work:work-runtime-ktx:$workVersion"

    // Firebase
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics:18.0.0"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics:17.3.0"
    const val firebaseMessaging = "com.google.firebase:firebase-messaging:21.0.0"

    const val firebaseDynamicLinks = "com.google.firebase:firebase-dynamic-links-ktx:19.1.1"

    private const val mlkitVersion = "16.1.1"
    const val mlkitLanguage = "com.google.mlkit:language-id:$mlkitVersion"

    // Networking
    private const val retrofitVersion = "2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    const val retrofitConverter = "com.squareup.retrofit2:converter-moshi:$retrofitVersion"

    private const val coroutineAuthVersion = "1.0.1"
    const val coroutineAuthAdapter = "cz.ackee.ackroutine:coroutine-adapter:$coroutineAuthVersion"
    const val coroutineAuthManager = "cz.ackee.ackroutine:coroutine-oauth:$coroutineAuthVersion"

    private const val msalVersion = "2.0.4"
    const val msal = "com.microsoft.identity.client:msal:$msalVersion"
    const val mask = "com.microsoft.device.display:display-mask:0.3.0"

    private const val socketIOVersion = "1.0.0"
    const val socketIO = "io.socket:socket.io-client:$socketIOVersion"

    // OkHttp
    private const val okhttpVersion = "4.10.0-RC1"
    const val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"

    // Moshi
    private const val moshiVersion = "1.11.0"
    const val moshi = "com.squareup.moshi:moshi:$moshiVersion"
    const val moshiAdapters = "com.squareup.moshi:moshi-adapters:$moshiVersion"
    const val moshiProcessor = "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    private const val mixPanelVersion = "5.8.5"
    const val mixpanel = "com.mixpanel.android:mixpanel-android:$mixPanelVersion"

    // Timber
    private const val timberVersion = "4.7.1"
    const val timber = "com.jakewharton.timber:timber:$timberVersion"

    // ThreeTen
    const val threeTenAbp = "com.jakewharton.threetenabp:threetenabp:1.3.0"

    // Testing
    private const val junitVersion = "4.13"
    const val junit = "junit:junit:$junitVersion"
}