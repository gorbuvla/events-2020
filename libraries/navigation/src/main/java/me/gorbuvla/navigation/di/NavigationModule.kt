package me.gorbuvla.navigation.di

import me.gorbuvla.events.flow.detail.EventDetailFragment
import me.gorbuvla.events.flow.list.EventListFragment
import me.gorbuvla.map.flow.map.EventMapFragment
import me.gorbuvla.navigation.activityflows.event.EventFlowActivity
import me.gorbuvla.navigation.activityflows.event.navigation.EventFlowCoordinatorImpl
import me.gorbuvla.navigation.activityflows.home.HomeFlowActivity
import me.gorbuvla.navigation.activityflows.home.navigation.HomeFlowCoordinatorImpl
import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module

/**
 * TODO add class description
 */
val navigationModule = module {

    scope(named(HomeFlowActivity.SCOPE_NAME)) {
        scoped {
            HomeFlowCoordinatorImpl()
        } binds arrayOf(
            EventMapFragment.NavigationDelegate::class,
            EventListFragment.NavigationDelegate::class
        )
    }

    scope(named(EventFlowActivity.SCOPE_NAME)) {
        scoped { (eventId: String) ->
            EventFlowCoordinatorImpl()
        } binds arrayOf(
            EventDetailFragment.NavigationDelegate::class
        )
    }
}