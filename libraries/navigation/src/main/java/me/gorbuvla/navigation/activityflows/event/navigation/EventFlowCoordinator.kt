package me.gorbuvla.navigation.activityflows.event.navigation

import androidx.navigation.NavController
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.flow.detail.EventDetailFragment
import me.gorbuvla.navigation.coordinator.FlowCoordinator
import me.gorbuvla.ui.activity.FlowActivity

/**
 * TODO add class description
 */
interface EventFlowCoordinator : FlowCoordinator, EventDetailFragment.NavigationDelegate

class EventFlowCoordinatorImpl : EventFlowCoordinator {
    override var activity: FlowActivity? = null

    override var navigationController: NavController? = null

    override fun open(event: Event) {

    }
}