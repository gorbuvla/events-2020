package me.gorbuvla.map.di

import me.gorbuvla.core.di.domainModule
import org.koin.dsl.module

private val featureModule = module {

}

val mapModule = domainModule + featureModule