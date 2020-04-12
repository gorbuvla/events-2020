package me.gorbuvla.navigation.activityflows.event.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.flow.detail.EventDetailFragment
import me.gorbuvla.navigation.R
import me.gorbuvla.navigation.coordinator.FlowCoordinator
import me.gorbuvla.navigation.coordinator.NavigationPage
import me.gorbuvla.ui.activity.FlowActivity

/**
 * TODO add class description
 */
interface EventFlowCoordinator : FlowCoordinator, EventDetailFragment.NavigationDelegate {

    sealed class Page : NavigationPage.Destination() {

        data class EventDetail(val eventId: String) : NavigationPage.View() {
            override val destination: Int
                get() = R.id.open_event_detail
            override val bundle: Bundle?
                get() = EventDetailFragment.arguments(eventId)
        }

        data class Maps(val coordinate: Coordinate) : NavigationPage.External() {
            override val intent: Intent
                get() = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${coordinate.latitude},${coordinate.longitude}"))
        }

        data class Website(override val url: String) : Link()
    }
}

class EventFlowCoordinatorImpl : EventFlowCoordinator {
    override var activity: FlowActivity? = null

    override var navigationController: NavController? = null

    override fun open(eventId: String) {
        navigateTo(EventFlowCoordinator.Page.EventDetail(eventId))
    }

    override fun navigate(coordinate: Coordinate) {
        navigateTo(EventFlowCoordinator.Page.Maps(coordinate))
    }

    override fun openLink(url: String) {
        navigateTo(EventFlowCoordinator.Page.Website(url))
    }

    override fun navigateUp() {
        if (navigationController?.currentDestination?.id == R.id.event_detail) {
            activity?.let {
                it.flowScope.close() // TODO: 🤔
                it.onBackPressed()
            }
        } else {
            navigationController?.navigateUp()
        }
    }
}