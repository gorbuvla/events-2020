package me.gorbuvla.navigation.activityflows.home.navigation

import androidx.navigation.NavController
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.flow.list.EventListFragment
import me.gorbuvla.map.flow.map.EventMapFragment
import me.gorbuvla.navigation.coordinator.FlowCoordinator
import me.gorbuvla.ui.activity.FlowActivity

/**
 * TODO add class description
 */
interface MapFlowCoordinator : EventMapFragment.NavigationDelegate

interface ListFlowCoordinator : EventListFragment.NavigationDelegate

internal interface HomeFlowCoordinator : FlowCoordinator, MapFlowCoordinator, ListFlowCoordinator

internal class HomeFlowCoordinatorImpl : HomeFlowCoordinator {

    override var activity: FlowActivity? = null
    override var navigationController: NavController? = null

    override fun openEvent() {}

    override fun open(event: Event) {}
}
