package me.gorbuvla.events.di

import me.gorbuvla.domain.di.domainModule
import org.koin.dsl.module

val featureModule = module {

}

val eventsModule = domainModule + featureModule