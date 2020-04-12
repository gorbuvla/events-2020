package me.gorbuvla.navigation.coordinator

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.os.bundleOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import me.gorbuvla.ui.activity.FlowActivity
import timber.log.Timber

/**
 * Base Coordinator.
 */
interface FlowCoordinator : DefaultLifecycleObserver {

    var activity: FlowActivity?

    var navigationController: NavController?

    override fun onDestroy(owner: LifecycleOwner) {
        activity = null
        navigationController = null
    }

    fun finish() {
        activity?.let {
            it.finish()
            it.flowScope.close()
        }
    }

    fun finishAffinity() {
        activity?.let {
            it.finishAffinity()
            it.flowScope.close()
        }
    }

    fun navigateTo(page: NavigationPage) {
        when (page) {
            is NavigationPage.View -> navigationController?.navigate(page.destination, page.bundle)
            is NavigationPage.External -> {
                try {
                    activity?.startActivity(page.intent)
                } catch (e: ActivityNotFoundException) {
                    Timber.w(e, "Could not launch activity for intent: ${page.intent}")
                }
            }
            is NavigationPage.Link -> {
                activity?.let {
                    CustomTabsIntent.Builder()
                        .build()
                        .launchUrl(it, Uri.parse(page.url))
                }
            }
        }
    }
}

inline fun <reified F : FlowActivity> FlowCoordinator.startFlow(bundle: Bundle = bundleOf()) {
    activity?.let { it.startActivity(Intent(it, F::class.java).putExtras(bundle)) }
}

sealed class NavigationPage {
    abstract class Destination : NavigationPage()
    abstract class View : NavigationPage() {
        abstract val destination: Int
        abstract val bundle: Bundle?
    }

    abstract class External : NavigationPage() {
        abstract val intent: Intent
    }

    abstract class Link : NavigationPage() {
        abstract val url: String
    }
}
