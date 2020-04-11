package me.gorbuvla.map.di

import me.gorbuvla.domain.di.domainModule
import org.koin.dsl.module

private val featureModule = module {

}

val mapModule = domainModule + featureModule