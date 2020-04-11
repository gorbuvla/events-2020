package me.gorbuvla.events.flow.list.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.flow.list.EventCategory

/**
 * TODO add class description
 */
class EventListEpoxyController(
    private val onEventClick: (Event) -> Unit
) : TypedEpoxyController<List<EventCategory>>() {

    override fun buildModels(data: List<EventCategory>) {

    }
}