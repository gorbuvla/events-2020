package me.gorbuvla.navigation.di

import me.gorbuvla.events.screens.detail.EventDetailFragment
import me.gorbuvla.events.screens.list.EventListFragment
import me.gorbuvla.map.screens.map.EventMapFragment
import me.gorbuvla.navigation.activityflows.event.EventFlowActivity
import me.gorbuvla.navigation.activityflows.event.navigation.EventFlowCoordinatorImpl
import me.gorbuvla.navigation.activityflows.home.HomeFlowActivity
import me.gorbuvla.navigation.activityflows.home.navigation.HomeFlowCoordinatorImpl
import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module

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
        scoped {
            EventFlowCoordinatorImpl()
        } binds arrayOf(
            EventDetailFragment.NavigationDelegate::class
        )
    }
}