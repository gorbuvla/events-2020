package me.gorbuvla.events.scenes.detail.epoxy

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.api.load
import com.airbnb.epoxy.EpoxyAttribute
import me.gorbuvla.domain.domain.Event
import me.gorbuvla.events.databinding.LayoutEventHeaderBinding
import me.gorbuvla.ui.epoxy.EpoxyModelWithBinding

/**
 * Epoxy Model for event description.
 */
open class EventHeaderModel : EpoxyModelWithBinding<LayoutEventHeaderBinding>() {

    @EpoxyAttribute
    lateinit var event: Event

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup): LayoutEventHeaderBinding {
        return LayoutEventHeaderBinding.inflate(inflater, container, false)
    }

    override fun LayoutEventHeaderBinding.bind() {
        eventImage.load(event.image)
        descriptionText.text = event.description
    }
}