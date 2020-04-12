package me.gorbuvla.navigation.activityflows.home.navigation

import android.content.Intent
import android.net.Uri
import androidx.navigation.NavController
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.flow.list.EventListFragment
import me.gorbuvla.map.flow.map.EventMapFragment
import me.gorbuvla.navigation.activityflows.event.EventFlowActivity
import me.gorbuvla.navigation.coordinator.FlowCoordinator
import me.gorbuvla.navigation.coordinator.NavigationPage
import me.gorbuvla.navigation.coordinator.startFlow
import me.gorbuvla.ui.activity.FlowActivity

interface MapFlowCoordinator : EventMapFragment.NavigationDelegate

interface ListFlowCoordinator : EventListFragment.NavigationDelegate

internal interface HomeFlowCoordinator : FlowCoordinator, MapFlowCoordinator, ListFlowCoordinator {

    sealed class Page : NavigationPage.Destination() {

        data class Maps(val coordinate: Coordinate) : NavigationPage.External() {
            override val intent: Intent
                get() = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=${coordinate.latitude},${coordinate.longitude}"))
        }
    }
}

internal class HomeFlowCoordinatorImpl : HomeFlowCoordinator {

    override var activity: FlowActivity? = null
    override var navigationController: NavController? = null

    override fun open(eventId: String) {
        startFlow<EventFlowActivity>(bundle = EventFlowActivity.arguments(eventId))
    }

    override fun navigate(coordinate: Coordinate) {
        navigateTo(HomeFlowCoordinator.Page.Maps(coordinate))
    }
}
