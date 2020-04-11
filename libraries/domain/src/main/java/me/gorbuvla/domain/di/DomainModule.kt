package me.gorbuvla.domain.di

import me.gorbuvla.domain.model.EventRepository
import me.gorbuvla.domain.model.EventRepositoryImpl
import org.koin.dsl.module

val domainModule = module {

    single<EventRepository> {
        EventRepositoryImpl()
    }
}