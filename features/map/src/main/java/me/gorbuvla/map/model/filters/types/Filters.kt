package me.gorbuvla.map.model.filters.types

typealias Filter<T> = (item: T, active: Boolean) -> Boolean

typealias FilterApplicator<T> = (item: T) -> Boolean

fun <T> FilterApplicator(value: Boolean, filter: Filter<T>): FilterApplicator<T> = { item: T -> filter(item, value) }
