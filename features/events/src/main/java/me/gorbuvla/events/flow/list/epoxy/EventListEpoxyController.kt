package me.gorbuvla.events.flow.list.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.domain.domain.EventType
import me.gorbuvla.events.flow.list.EventCategory
import me.gorbuvla.ui.epoxy.spacer
import me.gorbuvla.ui.util.Dimen
import me.gorbuvla.ui.util.Text

/**
 * Epoxy controller for screen with events.
 */
class EventListEpoxyController(
    private val onToggleCategory: (EventType) -> Unit,
    private val onEventClick: (Event) -> Unit
) : TypedEpoxyController<List<EventCategory>>() {

    override fun buildModels(categories: List<EventCategory>) {
        categories.forEach { (type, events, expanded) ->
            collapsingCategory {
                id(type.name)
                title(Text.Raw(type.title))
                expanded(expanded)
                onExpand { onToggleCategory(type) }
            }

            if (expanded) {
                events.forEachIndexed { index, event ->
                    eventItem {
                        id("event-${event.id}")
                        event(event)
                        onEventClick(onEventClick)
                    }

                    if (index != events.lastIndex) {
                        spacer {
                            id("spacer_after_${event.id}")
                            marginStart(Dimen.Dp(100))
                        }
                    }
                }
            }
        }
    }
}