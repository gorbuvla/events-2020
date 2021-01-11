package me.gorbuvla.events.scenes.detail.epoxy

import com.airbnb.epoxy.Typed2EpoxyController
import me.gorbuvla.domain.domain.Coordinate
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.R
import me.gorbuvla.events.scenes.list.epoxy.eventItem
import me.gorbuvla.ui.epoxy.section
import me.gorbuvla.ui.epoxy.spacer
import me.gorbuvla.ui.util.Dimen
import me.gorbuvla.ui.util.Text

/**
 * Epoxy Controller for Event detail screen.
 */
class EventDetailEpoxyController(
    private val onStartNavigation: (Coordinate) -> Unit,
    private val onSimilarClick: (Event) -> Unit
) : Typed2EpoxyController<Event, List<Event>>() {

    override fun buildModels(event: Event, similar: List<Event>) {
        eventHeader {
            id("header")
            event(event)
        }

        spacer {
            id("spacer_before_address")
            marginStart(Dimen.Res(R.dimen.space_default))
            marginEnd(Dimen.Res(R.dimen.space_default))
        }

        eventLocation {
            id("address")
            address(event.address)
            onAddressClick(onStartNavigation)
        }

        spacer {
            id("spacer_after_address")
            marginStart(Dimen.Res(R.dimen.space_default))
            marginEnd(Dimen.Res(R.dimen.space_default))
        }

        if (similar.isNotEmpty()) {
            section {
                id("similar-section")
                title(Text.Raw("Similar"))
            }
        }

        similar.forEachIndexed { index, similarEvent ->
            eventItem {
                id("event-${event.id}")
                event(similarEvent)
                onEventClick(onSimilarClick)
            }

            if (index != similar.lastIndex) {
                spacer {
                    id("spacer_after_${event.id}")
                    marginStart(Dimen.Dp(116))
                }
            }
        }
    }
}