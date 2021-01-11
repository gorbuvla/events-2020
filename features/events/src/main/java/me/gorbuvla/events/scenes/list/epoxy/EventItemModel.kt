package me.gorbuvla.events.scenes.list.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.api.load
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.databinding.LayoutEventItemBinding
import me.gorbuvla.ui.epoxy.EpoxyModelWithBinding

/**
 * Epoxy Model to display list of [Event] items.
 */
open class EventItemModel : EpoxyModelWithBinding<LayoutEventItemBinding>() {

    @EpoxyAttribute
    lateinit var event: Event

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var onEventClick: (Event) -> Unit

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutEventItemBinding {
        return LayoutEventItemBinding.inflate(inflater, container, false)
    }

    override fun LayoutEventItemBinding.bind() {
        titleText.text = event.title
        subtitleText.text = event.description

        eventImage.load(event.image)

        root.setOnClickListener {
            onEventClick(event)
        }
    }
}